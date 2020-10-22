package plugin.activatedBlock;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Drill {
	private boolean placed = false;
	private Inventory inv;
	private Location loc;
	
	public Drill(Player player) {
		inv = Bukkit.createInventory(player, 27, "Drill");
		setBlockLocation(new Location(player.getWorld(), 0, 0, 0));
	}
	
	public void setPlaced(boolean input) {
		placed = input;
	}
	
	public void setBlockLocation(Location newBlock) {
		loc = newBlock;
	}
	
	public boolean isPlaced() {
		return placed;
	}
	
	public void openDrill(Player player) {
		player.openInventory(inv);
	}
	
	public Location getBlockLocation() {
		return loc;
	}
}
