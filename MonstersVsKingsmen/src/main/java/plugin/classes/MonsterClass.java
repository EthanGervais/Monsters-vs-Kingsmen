package plugin.classes;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;

public class MonsterClass {
	private Player player;
	private ArrayList<ItemStack> items;
	private DisguiseType type;
	
	public void setPlayer(Player newPlayer) {
		player = newPlayer;
	}
	
	public void setItems(ArrayList<ItemStack> newItems) {
		items = newItems;
	}
	
	public void setDisguise(DisguiseType newType) {
		type = newType;
	}
	
	public void spawnMonster() {
		MobDisguise disguise = new MobDisguise(type);
		disguise.setEntity(player);
		disguise.startDisguise();
		disguise.setSelfDisguiseVisible(false);
		
		for(ItemStack item : items) {
			player.getInventory().addItem(item);
		}
	}
}
