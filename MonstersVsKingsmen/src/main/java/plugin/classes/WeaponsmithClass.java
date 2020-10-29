package plugin.classes;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class WeaponsmithClass {
	public void giveItems(Player player) {
		Inventory inventory = player.getInventory();
		ItemStack axeStack = new ItemStack(Material.DIAMOND_AXE, 1);
		ItemStack anvilStack = new ItemStack(Material.ANVIL, 1);
		ItemStack dyeStack = new ItemStack(Material.WHITE_DYE, 64);
		inventory.remove(Material.SKELETON_HORSE_SPAWN_EGG);
		inventory.addItem(axeStack);
		inventory.addItem(anvilStack);
		inventory.addItem(dyeStack);
	}
}
