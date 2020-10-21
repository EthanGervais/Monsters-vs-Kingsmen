package plugin.monstersvskingsmen;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;

import plugin.classes.*;

public final class MonstersVsKingsmen extends JavaPlugin implements Listener {
	private KingClass king = new KingClass();
	private PeenutClass peenut = new PeenutClass();
	private DMacClass dmac = new DMacClass();
	private ZatrickClass zatrick = new ZatrickClass();

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
		
		// King Class
		if (inventory.getItemInMainHand().getType() == Material.SLIME_BALL) {
			king.thorsHammer(player);
		} else if (inventory.getItemInMainHand().getType() == Material.PUFFERFISH_SPAWN_EGG) {
			event.setCancelled(true);
			king.giveItems(player);
		}

		// Peenut Class
		if (inventory.getItemInMainHand().getType() == Material.BLAZE_POWDER) {
			peenut.fireBall(player);
		} else if (inventory.getItemInMainHand().getType() == Material.TURTLE_SPAWN_EGG) {
			event.setCancelled(true);
			peenut.giveItems(player);
		}
		
		// DMac Class
		if (inventory.getItemInMainHand().getType() == Material.END_CRYSTAL) {
			dmac.aoeEffect(player);
		} else if (inventory.getItemInMainHand().getType() == Material.SALMON_SPAWN_EGG) {
			event.setCancelled(true);
			dmac.giveItems(player);
		}
		
		// Zatrick Class
		if (inventory.getItemInMainHand().getType() == Material.CRIMSON_ROOTS) {
			zatrick.needHealing(player);;
		} else if (inventory.getItemInMainHand().getType() == Material.MOOSHROOM_SPAWN_EGG) {
			event.setCancelled(true);
			zatrick.giveItems(player);
		}
	}

	@EventHandler
	public void onEntityDamage(EntityDamageEvent event) {
		if (event.getCause() == DamageCause.FIRE || event.getCause() == DamageCause.FIRE_TICK) {
			event.setDamage(2.5);
		}
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.isOp() && cmd.getName().equalsIgnoreCase("startgame")) {
				SetUpLobby setup = new SetUpLobby();
				setup.assignRoles();
			}
		}
		return true;
	}
}
