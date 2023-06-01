package puj.quickparked.quickparkedmobile.activities;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.Nullable;



public class AuthenticatedActivity extends BasicActivity{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    protected boolean isAuthenticated() {
        sharedPreferences = getSharedPreferences("session", Context.MODE_PRIVATE);
        return sharedPreferences.getString("token", null) != null;
    }
    protected String getUserId(){
        sharedPreferences = getSharedPreferences("session", Context.MODE_PRIVATE);
        String id = sharedPreferences.getString("idUser", null);
        return sharedPreferences.getString("idUser", null);
    }

    protected boolean existeDestinoGeneral(){
        sharedPreferences = getSharedPreferences("session", Context.MODE_PRIVATE);
        return sharedPreferences.getString("direccionGeneral", null) != null;
    }

    protected String getDestinoGeneral(){
        sharedPreferences = getSharedPreferences("session", Context.MODE_PRIVATE);
        return sharedPreferences.getString("direccionGeneral", null);
    }

    protected String getTokenUser(){
        sharedPreferences = getSharedPreferences("session", Context.MODE_PRIVATE);
        return sharedPreferences.getString("token", null);
    }

    protected String getUsername(){
        sharedPreferences = getSharedPreferences("session", Context.MODE_PRIVATE);
        return sharedPreferences.getString("username", null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!isAuthenticated()) {
            startActivity(LoginActivity.createIntent(this));
        }
    }


    public void signOut(){
        sharedPreferences = getSharedPreferences("session", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        startActivity(LoginActivity.createIntent(this));
    }
}
