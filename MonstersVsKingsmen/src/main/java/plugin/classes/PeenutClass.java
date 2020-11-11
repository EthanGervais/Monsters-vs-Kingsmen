package plugin.classes;

import java.util.Set;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PeenutClass {
	public void giveItems(Player player) {
		Inventory inventory = player.getInventory();
		ItemStack stack = new ItemStack(Material.BLAZE_POWDER, 1);
		ItemMeta meta = stack.getItemMeta();
		meta.setDisplayName("Flame on!");
		stack.setItemMeta(meta);
		inventory.remove(Material.TURTLE_SPAWN_EGG);
		inventory.addItem(stack);
	}

	public void fireBall(Player player) {
		if (player.getWorld().getBlockAt(player.getTargetBlock((Set<Material>) null, 200).getLocation().add(0, 1, 0)).getType() == Material.AIR) {
			player.getWorld().getBlockAt(player.getTargetBlock((Set<Material>) null, 200).getLocation()).getRelative(BlockFace.UP).setType(Material.FIRE);
		}
	}
}
