package jrdcom.com.audiotrackdemo;

import android.content.Context;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileInputStream;

public class MainActivity extends AppCompatActivity {
    private Button btn_play;
    private Button btn_stop;
    private AudioTrack mAudioTrack;
    private myThread thread1;

    private FileInputStream fileInputStream = null;
    private byte buffer[] = new byte[16*10000];
    private File audioFile = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_play = (Button)findViewById(R.id.btn_play);
        btn_stop = (Button)findViewById(R.id.btn_stop);

        btn_play.setOnClickListener(onClickListener);
        btn_stop.setOnClickListener(onClickListener);


    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_play:
                    thread1.start();
                    break;
                case R.id.btn_stop:
                    stopAudioTrack();
                    break;
            }
        }
    };

    void stopAudioTrack(){
        myThread.interrupted();
        mAudioTrack.stop();
        mAudioTrack.release();
    }

    @Override
    protected void onResume() {
        super.onResume();

        //初始化
        initAudioTrack();
        //开启线程写数据
        thread1 = new myThread();
        //thread1.start();
        File externalFilesDir = getExternalFilesDir(null);
//String currentDir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Screenshot";
        audioFile= new File(externalFilesDir.getPath().toString() +"/"+ "test.wav"); //storage/emulated/0/Android/data/jrdcom.com....

        System.out.println(audioFile.length());
        try {
            fileInputStream = new FileInputStream(audioFile);
            fileInputStream.skip(0x2c);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    void initAudioTrack(){
        //获取minbuffer
        int minBuffer = AudioTrack.getMinBufferSize(8000, AudioFormat.CHANNEL_CONFIGURATION_STEREO,AudioFormat.ENCODING_PCM_16BIT);

        mAudioTrack = new AudioTrack(AudioManager.STREAM_MUSIC,
                8000,AudioFormat.CHANNEL_CONFIGURATION_STEREO,
                AudioFormat.ENCODING_PCM_16BIT,minBuffer,AudioTrack.MODE_STREAM);
        mAudioTrack.play();
    }

    private class myThread extends Thread{
        @Override
        public void run() {
            try
            {
                while(fileInputStream.read(buffer)>=0)
                {
                    System.out.println("write pcm data");
                    mAudioTrack.write(buffer, 0, buffer.length);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

}
