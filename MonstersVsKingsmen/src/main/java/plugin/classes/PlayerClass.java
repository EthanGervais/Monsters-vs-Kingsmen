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
	
	public void giveItems() {
		player.getInventory().remove(egg);
		for(ItemStack item : items) {
			player.getInventory().addItem(item);
		}
		if(!isNoble) {
			player.getInventory().addItem(book);
		}
	}
}
