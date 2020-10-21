package plugin.activatedBlock;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Drill {
	private boolean placed = false;
	Inventory inv;
	
	public Drill(Player player) {
		inv = Bukkit.createInventory(player, 27, "Drill");
	}
	
	public void setPlaced(boolean input) {
		placed = input;
	}
	
	public boolean isPlaced() {
		return placed;
	}
	
	public void openDrill(Player player) {
		player.openInventory(inv);
	}
}
