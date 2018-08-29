package io.chronicninjaz.quests.utils;

import org.bukkit.ChatColor;

import java.util.ArrayList;

public class ArrayUtil {

    public static ArrayList<String> getArray(String... lores){
        ArrayList<String> returning = new ArrayList<>();

        for(String lore : lores)
            returning.add(ChatColor.translateAlternateColorCodes('&', lore));

        return returning;
    }
}
