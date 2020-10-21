package plugin.classes;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class BakerClass {
	public void giveItems(Player player) {
		Inventory inventory = player.getInventory();
		ItemStack seedStack = new ItemStack(Material.WHEAT_SEEDS, 64);
		ItemStack coalStack = new ItemStack(Material.COAL, 64);
		inventory.remove(Material.ZOGLIN_SPAWN_EGG);
		inventory.addItem(seedStack);
		inventory.addItem(coalStack);
	}
	
	public void addFurnaceRecipe(Plugin plugin) {
		ItemStack result = new ItemStack(Material.COOKIE);
		NamespacedKey cookiekey = new NamespacedKey(plugin, "cookie_key");
		FurnaceRecipe recipe = new FurnaceRecipe(cookiekey, result, Material.WHEAT, 0, 200);
		plugin.getServer().addRecipe(recipe);
	}
}
