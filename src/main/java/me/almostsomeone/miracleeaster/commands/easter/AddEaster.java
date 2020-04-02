package me.almostsomeone.miracleeaster.commands.easter;

import me.almostsomeone.miracleeaster.data.Config;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Set;

import static me.almostsomeone.miracleeaster.bukkit.Text.color;

public class AddEaster implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) return true; //Als de sender geen player is, return;
        Player player = ((Player)sender);
        if (!player.getTargetBlock((Set)null, 5).getType().equals(Material.SKULL)) {
            sender.sendMessage(color("&cYou have to be looking at a skull."));
            return true;
        }

        if (Config.getEggs().contains(player.getTargetBlock((Set)null, 5))) {
            sender.sendMessage(color("&cThis egg is already added."));
            return true;
        }

        Config.addEgg(player.getTargetBlock((Set)null, 5));
        player.sendMessage(color("&aYou've successfully added an egg!"));
        return true;
    }
}
