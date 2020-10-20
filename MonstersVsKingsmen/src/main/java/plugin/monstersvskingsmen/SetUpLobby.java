package plugin.monstersvskingsmen;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SetUpLobby {
	
	public void assignRoles() {
		for(Player p : Bukkit.getOnlinePlayers()) {
			//TODO Add randomizer to change possible classes
			Inventory inv = p.getInventory();
			ItemStack spawn = new ItemStack(Material.PUFFERFISH_SPAWN_EGG, 1);
			ItemMeta meta = spawn.getItemMeta();
			meta.setDisplayName("K1ng");
			spawn.setItemMeta(meta);
			inv.addItem(spawn);
		}
	}
	
}
