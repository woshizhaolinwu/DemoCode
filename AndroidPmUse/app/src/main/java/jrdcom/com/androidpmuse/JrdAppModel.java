package jrdcom.com.androidpmuse;

import android.graphics.drawable.Drawable;

/**
 * Created by longcheng on 2017/6/3.
 */

public class JrdAppModel {
    private String appName;
    private String appLabel;
    private Drawable appIcon;

    public Drawable getAppIcon() {
        return appIcon;
    }

    public String getAppLabel() {
        return appLabel;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppIcon(Drawable appIcon) {
        this.appIcon = appIcon;
    }

    public void setAppLabel(String appLabel) {
        this.appLabel = appLabel;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
}
