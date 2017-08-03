package layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.peter.parkinglotapp.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by peter on 02/08/17.
 */

public class popup extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        final EditText editText;
        setContentView(R.layout.popup);
        Button done = (Button) findViewById(R.id.doneButton);
        editText = (EditText) findViewById(R.id.purTime);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        editText.setText("wtf is it working or not");
        final int width = dm.widthPixels;
        final int height = dm.heightPixels;
        getWindow().setLayout((int) (width * 0.5), (int) (height * 0.2));
        done.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("result",editText.getText());
                setResult(Activity.RESULT_OK,intent);
                System.out.println("sending results from popup");
                finish();
            }
        });
    }
}
