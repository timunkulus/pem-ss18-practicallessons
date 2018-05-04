package de.lmu.ifi.session2;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.tierd.pemsession2.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    AccelerometerFragment accFragment;
    MapFragment mapFragment;
    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.accButton)
    public void accButtonClick(){
        Log.i(TAG, "acc button clicked");
        removeFragment(accFragment);
        removeFragment(mapFragment);
        accFragment = new AccelerometerFragment();
        getFragmentManager().beginTransaction().add(R.id.fragmentContainer, accFragment).commit();
    }

    @OnClick(R.id.mapButton)
    public void mapButtonClick() {
        Log.i(TAG, "map button clicked");
        removeFragment(accFragment);
        removeFragment(mapFragment);
        mapFragment = new MapFragment();
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                LatLng munich = new LatLng(48.137154, 11.576124);
                googleMap.addMarker(new MarkerOptions().position(munich).title("Munich"));
            }
        });
        getFragmentManager().beginTransaction().add(R.id.fragmentContainer, mapFragment).commit();
    }


    private void removeFragment( Fragment fragment){
        if(fragment != null)
            getFragmentManager().beginTransaction().remove(fragment).commit();
    }
}
