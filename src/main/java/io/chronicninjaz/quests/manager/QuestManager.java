package io.chronicninjaz.quests.manager;

import io.chronicninjaz.quests.Quests;
import io.chronicninjaz.quests.enums.QuestType;
import io.chronicninjaz.quests.player.QuestPlayer;
import io.chronicninjaz.quests.quest.Quest;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

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


            }
        }


        /**
        if(event.getRightClicked() instanceof Villager){

            event.getPlayer().sendMessage("clicked villager");
            event.setCancelled(true);
            Quest quest = quests.stream().filter(q -> q.getVillager().getUniqueId() == event.getRightClicked().getUniqueId()).findFirst().orElse(null);

            if(quest != null){

                QuestPlayer questPlayer = Quests.getInstance().getQuestManager().getQuestPlayer(event.getPlayer());

                if(questPlayer != null){

                }


                /**
                 *
                 * check if player has already started the quest
                 *
                 * check if player has the ability to do more then one quest at the same time
                 *
                 * check to see if the player has the right amount of stuff the complete the quest
                 *
                 */
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
        new QuestPlayer(player, config.getInt("questPoints"), config.getBoolean("questStarted"), config.getDouble("duration"), QuestType.getFromName(config.getString("questType")));
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
