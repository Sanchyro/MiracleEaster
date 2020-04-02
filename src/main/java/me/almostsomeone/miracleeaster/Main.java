package me.almostsomeone.miracleeaster;

import me.almostsomeone.miracleeaster.commands.EasterCMD;
import me.almostsomeone.miracleeaster.data.Config;
import me.almostsomeone.miracleeaster.data.GamePlayer;
import me.almostsomeone.miracleeaster.listeners.player.playerInteract;
import me.almostsomeone.miracleeaster.listeners.player.playerJoin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        Config.load();
        GamePlayer.load();

        getCommand("easter").setExecutor(new EasterCMD());
        getServer().getPluginManager().registerEvents(new playerInteract(), this);
        getServer().getPluginManager().registerEvents(new playerJoin(), this);
    }

    @Override
    public void onDisable() {
        Config.save();
        GamePlayer.save();
    }

    public static Main getInstance(){
        return Main.getPlugin(Main.class);
    }
}
