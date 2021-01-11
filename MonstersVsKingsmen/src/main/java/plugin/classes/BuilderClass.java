package plugin.classes;

import plugin.activatedBlock.Drill;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class BuilderClass extends PlayerClass {
	private Hashtable<String, Drill> drills = new Hashtable<String, Drill>();

	public BuilderClass(ArrayList<Player> nobles, KingClass king, PeenutClass peenut, DMacClass dmac, ZatrickClass zatrick, HotTubClass hottub) {
		super(nobles, king, peenut, dmac, zatrick, hottub);

		ItemStack stoneStack = new ItemStack(Material.STONE_BRICKS, 128);
		ItemStack drillStack = new ItemStack(Material.STONECUTTER, 1);
		ItemStack pickStack = new ItemStack(Material.DIAMOND_PICKAXE, 1);
		ArrayList<ItemStack> items = new ArrayList<ItemStack>();
		items.add(stoneStack);
		items.add(drillStack);
		items.add(pickStack);
		super.setItems(items);

		super.setBook(infoBook());
		super.setSpawnEgg(Material.ENDERMAN_SPAWN_EGG);
	}

	public void setClass(Player player) {
		Drill drill = new Drill(player);
		drills.put(player.getDisplayName(), drill);
		super.setPlayer(player);
		super.giveItems();
	}

	public Hashtable<String, Drill> getDrills() {
		return drills;
	}

	public ItemStack infoBook() {
		List<String> pages = new ArrayList<String>();
		pages.add(
				"The builder's job is to build a castle strong enough to protect kingsmen and nobles from the coming hordes.");
		pages.add(
				"Build a keep as fast as you can. Place your drill on the ground and it will slowly give you blocks to build with.");
		pages.add("NOTE: A roof might be important to protect against a dragon.\n\n"
				+ "Also, other kingsmen and nobles might want some blocks just in case they need them during the fight.");

		ItemStack book = new ItemStack(Material.WRITTEN_BOOK, 1);
		BookMeta bookMeta = (BookMeta) book.getItemMeta();
		bookMeta.setTitle("How to be a Builder");
		bookMeta.setAuthor("K1ng");
		bookMeta.setPages(pages);
		book.setItemMeta(bookMeta);
		return book;
	}
}
