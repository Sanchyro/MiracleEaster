package me.almostsomeone.miracleeaster.commands.easter;

import me.almostsomeone.miracleeaster.bukkit.ChatCenterUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HelpEaster implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player)sender;
        ChatCenterUtil.sendCenteredMessage(player, "&5&m-----------------------------------------------------");
        ChatCenterUtil.sendCenteredMessage(player, "&fEaster");
        ChatCenterUtil.sendCenteredMessage(player, "&7Event Plugin.");
        ChatCenterUtil.sendCenteredMessage(player, "");
        ChatCenterUtil.sendCenteredMessage(player, "&f/easter list");
        ChatCenterUtil.sendCenteredMessage(player, "&f/easter add");
        ChatCenterUtil.sendCenteredMessage(player, "&f/easter remove (opt. ID)");
        ChatCenterUtil.sendCenteredMessage(player, "&f/easter delete (opt. ID)");
        ChatCenterUtil.sendCenteredMessage(player, "&5&m-----------------------------------------------------");
        return true;
    }
}
