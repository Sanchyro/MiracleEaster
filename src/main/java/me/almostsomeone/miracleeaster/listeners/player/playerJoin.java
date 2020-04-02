package me.almostsomeone.miracleeaster.listeners.player;

import me.almostsomeone.miracleeaster.data.GamePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

import java.util.ArrayList;

public class playerJoin implements Listener{

    @EventHandler
    public void onPreLogin(AsyncPlayerPreLoginEvent event){
        if (!GamePlayer.players.stream().anyMatch(u -> u.uuid.equals(event.getUniqueId())))
            GamePlayer.players.add(new GamePlayer(event.getUniqueId(), new ArrayList<>()));
    }
}
