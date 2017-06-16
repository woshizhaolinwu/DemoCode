package jrdcom.com.jrdamuse;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private ActivityManager am;
    private ViewStub viewStub;
    private Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*am =(ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);

        //ActivityManager提供的方法：
        am.getRunningAppProcesses();
        ActivityManager.MemoryInfo memoryInfo =new ActivityManager.MemoryInfo();
        am.getMemoryInfo(memoryInfo);
        //am.getProcessMemoryInfo(1001);
        am.getRunningServices(4);*/

        Debug.startMethodTracing("/storage/emulated/0/Android/data/jrdcom.com.jrdamuse/aa.trace");
        mButton = (Button)findViewById(R.id.btn_show);
        viewStub = (ViewStub)findViewById(R.id.view_stub);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               View view =  viewStub.inflate();
                TextView textView = (TextView)view.findViewById(R.id.text_stub);
                textView.setText("just show view stub");
            }
        });
        Character c = new Character('a');
        Character.isLetter('b'); //是否是一个字母
        Character.isDigit(1);    //是否是一个数字
        Character.isWhitespace(' ');    //是否是一个空格
        Character.isUpperCase('A'); //是否是大写字母
        Character.isLowerCase('a'); //是否是小写字母
        Character.toUpperCase('a'); //指定字母的大写形式
        Character.toLowerCase('A'); //指定字母的小写形式
        c.toString();           //转成 String

        int[] a = new int[3];
        int[] b = new int[4];
        Arrays.fill(a, 0);      //将0塞入a 数组
        Arrays.sort(a);         //对数组a进行升序排列
        Arrays.equals(a, b);    //比较两个数组是否相等
        Arrays.binarySearch(a, 0);  //用二分法在数组a中找0，调用前必须排好序


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Debug.stopMethodTracing();

    }
}
