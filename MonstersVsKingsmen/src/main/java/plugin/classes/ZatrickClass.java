package plugin.classes;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ZatrickClass {
	public void giveItems(Player player) {
		Inventory inventory = player.getInventory();
		ItemStack stack = new ItemStack(Material.CRIMSON_ROOTS, 1);
		inventory.remove(Material.MOOSHROOM_SPAWN_EGG);
		inventory.addItem(stack);
	}
	
	public void needHealing(Player player) {
		for (Entity entity : player.getNearbyEntities(10, 10, 10)) {
			if (entity instanceof Player) {
				Player newPlayer = (Player) entity;
				newPlayer.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 30, 2));
			}
		}
	}

}
