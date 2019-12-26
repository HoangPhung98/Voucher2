package com.kingphung.voucher2.view;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Dash;
import com.google.android.gms.maps.model.Dot;
import com.google.android.gms.maps.model.Gap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.maps.GeoApiContext;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.internal.PolylineEncoding;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.kingphung.voucher2.R;
import com.kingphung.voucher2.model.adapter.MyClusterManagerRenderer;
import com.kingphung.voucher2.model.entity.Resaurant;
import com.kingphung.voucher2.model.entity.Voucher;
import com.kingphung.voucher2.presenter.direction.P_CalculateDirection;
import com.kingphung.voucher2.presenter.fetchListRestaurent.P_FetchListRestaurant;
import com.kingphung.voucher2.presenter.searchVoucher.P_SearchVoucher;
import com.kingphung.voucher2.ultils.Instant;
import com.kingphung.voucher2.view.direction.V_I_CalculateDirection;
import com.kingphung.voucher2.view.fetchListRestaurant.V_I_FetchListRestaurant;
import com.kingphung.voucher2.view.searchVoucher.V_I_SearchVoucher;
import com.kingphung.voucher2.view.showPopupVoucherPager.V_I_ShowPopupVoucherPager;
import com.kingphung.voucher2.view.showPopupVoucherPager.V_ShowPopupVoucherPager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MapsActivity extends FragmentActivity
        implements OnMapReadyCallback,
        View.OnClickListener,
        V_I_FetchListRestaurant,
        V_I_ShowPopupVoucherPager,
        V_I_CalculateDirection,
        V_I_SearchVoucher {

    private String TAG = "kkkMapsActivity";

    //UI
    ImageButton btExit;
    EditText etSearch;
    private GoogleMap mMap;
    private ClusterManager<Resaurant> clusterManager;
    private MyClusterManagerRenderer myClusterManagerRenderer;
    private List<Polyline> polylines = new ArrayList<>();

    //location
    private FusedLocationProviderClient fusedLocationProviderClient;
    private Location mLastKnownLocation;
    private final LatLng mDefaultLocation = new LatLng(38.87104557, -77.05596332);
    private static final int    DEFAULT_ZOOM = 15;
    private GeoApiContext geoApiContext;

    //var
    ArrayList<Resaurant> listRestaurant;

    //instants
    private final static int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 125;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Resaurant.CreateDataScript(FirebaseDatabase.getInstance().getReference(Instant.VOUCHER_REF));
        listRestaurant = new ArrayList<>();
        UI();
        getLocationPermission();
    }



    private void UI() {
        initUI();
        setOnClickUI();
    }

    private void initUI() {
        btExit = findViewById(R.id.btLogout);
        etSearch = findViewById(R.id.etSearch);
    }

    private void setOnClickUI() {
        btExit.setOnClickListener(this);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchVoucher(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void searchVoucher(CharSequence searchWord) {
        P_SearchVoucher p_searchVoucher = new P_SearchVoucher(this, listRestaurant, searchWord);
        p_searchVoucher.search();
    }
    @Override
    public void onCompleteSearchVoucher(ArrayList<Resaurant> listRestaurant) {
        drawMakers(listRestaurant);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btLogout:
                showAlertDialogLogout();
                break;
        }
    }

    private void showAlertDialogLogout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Logout");
        builder.setMessage("Do you want to logout?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                AuthUI.getInstance().signOut(MapsActivity.this).
                        addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText( MapsActivity.this, "Bye", Toast.LENGTH_SHORT).show();
                                updateUI();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText( MapsActivity.this, "Error, try again", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        builder.setNegativeButton("Stay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void updateUI() {
        finish();
    }

    private void getLocationPermission() {
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {

            checkGPS();

        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    checkGPS();

                }else{
                    Toast.makeText(this, "Please grant me location access permission", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void checkGPS() {
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if(!lm.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            showAlertDialogGPSRequest();
        }else{
            initGoogleMap();


            getDeviceLocation();
        }
    }

    private void initGoogleMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        if(geoApiContext == null){
            geoApiContext = new GeoApiContext.Builder()
                    .apiKey("AIzaSyBD80faY0jG67x-rKY0kaYL256QdKZHocY")
                    .build();
        }
    }

    private void showAlertDialogGPSRequest() {
        new AlertDialog.Builder(MapsActivity. this )
                .setMessage( "GPS Enable" )
                .setPositiveButton( "Settings" , new
                        DialogInterface.OnClickListener() {
                            @Override
                            public void onClick (DialogInterface paramDialogInterface , int paramInt) {
                                startActivity( new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS )) ;
                            }
                        })
                .setNegativeButton("Cancel", new
                        DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MapsActivity.this, "Please enable GPS", Toast.LENGTH_LONG).show();
                            }
                        })
                .show() ;
    }

    private void getDeviceLocation() {
        try {
            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
            Task locationResult = fusedLocationProviderClient.getLastLocation();

                locationResult.addOnCompleteListener(this, new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {

                        if (task.isSuccessful() && task.getResult()!=null) {
                            mLastKnownLocation = (Location) task.getResult();
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                    new LatLng(mLastKnownLocation.getLatitude(),
                                            mLastKnownLocation.getLongitude()), DEFAULT_ZOOM));

                        } else {
                            mLastKnownLocation = new Location("provider");
                            mLastKnownLocation.setAltitude(mDefaultLocation.latitude);
                            mLastKnownLocation.setLongitude(mDefaultLocation.longitude);
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mDefaultLocation, DEFAULT_ZOOM));
                            mMap.getUiSettings().setMyLocationButtonEnabled(false);
                        }
                        fetchListRestaurant();
                    }
                });
        } catch(SecurityException e)  {
            Log.d("KIG-exception: %s", e.getMessage());
        }
    }



    private void fetchListRestaurant() {
        P_FetchListRestaurant p_fetchListRestaurant = new P_FetchListRestaurant(this, this);
        p_fetchListRestaurant.fetch();

    }

    @Override
    public void onCompleteFetListRestaurant(ArrayList<Resaurant> listRestaurant) {
        this.listRestaurant.clear();
        this.listRestaurant.addAll(listRestaurant);
        drawMakers(listRestaurant);
    }

    private void drawMakers(ArrayList<Resaurant> listRestaurant) {
        setUpCluster();
        clusterManager.clearItems();
        clusterManager.addItems(listRestaurant);
        clusterManager.cluster();
    }

    private void setUpCluster() {
        if(clusterManager==null)
        {
            clusterManager = new ClusterManager<>(this, mMap);

            if(myClusterManagerRenderer == null){
                myClusterManagerRenderer = new MyClusterManagerRenderer(
                        this,
                        mMap,
                        clusterManager
                );
                clusterManager.setRenderer(myClusterManagerRenderer);
            }

            mMap.setOnCameraIdleListener(clusterManager);
            mMap.setOnMarkerClickListener(clusterManager);
            clusterManager.setOnClusterItemClickListener(new ClusterManager.OnClusterItemClickListener<Resaurant>() {
                @Override
                public boolean onClusterItemClick(Resaurant resaurant) {
                    openPopUpWindowVoucherPager(resaurant);
                    return true;
                }
            });
        }

    }

    private void openPopUpWindowVoucherPager(Resaurant resaurant) {
        V_ShowPopupVoucherPager v_showPopupVoucherPager = new V_ShowPopupVoucherPager(this, this, resaurant);
        v_showPopupVoucherPager.show();
    }

    @Override
    public void onCompleteVoucherSelected(Resaurant resaurant, Voucher voucher, String TAG_DIRECTION) {
        Toast.makeText(this, voucher.getTitle() + "-" + TAG_DIRECTION, Toast.LENGTH_SHORT).show();
        calculateDirection(new LatLng(mLastKnownLocation.getLatitude(), mLastKnownLocation.getLongitude()), resaurant.getPosition(), TAG_DIRECTION);
    }

    private void calculateDirection(LatLng start, LatLng destination, String tag_direction) {
        P_CalculateDirection p_calculateDirection = new P_CalculateDirection(this, this, geoApiContext, start, destination, tag_direction);
        p_calculateDirection.calculate();
    }
    @Override
    public void onCompleteCalculateDirection(DirectionsResult directionsResult, String TAG_DIRECTION) {
        Log.d(TAG, directionsResult.routes[0].toString());
        Log.d(TAG, directionsResult.routes[0].legs[0].duration.toString());
        Log.d(TAG, directionsResult.geocodedWaypoints[0].toString());
        addPolylineToMap(directionsResult, TAG_DIRECTION);
    }

    private void addPolylineToMap(DirectionsResult directionsResult, String TAG_DIRECTION) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {

                for(DirectionsRoute route: directionsResult.routes){
                    List<com.google.maps.model.LatLng> decodedPath
                            = PolylineEncoding.decode(route.overviewPolyline.getEncodedPath());
                    List<LatLng> newDecodedPath = new ArrayList<>();
                    for(com.google.maps.model.LatLng latLng:decodedPath){
                        newDecodedPath.add(new LatLng(latLng.lat, latLng.lng));
                    }
//                    mMap.clear();
                    clusterManager.cluster();
                    clearPolylines();
                    Polyline polyline = mMap.addPolyline(new PolylineOptions().addAll(newDecodedPath));
                    polylines.add(polyline);
                    polyline.setColor(R.color.gray);
                    polyline.setPattern(getPattern(TAG_DIRECTION));
                    polyline.setClickable(true);
                }
            }
        });
    }

    private void clearPolylines() {
        for(Polyline polyline:polylines){
            polyline.remove();
        }
    }

    private List<PatternItem> getPattern(String tag_direction) {
        PatternItem DOT = new Dot();
        PatternItem DASH = new Dash(5);
        PatternItem GAP = new Gap(10);
        if(tag_direction.equals(Instant.BIKE)){
            return Arrays.asList(DOT, GAP);
        }else{
            if(tag_direction.equals(Instant.WALK)){
                return Arrays.asList(DOT, GAP);
            }
        }
        return null;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.setMyLocationEnabled(true);
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @Override
    protected void onResume() {
        super.onResume();
        getLocationPermission();
    }



}
