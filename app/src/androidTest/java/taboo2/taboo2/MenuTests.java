package taboo2.taboo2;

import android.support.test.espresso.Espresso;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import taboo2.taboo2.activities.FirstScreenActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MenuTests {

    private static final String ABOUT_AUTHOR = "O autorze";
    private static final String SETTINGS = "Ustawienia";
    private static final String GAME_RULES = "Zasady gry";
    private static final String START_GAME = "Rozpocznij grę";
    private static final String RULES_PAGE = "Rules page";
    private static final String SETTINGS_PAGE = "Settings page";
    private static final String AUTHOR_PAGE = "Author page";

    @Rule
    public ActivityTestRule<FirstScreenActivity> mActivityRule = new ActivityTestRule(FirstScreenActivity.class);

    @Test
    public void shouldCheckAboutButton() {
        onView(withId(R.id.about))
                .check(matches(withText(ABOUT_AUTHOR)));
    }

    @Test
    public void shouldCheckSettingsButton() {
        onView(withId(R.id.settings))
                .check(matches(withText(SETTINGS)));
    }

    @Test
    public void shouldCheckRulesButton() {
        onView(withId(R.id.gameRules))
                .check(matches(withText(GAME_RULES)));
    }

    @Test
    public void shouldCheckStartGameButton() {
        onView(withId(R.id.startGame))
                .check(matches(withText(START_GAME)));
    }

    @Test
    public void shouldOpenPlayGameActivity() {
        onView(withId(R.id.startGame))
                .perform(click());
        onView(withId(R.id.correct_answer))
                .check(matches(isDisplayed()));
    }

    @Test
    public void shouldOpenGameRulesActivity() {
        onView(withId(R.id.gameRules))
                .perform(click());
        onView(withText(RULES_PAGE))
                .check(matches(isDisplayed()));
    }

    @Test
    public void shouldOpenSettingsActivity() {
        onView(withId(R.id.settings))
                .perform(click());
        onView(withText(SETTINGS_PAGE))
                .check(matches(isDisplayed()));
    }

    @Test
    public void shouldOpenAboutAuthorActivity() {
        onView(withId(R.id.about))
                .perform(click());
        onView(withText(AUTHOR_PAGE))
                .check(matches(isDisplayed()));
    }

}
