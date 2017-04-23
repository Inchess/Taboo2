package taboo2.taboo2.scores;


public class ScoresMethods {

    /* ===============================================
    ----------------------METHODS---------------------
    =============================================== */

    public void addPointTogreenTeamScore(int points) {

        ScoresVariables.setgreenTeamScore(ScoresVariables.getgreenTeamScore() + points);
    }

    public void addPointToRedTeamScore(int points) {

        ScoresVariables.setRedTeamScore(ScoresVariables.getRedTeamScore() + points);
    }

    public int getgreenTeamScore() {

        return ScoresVariables.getgreenTeamScore();
    }

    public int getRedTeamScore() {

        return ScoresVariables.getRedTeamScore();
    }

}
