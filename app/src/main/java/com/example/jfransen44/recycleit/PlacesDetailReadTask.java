package com.example.jfransen44.recycleit;

import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by Spock on 5/18/2016.
 */
public class PlacesDetailReadTask extends AsyncTask<Object, Integer, String> {
    String googlePlacesData = null;
    MainActivity mainActivity;

    public PlacesDetailReadTask(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }

    @Override
    protected String doInBackground(Object... inputObj) {
        try{
            Log.d("PLACES DETAIL READ TASK", inputObj[0].toString());
            String googlePlacesURL = (String) inputObj[0];
            GoogleHttp http = new GoogleHttp();
            googlePlacesData = http.readGoogle(googlePlacesURL);
            Log.d("GOOGLEPLACESDATARETURN", googlePlacesData.toString());
        }
        catch (Exception e){
            Log.d("Places Detail Read Task", e.toString());
        }
        return googlePlacesData;
    }

    protected void onPostExecute(String result){
        //Log.d("Tag", result);
        mainActivity.setPlacesDetail(result);

    }
}
