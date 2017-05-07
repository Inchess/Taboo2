package taboo2.taboo2;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import taboo2.taboo2.activities.PlayGameActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class PlayGameTests {

    private static final String CORRECT_ANSWER = "Correct";
    private static final String INCORRECT_ANSWER = "Incorrect";
    private static final String CHANGE_TEAM = "End turn";
    private static final String GREEN_TEAM_SCORE_AT_BEGINNING = "0";
    private static final String RED_TEAM_SCORE_AT_BEGINNING = "0";

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

    @Test
    public void shouldContainGreenTeamScore() {
        onView(withId(R.id.score_green_team))
                .check(matches(isDisplayed()));
    }

    @Test
    public void shouldContainRedTeamScore() {
        onView(withId(R.id.score_red_team))
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

    @Test
    public void shouldCheckTextGreenTeam() {
        onView(withId(R.id.score_green_team))
                .check(matches(withText(GREEN_TEAM_SCORE_AT_BEGINNING)));
    }

    @Test
    public void shouldCheckTextRedTeam() {
        onView(withId(R.id.score_green_team))
                .check(matches(withText(RED_TEAM_SCORE_AT_BEGINNING)));
    }
}
