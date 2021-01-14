package plugin.classes;

import java.util.ArrayList;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;


public class PlayerClass {
	private Player player;
	private ArrayList<ItemStack> items;
	private ItemStack book;
	private Material egg;
	private boolean isNoble = false;
	
	public void setPlayer(Player newPlayer) {
		player = newPlayer;
	}
	
	public void setItems(ArrayList<ItemStack> newItems) {
		items = newItems;
	}
	
	public void setBook(ItemStack newBook) {
		book = newBook;
	}
	
	public void setSpawnEgg(Material newEgg) {
		egg = newEgg;
	}
	
	public void setPlayerAsNoble() {
		isNoble = true;
	}
	
	public void giveItems(ArrayList<Player> nobles, KingClass king, PeenutClass peenut, DMacClass dmac, ZatrickClass zatrick, HotTubClass hottub) {
		player.getInventory().remove(egg);
		for(ItemStack item : items) {
			player.getInventory().addItem(item);
		}
		if(!isNoble) {
			player.getInventory().addItem(book);
		}
		
		if(nobles.contains(player) && !isNoble) {
			assignNoble(nobles, king, peenut, dmac, zatrick, hottub);
		}
	}
	
	public void assignNoble(ArrayList<Player> nobles, KingClass king, PeenutClass peenut, DMacClass dmac, ZatrickClass zatrick, HotTubClass hottub) {
		for (int i = 0; i < nobles.size(); i++) {
			if (player == nobles.get(i)) {
				if (i == 0) {
					king.setClass(player, nobles, king, peenut, dmac, zatrick, hottub);
				} else if (i == 1) {
					peenut.setClass(player, nobles, king, peenut, dmac, zatrick, hottub);
				} else if (i == 2) {
					dmac.setClass(player, nobles, king, peenut, dmac, zatrick, hottub);
				} else if (i == 3) {
					zatrick.setClass(player, nobles, king, peenut, dmac, zatrick, hottub);
				} else if (i == 4) {
					hottub.setClass(player, nobles, king, peenut, dmac, zatrick, hottub);
				}
			}
		}
	}
}
