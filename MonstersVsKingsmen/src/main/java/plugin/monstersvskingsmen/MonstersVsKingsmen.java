package plugin.monstersvskingsmen;

import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;

public final class MonstersVsKingsmen extends JavaPlugin implements Listener{
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	@Override
	public void onDisable() {
		
	}
	
	@EventHandler
	public void onPlayerInteractBlock(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		PlayerInventory inventory = player.getInventory();
		if(inventory.getItemInMainHand().getType() == Material.FISHING_ROD) {
			getLogger().info("TEST");
			player.getWorld().strikeLightning(player.getTargetBlock((Set<Material>) null, 200).getLocation());
		}
	}
}
