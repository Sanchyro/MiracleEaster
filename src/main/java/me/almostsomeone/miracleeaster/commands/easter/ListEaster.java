package me.almostsomeone.miracleeaster.commands.easter;

import me.almostsomeone.miracleeaster.bukkit.ChatCenterUtil;
import me.almostsomeone.miracleeaster.data.Config;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import static me.almostsomeone.miracleeaster.bukkit.Text.color;

public class ListEaster implements CommandExecutor {

    Integer pos = 0;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player)sender;
        ChatCenterUtil.sendCenteredMessage(player, "&5&m-----------------------------------------------------");
        ChatCenterUtil.sendCenteredMessage(player, "&fEggs");
        ChatCenterUtil.sendCenteredMessage(player, "&7A list of all eggs.");
        ChatCenterUtil.sendCenteredMessage(player, "&7Click on a location to teleport.");
        ChatCenterUtil.sendCenteredMessage(player, "");
        Config.getEggs().forEach(egg -> {
            pos++;

            IChatBaseComponent comp = IChatBaseComponent.ChatSerializer
                    .a(color("{text: \"&5" + pos + ". &f" + egg.getWorld().getName() + ", " + (Math.round(egg.getX() * 100.00) / 100.00) + ", " + (Math.round(egg.getY() * 100.00) / 100.00) + ", " + (Math.round(egg.getZ() * 100.00) / 100.00) + "\", clickEvent: {\"action\": \"run_command\" , value: \"/minecraft:tp " + player.getName() + " " + egg.getX() + " " + egg.getY() + " " + egg.getZ() + "\"}}"));
            PacketPlayOutChat ppoc = new PacketPlayOutChat(comp);
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(ppoc);
        });

        if(Config.getEggs().isEmpty())
            player.sendMessage(color("&cNo eggs added yet"));
        ChatCenterUtil.sendCenteredMessage(player, "&5&m-----------------------------------------------------");
        pos=0;
        return true;
    }
}
