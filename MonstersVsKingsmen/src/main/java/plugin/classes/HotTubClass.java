package plugin.classes;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class HotTubClass {
	public void giveItems(Player player) {
		Inventory inventory = player.getInventory();
		ItemStack stack = new ItemStack(Material.BLAZE_ROD, 1);
		ItemMeta meta = stack.getItemMeta();
		meta.setDisplayName("Going Ghost!");
		stack.setItemMeta(meta);
		inventory.remove(Material.ENDERMITE_SPAWN_EGG);
		inventory.addItem(stack);
	}

	public void invisibility(Player player) {
		player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 400, 2));
	}
}
