package layout;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.peter.parkinglotapp.R;

public class fragment2 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_fragment2, container, false);
        GridView gridView = (GridView) view.findViewById(R.id.parkinglot_grid);
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
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
    // TODO: Rename method, update argument and hook method into UI event
}
