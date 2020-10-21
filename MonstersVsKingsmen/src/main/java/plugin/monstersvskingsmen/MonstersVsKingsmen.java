package plugin.monstersvskingsmen;

import java.util.Hashtable;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;

import plugin.classes.*;
import plugin.activatedBlock.*;

public final class MonstersVsKingsmen extends JavaPlugin implements Listener {
	private KingClass king = new KingClass();
	private PeenutClass peenut = new PeenutClass();
	private DMacClass dmac = new DMacClass();
	private ZatrickClass zatrick = new ZatrickClass();
	private HotTubClass hottub = new HotTubClass();
	private BuilderClass builder = new BuilderClass();
	private BakerClass baker = new BakerClass();
	
	private Hashtable<String, Drill> drills = new Hashtable<String, Drill>();

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
			event.setCancelled(true);
			king.thorsHammer(player);
		} else if (inventory.getItemInMainHand().getType() == Material.PUFFERFISH_SPAWN_EGG) {
			event.setCancelled(true);
			king.giveItems(player);
		}

		// Peenut Class
		if (inventory.getItemInMainHand().getType() == Material.BLAZE_POWDER) {
			event.setCancelled(true);
			peenut.fireBall(player);
		} else if (inventory.getItemInMainHand().getType() == Material.TURTLE_SPAWN_EGG) {
			event.setCancelled(true);
			peenut.giveItems(player);
		}

		// DMac Class
		if (inventory.getItemInMainHand().getType() == Material.END_CRYSTAL) {
			event.setCancelled(true);
			dmac.aoeEffect(player);
		} else if (inventory.getItemInMainHand().getType() == Material.SALMON_SPAWN_EGG) {
			event.setCancelled(true);
			dmac.giveItems(player);
		}

		// Zatrick Class
		if (inventory.getItemInMainHand().getType() == Material.CRIMSON_ROOTS) {
			event.setCancelled(true);
			zatrick.needHealing(player);
			;
		} else if (inventory.getItemInMainHand().getType() == Material.MOOSHROOM_SPAWN_EGG) {
			event.setCancelled(true);
			zatrick.giveItems(player);
		}

		// HotTub Class
		if (inventory.getItemInMainHand().getType() == Material.DRAGON_HEAD) {
			event.setCancelled(true);
			hottub.invisibility(player);
			;
		} else if (inventory.getItemInMainHand().getType() == Material.ENDERMITE_SPAWN_EGG) {
			event.setCancelled(true);
			hottub.giveItems(player);
		}

		// Builder Class
		if (inventory.getItemInMainHand().getType() == Material.STONECUTTER) {
			if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
				drills = builder.getDrills();
				Drill drill = drills.get(player.getDisplayName());
				drill.setPlaced(true);
			}
		} else if (event.getClickedBlock() != null && event.getClickedBlock().getType() == Material.STONECUTTER) {
			if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
				event.setCancelled(true);
				drills = builder.getDrills();
				if(drills.containsKey(player.getDisplayName())) {
					Drill drill = drills.get(player.getDisplayName());
					drill.openDrill(player);
				}
			}
		} else if (inventory.getItemInMainHand().getType() == Material.ENDERMAN_SPAWN_EGG) {
				event.setCancelled(true);
				builder.giveItems(player);
		}
		
		// Baker Class
		if (inventory.getItemInMainHand().getType() == Material.ZOGLIN_SPAWN_EGG) {
			event.setCancelled(true);
			baker.giveItems(player);
		}
	}
	
	@EventHandler
	public void onEntityDamage(EntityDamageEvent event) {
		if (event.getCause() == DamageCause.FIRE || event.getCause() == DamageCause.FIRE_TICK) {
			event.setDamage(2.5);
		}
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		if (event.getBlock().getType() == Material.STONECUTTER) {
			drills = builder.getDrills();
			Drill drill = drills.get(event.getPlayer().getDisplayName());
			drill.setPlaced(false);
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
