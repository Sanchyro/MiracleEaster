package me.almostsomeone.miracleeaster.listeners.player;

import me.almostsomeone.miracleeaster.data.Config;
import me.almostsomeone.miracleeaster.data.GamePlayer;
import net.miraclepvp.kitpvp.data.Data;
import net.miraclepvp.kitpvp.data.user.User;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import static me.almostsomeone.miracleeaster.bukkit.Text.color;

public class playerInteract implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        if(event.getClickedBlock() == null) return;
        if(event.getClickedBlock().getType() == null) return;
        if(!event.getClickedBlock().getType().equals(Material.SKULL)) return;

        //Klikt op blok
        if(!Config.getEggs().contains(event.getClickedBlock())) return;



        //Klikt op een ei uit de list
        User user = Data.getUser(event.getPlayer()); //MiraclePvP User opgehaald
        GamePlayer gamePlayer = GamePlayer.getPlayer(user.getUuid()); //Easter User opgehaald

        if(gamePlayer.blocksClaimed.contains(event.getClickedBlock())){ //Player heeft hem al geclaimed
            event.getPlayer().sendMessage(color("&cYou've already claimed this egg. " + gamePlayer.blocksClaimed.size() + "/" + Config.getEggs().size()));
            return;
        }

        gamePlayer.blocksClaimed.add(event.getClickedBlock());
        event.getPlayer().sendMessage(color("&aCongratulations! You now have " + gamePlayer.blocksClaimed.size() + "/" + Config.getEggs().size() + " eggs."));
        event.getPlayer().sendMessage(color("&6+20 Experience"));
        user.setExperience(user.getExperience()+20, false);

        if(gamePlayer.blocksClaimed.size()>=Config.getEggs().size()){
            event.getPlayer().sendMessage(color("&aCongratulations, you claimed all eggs and received 2500 coins!"));
            event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.LEVEL_UP, 2F, 2F);
            user.setCoins(user.getCoins()+2500, false);
        }
    }
}

