package plugin.activatedBlock;

import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Drill {
	private boolean placed = false;
	private Inventory inv;
	private Location loc;
	private ItemStack stack = new ItemStack(Material.STONE_BRICKS, 1);
	
	public Drill(Player player) {
		inv = Bukkit.createInventory(player, 27, "Drill");
		setBlockLocation(new Location(player.getWorld(), 0, 0, 0));
		
		Thread thread = new Thread() {
			public void run() {
				while(player.getHealth() > 0) {
					if(isPlaced()) {
						inv.addItem(stack);
					}
					
					try {
						TimeUnit.SECONDS.sleep(2);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		thread.start();
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
