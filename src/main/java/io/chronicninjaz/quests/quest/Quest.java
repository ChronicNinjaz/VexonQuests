package io.chronicninjaz.quests.quest;

import io.chronicninjaz.quests.Quests;
import io.chronicninjaz.quests.enums.QuestType;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class Quest {
    private int id;
    private int questPoints;
    private String displayName;
    private ItemStack displayItem;
    private ArrayList<String> displayLore;
    private double duration;
    private QuestType questType;
    private Dialog dialog;

    /**
     * Villager data
     */
    private Villager villager;
    private Location spawn;

    public Quest(int id, int questPoints, String displayName, ArrayList<String> displayLore, double duration, Location spawn, QuestType questType, Dialog dialog){
        this.id = id;
        this.questPoints = questPoints;
        this.displayName = displayName;
        this.displayLore = displayLore;
        this.duration = duration;
        this.spawn = spawn;
        this.questType = questType;
        this.dialog = dialog;

        this.villager = (Villager) spawn.getWorld().spawnEntity(spawn, EntityType.VILLAGER);
        this.villager.setAI(false);
        this.villager.setCollidable(false);
        this.villager.setAdult();
        this.villager.setCanPickupItems(false);
        this.villager.setGlowing(true);
        this.villager.setCustomName(ChatColor.translateAlternateColorCodes('&', displayName));
        this.villager.setRemoveWhenFarAway(false);

        Quests.getInstance().getQuestManager().quests.add(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestPoints() {
        return questPoints;
    }

    public void setQuestPoints(int questPoints) {
        this.questPoints = questPoints;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public ItemStack getDisplayItem() {
        return displayItem;
    }

    public void setDisplayItem(ItemStack displayItem) {
        this.displayItem = displayItem;
    }

    public ArrayList<String> getDisplayLore() {
        return displayLore;
    }

    public void setDisplayLore(ArrayList<String> displayLore) {
        this.displayLore = displayLore;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public Villager getVillager() {
        return villager;
    }

    public void setVillager(Villager villager) {
        this.villager = villager;
    }

    public Location getSpawn() {
        return spawn;
    }

    public void setSpawn(Location spawn) {
        this.spawn = spawn;
    }

    public Dialog getDialog() {
        return dialog;
    }

    public void setDialog(Dialog dialog){
        this.dialog = dialog;
    }
}
