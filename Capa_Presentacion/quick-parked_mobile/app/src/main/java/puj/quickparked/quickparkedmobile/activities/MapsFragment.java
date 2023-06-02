package puj.quickparked.quickparkedmobile.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import puj.quickparked.quickparkedmobile.App;
import puj.quickparked.quickparkedmobile.R;
import puj.quickparked.quickparkedmobile.REST.IParquederosServices;
import puj.quickparked.quickparkedmobile.activities.dialog.LoadingDialog;
import puj.quickparked.quickparkedmobile.activities.dialog.ParqueaderoLibreDialog;
import puj.quickparked.quickparkedmobile.activities.dialog.ParqueaderoOpcupadoDialog;
import puj.quickparked.quickparkedmobile.activities.dialog.ReservaDialog;
import puj.quickparked.quickparkedmobile.adapter.SugerenciaParqueaderoAdapter;
import puj.quickparked.quickparkedmobile.databinding.FragmentMapsBinding;
import puj.quickparked.quickparkedmobile.model.SedeParqueaderoDTO;
import puj.quickparked.quickparkedmobile.model.ServicesRoutes;
import puj.quickparked.quickparkedmobile.model.SugerenciaParqueadero;
import puj.quickparked.quickparkedmobile.utils.AlertsHelper;
import puj.quickparked.quickparkedmobile.utils.BitmapUtils;
import puj.quickparked.quickparkedmobile.utils.DistanceUtils;
import puj.quickparked.quickparkedmobile.utils.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsFragment extends Fragment {

    private FragmentMapsBinding binding;
    private LoadingDialog loadingDialog;
    static final int INITIAL_ZOOM_LEVEL = 18;
    private GoogleMap googleMap;
    private boolean init = false;
    @Inject
    AlertsHelper alertsHelper;
    private List <Marker> markersParqueaderos = new ArrayList<>();
    private List <SedeParqueaderoDTO> sedeParqueaderoDTOS = new ArrayList<>();

    private IParquederosServices parquederosServices;

    private SugerenciaParqueaderoAdapter adapter;

    private List <SugerenciaParqueadero> sugerenciaParqueaderos = new ArrayList<>();


    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        @SuppressLint("MissingPermission")
        @Override
        public void onMapReady(GoogleMap Map) {
            googleMap = Map;
            googleMap.moveCamera(CameraUpdateFactory.zoomTo(INITIAL_ZOOM_LEVEL));
            googleMap.getUiSettings().setAllGesturesEnabled(true);
            googleMap.getUiSettings().setZoomGesturesEnabled(true);
            googleMap.setMyLocationEnabled(true);
            googleMap.getUiSettings().setMyLocationButtonEnabled(false);
            googleMap.setOnMarkerClickListener(markerClickListener);
            loadingDialog = new LoadingDialog(getContext());
            initParkings();
            loadingDialog.show();
        }
    };

    private GoogleMap.OnMarkerClickListener markerClickListener = new GoogleMap.OnMarkerClickListener() {
        @Override
        public boolean onMarkerClick(@NonNull Marker marker) {
            LatLng latLng = marker.getPosition();
            SedeParqueaderoDTO sede = bucarSede(latLng);
            if (sede.getCupo() > 0){
                ParqueaderoLibreDialog dialog = new ParqueaderoLibreDialog(getContext());
                dialog.getBinding().ratingBar.setRating(5f);
                int colorYellow = getResources().getColor(R.color.yellow_300);
                ColorFilter colorFilter = new PorterDuffColorFilter(colorYellow, PorterDuff.Mode.SRC_ATOP);
                dialog.getBinding().ratingBar.setProgressTintList(ColorStateList.valueOf(colorYellow));
                dialog.getBinding().ratingBar.setProgressTintMode(PorterDuff.Mode.SRC_ATOP);
                alertsHelper.shortToast(getContext(), String.valueOf(dialog.getBinding().ratingBar.getRating()));
                dialog.getBinding().textViewNombreParqueadero.setText(sede.getNombreSede());
                dialog.getBinding().textViewCupos.setText(String.valueOf(sede.getCupo()));
                dialog.show();
                dialog.getBinding().buttonIrSinReserva.setOnClickListener(v -> {
                    createIntentIr(latLng);
                    dialog.getBinding().buttonReservar.setOnClickListener(v1 -> {
                        ReservaDialog reservaDialog = new ReservaDialog(getContext());
                        reservaDialog.getBinding().button.setOnClickListener(v2 -> {
                            alertsHelper.shortToast(getContext(), "Reservando");
                            createIntentIr(latLng);
                        });
                    });
                });
            }
            else{
                ParqueaderoOpcupadoDialog dialog = new ParqueaderoOpcupadoDialog(getContext());
                dialog.getBinding().textViewNombreParqueadero.setText(sede.getNombreSede());
                dialog.getBinding().ratingBar.setRating(3.2f);
                int colorYellow = getResources().getColor(R.color.yellow_300);
                ColorFilter colorFilter = new PorterDuffColorFilter(colorYellow, PorterDuff.Mode.SRC_ATOP);
                dialog.getBinding().ratingBar.setProgressTintList(ColorStateList.valueOf(colorYellow));
                dialog.getBinding().ratingBar.setProgressTintMode(PorterDuff.Mode.SRC_ATOP);
                dialog.show();
                dialog.getBinding().buttonAvisar.setOnClickListener(v -> {
                    alertsHelper.shortToast(getContext(), "Avisando a " + sede.getNombreSede());
                });
            }
            return false;
        }
    };

    @SuppressLint("DefaultLocale")
    private void createIntentIr(LatLng pos){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(String.format("geo:0,0?q=%f,%f", pos.latitude, pos.longitude)));
        Intent chooser = Intent.createChooser(intent, "Selecciona una aplicación de navegación");
        startActivity(chooser);
    }

    private SedeParqueaderoDTO bucarSede(LatLng latLng) {
        for (SedeParqueaderoDTO sedeParqueaderoDTO : sedeParqueaderoDTOS){
            if (sedeParqueaderoDTO.getLatitud() == latLng.latitude && sedeParqueaderoDTO.getLongitud() == latLng.longitude){
                return sedeParqueaderoDTO;
            }
        }
        return null;
    }

    private void initParkings() {
        AuthenticatedActivity activity = (AuthenticatedActivity) getActivity();
        parquederosServices = RetrofitClient.getRetrofitInstance(ServicesRoutes.BASE_URL, activity.getTokenUser()).create(IParquederosServices.class);

        Call <List <SedeParqueaderoDTO>> call = parquederosServices.getParqueaderos();

        call.enqueue(new Callback<List<SedeParqueaderoDTO>>() {
            @Override
            public void onResponse(Call<List<SedeParqueaderoDTO>> call, Response<List<SedeParqueaderoDTO>> response) {
                if (!response.isSuccessful()){
                    alertsHelper.shortToast(getContext(), "Error: " + response.code());
                    return;
                }
                List<SedeParqueaderoDTO> parqueaderoDTOS = response.body();
                for (SedeParqueaderoDTO sedeParqueaderoDTO : parqueaderoDTOS){
                    Marker marker;
                    if (sedeParqueaderoDTO.getCupo() > 0){
                        marker = googleMap.addMarker(new MarkerOptions()
                                .position(new LatLng(sedeParqueaderoDTO.getLatitud(), sedeParqueaderoDTO.getLongitud()))
                                .title(sedeParqueaderoDTO.getNombreSede())
                                .icon(BitmapUtils.getBitmapDescriptor(getContext(), R.drawable.baseline_local_parking_24_blue))
                        );
                    }else{
                        marker = googleMap.addMarker(new MarkerOptions()
                                .position(new LatLng(sedeParqueaderoDTO.getLatitud(), sedeParqueaderoDTO.getLongitud()))
                                .title(sedeParqueaderoDTO.getNombreSede())
                                .icon(BitmapUtils.getBitmapDescriptor(getContext(), R.drawable.baseline_local_parking_24_red))
                        );
                    }
                    markersParqueaderos.add(marker);
                }
                loadingDialog.dismiss();
                llenarArreglo(parqueaderoDTOS);

            }

            @Override
            public void onFailure(Call<List<SedeParqueaderoDTO>> call, Throwable t) {
                alertsHelper.shortToast(getContext(), "Error: " + t.getMessage());
                loadingDialog.dismissDialog();
            }
        });
    }

    private void llenarArreglo(List<SedeParqueaderoDTO> parqueaderoDTOS) {
        for (SedeParqueaderoDTO sedeParqueaderoDTO : parqueaderoDTOS){
            sedeParqueaderoDTOS.add(sedeParqueaderoDTO);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ((App) requireActivity().getApplicationContext()).getAppComponent().inject(this);
        binding = FragmentMapsBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
        adapter = new SugerenciaParqueaderoAdapter(getContext(), R.layout.sugerencia_adapter, sugerenciaParqueaderos);
        binding.bottomSheet.loyoutSheet.suggestionParkings.setAdapter(adapter);

        binding.topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        binding.navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.nav_user) {
                    getActivity().startActivity(new Intent(getActivity(), UserActivity.class));
                    return true;
                }else if (item.getItemId() == R.id.nav_map){
                    binding.drawerLayout.openDrawer(GravityCompat.START);
                    return true;
                }
                else if(item.getItemId() == R.id.nav_favorites){

                }
                else if (item.getItemId() == R.id.nav_cerrar_sesion){
                    AuthenticatedActivity activity = (AuthenticatedActivity) getActivity();
                    activity.signOut();
                    return true;
                }
                return false;
            }
        });
    }

    public void updateLocation(Location lastLocation) {
        if (googleMap != null) {
            if (!init){
                init = true;
                centrarCamara(new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude()));
            }
            llenarSugerencuas(new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude()));
        }
    }

    private void centrarCamara(LatLng pos) {
        LatLng latLng = new LatLng(pos.latitude, pos.longitude);
        googleMap.moveCamera(CameraUpdateFactory.zoomTo(INITIAL_ZOOM_LEVEL));
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
    }

    private void llenarSugerencuas(LatLng lastLocation){
        sugerenciaParqueaderos.clear();
        for(SedeParqueaderoDTO sedeParqueaderoDTO : sedeParqueaderoDTOS){
            double distancia = DistanceUtils.calculateDistanceInKilometer(lastLocation.latitude, lastLocation.longitude, sedeParqueaderoDTO.getLatitud(), sedeParqueaderoDTO.getLongitud());
            if (distancia < 1){
                SugerenciaParqueadero sug = new SugerenciaParqueadero(sedeParqueaderoDTO.getNombreSede(), distancia);
                sugerenciaParqueaderos.add(sug);
                adapter.notifyDataSetChanged();
            }
        }
    }
}