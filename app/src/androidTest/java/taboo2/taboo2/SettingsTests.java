package taboo2.taboo2;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.SoundEffectConstants;

import org.hamcrest.Matchers;
import org.hamcrest.core.AllOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import taboo2.taboo2.activities.SettingsActivity;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isNotChecked;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class SettingsTests {

    private static final String POINTS_TO_WIN = "Punkty do wygrania";
    private static final String FORBIDDEN_WORDS = "Zakazane słowa";
    private static final String POINTS_CORRECT_ANSWER = "Prawidłowa odpowiedź";
    private static final String POINTS_INCORRECT_ANSWER = "Nieprawidłowa odpowiedź";
    private static final String TIME_PER_PLAYER = "Czas na gracza";
    private static final String QUESTIONS_LEVEL = "Poziom trudności:";
    private static final String EASY_LEVEL = "Łatwy";
    private static final String AVERAGE_LEVEL = "Średni";
    private static final String DIFFICULT_LEVEL = "Trudny";
    private static final String VERY_DIFFICULT_LEVEL = "Bardzo trudny";
    private static final String SAVE_AND_QUIT = "Zapisz i wróć";
    private static final String DEFAULT_POINTS_TO_WIN = "30";
    private static final String DEFAULT_FORBIDDEN_WORDS = "5";
    private static final String DEFAULT_CORRECT_ANSWER = "1";
    private static final String DEFAULT_INCORRECT_ANSWER = "-1";
    private static final String DEFAULT_TIME_PER_PLAYER = "01:00";

    @Rule
    public ActivityTestRule<SettingsActivity> mActivityRule = new ActivityTestRule(SettingsActivity.class);

    /* ==========================================
    ---------------CHECK DISPLAYED---------------
    ========================================== */

    @Test
    public void shouldContainSpinnerPointsToWin() {
        onView(withId(R.id.spinner$_points_to_win))
                .check(matches(isDisplayed()));
    }

    @Test
    public void shouldContainSpinnerForbiddenWords() {
        onView(withId(R.id.spinner$_forbidden_words))
                .check(matches(isDisplayed()));
    }

    @Test
    public void shouldContainSpinnerCorrectAnswer() {
        onView(withId(R.id.spinner$_points_correct_answer))
                .check(matches(isDisplayed()));
    }

    @Test
    public void shouldContainSpinnerIncorrectAnswer() {
        onView(withId(R.id.spinner$_points_incorrect_answer))
                .check(matches(isDisplayed()));
    }

    @Test
    public void shouldContainSpinnerTimePerPlayer() {
        onView(withId(R.id.spinner$_time_per_player))
                .check(matches(isDisplayed()));
    }

    @Test
    public void shouldContainTextViewPointsToWin() {
        onView(withId(R.id.textView$_points_to_win))
                .check(matches(isDisplayed()));
    }

    @Test
    public void shouldContainTextViewForbiddenWords() {
        onView(withId(R.id.textView$_forbidder_words))
                .check(matches(isDisplayed()));
    }

    @Test
    public void shouldContainTextViewCorrectAnswer() {
        onView(withId(R.id.textView$_points_correct_answer))
                .check(matches(isDisplayed()));
    }

    @Test
    public void shouldContainTextViewIncorrectAnswer() {
        onView(withId(R.id.textView$_points_incorrect_answer))
                .check(matches(isDisplayed()));
    }

    @Test
    public void shouldContainTextViewTimePerPlayer() {
        onView(withId(R.id.textView$_time_per_player))
                .check(matches(isDisplayed()));
    }

    @Test
    public void shouldContainTextViewQuestionsLevel() {
        onView(withId(R.id.textView$_question_level))
                .check(matches(isDisplayed()));
    }

    @Test
    public void shouldContainTextViewEasyLevel() {
        onView(withId(R.id.level_easy))
                .check(matches(isDisplayed()));
    }

    @Test
    public void shouldContainTextViewAverageLevel() {
        onView(withId(R.id.level_average))
                .check(matches(isDisplayed()));
    }

    @Test
    public void shouldContainTextViewDifficultLevel() {
        onView(withId(R.id.level_difficult))
                .check(matches(isDisplayed()));
    }

    @Test
    public void shouldContainTextViewVeryDifficultLevel() {
        onView(withId(R.id.level_very_difficult))
                .check(matches(isDisplayed()));
    }

    @Test
    public void shouldContainTextViewSaveChanges() {
        onView(withId(R.id.button_save_changes))
                .check(matches(isDisplayed()));
    }

    /* ==========================================
    -----------------CHECK TEXT------------------
    ========================================== */

    @Test
    public void shouldCheckTextPointsToWin() {
        onView(withId(R.id.textView$_points_to_win))
                .check(matches(withText(POINTS_TO_WIN)));
    }

    @Test
    public void shouldCheckTextForbiddenWords() {
        onView(withId(R.id.textView$_forbidder_words))
                .check(matches(withText(FORBIDDEN_WORDS)));
    }

    @Test
    public void shouldCheckTextPointsCorrectAnswer() {
        onView(withId(R.id.textView$_points_correct_answer))
                .check(matches(withText(POINTS_CORRECT_ANSWER)));
    }

    @Test
    public void shouldCheckTextIncorrectAnswer() {
        onView(withId(R.id.textView$_points_incorrect_answer))
                .check(matches(withText(POINTS_INCORRECT_ANSWER)));
    }

    @Test
    public void shouldCheckTextTimePerPlayer() {
        onView(withId(R.id.textView$_time_per_player))
                .check(matches(withText(TIME_PER_PLAYER)));
    }

    @Test
    public void shouldCheckTextQuestionsLevel() {
        onView(withId(R.id.textView$_question_level))
                .check(matches(withText(QUESTIONS_LEVEL)));
    }

    @Test
    public void shouldCheckTextEasyLevel() {
        onView(withId(R.id.level_easy))
                .check(matches(withText(EASY_LEVEL)));
    }

    @Test
    public void shouldCheckTextAverageLevel() {
        onView(withId(R.id.level_average))
                .check(matches(withText(AVERAGE_LEVEL)));
    }

    @Test
    public void shouldCheckTextDifficultLevel() {
        onView(withId(R.id.level_difficult))
                .check(matches(withText(DIFFICULT_LEVEL)));
    }

    @Test
    public void shouldCheckTextVeryDifficultLevel() {
        onView(withId(R.id.level_very_difficult))
                .check(matches(withText(VERY_DIFFICULT_LEVEL)));
    }

    @Test
    public void shouldCheckTextSaveChanges() {
        onView(withId(R.id.button_save_changes))
                .check(matches(withText(SAVE_AND_QUIT)));
    }

    @Test
    public void shouldCheckTextSpinnerPointsToWin() {
        onView(withId(R.id.spinner$_points_to_win))
                .check(matches(withSpinnerText(containsString(DEFAULT_POINTS_TO_WIN))));
    }

    @Test
    public void shouldCheckTextSpinnerForbiddenWords() {
        onView(withId(R.id.spinner$_forbidden_words))
                .check(matches(withSpinnerText(containsString(DEFAULT_FORBIDDEN_WORDS))));
    }

    @Test
    public void shouldCheckTextSpinnerCorrectAnswer() {
        onView(withId(R.id.spinner$_points_correct_answer))
                .check(matches(withSpinnerText(containsString(DEFAULT_CORRECT_ANSWER))));
    }

    @Test
    public void shouldCheckTextSpinnerIncorrectAnswer() {
        onView(withId(R.id.spinner$_points_incorrect_answer))
                .check(matches(withSpinnerText(containsString(DEFAULT_INCORRECT_ANSWER))));
    }

    @Test
    public void shouldCheckTextSpinnerTimePerPlayer() {
        onView(withId(R.id.spinner$_time_per_player))
                .check(matches(withSpinnerText(containsString(DEFAULT_TIME_PER_PLAYER))));
    }

    /* ==========================================
    -----------CHANGE VALUE IN SPINNER-----------
    ========================================== */

    @Test
    public void checkChangeValueSpinnerPointsToWin() {
        onView(withId(R.id.spinner$_points_to_win))
                .perform(click());
        onData(allOf(is(instanceOf(String.class)), is("25")))
                .perform(click());
        onView(withId(R.id.spinner$_points_to_win))
                .check(matches(withSpinnerText(containsString("25"))));
    }

    @Test
    public void checkChangeValueSpinnerForbiddenWords() {
        onView(withId(R.id.spinner$_forbidden_words))
                .perform(click());
        onData(allOf(is(instanceOf(String.class)), is("3")))
                .perform(click());
        onView(withId(R.id.spinner$_forbidden_words))
                .check(matches(withSpinnerText(containsString("3"))));
    }

    @Test
    public void checkChangeValueSpinnerCorrectAnswer() {
        onView(withId(R.id.spinner$_points_correct_answer))
                .perform(click());
        onData(allOf(is(instanceOf(String.class)), is("2")))
                .perform(click());
        onView(withId(R.id.spinner$_points_correct_answer))
                .check(matches(withSpinnerText(containsString("2"))));
    }

    @Test
    public void checkChangeValueSpinnerIncorrectAnswer() {
        onView(withId(R.id.spinner$_points_incorrect_answer))
                .perform(click());
        onData(allOf(is(instanceOf(String.class)), is("0")))
                .perform(click());
        onView(withId(R.id.spinner$_points_incorrect_answer))
                .check(matches(withSpinnerText(containsString("0"))));
    }

    @Test
    public void checkChangeValueSpinnerTimePerPlayer() {
        onView(withId(R.id.spinner$_time_per_player))
                .perform(click());
        onData(allOf(is(instanceOf(String.class)), is("02:00")))
                .perform(click());
        onView(withId(R.id.spinner$_time_per_player))
                .check(matches(withSpinnerText(containsString("02:00"))));
    }

    /* ==========================================
    -----------------IS CHECKED------------------
    ========================================== */

    @Test
    public void checkEasyLevelNotChecked() {
        onView(withId(R.id.level_easy)).
                check(matches(isNotChecked()));
    }

    @Test
    public void checkAverageLevelNotChecked() {
        onView(withId(R.id.level_average)).
                check(matches(isNotChecked()));
    }

    @Test
    public void checkDifficultLevelNotChecked() {
        onView(withId(R.id.level_difficult)).
                check(matches(isNotChecked()));
    }

    @Test
    public void checkVeryDifficultLevelNotChecked() {
        onView(withId(R.id.level_very_difficult)).
                check(matches(isNotChecked()));
    }

}
