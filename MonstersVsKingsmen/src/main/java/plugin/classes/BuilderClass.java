package plugin.classes;

import plugin.activatedBlock.Drill;
import java.util.Hashtable;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class BuilderClass {
	private Hashtable<String, Drill> drills = new Hashtable<String, Drill>();
	
	public void giveItems(Player player) {
		Inventory inventory = player.getInventory();
		ItemStack stack1 = new ItemStack(Material.COBBLESTONE, 64);
		ItemStack stack2 = new ItemStack(Material.STONECUTTER, 1);
		inventory.remove(Material.ENDERMAN_SPAWN_EGG);
		inventory.addItem(stack1);
		inventory.addItem(stack2);
		
		Drill drill = new Drill(player);
		drills.put(player.getDisplayName(), drill);
	} 
	
	public Hashtable<String, Drill> getDrills(){
		return drills;
	}
}
