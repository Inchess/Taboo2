package taboo2.taboo2.global;


import android.view.View;

import taboo2.taboo2.scores.GreenTeamScores;
import taboo2.taboo2.scores.IScoresMethods;
import taboo2.taboo2.scores.RedTeamScores;

public class Global {

    static IScoresMethods currentPlayingTeam = new GreenTeamScores();
    static IScoresMethods notPlayingTeam = new RedTeamScores();
    private String redTeamName = "Czerwoni";
    private String greenTeamName = "Zieloni";

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
}
