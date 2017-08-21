package layout;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.peter.parkinglotapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class fragment1 extends Fragment {
    String popResult;
    ImageButton iButton1;
    ImageButton iButton2;
    ImageButton iButton3;
    ImageButton iButton4;
    ImageButton iButton5;
    ImageButton iButton6;
    public static int[] occArray;
    public static String[] timeArray;
    public static ImageButton[] buttonArray;
    public static TextView[] timeViews;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment1, container, false);
        occArray = new int[6];
        timeArray = new String[6];
        buttonArray = new ImageButton[6];
        buttonArray[0] = (ImageButton) view.findViewById(R.id.imageCar1);
        buttonArray[1] = (ImageButton) view.findViewById(R.id.imageCar2);
        buttonArray[2] = (ImageButton) view.findViewById(R.id.imageCar3);
        buttonArray[3] = (ImageButton) view.findViewById(R.id.imageCar4);
        buttonArray[4] = (ImageButton) view.findViewById(R.id.imageCar5);
        buttonArray[5] = (ImageButton) view.findViewById(R.id.imageCar6);
        timeViews = new TextView[6];
        timeViews[0] = (TextView) view.findViewById(R.id.time1);
        timeViews[1] = (TextView) view.findViewById(R.id.time2);
        timeViews[2] = (TextView) view.findViewById(R.id.time3);
        timeViews[3] = (TextView) view.findViewById(R.id.time4);
        timeViews[4] = (TextView) view.findViewById(R.id.time5);
        timeViews[5] = (TextView) view.findViewById(R.id.time6);
        System.out.println("WTF THE OCCARRAY" + occArray[0]);
        checkStatus();
        iButton1 = (ImageButton) view.findViewById(R.id.imageCar1);
        iButton1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(occArray[0] == 0) {
                    Intent popRes = new Intent(getActivity(), popupReserve.class);
                    popRes.putExtra("lotId", "1");
                    startActivityForResult(popRes, 1);
                }
                else
                {
                    Intent popClear = new Intent(getActivity(),popupLeave.class);
                    popClear.putExtra("lotId","1");
                    startActivityForResult(popClear,2);

                }
            }
        });
        iButton2 = (ImageButton) view.findViewById(R.id.imageCar2);
        iButton2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(occArray[1] == 0) {
                    Intent popRes = new Intent(getActivity(), popupReserve.class);
                    popRes.putExtra("lotId", "2");
                    startActivityForResult(popRes, 1);
                }
                else{
                    Intent popClear = new Intent(getActivity(),popupLeave.class);
                    popClear.putExtra("lotId","2");
                    startActivityForResult(popClear,2);
                }
            }
        });
        iButton3 = (ImageButton) view.findViewById(R.id.imageCar3);
        iButton3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(occArray[2] == 0) {
                    Intent popRes = new Intent(getActivity(), popupReserve.class);
                    popRes.putExtra("lotId", "3");
                    startActivityForResult(popRes, 1);
                }
                else{
                    Intent popClear = new Intent(getActivity(),popupLeave.class);
                    popClear.putExtra("lotId","3");
                    startActivityForResult(popClear,2);
                }
            }
        });
        iButton4 = (ImageButton) view.findViewById(R.id.imageCar4);
        iButton4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(occArray[3] == 0) {
                    Intent popRes = new Intent(getActivity(), popupReserve.class);
                    popRes.putExtra("lotId", "4");
                    startActivityForResult(popRes, 1);
                }
                else{
                    Intent popClear = new Intent(getActivity(),popupLeave.class);
                    popClear.putExtra("lotId","4");
                    startActivityForResult(popClear,2);
                }
            }
        });
        iButton5 = (ImageButton) view.findViewById(R.id.imageCar5);
        iButton5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(occArray[4] == 0) {
                    Intent popRes = new Intent(getActivity(), popupReserve.class);
                    popRes.putExtra("lotId", "5");
                    startActivityForResult(popRes, 1);
                }
                else {
                    Intent popClear = new Intent(getActivity(),popupLeave.class);
                    popClear.putExtra("lotId","5");
                    startActivityForResult(popClear,2);
                }
            }
        });
        iButton6 = (ImageButton) view.findViewById(R.id.imageCar6);
        iButton6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (occArray[5] == 0) {
                    Intent popRes = new Intent(getActivity(), popupReserve.class);
                    popRes.putExtra("lotId", "6");
                    startActivityForResult(popRes, 1);
                }
                else{
                    Intent popClear = new Intent(getActivity(),popupLeave.class);
                    popClear.putExtra("lotId","6");
                    startActivityForResult(popClear,2);
                }
            }
        });
        return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        String lotId;
        System.out.println("RESULT??????????@@@@@@@@@@@@@@@@@@@");
        if (requestCode == 1){
            if (resultCode == Activity.RESULT_OK){
                popResult = data.getStringExtra("result");
                lotId = data.getStringExtra("lotId");
                System.out.println("pop result onActvity result is" + popResult);;
                updateDatabase(lotId,"1",popResult);
                checkStatus();
                for(int i = 0; i<6; i++){
                    if (occArray[i] == 1) {
                        timeViews[i].setText(timeArray[i]);
                        buttonArray[i].setImageResource(R.drawable.nop);
                        //buttonArray[i].setClickable(false);
                    }
                    else{
                        timeViews[i].setText("N/A");
                        buttonArray[i].setImageResource(R.drawable.yesp);
                        //buttonArray[i].setClickable(true);
                    }
                }
            }
        }
        else{
            checkStatus();
        }
        View v = getView();
        v.invalidate();
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
    private void checkStatus() {
        checkButtonStatus checkStatus = new checkButtonStatus();
        checkStatus.execute();
    }

    // TODO: Rename method, update argument and hook method into UI event
    private void updateDatabase(String slot, String occ, String purTime) {
        String times[] = purTime.split(":");
        int purTimeHour = Integer.parseInt(times[0]) * 60;
        int purTimeMin = Integer.parseInt(times[1]);
        String leaveTimeString;
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String currentDateTimeString = format.format(new Date());
        try {
            Date date = format.parse(currentDateTimeString);
            final Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.MINUTE,purTimeHour+ purTimeMin);
            leaveTimeString = format.format(calendar.getTime());
            System.out.println("current time is " + currentDateTimeString + "the slot id is " +slot+ "THE TIME PUR" + purTime + "after time" + leaveTimeString);
            executeUpdate eUpdate = new executeUpdate();
            eUpdate.execute(slot,occ,currentDateTimeString,purTime,leaveTimeString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private class executeUpdate extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... params) {
            String reg_url = "http://192.168.0.11/client/update.php";
            String lotId = params[0];
            String isOccupied = params[1];
            String arrivalTime = params[2];
            String purchasedTime = params[3];
            String leavingTime = params[4];
            try {
                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
                System.out.println(lotId + isOccupied);
                String data = URLEncoder.encode("lotId","UTF-8") + "=" + URLEncoder.encode(lotId,"UTF-8") + "&"
                        + URLEncoder.encode("isOccupied","UTF-8") + "=" + URLEncoder.encode(isOccupied,"UTF-8") + "&"
                        + URLEncoder.encode("arrivalTime","UTF-8") + "=" + URLEncoder.encode(arrivalTime,"UTF-8")+ "&"
                        + URLEncoder.encode("purchasedTime","UTF-8") + "=" + URLEncoder.encode(purchasedTime,"UTF-8") + "&"
                        + URLEncoder.encode("leavingTime","UTF-8") + "=" + URLEncoder.encode(leavingTime,"UTF-8");
                System.out.println("THE DATA IS" + data);
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS = httpURLConnection.getInputStream();
                IS.close();
                return "added sucessfully";
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... params) {

        }
        @Override
        protected void onPostExecute(String result) {
            //Toast.makeText(getActivity(),result,Toast.LENGTH_LONG).show();
        }
    }
    private class checkButtonStatus extends AsyncTask<Void, Void, String> {
        String JSON_STRING;
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
                jsonArray = jsonObject.getJSONArray("server_response");
                int count = 0;
                String isOcc;
                String leavingTime;
                while(count<jsonArray.length()){
                    JSONObject jo = jsonArray.getJSONObject(count);
                    isOcc = jo.getString("isOccupied");
                    leavingTime = jo.getString("leavingTime");
                    timeArray[count] = leavingTime;
                    occArray[count] = Integer.parseInt(isOcc);
                    count = count + 1;
                }
                System.out.println("INSIDE UPDATE IS" + timeArray[0] +" AND " + occArray[1]);
                for(int i = 0; i<6; i++){
                    if (occArray[i] == 1) {
                        String oldTimeString = timeArray[i];
                        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(oldTimeString);
                        String newDateString = new SimpleDateFormat("HH:mm").format(date);
                        timeViews[i].setText(newDateString);
                        buttonArray[i].setImageResource(R.drawable.nop);
                        //buttonArray[i].setClickable(false);
                    }
                    else{
                        timeViews[i].setText("N/A");
                        buttonArray[i].setImageResource(R.drawable.yesp);
                        //buttonArray[i].setClickable(true);
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

    }
}
