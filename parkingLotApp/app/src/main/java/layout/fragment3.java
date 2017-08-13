package layout;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.peter.parkinglotapp.R;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarException;

public class fragment3 extends Fragment implements OnMapReadyCallback {
    // TODO: Rename parameter arguments, choose names that match
    GoogleMap mGoogleMap;
    MapView mMapView;
    View mView;
    private static TextView duration;
    private static TextView distance;
    private static final String DIRECTION_URL_API = "https://maps.googleapis.com/maps/api/directions/json?";
    private static final String API_KEY = "AIzaSyDiwNIP2MDIOgnWHtgFrQb_GmDJHsDBMjY";
    private String destination = "43.530660,-80.228900";
    private List<Polyline> polylinePaths = new ArrayList<>();
    private List<Marker> originMarkers = new ArrayList<>();
    private List<Marker> destinationMarkers = new ArrayList<>();
    private LocationManager locationManager;
    private LocationListener locationListener;
    private String currentLocation;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView  = inflater.inflate(R.layout.fragment_fragment3, container, false);
        Button goButton = (Button) mView.findViewById(R.id.goButton);
        distance = (TextView) mView.findViewById(R.id.distance);
        duration = (TextView) mView.findViewById(R.id.duration);
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequest();
            }
        });
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        Location buffLocation = locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER);
        currentLocation = buffLocation.getLatitude() + "," + buffLocation.getLongitude();
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                currentLocation = location.getLatitude() + "," + location.getLongitude();
                System.out.println(currentLocation);
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };
        System.out.println("WTF");
        System.out.println(currentLocation);
        locationManager.requestLocationUpdates("gps",5000,0,locationListener);
        return mView;
    }
    public void sendRequest(){
        try {
            System.out.println(createUrl());
            new DownloadData().execute(createUrl());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    private String createUrl() throws UnsupportedEncodingException {;

        return DIRECTION_URL_API + "origin=" + currentLocation + "&destination=" + destination + "&key=" + API_KEY;
    }

    private class DownloadData extends AsyncTask<String, Void, String> {
       @Override
       protected String doInBackground(String...params){
       String link = params[0];
           try {
               URL url = new URL(link);
               InputStream is = url.openConnection().getInputStream();
               StringBuffer buffer = new StringBuffer();
               BufferedReader reader = new BufferedReader(new InputStreamReader(is));

               String line;
               while ((line = reader.readLine()) != null) {
                   buffer.append(line+ "\n");
               }

               return buffer.toString();
           } catch (MalformedURLException e){
               e.printStackTrace();
           } catch (IOException e){
               e.printStackTrace();
           }
           return null;
       }
        @Override
        protected void onPostExecute(String res) {
            try {
                parseJSON(res);
            } catch (JSONException e){
                e.printStackTrace();
            }

        }
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMapView = (MapView) mView.findViewById(R.id.map);
        if (mMapView != null) {
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);
        }

    }
    private void parseJSON(String data) throws JSONException {
        if (data == null)
            return;
        List <Route> routes = new ArrayList<Route>();
        JSONObject jsonData = new JSONObject(data);
        JSONArray jsonRoutes = jsonData.getJSONArray("routes");

        for (int i = 0; i < jsonRoutes.length(); i++){
            JSONObject jsonRoute = jsonRoutes.getJSONObject(i);

            Route route = new Route();
            JSONObject overview_polylineJson = jsonRoute.getJSONObject("overview_polyline");
            JSONArray jsonLegs = jsonRoute.getJSONArray("legs");
            JSONObject jsonLeg =  jsonLegs.getJSONObject(0);
            JSONObject jsonDistance = jsonLeg.getJSONObject("distance");
            JSONObject jsonDuration = jsonLeg.getJSONObject("duration");
            JSONObject jsonEndLocation = jsonLeg.getJSONObject("end_location");
            JSONObject jsonStartLocation = jsonLeg.getJSONObject("start_location");


            route.distance = new Distance(jsonDistance.getString("text"),jsonDistance.getInt("value"));
            route.duration = new Duration(jsonDuration.getString("text"),jsonDuration.getInt("value"));
            route.endAddress = jsonLeg.getString("end_address");
            route.startAddress = jsonLeg.getString("start_address");
            route.startlocation = new LatLng(jsonStartLocation.getDouble("lat"), jsonStartLocation.getDouble("lng"));
            route.endLocation = new LatLng(jsonEndLocation.getDouble("lat"),jsonEndLocation.getDouble("lng"));
            route.points = decodePolyLine(overview_polylineJson.getString("points"));
            routes.add(route);

            distance.setText(route.distance.text);
            duration.setText(route.duration.text);

        }
        drawRoutes(routes);
    }
    private void drawRoutes(List<Route> routes)
    {
        polylinePaths = new ArrayList<>();
        originMarkers = new ArrayList<>();
        destinationMarkers = new ArrayList<>();

        for (Route route : routes) {
            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(route.startlocation, 16));

            PolylineOptions polylineOptions = new PolylineOptions().
                   geodesic(true).
                   color(Color.BLUE).
                   width(10);
            for (int i = 0; i <route.points.size();i++)
                polylineOptions.add(route.points.get(i));

            polylinePaths.add(mGoogleMap.addPolyline(polylineOptions));
        }

    }

    private List<LatLng>decodePolyLine(final String poly){
        int len = poly.length();
        int index = 0;
        List<LatLng> decoded = new ArrayList<LatLng>();
        int lat = 0;
        int lng = 0;

        while (index < len) {
            int b;
            int shift = 0;
            int result = 0;
            do {
                b = poly.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = poly.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            decoded.add(new LatLng(
                    lat / 100000d, lng / 100000d
            ));
        }
        return decoded;
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getActivity());

        mGoogleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        LatLng home = new LatLng(43.520988,-80.245368);
        LatLng parkingLot = new LatLng(43.530660,-80.228900);
        googleMap.addMarker(new MarkerOptions().position(home).title("statue of liberty"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(home,18));
        mGoogleMap.setMyLocationEnabled(true);
    }
}
