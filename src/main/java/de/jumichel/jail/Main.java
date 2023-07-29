package de.jumichel.jail;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    public static MySql sqlData;

    @Override
    public void onEnable() {
        System.out.println("Jumichels Jail System is starting up.");
        sqlData = new MySql("user", "user", "jailedplayer", "localhost", 3306);

        this.getCommand("jail").setExecutor(new CommandJail());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
