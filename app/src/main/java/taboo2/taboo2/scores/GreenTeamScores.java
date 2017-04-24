package taboo2.taboo2.scores;


public class GreenTeamScores implements IScoresMethods {

    @Override
    public void addPointToTeamScore(int points) {
        ScoresVariables.addGreenTeamScore(points);
    }

    @Override
    public int getTeamScore() {
        return ScoresVariables.getGreenTeamScore();
    }

    @Override
    public void setTeamScore(int points) {
        ScoresVariables.setGreenTeamScore(points);
    }
}
