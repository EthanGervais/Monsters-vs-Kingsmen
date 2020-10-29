package plugin.classes;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class TorchmanClass {
	public void giveItems(Player player) {
		Inventory inventory = player.getInventory();
		ItemStack axeStack = new ItemStack(Material.DIAMOND_AXE, 1);
		ItemMeta meta = axeStack.getItemMeta();
		meta.setDisplayName("Torchman's Axe");
		axeStack.setItemMeta(meta);
		
		inventory.remove(Material.TROPICAL_FISH_SPAWN_EGG);
		inventory.addItem(axeStack);
	}
	
//	public void onBlockBreak(BlockBreakEvent event) {
//		if (!(event instanceof BlockBreakEvent)) {
//			return;
//		}
//		
//		if (event.getBlock().getType() == Material.OAK_WOOD) {
//			
//		}
//	}
}
