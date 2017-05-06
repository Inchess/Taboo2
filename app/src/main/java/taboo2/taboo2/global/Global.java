package taboo2.taboo2.global;

import taboo2.taboo2.scores.GreenTeamScores;
import taboo2.taboo2.scores.IScoresMethods;
import taboo2.taboo2.scores.RedTeamScores;

public class Global {

    private static IScoresMethods currentPlayingTeam = new GreenTeamScores();
    private static IScoresMethods notPlayingTeam = new RedTeamScores();
    private static int pointsToWin = 30;
    private static int numberOfForbiddenWords = 5;
    private static int points_correctAnswer = 1;
    private static int points_incorrectAnswer = -1;
    private static String timePerPlayer = "01:00";
    private static boolean easyLevelChecked = false;
    private static boolean averageLevelChecked = true;
    private static boolean difficultLevelChecked = false;
    private static boolean veryDifficultLevelChecked = false;
    public static final String NAME_RED_TEAM = "Czerwoni";
    public static final String NAME_GREEN_TEAM = "Zieloni";
    public static final String STRING_START = "Rozpocznij grę";
    public static final String STRING_RULES = "Zasady gry";
    public static final String STRING_SETTINGS = "Ustawienia";
    public static final String STRING_ABOUT = "O autorze";

    /* ===============================================
    ------------------DEFAULT VALUES------------------
    ================================================ */

    public static final int DEFAULT_POINTS_TO_WIN = 30;
    public static final int DEFAULT_NUMBER_OF_FORBIDDEN_WORDS = 5;
    public static final int DEFAULT_POINTS_CORRECT_ANSWER = 1;
    public static final int DEFAULT_POINTS_INCORRECT_ANSWER = -1;
    public static final String DEFAULT_TIME_PER_PLAYER = "01:00";
    public static final boolean DEFAULT_EASY_LEVEL_CHECKED = false;
    public static final boolean DEFAULT_AVERAGE_LEVEL_CHECKED = true;
    public static final boolean DEFAULT_DIFFICULT_LEVEL_CHECKED = false;
    public static final boolean DEFAULT_VERY_DIFFICULT_LEVEL_CHECKED = false;

    /* ===============================================
    ----------------------METHODS---------------------
    ================================================ */

    public static void changeTeam() {
        IScoresMethods spareVariable = Global.getCurrentPlayingTeam();
        Global.setCurrentPlayingTeam(Global.getNotPlayingTeam());
        Global.setNotPlayingTeam(spareVariable);
    }

    /* ===============================================
    ----------------GETTERS AND SETTERS---------------
    ================================================ */

    public static IScoresMethods getCurrentPlayingTeam() {
        return currentPlayingTeam;
    }

    public static void setCurrentPlayingTeam(IScoresMethods currentPlayingTeam) {
        Global.currentPlayingTeam = currentPlayingTeam;
    }

    public static IScoresMethods getNotPlayingTeam() {
        return notPlayingTeam;
    }

    public static void setNotPlayingTeam(IScoresMethods notPlayingTeam) {
        Global.notPlayingTeam = notPlayingTeam;
    }

    public String getCurrentTeamText() {
        if (currentPlayingTeam instanceof GreenTeamScores) {
            return NAME_GREEN_TEAM;
        } else if (currentPlayingTeam instanceof RedTeamScores) {
            return NAME_RED_TEAM;
        } else {
            throw new IllegalArgumentException("Red team and green team are missing");
        }
    }

    public int getPointsToWin() {
        return pointsToWin;
    }

    public void setPointsToWin(int pointsToWin) {
        this.pointsToWin = pointsToWin;
    }

    public int getNumberOfForbiddenWords() {
        return numberOfForbiddenWords;
    }

    public void setNumberOfForbiddenWords(int numberOfForbiddenWords) {
        this.numberOfForbiddenWords = numberOfForbiddenWords;
    }

    public int getPoints_correctAnswer() {
        return points_correctAnswer;
    }

    public void setPoints_correctAnswer(int points_correctAnswer) {
        this.points_correctAnswer = points_correctAnswer;
    }

    public int getPoints_incorrectAnswer() {
        return points_incorrectAnswer;
    }

    public void setPoints_incorrectAnswer(int points_incorrectAnswer) {
        this.points_incorrectAnswer = points_incorrectAnswer;
    }

    public String getTimePerPlayer() {
        return timePerPlayer;
    }

    public void setTimePerPlayer(String timePerPlayer) {
        Global.timePerPlayer = timePerPlayer;
    }

    public static boolean isEasyLevelChecked() {
        return easyLevelChecked;
    }

    public static void setEasyLevelChecked(boolean easyLevelChecked) {
        Global.easyLevelChecked = easyLevelChecked;
    }

    public static boolean isAverageLevelChecked() {
        return averageLevelChecked;
    }

    public static void setAverageLevelChecked(boolean averageLevelChecked) {
        Global.averageLevelChecked = averageLevelChecked;
    }

    public static boolean isDifficultLevelChecked() {
        return difficultLevelChecked;
    }

    public static void setDifficultLevelChecked(boolean difficultLevelChecked) {
        Global.difficultLevelChecked = difficultLevelChecked;
    }

    public static boolean isVeryDifficultLevelChecked() {
        return veryDifficultLevelChecked;
    }

    public static void setVeryDifficultLevelChecked(boolean veryDifficultLevelChecked) {
        Global.veryDifficultLevelChecked = veryDifficultLevelChecked;
    }
}
