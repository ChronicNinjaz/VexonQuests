package io.chronicninjaz.quests.utils.itemstack;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemFactory extends Factory<ItemStack> {
    private ItemMeta itemMeta;

    public ItemFactory(Material material) {
        this(material, 1, (byte) 0);
    }

    public ItemFactory(Material material, int amount) {
        this(material, amount, (byte) 0);
    }

    public ItemFactory(Material material, int amount, byte data) {
        object = new ItemStack(material, amount, data);
        itemMeta = object.getItemMeta();
    }

    public ItemFactory setDisplayName(String displayName) {
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
        return this;
    }

    public ItemFactory setLore(List<String> lore) {
        List<String> currentLore = new ArrayList<>();
        for (String lorePart : lore) {
            currentLore.add(ChatColor.translateAlternateColorCodes('&', lorePart));
        }
        itemMeta.setLore(currentLore);
        return this;
    }

    public ItemFactory addItemFlag(ItemFlag... flag){
        itemMeta.addItemFlags(flag);
        return this;
    }

    public ItemFactory setLore(String... lore) {
        List<String> currentLore = new ArrayList<>();
        for (String lorePart : lore) {
            currentLore.add(ChatColor.translateAlternateColorCodes('&', lorePart));
        }
        itemMeta.setLore(currentLore);
        return this;
    }

    public ItemFactory appendLore(String... lore) {
        List<String> currentLore = (itemMeta.getLore() == null ? new ArrayList<>() : itemMeta.getLore());
        for (String lorePart : lore) {
            currentLore.add(ChatColor.translateAlternateColorCodes('&', lorePart));
        }
        itemMeta.setLore(currentLore);
        return this;
    }

    public ItemFactory enchant(Enchantment... enchantments) {
        if(enchantments == null){
            return this;
        }

        for (Enchantment enchantment : enchantments) {
            itemMeta.addEnchant(enchantment, 1, false);
        }
        return this;
    }

    @Override
    public ItemStack build() {
        object.setItemMeta(itemMeta);
        return object;
    }
}
