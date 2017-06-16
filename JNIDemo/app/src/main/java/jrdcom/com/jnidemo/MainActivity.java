package jrdcom.com.jnidemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HelloJni helloJni = new HelloJni();

        Toast.makeText(this, helloJni.stringFromJni(), Toast.LENGTH_SHORT).show();
    }
}
