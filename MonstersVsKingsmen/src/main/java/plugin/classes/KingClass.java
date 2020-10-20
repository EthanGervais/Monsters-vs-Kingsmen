package plugin.classes;

import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class KingClass {
	public void giveItems(Player player) {
		Inventory inventory = player.getInventory();
		ItemStack stack = new ItemStack(Material.SLIME_BALL, 1);
		inventory.remove(Material.PUFFERFISH_SPAWN_EGG);
		inventory.addItem(stack);
	}

	public void thorsHammer(Player player) {
		player.getWorld().strikeLightning(player.getTargetBlock((Set<Material>) null, 200).getLocation());
	}
}
