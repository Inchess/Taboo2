package taboo2.taboo2.global;

import taboo2.taboo2.scores.GreenTeamScores;
import taboo2.taboo2.scores.IScoresMethods;
import taboo2.taboo2.scores.RedTeamScores;

public class Global {

    private static IScoresMethods currentPlayingTeam = new GreenTeamScores();
    private static IScoresMethods notPlayingTeam = new RedTeamScores();
    private String redTeamName = "Czerwoni";
    private String greenTeamName = "Zieloni";
    private int pointsToWin;
    private int numberOfForbiddenWords;
    private int points_correctAnswer;
    private int points_incorrectAnswer;

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
            return greenTeamName;
        } else if (currentPlayingTeam instanceof RedTeamScores) {
            return redTeamName;
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
}
