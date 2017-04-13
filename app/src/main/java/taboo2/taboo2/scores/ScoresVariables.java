package taboo2.taboo2.scores;

/**
 * Created by tomaszkubit on 13/04/2017.
 */

public class ScoresVariables {

    private static int redTeamScore = 0;
    private static int blueTeamScore = 0;

    /* ===============================================
    ----------------GETTERS AND SETTERS---------------
    ================================================ */

    static int getRedTeamScore() {
        return redTeamScore;
    }

    static int getBlueTeamScore() {
        return blueTeamScore;
    }

    static void setRedTeamScore(int redTeamScore) {
        ScoresVariables.redTeamScore = redTeamScore;
    }

    static void setBlueTeamScore(int blueTeamScore) {
        ScoresVariables.blueTeamScore = blueTeamScore;
    }
}
