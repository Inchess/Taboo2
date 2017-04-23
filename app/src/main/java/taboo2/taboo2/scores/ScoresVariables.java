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

    static int getgreenTeamScore() {
        return greenTeamScore;
    }

    static void setRedTeamScore(int redTeamScore) {
        ScoresVariables.redTeamScore = redTeamScore;
    }

    static void setgreenTeamScore(int greenTeamScore) {
        ScoresVariables.greenTeamScore = greenTeamScore;
    }
}
