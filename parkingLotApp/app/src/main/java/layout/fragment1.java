package layout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.example.peter.parkinglotapp.R;

public class fragment1 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view  = inflater.inflate(R.layout.fragment_fragment1, container, false);
        String time = (String) DateFormat.format("dd/MM/yyyy \n kk:mm", System.currentTimeMillis());
        TextView buffer = (TextView)view.findViewById(R.id.time1);
        buffer.setText(time);
        /*GridView gridView = (GridView) view.findViewById(R.id.parkinglot_grid);
        if (gridView == null)
        {
            System.out.println("WTF view");
        }
        else
        {
            System.out.println("it should be working view");
        }
        ImageAdapter ImageAdapter1 =  new ImageAdapter(view.getContext());
        if (ImageAdapter1 == null)
        {
            System.out.println("WTF");
        }
        else
        {
            System.out.println("it should be working");
        }
        gridView.setAdapter(ImageAdapter1);

        // Inflate the layout for this fragment
        return view;*/
        return view;
    }
    public void wtfwtfwtf() {
        String time = (String) DateFormat.format("dd/MM/yyyy kk:mm", System.currentTimeMillis());
        TextView buffer = (TextView)getView().findViewById(R.id.time1);
        buffer.setText(time);

    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
    // TODO: Rename method, update argument and hook method into UI event
}
