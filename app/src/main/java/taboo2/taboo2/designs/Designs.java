package taboo2.taboo2.designs;

import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Arrays;
import java.util.List;

/**
 * Created by tomaszkubit on 27/03/2017.
 */

public class Designs {

    //================================== Add color to buttons

    public void buttons_addColor(List<Button> buttons, int color, int radius) {
        for(Button button: buttons) {
            RoundRectShape rect = new RoundRectShape(
                    new float[] {radius,radius, radius,radius, radius,radius, radius,radius},
                    null,
                    null);
            ShapeDrawable bg = new ShapeDrawable(rect);
            bg.getPaint().setColor(color);
            if (android.os.Build.VERSION.SDK_INT >= 16)
                button.setBackground(bg);
            else {
                button.setBackgroundDrawable(bg);
            }
        }
    }
    public void buttons_addColor(List<Button> buttons, int color) {
        buttons_addColor(buttons, color, 0);
    }
    public void buttons_addColor(Button[] buttonsArray, int color, int radius) {
        List<Button> buttons = Arrays.asList(buttonsArray);
        buttons_addColor(buttons, color, radius);
    }
    public void buttons_addColor(Button[] buttonsArray, int color) {
        buttons_addColor(buttonsArray, color, 0);
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

    //================================== Set buttons text height

    public void buttons_textHeight(List<Button> buttons, int textHeight) {
        for(Button button: buttons) {
            button.setHeight(textHeight);
        }
    }
    public void buttons_textHeight(Button[] buttonsArray, int textHeight) {
        List<Button> buttons = Arrays.asList(buttonsArray);
        buttons_textHeight(buttons, textHeight);
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
