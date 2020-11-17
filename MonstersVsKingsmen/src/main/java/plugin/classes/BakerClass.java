package plugin.classes;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.plugin.Plugin;

public class BakerClass {
	public void giveItems(Player player) {
		Inventory inventory = player.getInventory();
		ItemStack seedStack = new ItemStack(Material.WHEAT_SEEDS, 64);
		ItemStack coalStack = new ItemStack(Material.COAL, 64);
		ItemStack furnaceStack = new ItemStack(Material.FURNACE, 1);
		ItemStack boneStack = new ItemStack(Material.BONE, 64);
		ItemStack chestStack = new ItemStack(Material.CHEST, 1);
		ItemStack hoeStack = new ItemStack(Material.DIAMOND_HOE, 1);
		inventory.remove(Material.ZOGLIN_SPAWN_EGG);
		inventory.addItem(seedStack);
		inventory.addItem(coalStack);
		inventory.addItem(furnaceStack);
		inventory.addItem(boneStack);
		inventory.addItem(chestStack);
		inventory.addItem(hoeStack);
		infoBook(inventory);
	}

	public void addFurnaceRecipe(Plugin plugin) {
		ItemStack result = new ItemStack(Material.COOKIE);
		NamespacedKey cookiekey = new NamespacedKey(plugin, "cookie_key");
		FurnaceRecipe recipe = new FurnaceRecipe(cookiekey, result, Material.WHEAT, 0, 100);
		plugin.getServer().addRecipe(recipe);
	}
	
	public void infoBook(Inventory inv) {
		List<String> pages = new ArrayList<String>();
		pages.add("The baker's job is to prepare food to keep the kingsmen and nobles alive and healthy.");
		pages.add("All food types begin with harvesting wheat. You can use the seeds in your inventory and the bone meal to speed things up. "
				+ "Next, place the wheat in a furnace with coal and it will bake into a (special) food type.");
		pages.add("NOTE: You must stay inside a furnace and constantly pull out the prepared food. It will not stack in the furnace.");
		
		ItemStack book = new ItemStack(Material.WRITTEN_BOOK, 1);
		BookMeta bookMeta = (BookMeta) book.getItemMeta();
		bookMeta.setTitle("How to be a Baker");
		bookMeta.setAuthor("K1ng");
		bookMeta.setPages(pages);
		book.setItemMeta(bookMeta);
		inv.addItem(book);
	}
}
