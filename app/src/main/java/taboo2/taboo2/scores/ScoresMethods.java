package taboo2.taboo2.scores;


public class ScoresMethods {

    /* ===============================================
    ----------------------METHODS---------------------
    =============================================== */

    public void addPointToBlueTeamScore(int points) {

        ScoresVariables.setBlueTeamScore(ScoresVariables.getBlueTeamScore() + points);
    }

    public void addPointToRedTeamScore(int points) {

        ScoresVariables.setRedTeamScore(ScoresVariables.getRedTeamScore() + points);
    }

    public void getBlueTeamScore() {

        ScoresVariables.getBlueTeamScore();
    }

    public void getRedTeamScore() {

        ScoresVariables.getRedTeamScore();
    }

}
