package plugin.classes;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;

public class SkeletonClass extends MonsterClass {
	
	public SkeletonClass() {
		super();
		ItemStack bowStack = new ItemStack(Material.BOW);
		ItemStack arrowsStack = new ItemStack(Material.ARROW, 256);
		ArrayList<ItemStack> items = new ArrayList<ItemStack>();
		items.add(bowStack);
		items.add(arrowsStack);
		super.setItems(items);
		
		super.setDisguise(DisguiseType.SKELETON);
	}
	
	public void setClass(Player player) {
		super.setPlayer(player);
		super.spawnMonster();
		
		ItemStack[] armorStack = new ItemStack[4];
		armorStack[0] = new ItemStack(Material.LEATHER_BOOTS);
		armorStack[1] = new ItemStack(Material.LEATHER_LEGGINGS);
		armorStack[2] = new ItemStack(Material.LEATHER_CHESTPLATE);
		armorStack[3] = new ItemStack(Material.LEATHER_HELMET);
		
		for (int i = 0; i < armorStack.length; i++) {
			armorStack[i].addEnchantment(Enchantment.BINDING_CURSE, 1);
		}
		
		player.getInventory().setArmorContents(armorStack);
	}
	
	public void giveItems(Player player) {
		MobDisguise disguise = new MobDisguise(DisguiseType.SKELETON);
		disguise.setEntity(player);
		disguise.startDisguise();
		disguise.setSelfDisguiseVisible(false);

		Inventory inventory = player.getInventory();

		ItemStack bow = new ItemStack(Material.BOW);
		ItemStack arrows = new ItemStack(Material.ARROW, 256);
		inventory.addItem(bow);
		inventory.addItem(arrows);

		ItemStack[] armor = new ItemStack[4];
		armor[0] = new ItemStack(Material.LEATHER_BOOTS);
		armor[1] = new ItemStack(Material.LEATHER_LEGGINGS);
		armor[2] = new ItemStack(Material.LEATHER_CHESTPLATE);
		armor[3] = new ItemStack(Material.LEATHER_HELMET);
		
		for (int i = 0; i < armor.length; i++) {
			armor[i].addEnchantment(Enchantment.BINDING_CURSE, 1);
		}
		
		player.getInventory().setArmorContents(armor);
	}
}
