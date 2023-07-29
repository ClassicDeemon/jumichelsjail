package de.jumichel.jail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySql {

    public static final String PREFIX = "[JumichelJailSystem] ";

    private Connection connection;

    private String username;
    private String password;
    private String database;
    private String host;
    private int port;

    public MySql(String username, String password, String database, String host, int port) {
        this.username = username;
        this.password = password;
        this.database = database;
        this.host = host;
        this.port = port;

        if (connect()) {
            System.out.println(PREFIX +"Verbindung zur Datenbank hergestellt.");
        }
    }

    public boolean connect () {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
            return true;
        } catch (SQLException e) {
            System.out.println(PREFIX + "Fehler bei Datenbankverbindung.");
            e.printStackTrace();
            return false;
        }
    }

    public boolean hasConnection() {
        if(this.connection != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean disconnect () {
        if(hasConnection()) {
            try {
                this.connection.close();
                System.out.println(PREFIX + "Datenbankverbindung getrennt.");
                return true;
            } catch (SQLException e) {
                System.out.println(PREFIX + "Fehler bei Trennung der Datenbankverbindung.");
                e.printStackTrace();
                return false;
            }
        } else {
            System.out.println(PREFIX + "Es existiert keine Datenbankverbindung zum Schließen.");
            return true;
        }
    }

    public Connection getConnection() {
        return this.connection;
    }

}
