package com.t_kost.kost_tegal;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.app.Fragment;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.MapFragment;

public class MapActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener, OnMapReadyCallback , GoogleMap.OnMarkerClickListener {

    private LocationRequest mLocationRequest;
    private GoogleApiClient mGoogleApiClient;
    private LatLng latLng;
    private GoogleMap mGoogleMap;
    private SupportMapFragment mFragment;
    private Marker mCurrLocation;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        Button btClose = (Button) findViewById(R.id.btClose);
        SupportMapFragment mFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mFragment.getMapAsync(this);
        btClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dari=getIntent().getExtras().getString("dari");
                String tipe=getIntent().getExtras().getString("tipe");
                assert dari != null;
                if(dari.equalsIgnoreCase("home")) {
                    startActivity(new Intent(MapActivity.this, Home.class));
                    finish();
                }else
                {
                    Intent pindahkan=new Intent(MapActivity.this,KostRinci.class);
                    pindahkan.putExtra("tipe",tipe);
                    startActivity(pindahkan);
                    finish();
                }
            }
        });
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);


        mGoogleMap.clear();
        latLng = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Lokasi Saya");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        mCurrLocation = mGoogleMap.addMarker(markerOptions);
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,18));

        LatLng GRIYA = new LatLng(-6.8662015,109.1275087);
        Marker griya = mGoogleMap.addMarker(new MarkerOptions()
                .position(GRIYA)
                .title("Griya Safira")
                .snippet("Rp.400.000,-"));
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(GRIYA, 10));

        LatLng COBRA = new LatLng(-6.8674326,109.1081013);
        Marker cobra = mGoogleMap.addMarker(new MarkerOptions()
                .position(COBRA)
                .title("Cobra Kost")
                .snippet("Rp.300.000.-"));
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(COBRA, 10));

        LatLng ANISA = new LatLng(-6.8676129,109.108097);
        Marker anisa = mGoogleMap.addMarker(new MarkerOptions()
                .position(ANISA)
                .title("Anisa Kost")
                .snippet("Rp.300.000.-"));
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ANISA, 10));


        LatLng KUSUMA = new LatLng(-6.8646231,109.1303951);
        Marker kusuma = mGoogleMap.addMarker(new MarkerOptions()
                .position(KUSUMA)
                .title("Kusuma Kost")
                .snippet("Rp.500.000.-"));
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(KUSUMA, 10));

        LatLng AURORA = new LatLng(-6.875932,109.1338853);
        Marker aurora = mGoogleMap.addMarker(new MarkerOptions()
                .position(AURORA)
                .title("Aurora Kost")
                .snippet("Rp.1.200.000.-"));
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(AURORA, 10));

        LatLng SATRIA = new LatLng(-6.8787581,109.1350692);
        Marker satria = mGoogleMap.addMarker(new MarkerOptions()
                .position(SATRIA)
                .title("Satria Kencana")
                .snippet("Rp.900.000.-"));
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SATRIA, 10));

        LatLng INDIRA = new LatLng(-6.868213,109.1404489);
        Marker indira = mGoogleMap.addMarker(new MarkerOptions()
                .position(INDIRA)
                .title("Kost Indira")
                .snippet("Rp.1.100.000.-"));
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(INDIRA, 10));

        LatLng MAJU = new LatLng(-6.8861618,109.1407851);
        Marker maju = mGoogleMap.addMarker(new MarkerOptions()
                .position(MAJU)
                .title("Maju Kost")
                .snippet("Rp.550.000.-"));
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(MAJU, 10));

        LatLng PUTRA = new LatLng(-6.851058,109.146624);
        Marker putra = mGoogleMap.addMarker(new MarkerOptions()
                .position(PUTRA)
                .title("Putra Kost")
                .snippet("Rp.300.000.-"));
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(PUTRA, 10));

        LatLng PUTRI = new LatLng(-6.85216,109.1458473);
        Marker putri = mGoogleMap.addMarker(new MarkerOptions()
                .position(PUTRI)
                .title("Kost Putri Minang")
                .snippet("Rp.500.000.-"));
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(PUTRI, 10));


        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(5000); //5 seconds
        mLocationRequest.setFastestInterval(3000); //3 seconds
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        mLocationRequest.setSmallestDisplacement(0.1F); //1/10 meter

        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);

    }


    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }
    }

    @Override
    public void onLocationChanged(Location location) {

    }

   @Override
   public void onMapReady(GoogleMap googleMap) {

   mGoogleMap = googleMap;



     if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
         return;
      }
     mGoogleMap.setMyLocationEnabled(true);

       buildGoogleApiClient();

    mGoogleApiClient.connect();
   }
    @Override
    public void onBackPressed() {



    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }
}
