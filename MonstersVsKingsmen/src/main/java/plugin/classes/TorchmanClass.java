package plugin.classes;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class TorchmanClass extends PlayerClass {
	
	public TorchmanClass(ArrayList<Player> nobles, KingClass king, PeenutClass peenut, DMacClass dmac, ZatrickClass zatrick, HotTubClass hottub) {
		super(nobles, king, peenut, dmac, zatrick, hottub);
		ItemStack axeStack = new ItemStack(Material.DIAMOND_AXE, 1);
		ItemStack flintStack = new ItemStack(Material.FLINT_AND_STEEL, 1);
		ArrayList<ItemStack> items = new ArrayList<ItemStack>();
		items.add(axeStack);
		items.add(flintStack);
		super.setItems(items);

		super.setBook(infoBook());
		super.setSpawnEgg(Material.TROPICAL_FISH_SPAWN_EGG);
	}

	public void setClass(Player player) {
		super.setPlayer(player);
		super.giveItems();
	}

	public ItemStack infoBook() {
		List<String> pages = new ArrayList<String>();
		pages.add("The torchman's job is to light up the castle so that everyone can see.");
		pages.add("Get yourself some sticks, and left click on fire with them in your hand.");

		ItemStack book = new ItemStack(Material.WRITTEN_BOOK, 1);
		BookMeta bookMeta = (BookMeta) book.getItemMeta();
		bookMeta.setTitle("How to be a Torchman");
		bookMeta.setAuthor("K1ng");
		bookMeta.setPages(pages);
		book.setItemMeta(bookMeta);
		return book;
	}

}
