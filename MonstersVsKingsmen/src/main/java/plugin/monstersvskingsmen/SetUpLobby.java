package plugin.monstersvskingsmen;

import java.util.ArrayList;
import java.util.Collections;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SetUpLobby {

	public void assignRoles() {
		ArrayList<ItemStack> items = new ArrayList<ItemStack>();
		items.add(new ItemStack(Material.PUFFERFISH_SPAWN_EGG, 1));
		items.add(new ItemStack(Material.TURTLE_SPAWN_EGG, 1));

		int counter = 0;

		for (Player p : Bukkit.getOnlinePlayers()) {
			Collections.shuffle(items);
			
			Inventory inv = p.getInventory();
			ItemStack spawn = new ItemStack(items.get(counter));
			
//			// This literally does nothing :)
//			if (counter == items.size()) { // Amount of positions in ItemStack array.
//				counter = 0;
//			} else {
//				counter++;
//			}
			
			ItemMeta meta = spawn.getItemMeta();
			meta.setDisplayName("K1ng");
			spawn.setItemMeta(meta);
			inv.addItem(spawn);
		}
	}

}
