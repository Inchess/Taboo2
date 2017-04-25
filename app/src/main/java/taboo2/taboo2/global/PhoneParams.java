package taboo2.taboo2.global;

import android.content.res.Resources;


public class PhoneParams {

    private int phoneWidth;
    private int phoneHeight;

    public PhoneParams() {
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

    private static int pxToDp(int px) {
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
