package me.almostsomeone.miracleeaster.data;

import me.almostsomeone.miracleeaster.Main;
import me.almostsomeone.miracleeaster.bukkit.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.bukkit.Bukkit.getServer;

public class Config {

    private static List<Block> eggs = new ArrayList<>();

    public static void load(){
        getServer().getLogger().info("Loading the config...");
        try {
            FileManager.load(Main.getInstance(), "config.yml");
            getServer().getLogger().info("Succesfully loaded the config");
        }catch (Exception ex){
            getServer().getLogger().info("Failed to load the config, disabling the plugin");
            Main.getInstance().getPluginLoader().disablePlugin(Main.getInstance());
            return;
        }

        FileConfiguration config = FileManager.get("config.yml");

        config.getStringList("eggs").forEach(string -> { //Voor alle locaties in de config
            Location location = FileManager.deSerialize(string); //Maak een locatie van de tekst
            if(location.getBlock() == null) return; //Return als er geen blok staat op die locatie
            Block block = location.getBlock(); //Declareer "block"
            if(block.getType() == null || !block.getType().equals(Material.SKULL)) return; //Return als het blok op die locatie geen skull is
            eggs.add(block); //Voeg de skull toe in de lijst
        });
    }

    public static void save(){
        FileConfiguration config = null;
        try {
            config = FileManager.get("config.yml");
        }catch (Exception ex){
            getServer().getLogger().info("Failed to get the config, creating a new one");
            load();
            save();
        }

        if(config == null) return; //Return de code als de config niet opgehaald kan worden

        ArrayList<String> stringlocs = new ArrayList<>();
        stringlocs.clear();
        eggs.forEach(block -> stringlocs.add(FileManager.serialize(block.getLocation())));
        FileManager.set("config.yml", "eggs", stringlocs);
        FileManager.save(Main.getInstance(), "config.yml");
    }

    public static void addEgg(Block block){
        eggs.add(block);
    }

    public static void removeEgg(Block block){
        Integer nmbr = eggs.lastIndexOf(block);
        removeEgg(nmbr);
    }

    public static void removeEgg(Integer number){
        eggs.remove(number.intValue());
    }

    public static List<Block> getEggs() {
        return eggs;
    }
}
