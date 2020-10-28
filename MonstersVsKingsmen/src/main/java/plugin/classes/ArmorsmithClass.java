package plugin.classes;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.BlastingRecipe;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class ArmorsmithClass {
	public void giveItems(Player player) {
		Inventory inventory = player.getInventory();
		ItemStack coalStack = new ItemStack(Material.COAL_BLOCK, 64);
		ItemStack pistonStack = new ItemStack(Material.STICKY_PISTON, 1);
		ItemStack leverStack = new ItemStack(Material.LEVER, 1);
		ItemStack blastStack = new ItemStack(Material.BLAST_FURNACE, 1);
		inventory.remove(Material.OCELOT_SPAWN_EGG);
		inventory.addItem(coalStack);
		inventory.addItem(pistonStack);
		inventory.addItem(leverStack);
		inventory.addItem(blastStack);
	}
	
	public void addFurnaceRecipe(Plugin plugin) {
		ItemStack result = new ItemStack(Material.DIAMOND);
		NamespacedKey coalkey = new NamespacedKey(plugin, "coal_key");
		BlastingRecipe recipe = new BlastingRecipe(coalkey, result, Material.NETHERRACK, 0, 200);
		plugin.getServer().addRecipe(recipe);
	}
}
