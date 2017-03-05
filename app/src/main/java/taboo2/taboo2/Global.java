package taboo2.taboo2;

import android.app.Activity;
import android.content.res.Resources;
import android.util.DisplayMetrics;

/**
 * Created by y50-70 on 02.03.2017.
 */

public class Global {

    private int phoneWidth;
    private int phoneHeight;

    public Global() {
        init();
    }

    private void init() {
        phoneWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        phoneHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    //================================================
    //---------------GETTERS AND SETTERS--------------
    //================================================

    public int getPhoneWidth() {
        return phoneWidth;
    }

    public int getPhoneHeight() {
        return phoneHeight;
    }
}
