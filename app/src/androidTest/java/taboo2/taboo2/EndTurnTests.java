package taboo2.taboo2;


import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import taboo2.taboo2.activities.EndTurnActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class EndTurnTests {

    private static final String GREEN_TEAM_SCORE_AT_BEGINNING = "0";
    private static final String RED_TEAM_SCORE_AT_BEGINNING = "0";
    private static final String DEFAULT_NOW_PLAYS = "Czerwoni";
    private static final String NEXT_ROUND = "Następna runda";

    @Rule
    public ActivityTestRule<EndTurnActivity> mActivityRule = new ActivityTestRule(EndTurnActivity.class);

    /* ==========================================
    ---------------CHECK DISPLAYED---------------
    ========================================== */

    @Test
    public void shouldContainGreenTeamScore() {
        onView(withId(R.id.end_turn_green_team_scores))
                .check(matches(isDisplayed()));
    }

    @Test
    public void shouldContainRedTeamScore() {
        onView(withId(R.id.end_turn_red_team_scores))
                .check(matches(isDisplayed()));
    }

    @Test
    public void shouldContainNowPlays() {
        onView(withId(R.id.now_plays))
                .check(matches(isDisplayed()));
    }

    @Test
    public void shouldContainButtonNextRound() {
        onView(withId(R.id.next_round))
                .check(matches(isDisplayed()));
    }

    /* ==========================================
    -----------------CHECK TEXT------------------
    ========================================== */

    @Test
    public void shouldCheckTextGreenTeamScore() {
        onView(withId(R.id.end_turn_green_team_scores))
                .check(matches(withText(GREEN_TEAM_SCORE_AT_BEGINNING)));
    }

    @Test
    public void shouldCheckTextRedTeamScore() {
        onView(withId(R.id.end_turn_red_team_scores))
                .check(matches(withText(RED_TEAM_SCORE_AT_BEGINNING)));
    }

    @Test
    public void shouldCheckTextNowPlays() {
        onView(withId(R.id.now_plays))
                .check(matches(withText(DEFAULT_NOW_PLAYS)));
    }

    @Test
    public void shouldCheckTextNextRound() {
        onView(withId(R.id.next_round))
                .check(matches(withText(NEXT_ROUND)));
    }
}
