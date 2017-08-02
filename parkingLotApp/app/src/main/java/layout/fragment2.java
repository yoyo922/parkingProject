package layout;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.peter.parkinglotapp.R;;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class fragment2 extends Fragment{
    String JSON_STRING;
    View view;
    // TODO: Rename parameter arguments, choose names that match
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view  = inflater.inflate(R.layout.fragment_fragment2, container, false);

        //container.removeAllViews();
        // Inflate the layout for this fragment
        getData();
        return view;
    }

    private void getData() {
        RetriveDataInfo retriveDataInfo = new RetriveDataInfo();
        retriveDataInfo.execute();
    }
    private class RetriveDataInfo extends AsyncTask<Void, Void, String> {

        String json_url;
        @Override
        protected void onPreExecute()
        {
            json_url = "http://192.168.0.11/client/getdata.php";
        }
        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                while ((JSON_STRING = bufferedReader.readLine()) != null){
                    stringBuilder.append(JSON_STRING+"\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result){
            System.out.println(result);
            parseJSON(result);
        }

    }
    private void parseJSON(String jsonString) {
        JSONObject jsonObject;
        JSONArray jsonArray;
        if(jsonString == null) {
            Toast.makeText(getActivity(),"Json dose not exist", Toast.LENGTH_LONG).show();
        }
        else{
            try {
                jsonObject = new JSONObject(jsonString);
                InfoAdaptor infoAdaptor;
                ListView listView;
                listView = (ListView)view.findViewById(R.id.listview);;
                infoAdaptor= new InfoAdaptor(getActivity(),R.layout.row_layout);
                listView.setAdapter(infoAdaptor);
                jsonArray = jsonObject.getJSONArray("server_response");
                int count = 0;
                String lotId,isOcc,arrivalTime,leavingTime,purchasedTime;
                while(count<jsonArray.length()){
                    JSONObject jo = jsonArray.getJSONObject(count);
                    lotId = jo.getString("lotId");
                    isOcc = jo.getString("isOccupied");
                    arrivalTime = jo.getString("arrivalTime");
                    purchasedTime = jo.getString("purchasedTime");
                    leavingTime = jo.getString("leavingTime");
                    ParkingInfo info = new ParkingInfo(lotId,isOcc,arrivalTime,purchasedTime,leavingTime);
                    infoAdaptor.add(info);
                    System.out.println("WTF IS IT WORKING?" + lotId + "the count is " + jsonArray.length());
                    count = count + 1;
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }
}

