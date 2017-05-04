package taboo2.taboo2.designs;

import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Designs {

    //================================== Add color to views

    public void views_addColorAndRadius(View[] views, int color, int radius) {
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

    //================================== Add text height and location to textViews

    public void views_textHeightAndLocation(TextView[] views, int height) {
        for(TextView view: views) {
            view.setGravity(Gravity.CENTER);
            view.setTextSize(TypedValue.COMPLEX_UNIT_PX, height);
        }
    }

    //================================== Set textViews text height

    public void textViews_textHeight(TextView[] textViews, int textHeight) {
        for(TextView textView: textViews) {
            textView.setHeight(textHeight);
        }
    }

    //================================== Add margins to views

    public void views_setMargins(View[] views, int marginTopBottom, int marginSide) {
        for(View view: views) {
            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) view
                    .getLayoutParams();
            mlp.setMargins(marginSide, marginTopBottom,
                    marginSide, marginTopBottom);
        }
    }

    //================================== Add margin to only one view

    public void setMarginToOneView(View view, int marginTop,
                                     int marginBottom, int marginSide) {
        ViewGroup.MarginLayoutParams params =
                (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        params.setMargins(marginSide, marginTop,
                marginSide, marginBottom);
    }
}
