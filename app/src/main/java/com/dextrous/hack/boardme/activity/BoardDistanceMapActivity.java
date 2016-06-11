package com.dextrous.hack.boardme.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.akexorcist.googledirection.DirectionCallback;
import com.akexorcist.googledirection.GoogleDirection;
import com.akexorcist.googledirection.constant.TransitMode;
import com.akexorcist.googledirection.constant.TransportMode;
import com.akexorcist.googledirection.model.Direction;
import com.akexorcist.googledirection.util.DirectionConverter;
import com.dextrous.hack.boardme.R;
import com.dextrous.hack.boardme.constant.BoardMeConstants;
import com.dextrous.hack.boardme.model.BoardMeLocation;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import static com.dextrous.hack.boardme.constant.BoardMeConstants.INTENT_PARAM_CURRENT_USER_LOCATION_KEY;
import static com.dextrous.hack.boardme.constant.BoardMeConstants.INTENT_PARAM_RECENT_BOARD_LOCATION_KEY;
import static com.dextrous.hack.boardme.constant.BoardMeConstants.MAP_DEFAULT_ZOOM;

public class BoardDistanceMapActivity extends AppCompatActivity implements DirectionCallback, OnMapReadyCallback {

    private GoogleMap googleMap;
    private String serverKey;
    private LatLng camera;
    private LatLng origin;
    private LatLng destination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Object temp = getIntent().getSerializableExtra(INTENT_PARAM_CURRENT_USER_LOCATION_KEY);
        BoardMeLocation userLocation = temp != null ? (BoardMeLocation) temp : null;
        temp = getIntent().getSerializableExtra(INTENT_PARAM_RECENT_BOARD_LOCATION_KEY);
        BoardMeLocation recentBoardLocation = temp != null ? (BoardMeLocation) temp : null;
        if (userLocation != null
                && recentBoardLocation != null) {
//            BoardMeLocation centre = AndroidUtil.midPoint(userLocation, recentBoardLocation);
            BoardMeLocation centre = userLocation;
            camera = new LatLng(centre.getLatitude(), centre.getLongitude());
            origin = new LatLng(userLocation.getLatitude(), userLocation.getLongitude());
            destination = new LatLng(recentBoardLocation.getLatitude(), recentBoardLocation.getLongitude());
            serverKey = getResources().getString(R.string.google_maps_api_key);
            setContentView(R.layout.activity_board_distance_map_activity);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);
            GoogleDirection.withServerKey(serverKey)
                    .from(origin)
                    .to(destination)
                    .transportMode(TransportMode.TRANSIT)
                    .transitMode(TransitMode.BUS)
                    .execute(this);
        }

    }

    @Override
    public void onDirectionSuccess(Direction direction, String rawBody) {
        Toast.makeText(this, "Success with status : " + direction.getStatus(), Toast.LENGTH_LONG).show();
        if (direction.isOK()) {
            googleMap.addMarker(new MarkerOptions().position(origin));
            googleMap.addMarker(new MarkerOptions().position(destination));

            ArrayList<LatLng> directionPositionList = direction.getRouteList().get(0).getLegList().get(0).getDirectionPoint();
            googleMap.addPolyline(DirectionConverter.createPolyline(this, directionPositionList, 5, Color.RED));
        }
    }

    @Override
    public void onDirectionFailure(Throwable t) {
        Toast.makeText(this, BoardMeConstants.MSG_GENERIC_ERROR + t.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(camera, MAP_DEFAULT_ZOOM));
    }
}
