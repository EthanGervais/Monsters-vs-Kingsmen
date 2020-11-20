package plugin.classes;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.plugin.Plugin;

public class BakerClass extends PlayerClass {

	public BakerClass() {
		super();
		
		ItemStack seedStack = new ItemStack(Material.WHEAT_SEEDS, 64);
		ItemStack coalStack = new ItemStack(Material.COAL, 64);
		ItemStack furnaceStack = new ItemStack(Material.FURNACE, 1);
		ItemStack boneStack = new ItemStack(Material.BONE, 64);
		ItemStack chestStack = new ItemStack(Material.CHEST, 1);
		ItemStack hoeStack = new ItemStack(Material.DIAMOND_HOE, 1);
		ArrayList<ItemStack> items = new ArrayList<ItemStack>();
		items.add(seedStack);
		items.add(coalStack);
		items.add(furnaceStack);
		items.add(boneStack);
		items.add(chestStack);
		items.add(hoeStack);
		super.setItems(items);

		super.setBook(infoBook());
		super.setSpawnEgg(Material.ZOGLIN_SPAWN_EGG);
	}

	public void setClass(Player player) {
		super.setPlayer(player);
		super.giveItems();
	}

	public void addFurnaceRecipe(Plugin plugin) {
		ItemStack result = new ItemStack(Material.COOKIE);
		NamespacedKey cookiekey = new NamespacedKey(plugin, "cookie_key");
		FurnaceRecipe recipe = new FurnaceRecipe(cookiekey, result, Material.WHEAT, 0, 100);
		plugin.getServer().addRecipe(recipe);
	}

	public ItemStack infoBook() {
		List<String> pages = new ArrayList<String>();
		pages.add("The baker's job is to prepare food to keep the kingsmen and nobles alive and healthy.");
		pages.add(
				"All food types begin with harvesting wheat. You can use the seeds in your inventory and the bone meal to speed things up. "
						+ "Next, place the wheat in a furnace with coal and it will bake into a (special) food type.");
		pages.add(
				"NOTE: You must stay inside a furnace and constantly pull out the prepared food. It will not stack in the furnace.");

		ItemStack book = new ItemStack(Material.WRITTEN_BOOK, 1);
		BookMeta bookMeta = (BookMeta) book.getItemMeta();
		bookMeta.setTitle("How to be a Baker");
		bookMeta.setAuthor("K1ng");
		bookMeta.setPages(pages);
		book.setItemMeta(bookMeta);
		return book;
	}
}
