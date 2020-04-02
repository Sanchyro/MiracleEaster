package me.almostsomeone.miracleeaster.commands.easter;

import me.almostsomeone.miracleeaster.data.Config;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Set;

import static me.almostsomeone.miracleeaster.bukkit.Text.color;

public class RemoveEaster implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = ((Player) sender);

        if (args.length == 1) { //Als er geen ID meegegeven is
            if(!(sender instanceof Player)) return true; //Als de sender geen player is, return;
            Block block = player.getTargetBlock((Set) null, 5);
            if (!block.getType().equals(Material.SKULL)) {
                sender.sendMessage(color("&cYou have to be looking at a skull."));
                return true;
            }

            if (!Config.getEggs().contains(block)) {
                sender.sendMessage(color("&cThis egg is not in the list."));
                return true;
            }

            Config.removeEgg(block);
            player.sendMessage(color("&aYou've successfully removed an egg!"));
            return true;
        } else {
            try {
                Integer id = Integer.parseInt(args[1])-1;

                if (Config.getEggs().size() < id) {
                    sender.sendMessage(color("&cThis egg is not in the list."));
                    return true;
                }

                Config.removeEgg(id);
                player.sendMessage(color("&aYou've successfully removed an egg!"));
                return true;
            } catch (Exception ex) {
                if(args[1].equalsIgnoreCase("all")){
                    Config.getEggs().clear();
                    player.sendMessage(color("&aYou successfully removed all the eggs"));
                    return true;
                }
                sender.sendMessage(color("&cThis egg is not in the list."));
                return true;
            }
        }
    }
}
