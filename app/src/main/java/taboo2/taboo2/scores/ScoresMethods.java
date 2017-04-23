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

    public int getBlueTeamScore() {

        return ScoresVariables.getBlueTeamScore();
    }

    public int getRedTeamScore() {

        return ScoresVariables.getRedTeamScore();
    }

}
