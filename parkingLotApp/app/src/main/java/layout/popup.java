package layout;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.example.peter.parkinglotapp.R;

/**
 * Created by peter on 02/08/17.
 */

public class popup extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * 0.5), (int) (height * 0.2));

    }
}
