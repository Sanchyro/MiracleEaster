package me.almostsomeone.miracleeaster.commands;

import me.almostsomeone.miracleeaster.commands.easter.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.almostsomeone.miracleeaster.bukkit.Text.color;

public class EasterCMD implements CommandExecutor {

    /*
        /easter help
        /easter list
        /easter add
        /easter remove
        /easter delete
     */

    private HelpEaster helpEaster = new HelpEaster();
    private ListEaster listEaster = new ListEaster();
    private AddEaster addEaster = new AddEaster();
    private RemoveEaster removeEaster = new RemoveEaster();
    private DeleteEaster deleteEaster = new DeleteEaster();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender.hasPermission("miracleeaster.command"))){
            sender.sendMessage(color("&cYou don't have the required permissions."));
            return true; //Return de code. Geen permissies
        }
        if(args.length == 0){
            sender.sendMessage(color("&cPlease use /easter help for help."));
            return true;
        }
        switch (args[0].toLowerCase()){
            case "help":
                helpEaster.onCommand(sender, command, label, args);
                break;
            case "list":
                listEaster.onCommand(sender, command, label, args);
                break;
            case "add":
                addEaster.onCommand(sender, command, label, args);
                break;
            case "remove":
                removeEaster.onCommand(sender, command, label, args);
                break;
            case "delete":
                deleteEaster.onCommand(sender, command, label, args);
                break;
            default:
                sender.sendMessage(color("&cPlease use /easter help for help."));
                break;
        }
        return true;
    }
}
