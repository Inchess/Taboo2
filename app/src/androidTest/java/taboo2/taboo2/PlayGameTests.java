package taboo2.taboo2;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import taboo2.taboo2.activities.PlayGameActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


public class PlayGameTests {

    private static final String CORRECT_ANSWER = "Correct";
    private static final String INCORRECT_ANSWER = "Incorrect";
    private static final String CHANGE_TEAM = "End tur";

    @Rule
    public ActivityTestRule<PlayGameActivity> mActivityRule = new ActivityTestRule(PlayGameActivity.class);

    @Test
    public void shouldContainButtonCorrectAnswer() {
        onView(withId(R.id.correct_answer))
                .check(matches(isDisplayed()));
    }

    @Test
    public void shouldContainButtonIncorrectAnswer() {
        onView(withId(R.id.incorrect_answer))
                .check(matches(isDisplayed()));
    }

    @Test
    public void shouldContainButtonEndTurn() {
        onView(withId(R.id.change_team))
                .check(matches(isDisplayed()));
    }

//    @Test
//    public void
}
