package puj.quickparked.quickparkedmobile.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.Location;
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
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.navigation.NavigationView;

import puj.quickparked.quickparkedmobile.App;
import puj.quickparked.quickparkedmobile.R;
import puj.quickparked.quickparkedmobile.databinding.FragmentMapsBinding;

public class MapsFragment extends Fragment {


    private FragmentMapsBinding binding;
    static final int INITIAL_ZOOM_LEVEL = 18;
    private GoogleMap googleMap;
    private boolean init = false;

    private


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
            initParkings();
        }
    };

    private void initParkings() {

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

        }
    }

    private void centrarCamara(LatLng pos) {
        LatLng latLng = new LatLng(pos.latitude, pos.longitude);
        googleMap.moveCamera(CameraUpdateFactory.zoomTo(INITIAL_ZOOM_LEVEL));
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
    }

}