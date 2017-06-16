package jrdcom.com.jnidemo;

/**
 * Created by longcheng on 2017/6/16.
 */

public class HelloJni {

    static{
        System.loadLibrary("hello-jni");
    }
    public native String stringFromJni();
}
