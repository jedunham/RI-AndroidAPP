package com.example.jfransen44.recycleit;

import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

/**
 * Created by JFransen44 on 4/30/16.
 */
public class GooglePlacesDisplayTask extends AsyncTask<Object, Integer, List<HashMap<String, String>>> {

    JSONObject googlePlacesJson;
    GoogleMap mMap;
    MainActivity mainActivity = new MainActivity();
    @Override
    protected List<HashMap<String, String>> doInBackground(Object... inputObj) {

        List<HashMap<String, String>> googlePlacesList = null;
        GooglePlaces placesJsonParser = new GooglePlaces();

        try {
            mMap = (GoogleMap) inputObj[0];
            googlePlacesJson = new JSONObject((String) inputObj[1]);
            googlePlacesList = placesJsonParser.parse(googlePlacesJson, 1001);
        }
        catch (Exception e) {
            Log.d("Exception", e.toString());
        }
        //Log.d("Google Places List:", googlePlacesList.toString());//for testing
        return googlePlacesList;
    }

    protected void onPostExecute(List<HashMap<String, String>> list){
        for (int i = 0; i < list.size(); i++){
            //MarkerOptions markerOptions = new MarkerOptions();
            HashMap<String, String> googlePlace = list.get(i);
            double lat = Double.parseDouble(googlePlace.get("lat"));
            double lng = Double.parseDouble(googlePlace.get("lng"));
            String placeName = googlePlace.get("place_name");
            String vicinity = googlePlace.get("vicinity");
            LatLng latLng = new LatLng(lat, lng);
            //markerOptions.position(latLng);
            //markerOptions.title(placeName + " : " + vicinity);
            //mMap.addMarker(markerOptions);
            Marker m = mMap.addMarker(new MarkerOptions().position(latLng).title(placeName + ". Click for more details."));

        }
    }
}
