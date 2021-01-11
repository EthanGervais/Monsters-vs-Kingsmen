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
	
	private ArrayList<Player> nobles;
	private KingClass king;
	private PeenutClass peenut;
	private DMacClass dmac;
	private ZatrickClass zatrick;
	private HotTubClass hottub;
	
	public PlayerClass(ArrayList<Player> nobles, KingClass king, PeenutClass peenut, DMacClass dmac, ZatrickClass zatrick, HotTubClass hottub) {
		this.king = king;
		this.peenut = peenut;
		this.dmac = dmac;
		this.zatrick = zatrick;
		this.hottub = hottub;
		this.nobles = nobles;
	}
	
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
	
	public void giveItems() {
		player.getInventory().remove(egg);
		for(ItemStack item : items) {
			player.getInventory().addItem(item);
		}
		if(!isNoble) {
			player.getInventory().addItem(book);
		}
		
		if(nobles.contains(player)) {
			assignNoble();
		}
	}
	
	public void assignNoble() {
		for (int i = 0; i < nobles.size(); i++) {
			if (player == nobles.get(i)) {
				if (i == 0) {
					king.setPlayer(player);
				} else if (i == 1) {
					peenut.setPlayer(player);
				} else if (i == 2) {
					dmac.setPlayer(player);
				} else if (i == 3) {
					zatrick.setPlayer(player);
				} else if (i == 4) {
					hottub.setPlayer(player);
				}
			}
		}
	}
}
