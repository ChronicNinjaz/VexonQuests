package io.chronicninjaz.quests;

import io.chronicninjaz.quests.manager.QuestManager;
import io.chronicninjaz.quests.quest.FoodQuest;
import io.chronicninjaz.quests.quest.Quest;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Quests extends JavaPlugin {

    private static Quests instance;
    private QuestManager questManager;

    public void onEnable(){
        instance = this;

        getDataFolder().mkdir();

        File file = new File(getDataFolder() + File.separator + "players");
        if(!file.exists())
        {
            file.mkdir();
        }

        this.questManager = new QuestManager();

        new FoodQuest();

        Bukkit.getOnlinePlayers().stream().forEach(player -> questManager.loadQuestPlayer(player));
    }

    public void onDisable(){
        Bukkit.getOnlinePlayers().stream().forEach(player -> questManager.unloadQuestPlayer(player));
        questManager.quests.stream().forEach(quest -> quest.getVillager().remove());

        instance = null;
    }

    public static Quests getInstance() {
        return instance;
    }

    public QuestManager getQuestManager() {
        return questManager;
    }
}
