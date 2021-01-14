package plugin.classes;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class WeaponsmithClass extends PlayerClass {
	public WeaponsmithClass() {
		super();
		ItemStack axeStack = new ItemStack(Material.DIAMOND_AXE, 1);
		ItemStack anvilStack = new ItemStack(Material.ANVIL, 1);
		ItemStack dyeStack = new ItemStack(Material.WHITE_DYE, 15);
		ItemStack lavaStack = new ItemStack(Material.LAVA_BUCKET, 1);
		ItemStack stringStack = new ItemStack(Material.STRING, 32);
		ItemStack chestStack = new ItemStack(Material.CHEST, 1);
		ArrayList<ItemStack> items = new ArrayList<ItemStack>();
		items.add(axeStack);
		items.add(anvilStack);
		items.add(dyeStack);
		items.add(lavaStack);
		items.add(stringStack);
		items.add(chestStack);
		super.setItems(items);
		
		super.setBook(infoBook());
		super.setSpawnEgg(Material.SKELETON_HORSE_SPAWN_EGG);
	}
	
	public void setClass(Player player, ArrayList<Player> nobles, KingClass king, PeenutClass peenut, DMacClass dmac, ZatrickClass zatrick, HotTubClass hottub) {
		super.setPlayer(player);
		super.giveItems(nobles, king, peenut, dmac, zatrick, hottub);
	}
	
	public ItemStack infoBook() {
		List<String> pages = new ArrayList<String>();
		pages.add("The weaponsmith's job is to provide all the kingsmen and nobles with weapons of mass destruction.");
		pages.add("Use the string and get some sticks in order to build bows.\n"
				+ "Take your white dye and right click on some lava in order to get iron ingots.");
		pages.add("In order to make a sword, first start by crafting it in wood. "
				+ "Next, mix the wooden sword with an iron ingot in the anvil. "
				+ "Finally, see if you can get some diamonds from the armorsmith and mix those with the iron sword in the anvil.");
		
		ItemStack book = new ItemStack(Material.WRITTEN_BOOK, 1);
		BookMeta bookMeta = (BookMeta) book.getItemMeta();
		bookMeta.setTitle("How to be a Weaponsmith");
		bookMeta.setAuthor("K1ng");
		bookMeta.setPages(pages);
		book.setItemMeta(bookMeta);
		return book;
	}
}
