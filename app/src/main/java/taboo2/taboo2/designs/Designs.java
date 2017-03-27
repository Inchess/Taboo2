package taboo2.taboo2.designs;

import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

/**
 * Created by tomaszkubit on 27/03/2017.
 */

public class Designs {

    //================================== Add color to views

    public void views_addColorAndRadius(List<View> views, int color, int radius) {
        for(View view: views) {
            RoundRectShape rect = new RoundRectShape(
                    new float[] {radius,radius, radius,radius, radius,radius, radius,radius},
                    null,
                    null);
            ShapeDrawable bg = new ShapeDrawable(rect);
            bg.getPaint().setColor(color);
            if (android.os.Build.VERSION.SDK_INT >= 16)
                view.setBackground(bg);
            else {
                view.setBackgroundDrawable(bg);
            }
        }
    }
    public void views_addColor(List<View> views, int color) {
        views_addColorAndRadius(views, color, 0);
    }
    public void views_addColorAndRadius(View[] viewsArray, int color, int radius) {
        List<View> views = Arrays.asList(viewsArray);
        views_addColorAndRadius(views, color, radius);
    }
    public void views_addColor(View[] viewsArray, int color) {
        views_addColorAndRadius(viewsArray, color, 0);
    }

    //================================== Add text height and location to buttons

    public void buttons_textHeightAndLocation(List<Button> buttons, int height) {
        for(Button button: buttons) {
            button.setGravity(Gravity.CENTER);
            button.setTextSize(TypedValue.COMPLEX_UNIT_PX, height);
        }
    }
    public void buttons_textHeightAndLocation(Button[] buttonsArray, int height) {
        List<Button> buttons = Arrays.asList(buttonsArray);
        buttons_textHeightAndLocation(buttons, height);
    }

    //================================== Set textViews text height

    public void textViews_textHeight(List<TextView> textViews, int textHeight) {
        for(TextView textView: textViews) {
            textView.setHeight(textHeight);
        }
    }
    public void textViews_textHeight(TextView[] textViewsArray, int textHeight) {
        List<TextView> textViews = Arrays.asList(textViewsArray);
        textViews_textHeight(textViews, textHeight);
    }

    //================================== Add margins to views

    public void views_setMargins(List<View> views, int marginTopBottom, int marginSide) {
        for(View view: views) {
            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) view
                    .getLayoutParams();
            mlp.setMargins(marginSide, marginTopBottom,
                    marginSide, marginTopBottom);
        }
    }
    public void views_setMargins(View[] viewsArray, int marginTopBottom, int marginSide) {
        List<View> views = Arrays.asList(viewsArray);
        views_setMargins(views, marginTopBottom, marginSide);
    }

    //================================== Add margin to only one view

    public void setMarginToOneView(View view, int marginTop,
                                     int marginBottom, int marginSide) {
        ViewGroup.MarginLayoutParams params =
                (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        params.setMargins(marginSide, marginTop,
                marginSide, marginBottom);
    }

    //==================================

    //==================================


}
