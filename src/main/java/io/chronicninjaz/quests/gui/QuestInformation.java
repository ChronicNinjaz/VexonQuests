package io.chronicninjaz.quests.gui;

import io.chronicninjaz.quests.player.QuestPlayer;
import io.chronicninjaz.quests.utils.gui.MenuFactory;
import io.chronicninjaz.quests.utils.gui.MenuItem;
import io.chronicninjaz.quests.utils.itemstack.ItemFactory;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public class QuestInformation extends MenuFactory {

    public QuestInformation(QuestPlayer questPlayer){
        super("&cQuest Information", 3);

        addItem(new MenuItem(10, new ItemFactory(Material.DIAMOND).setLore(questPlayer.getQuestPoints() + " points.").build()){

            @Override
            public void click(Player player, ClickType clickType) {
                player.sendMessage("something");
            }

        });

        openInventory(questPlayer.getPlayer());
    }
}
