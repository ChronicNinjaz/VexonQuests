package io.chronicninjaz.quests.quest.tasks;

import io.chronicninjaz.quests.enums.QuestType;
import io.chronicninjaz.quests.quest.Dialog;
import io.chronicninjaz.quests.quest.Quest;
import io.chronicninjaz.quests.utils.ArrayUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.ArrayList;

public class FoodQuest extends Quest {

    public FoodQuest(){
        super(1, 2, "&7[&cQuest&7] &6The hungry villager", new ArrayUtil().getArray("&7[&cQuest&7] &aGather &b2 steak&a.", "&aReturn to me within &c1 hour.", "&aUpon completion &c2 points &awill be gifted to you!"), 60, new Location(Bukkit.getWorld("world"), 51.443, 72, 260.588), QuestType.GATHER_STEAK, new Dialog(false, new ArrayList<>(), 0));

        ArrayList<String> dialog = new ArrayList<>();

        dialog.add("<action> {ID:1;Text:[Accept];Message:You-have-accepted-the-quest!;Hover:this-is-message-one;/ID:2;Text:[Deny];Message:You-have-not-accepted-the-quest!;Hover:this-is-message-two;} </action> this is the message {1} and this is 2 {2}");
        dialog.add("Welcome traveler!");
        dialog.add("Would you like to start a new quest?");
        dialog.add("<action></action> Would you like to start a new quest? [Yes] [No]");
        dialog.add("Sure! Here's what to do...");

        setDialog(new Dialog(dialog));
    }
}
