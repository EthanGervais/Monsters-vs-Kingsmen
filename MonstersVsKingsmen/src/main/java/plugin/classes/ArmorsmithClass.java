package plugin.classes;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.BlastingRecipe;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.plugin.Plugin;

public class ArmorsmithClass {
	public void giveItems(Player player) {
		Inventory inventory = player.getInventory();
		ItemStack coalStack = new ItemStack(Material.COAL_BLOCK, 128);
		ItemStack pistonStack = new ItemStack(Material.STICKY_PISTON, 1);
		ItemStack leverStack = new ItemStack(Material.LEVER, 1);
		ItemStack blastStack = new ItemStack(Material.BLAST_FURNACE, 1);
		ItemStack pickStack = new ItemStack(Material.IRON_PICKAXE, 1);
		ItemStack chestStack = new ItemStack(Material.CHEST, 1);
		inventory.remove(Material.OCELOT_SPAWN_EGG);
		inventory.addItem(coalStack);
		inventory.addItem(pistonStack);
		inventory.addItem(leverStack);
		inventory.addItem(blastStack);
		inventory.addItem(pickStack);
		inventory.addItem(chestStack);
		infoBook(inventory);
	}
	
	public void addFurnaceRecipe(Plugin plugin) {
		ItemStack result = new ItemStack(Material.DIAMOND);
		NamespacedKey coalkey = new NamespacedKey(plugin, "coal_key");
		BlastingRecipe recipe = new BlastingRecipe(coalkey, result, Material.NETHERRACK, 0, 200);
		plugin.getServer().addRecipe(recipe);
	}
	
	public void infoBook(Inventory inv) {
		List<String> pages = new ArrayList<String>();
		pages.add("The armorsmith's job is to prepare all kingsmen and nobles with full diamond armor sets before the hordes of monsters are released.");
		pages.add("Diamond armor can be crafted normally at a work bench (although you will need the wood to make a crafting table).");
		pages.add("In order to get diamonds, you must compress coal and smelt it in a blast furnace. "
				+ "Coal is compressed using an upwards facing sticky piston. "
				+ "Place a coal block on top, extend and contract the piston, and the coal will slowly turn into compressed coal.");
		pages.add("NOTE: Be careful, over compressing coal will pulverize it to dust!\n\n"
				+ "Also, other classes (such as the weaponsmith) may need some diamonds to make other items.");
		
		ItemStack book = new ItemStack(Material.WRITTEN_BOOK, 1);
		BookMeta bookMeta = (BookMeta) book.getItemMeta();
		bookMeta.setTitle("How to be an Armorsmith");
		bookMeta.setAuthor("K1ing");
		bookMeta.setPages(pages);
		book.setItemMeta(bookMeta);
		inv.addItem(book);
	}
}
