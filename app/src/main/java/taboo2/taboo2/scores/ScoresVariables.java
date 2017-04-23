package taboo2.taboo2.scores;


public class ScoresVariables {

    private static int redTeamScore = 0;
    private static int greenTeamScore = 0;

    /* ===============================================
    ----------------GETTERS AND SETTERS---------------
    ================================================ */

    static int getRedTeamScore() {
        return redTeamScore;
    }

    static int getGreenTeamScore() {
        return greenTeamScore;
    }

    static void setRedTeamScore(int redTeamScore) {
        ScoresVariables.redTeamScore += redTeamScore;
    }

    static void setGreenTeamScore(int greenTeamScore) {
        ScoresVariables.greenTeamScore += greenTeamScore;
    }
}
