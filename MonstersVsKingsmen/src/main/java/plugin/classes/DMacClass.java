package plugin.classes;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class DMacClass {
	public void giveItems(Player player) {
		Inventory inventory = player.getInventory();
		ItemStack stack = new ItemStack(Material.END_CRYSTAL, 1);
		inventory.remove(Material.SALMON_SPAWN_EGG);
		inventory.addItem(stack);
	}

	public void aoeEffect(Player player) {
		for(Entity entity : player.getNearbyEntities(10, 10, 10)) {
			if(entity instanceof Player) {
				Player newPlayer = (Player) entity;
				newPlayer.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 2));
				newPlayer.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200, 2));
			}
		}
	}
}
