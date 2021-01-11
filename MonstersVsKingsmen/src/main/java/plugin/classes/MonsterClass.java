package plugin.classes;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class MonsterClass {
	private Player player;
	private ArrayList<ItemStack> items;
	
	public void setPlayer(Player newPlayer) {
		player = newPlayer;
	}
	
	public void setItems(ArrayList<ItemStack> newItems) {
		items = newItems;
	}
	
	public void spawnMonster() {
		for(ItemStack item : items) {
			player.getInventory().addItem(item);
		}
	}
}
