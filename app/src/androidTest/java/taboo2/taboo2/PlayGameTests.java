package taboo2.taboo2;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import taboo2.taboo2.activities.PlayGameActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


public class PlayGameTests {

    private static final String CORRECT_ANSWER = "Correct";
    private static final String INCORRECT_ANSWER = "Incorrect";
    private static final String CHANGE_TEAM = "End turn";

    @Rule
    public ActivityTestRule<PlayGameActivity> mActivityRule = new ActivityTestRule(PlayGameActivity.class);

    /* ==========================================
    ---------------CHECK DISPLAYED---------------
    ========================================== */

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

    /* ==========================================
    -----------------CHECK TEXT------------------
    ========================================== */

    @Test
    public void shouldCheckTextCorrectAnswer() {
        onView(withId(R.id.correct_answer))
                .check(matches(withText(CORRECT_ANSWER)));
    }

    @Test
    public void shouldCheckTextIncorrectAnswer() {
        onView(withId(R.id.incorrect_answer))
                .check(matches(withText(INCORRECT_ANSWER)));
    }

    @Test
    public void shouldCheckTextEndTurn() {
        onView(withId(R.id.change_team))
                .check(matches(withText(CHANGE_TEAM)));
    }
}
