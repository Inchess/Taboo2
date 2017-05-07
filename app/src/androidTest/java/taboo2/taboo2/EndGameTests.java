package taboo2.taboo2;


import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import taboo2.taboo2.activities.EndGameActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class EndGameTests {

    private static final String GREEN_TEAM_SCORE_AT_BEGINNING = "0";
    private static final String RED_TEAM_SCORE_AT_BEGINNING = "0";
    private static final String DEFAULT_GREEN_TEAM_WON = "Zieloni wygrali!";
    private static final String NEW_GAME = "Nowa gra";

    @Rule
    public ActivityTestRule<EndGameActivity> mActivityRule = new ActivityTestRule(EndGameActivity.class);

    /* ==========================================
    ---------------CHECK DISPLAYED---------------
    ========================================== */

    @Test
    public void shouldContainGreenTeamScore() {
        onView(withId(R.id.end_game_green_team_scores))
                .check(matches(isDisplayed()));
    }

    @Test
    public void shouldContainRedTeamScore() {
        onView(withId(R.id.end_game_red_team_scores))
                .check(matches(isDisplayed()));
    }

    @Test
    public void shouldContainNowPlays() {
        onView(withId(R.id.winning_team))
                .check(matches(isDisplayed()));
    }

    @Test
    public void shouldContainButtonNewGame() {
        onView(withId(R.id.new_game))
                .check(matches(isDisplayed()));
    }

    /* ==========================================
    -----------------CHECK TEXT------------------
    ========================================== */

    @Test
    public void shouldCheckTextGreenTeamScore() {
        onView(withId(R.id.end_game_green_team_scores))
                .check(matches(withText(GREEN_TEAM_SCORE_AT_BEGINNING)));
    }

    @Test
    public void shouldCheckTextRedTeamScore() {
        onView(withId(R.id.end_game_red_team_scores))
                .check(matches(withText(RED_TEAM_SCORE_AT_BEGINNING)));
    }

    @Test
    public void shouldCheckTextNowPlays() {
        onView(withId(R.id.winning_team))
                .check(matches(withText(DEFAULT_GREEN_TEAM_WON)));
    }

    @Test
    public void shouldCheckTextNewGame() {
        onView(withId(R.id.new_game))
                .check(matches(withText(NEW_GAME)));
    }
}
