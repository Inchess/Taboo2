package taboo2.taboo2.scores;


public class RedTeamScores implements IScoresMethods {

    @Override
    public void addPointToTeamScore(int points) {
        ScoresVariables.setRedTeamScore(points);
    }

    @Override
    public int getTeamScore() {
        return ScoresVariables.getRedTeamScore();
    }
}
