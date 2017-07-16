package jrdcom.com.anrdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btn_anr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_anr = (Button)findViewById(R.id.btn_anr);
        btn_anr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                while(true){
                    try{
                        Thread.sleep(1000);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });

    }
}
