package de.jumichel.jail;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    public static MySql sqlData;

    @Override
    public void onEnable() {
        System.out.println(MySql.PREFIX + "System startet");
        sqlData = new MySql("user", "user", "jaildatabase", "localhost", 3306);

        this.getCommand("jail").setExecutor(new CommandJail());
        getServer().getPluginManager().registerEvents(new JoinEventListener(), this);

        System.out.println(MySql.PREFIX + "System ist gestartet.");

    }

    @Override
    public void onDisable() {
        System.out.println(MySql.PREFIX + "System wird gestoppt.");
    }
}
