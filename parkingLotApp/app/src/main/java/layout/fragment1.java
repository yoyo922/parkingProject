package layout;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.peter.parkinglotapp.MainActivity;
import com.example.peter.parkinglotapp.R;

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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class fragment1 extends Fragment {
    String popResult;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment1, container, false);
        String time = (String) DateFormat.format("dd/MM\nkk:mm", System.currentTimeMillis());
        TextView buffer = (TextView) view.findViewById(R.id.time1);
        buffer.setText(time);
        ImageButton iButton1 = (ImageButton) view.findViewById(R.id.imageCar1);
        iButton1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
                String currentDateTimeString = format.format(new Date());
                System.out.println(currentDateTimeString);
                Intent pop =  new Intent(getActivity(),popup.class);
                System.out.println("starting popup");
                startActivityForResult(pop,1);
                System.out.println("pop.getStringArrayExtra(\"result\");");

                //updateDatabase(1);
            }
        });
        ImageButton iButton2 = (ImageButton) view.findViewById(R.id.imageCar1);
        iButton2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),popup.class));
                //updateDatabase(2);
            }
        });
        ImageButton iButton3 = (ImageButton) view.findViewById(R.id.imageCar1);
        iButton3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),popup.class));
                //updateDatabase(3);
            }
        });
        ImageButton iButton4 = (ImageButton) view.findViewById(R.id.imageCar1);
        iButton4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),popup.class));
                //updateDatabase(4);
            }
        });
        ImageButton iButton5 = (ImageButton) view.findViewById(R.id.imageCar1);
        iButton5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),popup.class));
                //updateDatabase(5);
            }
        });
        ImageButton iButton6 = (ImageButton) view.findViewById(R.id.imageCar1);
        iButton6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),popup.class));
                //updateDatabase(6);
            }
        });

        return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        System.out.println("RESULT??????????@@@@@@@@@@@@@@@@@@@");
        super.onActivityResult(requestCode,resultCode,data);
        System.out.println("RESULT??????????@@@@@@@@@@@@@@@@@@@");
        if (requestCode == 1){
            if (resultCode == Activity.RESULT_OK){
                popResult = data.getStringExtra("result");
            }
        }
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    // TODO: Rename method, update argument and hook method into UI event
    private void updateDatabase(int slot,String arrTime, String purTime) {
        String lotId = "6";
        String isOccupied = "6";
        String arrivalTime = "0";
        String leavingTimer = "0";
        String purchasedTime = "0";
        excuteUpdate eUpdate = new excuteUpdate();
        eUpdate.execute(lotId,isOccupied,arrivalTime,leavingTimer,purchasedTime);
    }

    private class excuteUpdate extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... params) {
            String reg_url = "http://192.168.0.11/client/insertData.php";
            String lotId = params[0];
            String isOccupied = params[1];
            String arrivalTime = params[2];
            String leavingTime = params[3];
            String purchasedTime = params[4];
            try {
                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
                System.out.println(lotId + isOccupied);
                String data = URLEncoder.encode("lotId","UTF-8") + "=" + URLEncoder.encode(lotId,"UTF-8") + "&"
                        + URLEncoder.encode("isOccupied","UTF-8") + "=" + URLEncoder.encode(isOccupied,"UTF-8");
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
}
