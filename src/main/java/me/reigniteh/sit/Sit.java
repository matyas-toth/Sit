package me.reigniteh.sit;

import me.reigniteh.sit.commands.SitCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class Sit extends JavaPlugin {

    public static Sit instance;

    @Override
    public void onEnable() {

        instance = this;

        this.getCommand("sit").setExecutor(new SitCommand());
        this.getServer().getPluginManager().registerEvents(new SitCommand(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Sit getInstance() {

        return instance;

    }

}
