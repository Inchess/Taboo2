package taboo2.taboo2.global;


import taboo2.taboo2.scores.GreenTeamScores;
import taboo2.taboo2.scores.IScoresMethods;
import taboo2.taboo2.scores.RedTeamScores;

public class Global {

    static IScoresMethods currentPlayingTeam = new RedTeamScores();
    static IScoresMethods notPlayingTeam = new GreenTeamScores();

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
}
