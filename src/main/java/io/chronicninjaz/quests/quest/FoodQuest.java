package io.chronicninjaz.quests.quest;

import io.chronicninjaz.quests.enums.QuestType;
import io.chronicninjaz.quests.utils.ArrayUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class FoodQuest extends Quest {

    public FoodQuest(){
        super(1, 2, "&7[&cQuest&7] &6The hungry villager", new ArrayUtil().getArray("&7[&cQuest&7] &aGather &b2 steak&a.", "&aReturn to me within &c1 hour.", "&aUpon completion &c2 points &awill be gifted to you!"), 60, new Location(Bukkit.getWorld("world"), 51.443, 72, 260.588), QuestType.GATHER_STEAK);


    }
}
