package taboo2.taboo2;

import android.app.Activity;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;

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
        int width = Resources.getSystem().getDisplayMetrics().widthPixels;
        int height = Resources.getSystem().getDisplayMetrics().heightPixels;
        phoneWidth = pxToDp(width);
        phoneHeight = pxToDp(height);
    }

    /* ===============================================
    ----------------------METHODS---------------------
    =============================================== */

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    /* ===============================================
    ----------------GETTERS AND SETTERS---------------
    ================================================ */

    public int getPhoneWidth() {
        return phoneWidth;
    }

    public int getPhoneHeight() {
        return phoneHeight;
    }
}
