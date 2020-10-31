package plugin.classes;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;

public class ZombieClass {
	public void giveItems(Player player) {
		MobDisguise disguise = new MobDisguise(DisguiseType.ZOMBIE);
		disguise.setEntity(player);
		disguise.startDisguise();
		disguise.setSelfDisguiseVisible(false);
		
		Inventory inventory = player.getInventory();
		inventory.remove(Material.ZOMBIE_SPAWN_EGG);
		
		ItemStack sword = new ItemStack(Material.IRON_SWORD);
		inventory.addItem(sword);
		
		ItemStack[] armor = new ItemStack[4];
		armor[0] = new ItemStack(Material.IRON_BOOTS);
		armor[1] = new ItemStack(Material.IRON_LEGGINGS);
		armor[2] = new ItemStack(Material.IRON_CHESTPLATE);
		armor[3] = new ItemStack(Material.IRON_HELMET);
		
		for (int i = 0; i < armor.length; i++) {
			armor[i].addEnchantment(Enchantment.BINDING_CURSE, 1);
		}
		
		player.getInventory().setArmorContents(armor);
	}
}
