package plugin.classes;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class DMacClass {
	public void giveItems(Player player) {
		Inventory inventory = player.getInventory();
		ItemStack stack = new ItemStack(Material.END_CRYSTAL, 1);
		ItemMeta meta = stack.getItemMeta();
		meta.setDisplayName("LET'S GO!");
		stack.setItemMeta(meta);
		inventory.remove(Material.SALMON_SPAWN_EGG);
		inventory.addItem(stack);
	}

	public void aoeEffect(Player player) {
		player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 400, 1));
		player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 400, 1));
		for(Entity entity : player.getNearbyEntities(10, 10, 10)) {
			if(entity instanceof Player) {
				Player newPlayer = (Player) entity;
				newPlayer.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 400, 1));
				newPlayer.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 400, 1));
			}
		}
	}
}
