package io.chronicninjaz.quests.manager;

import io.chronicninjaz.quests.Quests;
import io.chronicninjaz.quests.enums.QuestType;
import io.chronicninjaz.quests.gui.QuestInformation;
import io.chronicninjaz.quests.player.QuestPlayer;
import io.chronicninjaz.quests.quest.Dialog;
import io.chronicninjaz.quests.quest.Quest;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.world.ChunkUnloadEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.io.File;
import java.util.ArrayList;

public class QuestManager implements Listener{
    public ArrayList<QuestPlayer> players;
    public ArrayList<Quest> quests;

    public QuestManager(){
        Bukkit.getPluginManager().registerEvents(this, Quests.getInstance());

        this.players = new ArrayList<>();
        this.quests = new ArrayList<>();
    }

    public QuestPlayer getQuestPlayer(Player player){
        return players.stream().filter(questPlayer -> questPlayer.getPlayer().getUniqueId() == player.getUniqueId()).findFirst().orElse(null);
    }

    @EventHandler
    public void onNPCClick(EntityDamageByEntityEvent event){
        if(event.getEntity() instanceof Villager){
            Quest quest = quests.stream().filter(q -> q.getVillager().getUniqueId() == event.getEntity().getUniqueId()).findFirst().orElse(null);
            if(quest != null){
                event.setCancelled(true);
                if(event.getDamager() instanceof Player){

                    // new QuestInformation(getQuestPlayer((Player) event.getDamager()));

                    // start dialog
                }
            }
        }
    }

    @EventHandler
    public void noNPCInventory(PlayerInteractEntityEvent event) {
        if (event.getRightClicked() instanceof Villager)
                if(event.getHand() == EquipmentSlot.HAND)
                    if(quests.stream().filter(q -> q.getVillager().getUniqueId() == event.getRightClicked().getUniqueId()).findFirst().orElse(null) != null)
                        event.setCancelled(true);
    }

    @EventHandler
    public void chunkUnloadEvent(ChunkUnloadEvent event){
        quests.stream().forEach(quest -> {
            if(quest.getVillager().getLocation().getChunk() == event.getChunk())
                event.setCancelled(true);
        });
    }

    public void loadQuestPlayer(Player player){
        File file = new File(Quests.getInstance().getDataFolder() + File.separator + "players" + File.separator + player.getUniqueId() + ".yml");

        if(!file.exists()){
            try {
                file.createNewFile();
                YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
                config.set("questPoints", 0);
                config.set("questStarted", false);
                config.set("duration", 0);
                config.set("questType", null);
                config.save(file);
            }catch (Exception e){e.printStackTrace();}
        }

        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        new QuestPlayer(player, config.getInt("questPoints"), config.getBoolean("questStarted"), config.getDouble("duration"), QuestType.getFromName(config.getString("questType")), new Dialog(false, null, 1));
    }

    public void unloadQuestPlayer(Player player){
        QuestPlayer qp = players.stream().filter(questPlayer -> questPlayer.getPlayer().getUniqueId() == player.getUniqueId()).findFirst().orElse(null);
        if(qp != null){
            File file = new File(Quests.getInstance().getDataFolder() + File.separator + "players" + File.separator + player.getUniqueId() + ".yml");
            if(file.exists()) {
                try {
                    YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
                    config.set("questPoints", qp.getQuestPoints());
                    config.set("questStarted", qp.hasQuestStarted());
                    config.set("duration", qp.getDuration());
                    config.set("questType", qp.getQuestType());
                    config.save(file);
                }catch (Exception e){e.printStackTrace();}
            }
            players.remove(qp);
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        loadQuestPlayer(event.getPlayer());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        unloadQuestPlayer(event.getPlayer());
    }
}
