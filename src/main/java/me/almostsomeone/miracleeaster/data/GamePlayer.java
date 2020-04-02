package me.almostsomeone.miracleeaster.data;

import me.almostsomeone.miracleeaster.Main;
import me.almostsomeone.miracleeaster.bukkit.FileManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.bukkit.Bukkit.getServer;

public class GamePlayer {

    public static void load() {
        FileManager.load(Main.getInstance(), "playerdata.yml");
        try {
            FileManager.load(Main.getInstance(), "playerdata.yml");
            getServer().getLogger().info("Succesfully loaded the playerdata");
        } catch (Exception ex) {
            getServer().getLogger().info("Failed to load the playerdata, disabling the plugin");
            Main.getInstance().getPluginLoader().disablePlugin(Main.getInstance());
            return;
        }

        FileConfiguration playerdata = FileManager.get("playerdata.yml");
        try {
            for (String s : playerdata.getConfigurationSection("players").getKeys(false)) {
                ArrayList<Block> blocks = new ArrayList<>();
                playerdata.getStringList("players." + s).forEach(string -> {//Voor alle locaties in de config
                    Location location = FileManager.deSerialize(string); //Maak een locatie van de tekst
                    if (location.getBlock() == null) return; //Return als er geen blok staat op die locatie
                    Block block = location.getBlock(); //Declareer "block"
                    if (block.getType() == null || !block.getType().equals(Material.SKULL))
                        return; //Return als het blok op die locatie geen skull is
                    blocks.add(block); //Voeg de skull toe in de lijst
                });
                players.add(new GamePlayer(UUID.fromString(s), blocks));
            }
        } catch (NullPointerException e) {
        }
    }

    public static void save() {
        FileConfiguration config = null;
        try {
            config = FileManager.get("playerdata.yml");
        }catch (Exception ex){
            getServer().getLogger().info("Failed to get the playerdata, creating a new one");
            load();
            save();
        }

        if(config == null) return;
        FileConfiguration finalConfig = config;
        players.forEach(player -> {
            List<String> blockLocs = new ArrayList<>();
            blockLocs.clear();
            player.blocksClaimed.forEach(block -> blockLocs.add(FileManager.serialize(block.getLocation())));
            finalConfig.set("players." + player.uuid, blockLocs);
        });
        FileManager.save(Main.getInstance(), "playerdata.yml");
    }

    public static GamePlayer getPlayer(UUID uuid){
        return players.stream().filter(player -> player.uuid.equals(uuid)).findFirst().get();
    }

    /*      GAMEPLAYER OBJECT       */

    public static List<GamePlayer> players = new ArrayList<>();

    public List<Block> blocksClaimed;
    public UUID uuid;

    public GamePlayer(UUID uuid, List<Block> blocksClaimed) {
        this.uuid = uuid;
        this.blocksClaimed = blocksClaimed;
    }

}
