package de.jumichel.jail;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class JailData {

    public static void setPlayerinJail(String uuid) {

        String query = "INSERT INTO jailedplayer (jail_uuid, jail_jailed)VALUES('" + uuid + "', 1);";
        try {
            PreparedStatement st = Main.sqlData.getConnection().prepareStatement(query);
            st.execute();
        } catch (SQLException e) {
            System.out.println(MySql.PREFIX + "Fehler bei jailen eines Spielers in der Datenbank.");
            e.printStackTrace();
        }
    }

    public static void setPlayerOutJail(String uuid) {
        try {
            PreparedStatement st = Main.sqlData.getConnection().prepareStatement("DELETE FROM jailedplayer WHERE jail_uuid = '" + uuid + "';");
            st.execute();
        } catch (SQLException e) {
            System.out.println(MySql.PREFIX + "Fehler bei entjailen eines Spielers in der Datenbank.");
            e.printStackTrace();
        }
    }

    public ArrayList<String> getJailedPlayer() {
        ArrayList<String> jailedPlayers = new ArrayList<String>();
        try {
            PreparedStatement st = Main.sqlData.getConnection().prepareStatement("SELECT jail_uuid FROM jailedplayer WHERE jail_jailed = 1");
            ResultSet result = st.executeQuery();
            while (result.next()) {
                jailedPlayers.add(result.getString("jail_uuid"));
            }
            return jailedPlayers;
        } catch (SQLException e) {
            System.out.println(MySql.PREFIX + "Fehler bei Abfrage, der eingesperrten Spieler.");
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isInDatabase(String uuid) {
        try {
            PreparedStatement st = Main.sqlData.getConnection().prepareStatement("SELECT * FROM jailedplayer WHERE jail_uuid = '" + uuid + "';");
            ResultSet result = st.executeQuery();
            while (result.next()) {
                if (result.getString("jail_uuid").isEmpty()) {
                    return false;
                } else {
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println(MySql.PREFIX + "Fehler bei Abfrage, ob Spieler in Datenbank schon vorhanden.");
            e.printStackTrace();
            return false;
        }
        return false;
    }
}
