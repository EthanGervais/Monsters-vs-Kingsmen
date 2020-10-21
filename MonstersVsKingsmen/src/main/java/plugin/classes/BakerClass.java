package plugin.classes;

import java.util.ArrayList;
import java.util.Collections;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class BakerClass {
	public void giveItems(Player player) {
		Inventory inventory = player.getInventory();
		ItemStack seedStack = new ItemStack(Material.WHEAT_SEEDS, 64);
		ItemStack coalStack = new ItemStack(Material.COAL, 64);
		inventory.remove(Material.ZOGLIN_SPAWN_EGG);
		inventory.addItem(seedStack);
		inventory.addItem(coalStack);
	}

}
