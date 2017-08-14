package layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.peter.parkinglotapp.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by peter on 02/08/17.
 */

public class popupReserve extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        final EditText editText;
        setContentView(R.layout.popup);
        Button done = (Button) findViewById(R.id.doneButton);
        editText = (EditText) findViewById(R.id.purTime);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        editText.setHint("HH:MM");
        final int width = dm.widthPixels;
        final int height = dm.heightPixels;
        getWindow().setLayout((int) (width * 0.5), (int)(height * 0.2));
        done.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String result;
                String lotId = getIntent().getStringExtra("lotId");
                result = editText.getText().toString();
                if (result.contains(":")) {
                    Intent intent = new Intent();
                    intent.putExtra("result", result);
                    intent.putExtra("lotId", lotId);
                    setResult(Activity.RESULT_OK, intent);
                    System.out.println("sending results from popupReserve");
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(),"invalid format",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
