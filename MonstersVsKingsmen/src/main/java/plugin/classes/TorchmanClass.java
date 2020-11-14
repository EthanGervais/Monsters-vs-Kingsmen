package plugin.classes;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;

public class TorchmanClass {
	public void giveItems(Player player) {
		Inventory inventory = player.getInventory();
		ItemStack axeStack = new ItemStack(Material.DIAMOND_AXE, 1);
		ItemStack flintStack = new ItemStack(Material.FLINT_AND_STEEL, 1);
		ItemMeta meta = axeStack.getItemMeta();
		meta.setDisplayName("Torchman's Axe");
		axeStack.setItemMeta(meta);
		inventory.remove(Material.TROPICAL_FISH_SPAWN_EGG);
		inventory.addItem(axeStack);
		inventory.addItem(flintStack);
		infoBook(inventory);
	}
	
	public void infoBook(Inventory inv) {
		List<String> pages = new ArrayList<String>();
		pages.add("The torchman's job is to light up the castle so that everyone can see.");
		pages.add("Get yourself some sticks, and left click on fire with them in your hand.");
		
		ItemStack book = new ItemStack(Material.WRITTEN_BOOK, 1);
		BookMeta bookMeta = (BookMeta) book.getItemMeta();
		bookMeta.setTitle("How to be a Torchman");
		bookMeta.setAuthor("K1ing");
		bookMeta.setPages(pages);
		book.setItemMeta(bookMeta);
		inv.addItem(book);
	}
	
}
