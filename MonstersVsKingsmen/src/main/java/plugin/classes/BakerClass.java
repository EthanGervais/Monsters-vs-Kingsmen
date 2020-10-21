package plugin.classes;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class BakerClass {
	public void giveItems(Player player) {
		Inventory inventory = player.getInventory();
		ItemStack stack1 = new ItemStack(Material.COBBLESTONE, 64);
		ItemStack stack2 = new ItemStack(Material.STONECUTTER, 1);
		inventory.remove(Material.ENDERMAN_SPAWN_EGG);
		inventory.addItem(stack1);
		inventory.addItem(stack2);
	} 
}
