package me.stonygames.Defend.Methoden;

import java.util.ArrayList;

import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;

public class ItemStack_Methoden {

    public static ItemStack createItemStack(Material mat, String name, short data, int amount, String lore1, String lore2, boolean glow, boolean hideA) {
        ArrayList<String> lore = new ArrayList<String>();
        if(lore1 != "") {
            lore.add(lore1);
        }
        if(lore2 != "") {
            lore.add(lore2);
        }

        ItemStack item1 = new ItemStack(mat, amount, data);
        ItemMeta meta1 = item1.getItemMeta();
        meta1.setDisplayName(name);
        meta1.setLore(lore);
        if(hideA) {
            meta1.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        }
        if(glow) {
            meta1.addEnchant(Enchantment.DURABILITY, 10, true);
            meta1.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        item1.setItemMeta(meta1);

        return item1;
    }

    public static ItemStack createItemStack4(Material mat, String name, short data, int amount, String lore1, String lore2,String lore3,String lore4,String lore5, boolean glow, boolean hideA) {
        ArrayList<String> lore = new ArrayList<String>();
        if(lore1 != "") {
            lore.add(lore1);
        }
        if(lore2 != "") {
            lore.add(lore2);
        }
        if(lore3 != "") {
            lore.add(lore3);
        }
        if(lore4 != "") {
            lore.add(lore4);
        }
        if(lore5 != "") {
            lore.add(lore5);
        }

        ItemStack item1 = new ItemStack(mat, amount, data);
        ItemMeta meta1 = item1.getItemMeta();
        meta1.setDisplayName(name);
        meta1.setLore(lore);
        if(hideA) {
            meta1.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        }
        if(glow) {
            meta1.addEnchant(Enchantment.DURABILITY, 10, true);
            meta1.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        item1.setItemMeta(meta1);

        return item1;
    }

    public static ItemStack createHeadItem(Material mat, String name, short data, int amount, String lore1, String lore2, String owner, boolean glow, boolean hideA) {
        ArrayList<String> lore = new ArrayList<String>();
        if(lore1 != "") {
            lore.add(lore1);
        }
        if(lore2 != "") {
            lore.add(lore2);
        }

        ItemStack item1 = new ItemStack(mat, amount, data);
        SkullMeta skullmeta = (SkullMeta) item1.getItemMeta();
        skullmeta.setDisplayName(name);
        skullmeta.setOwner(owner);

        skullmeta.setLore(lore);
        if(hideA) {
            skullmeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        }
        if(glow) {
            skullmeta.addEnchant(Enchantment.DURABILITY, 10, true);
            skullmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        item1.setItemMeta(skullmeta);

        return item1;
    }

    public static ItemStack createHeadItemWithOneLore(Material mat, String name, short data, int amount, String lore1, String owner, boolean glow, boolean hideA) {
        ArrayList<String> lore = new ArrayList<String>();
        if(lore1 != "") {
            lore.add(lore1);
        }

        ItemStack item1 = new ItemStack(mat, amount, data);
        SkullMeta skullmeta = (SkullMeta) item1.getItemMeta();
        skullmeta.setDisplayName(name);
        skullmeta.setOwner(owner);

        skullmeta.setLore(lore);
        if(hideA) {
            skullmeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        }
        if(glow) {
            skullmeta.addEnchant(Enchantment.DURABILITY, 10, true);
            skullmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        item1.setItemMeta(skullmeta);

        return item1;
    }

    public static ItemStack createItemStack2(Material mat, String name, short data, int amount, boolean glow, boolean hideA) {

        ItemStack item1 = new ItemStack(mat, amount, data);
        ItemMeta meta1 = item1.getItemMeta();
        meta1.setDisplayName(name);
        if(hideA) {
            meta1.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        }
        if(glow) {
            meta1.addEnchant(Enchantment.DURABILITY, 10, true);
            meta1.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        item1.setItemMeta(meta1);

        return item1;
    }

    public static ItemStack removeAttributes(ItemStack i){
        if(i == null || i.getType() == Material.BOOK_AND_QUILL)
            return i;

        ItemStack item = i.clone();

        net.minecraft.server.v1_8_R3.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
        NBTTagCompound tag;
        if (!nmsStack.hasTag()){
            tag = new NBTTagCompound();
            nmsStack.setTag(tag);
        }else
            tag = nmsStack.getTag();

        NBTTagList am = new NBTTagList();

        tag.set("AttributeModifiers", am);
        nmsStack.setTag(tag);

        return CraftItemStack.asBukkitCopy(nmsStack);
    }

    @SuppressWarnings("deprecation")
    public static ItemStack createSpawnEgg(EntityType entity, String name, short data, int amount, boolean glow) {
        ItemStack item1 = new ItemStack(Material.MONSTER_EGG, amount, entity.getTypeId());
        ItemMeta meta1 = item1.getItemMeta();
        meta1.setDisplayName(name);
        if(glow) {
            meta1.addEnchant(Enchantment.DURABILITY, 10, true);
            meta1.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        item1.setItemMeta(meta1);

        return item1;
    }

    public static ItemStack createLeatherItem(Material mat, String name, short data, int amount, String lore1, String lore2, Color color, boolean glow) {
        ArrayList<String> lore = new ArrayList<String>();
        if(lore1 != "") {
            lore.add(lore1);
        }
        if(lore2 != "") {
            lore.add(lore2);
        }

        ItemStack item1 = new ItemStack(mat, amount, data);
        LeatherArmorMeta meta1 = (LeatherArmorMeta) item1.getItemMeta();
        meta1.setDisplayName(name);
        meta1.setLore(lore);
        meta1.setColor(color);
        meta1.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta1.spigot().setUnbreakable(true);
        if(glow) {
            meta1.addEnchant(Enchantment.DURABILITY, 1000, true);
            meta1.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        item1.setItemMeta(meta1);

        return item1;
    }

    public static ItemStack createBanner(String name, int amount, short id, String lore1, String lore2, boolean glow, DyeColor color) {
        ArrayList<String> lore = new ArrayList<String>();
        if(lore1 != "") {
            lore.add(lore1);
        }
        if(lore2 != "") {
            lore.add(lore2);
        }

        ItemStack item1 = new ItemStack(Material.BANNER, amount, id);
        BannerMeta meta1 = (BannerMeta) item1.getItemMeta();
        meta1.setBaseColor(color);
        meta1.setDisplayName(name);
        meta1.setLore(lore);
        if(glow) {
            meta1.addEnchant(Enchantment.DURABILITY, 10, true);
            meta1.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        item1.setItemMeta(meta1);

        return item1;
    }

    public static ItemStack createItemWithEnchants(Material mat, String name, short data, int amount, Enchantment ench1, Enchantment ench2, int lvl1, int lvl2) {
        ItemStack item1 = new ItemStack(mat, amount, data);
        ItemMeta meta1 = item1.getItemMeta();
        meta1.setDisplayName(name);
        meta1.addEnchant(ench1, lvl1, true);
        meta1.addEnchant(ench2, lvl2, true);
        item1.setItemMeta(meta1);

        return item1;
    }

    public static boolean containsItem(Inventory inv, ItemStack s) {
        boolean ok = false;
        int found = 0;

        for(ItemStack stack : inv.getContents()) {
            if(stack == null) {
                continue;
            }
            if(stack.getType().equals(s.getType())) {
                if(stack.getData().equals(s.getData())) {
                    if(stack.hasItemMeta() && s.hasItemMeta()) {
                        if(stack.getItemMeta().equals(s.getItemMeta())) {
                            int amount = stack.getAmount();
                            found = found + amount;
                            if(found >= s.getAmount()) {
                                break;
                            } else {
                                continue;
                            }
                        }
                    } else {
                        int amount = stack.getAmount();
                        found = found + amount;
                        if(found >= s.getAmount()) {
                            break;
                        } else {
                            continue;
                        }
                    }
                } else {
                    continue;
                }
            } else {
                continue;
            }
        }

        if(found >= s.getAmount()) {
            ok = true;
        }

        return ok;
    }

    public static ItemStack createHead(String player, String name, String lore_line1, String lore_line2) {
        ArrayList<String> lore = new ArrayList<String>();
        if(!(lore_line1.equals(""))) {
            lore.add(lore_line1);
        }
        if(!(lore_line2.equals(""))) {
            lore.add(lore_line2);
        }
        ItemStack item1 = new ItemStack(Material.SKULL_ITEM,1,(short)3);
        SkullMeta meta1 = (SkullMeta) item1.getItemMeta();
        meta1.setOwner(player);
        meta1.setLore(lore);
        meta1.setDisplayName(name);
        item1.setItemMeta(meta1);

        return item1;
    }
}

