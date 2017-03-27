package taboo2.taboo2.designs;

import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.widget.Button;

import java.util.Arrays;
import java.util.List;

/**
 * Created by tomaszkubit on 27/03/2017.
 */

public class Designs {

    public void addColorToButtons(List<Button> buttons, int color, int radius) {

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
    public void addColorToButtons(List<Button> buttons, int color) {
        addColorToButtons(buttons, color, 0);
    }
    public void addColorToButtons(Button[] buttonsArray, int color, int radius) {
        List<Button> buttons = Arrays.asList(buttonsArray);
        addColorToButtons(buttons, color, radius);
    }
    public void addColorToButtons(Button[] buttonsArray, int color) {
        addColorToButtons(buttonsArray, color, 0);
    }

}
