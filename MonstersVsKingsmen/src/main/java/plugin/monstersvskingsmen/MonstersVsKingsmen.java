package plugin.monstersvskingsmen;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.libs.org.apache.commons.io.FileUtils;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.boss.BarStyle;

import net.md_5.bungee.api.ChatColor;
import plugin.classes.*;
import plugin.activatedBlock.*;

public final class MonstersVsKingsmen extends JavaPlugin implements Listener {
	private ArrayList<Player> nobles = new ArrayList<Player>();
	private KingClass king = new KingClass();
	private PeenutClass peenut = new PeenutClass();
	private DMacClass dmac = new DMacClass();
	private ZatrickClass zatrick = new ZatrickClass();
	private HotTubClass hottub = new HotTubClass();
	private BuilderClass builder = new BuilderClass();
	private BakerClass baker = new BakerClass();
	private ArmorsmithClass armorsmith = new ArmorsmithClass();
	private WeaponsmithClass weaponsmith = new WeaponsmithClass();
	private TorchmanClass torchman = new TorchmanClass();

	private ZombieClass zombieClass = new ZombieClass();
	private SkeletonClass skeletonClass = new SkeletonClass();
	private CreeperClass creeperClass = new CreeperClass();
	private DragonClass dragonClass = new DragonClass();

	private long kingCooldown = System.currentTimeMillis() / 1000;
	private long peenutCooldown = System.currentTimeMillis() / 1000;
	private long dmacCooldown = System.currentTimeMillis() / 1000;
	private long zatrickCooldown = System.currentTimeMillis() / 1000;
	private long hottubCooldown = System.currentTimeMillis() / 1000;
	private long dragonFireballCooldown = System.currentTimeMillis() / 1000;
	private long dragonSecondaryFireballCooldown = System.currentTimeMillis() / 1000;
	private long dragonLavaCooldown = System.currentTimeMillis() / 1000;

	private boolean systemReset = true;
	private boolean releaseMonsters = false;
	private boolean dragonActive = false;
	private int deathCounter;
	private int dragonDeathNumber;
	private Player dragonPlayer;
	private ItemStack ruleBook;
	private boolean last5 = false;
	private BossBar bar;

	private ArrayList<Player> alive = new ArrayList<Player>();

	private Hashtable<String, Drill> drills = new Hashtable<String, Drill>();

	private int mapCode = 0;

	public static MonstersVsKingsmen instance;

	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		removeRecipes();
		baker.addFurnaceRecipe(this);
		armorsmith.addFurnaceRecipe(this);
		instance = this;
		makeRuleBook();
		
//		Bukkit.getScheduler().scheduleSyncRepeatingTask(instance, new Runnable() {
//		    @Override
//		    public void run() {
//		    	for (Player p : alive) {
//					Location ploc = p.getLocation();
//					Location bloc = new Location(ploc.getWorld(), ploc.getX(), ploc.getY(), ploc.getZ());
//					int lightLevel = bloc.getBlock().getLightLevel();
//					
//					if (lightLevel <= 0) {
//						p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 60, 1));
//					}
//				}
//		    }
//		}, 0L, 20L);
	}

	@Override
	public void onDisable() {

	}

	public static int scheduleSyncDelayedTask(Runnable runnable, int delay) {
		return Bukkit.getScheduler().scheduleSyncDelayedTask(instance, runnable, delay);
	}

	public boolean kingInteractBlock(Player player, PlayerInventory inventory, PlayerInteractEvent event) {
		// King Class
		if (inventory.getItemInMainHand().getType() == Material.SLIME_BALL) {
			event.setCancelled(true);
			if (System.currentTimeMillis() / 1000 - kingCooldown >= 20) {
				king.thorsHammer(player);
				kingCooldown = System.currentTimeMillis() / 1000;
			} else {
				instance.getServer().getPlayer(player.getDisplayName()).sendMessage(
						"Cooldown: " + (20 - (System.currentTimeMillis() / 1000 - kingCooldown)) + " seconds.");
			}
			return true;
		} else if (inventory.getItemInMainHand().getType() == Material.PUFFERFISH_SPAWN_EGG) {
			event.setCancelled(true);

			player.teleport(new Location(Bukkit.getWorld("MvsK"), Bukkit.getWorld("MvsK").getSpawnLocation().getX(),
					Bukkit.getWorld("MvsK").getSpawnLocation().getY(),
					Bukkit.getWorld("MvsK").getSpawnLocation().getZ()));
			player.setGameMode(GameMode.SURVIVAL);
			king.setClass(player, nobles, king, peenut, dmac, zatrick, hottub);
			return true;
		}
		return false;
	}

	public boolean peenutInteractBlock(Player player, PlayerInventory inventory, PlayerInteractEvent event) {
		// Peenut Class
		if (inventory.getItemInMainHand().getType() == Material.BLAZE_POWDER) {
			event.setCancelled(true);
			if (System.currentTimeMillis() / 1000 - peenutCooldown >= 1) {
				peenut.fireBall(player);
				peenutCooldown = System.currentTimeMillis() / 1000;
			}
			return true;
		} else if (inventory.getItemInMainHand().getType() == Material.TURTLE_SPAWN_EGG) {
			event.setCancelled(true);
			player.teleport(new Location(Bukkit.getWorld("MvsK"), Bukkit.getWorld("MvsK").getSpawnLocation().getX(),
					Bukkit.getWorld("MvsK").getSpawnLocation().getY(),
					Bukkit.getWorld("MvsK").getSpawnLocation().getZ()));
			player.setGameMode(GameMode.SURVIVAL);
			peenut.setClass(player, nobles, king, peenut, dmac, zatrick, hottub);
			return true;
		}
		return false;
	}

	public boolean dmacInteractBlock(Player player, PlayerInventory inventory, PlayerInteractEvent event) {
		// DMac Class
		if (inventory.getItemInMainHand().getType() == Material.END_CRYSTAL) {
			event.setCancelled(true);
			if (System.currentTimeMillis() / 1000 - dmacCooldown >= 90) {
				dmac.aoeEffect(player);
				dmacCooldown = System.currentTimeMillis() / 1000;
			} else {
				instance.getServer().getPlayer(player.getDisplayName()).sendMessage(
						"Cooldown: " + (90 - (System.currentTimeMillis() / 1000 - dmacCooldown)) + " seconds.");
			}
			return true;
		} else if (inventory.getItemInMainHand().getType() == Material.SALMON_SPAWN_EGG) {
			event.setCancelled(true);
			player.teleport(new Location(Bukkit.getWorld("MvsK"), Bukkit.getWorld("MvsK").getSpawnLocation().getX(),
					Bukkit.getWorld("MvsK").getSpawnLocation().getY(),
					Bukkit.getWorld("MvsK").getSpawnLocation().getZ()));
			player.setGameMode(GameMode.SURVIVAL);
			dmac.setClass(player, nobles, king, peenut, dmac, zatrick, hottub);
			return true;
		}
		return false;
	}

	public boolean zatrickInteractBlock(Player player, PlayerInventory inventory, PlayerInteractEvent event) {
		// Zatrick Class
		if (inventory.getItemInMainHand().getType() == Material.CRIMSON_ROOTS) {
			event.setCancelled(true);
			if (System.currentTimeMillis() / 1000 - zatrickCooldown >= 90) {
				zatrick.needHealing(player);
				zatrickCooldown = System.currentTimeMillis() / 1000;
			} else {
				instance.getServer().getPlayer(player.getDisplayName()).sendMessage(
						"Cooldown: " + (90 - (System.currentTimeMillis() / 1000 - zatrickCooldown)) + " seconds.");
			}
			return true;
		} else if (inventory.getItemInMainHand().getType() == Material.MOOSHROOM_SPAWN_EGG) {
			event.setCancelled(true);
			player.teleport(new Location(Bukkit.getWorld("MvsK"), Bukkit.getWorld("MvsK").getSpawnLocation().getX(),
					Bukkit.getWorld("MvsK").getSpawnLocation().getY(),
					Bukkit.getWorld("MvsK").getSpawnLocation().getZ()));
			player.setGameMode(GameMode.SURVIVAL);
			zatrick.setClass(player, nobles, king, peenut, dmac, zatrick, hottub);
			return true;
		}
		return false;
	}

	public boolean hottubInteractBlock(Player player, PlayerInventory inventory, PlayerInteractEvent event) {
		// HotTub Class
		if (inventory.getItemInMainHand().getType() == Material.BLAZE_ROD) {
			event.setCancelled(true);
			if (System.currentTimeMillis() / 1000 - hottubCooldown >= 90) {
				hottub.invisibility(player);
				hottubCooldown = System.currentTimeMillis() / 1000;
			} else {
				instance.getServer().getPlayer(player.getDisplayName()).sendMessage(
						"Cooldown: " + (90 - (System.currentTimeMillis() / 1000 - hottubCooldown)) + " seconds.");
			}
			return true;
		} else if (inventory.getItemInMainHand().getType() == Material.ENDERMITE_SPAWN_EGG) {
			event.setCancelled(true);
			player.teleport(new Location(Bukkit.getWorld("MvsK"), Bukkit.getWorld("MvsK").getSpawnLocation().getX(),
					Bukkit.getWorld("MvsK").getSpawnLocation().getY(),
					Bukkit.getWorld("MvsK").getSpawnLocation().getZ()));
			player.setGameMode(GameMode.SURVIVAL);
			hottub.setClass(player, nobles, king, peenut, dmac, zatrick, hottub);
			return true;
		}
		return false;
	}

	public boolean builderInteractBlock(Player player, PlayerInventory inventory, PlayerInteractEvent event) {
		// Builder Class
		if (event.getClickedBlock() != null && event.getClickedBlock().getType() == Material.STONECUTTER
				&& event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			event.setCancelled(true);
			drills = builder.getDrills();
			if (drills.containsKey(player.getDisplayName()) && drills.get(player.getDisplayName()).getBlockLocation()
					.equals(event.getClickedBlock().getLocation())) {
				Drill drill = drills.get(player.getDisplayName());
				drill.openDrill(player);
			}
			return true;
		} else if (inventory.getItemInMainHand().getType() == Material.ENDERMAN_SPAWN_EGG) {
			event.setCancelled(true);
			player.teleport(new Location(Bukkit.getWorld("MvsK"), Bukkit.getWorld("MvsK").getSpawnLocation().getX(),
					Bukkit.getWorld("MvsK").getSpawnLocation().getY(),
					Bukkit.getWorld("MvsK").getSpawnLocation().getZ()));
			player.setGameMode(GameMode.SURVIVAL);
			builder.setClass(player, nobles, king, peenut, dmac, zatrick, hottub);
			return true;
		}
		return false;
	}

	public boolean bakerInteractBlock(Player player, PlayerInventory inventory, PlayerInteractEvent event) {
		// Baker Class
		if (inventory.getItemInMainHand().getType() == Material.ZOGLIN_SPAWN_EGG) {
			event.setCancelled(true);
			player.teleport(new Location(Bukkit.getWorld("MvsK"), Bukkit.getWorld("MvsK").getSpawnLocation().getX(),
					Bukkit.getWorld("MvsK").getSpawnLocation().getY(),
					Bukkit.getWorld("MvsK").getSpawnLocation().getZ()));
			player.setGameMode(GameMode.SURVIVAL);
			baker.setClass(player, nobles, king, peenut, dmac, zatrick, hottub);
			return true;
		}
		// Eat Cake Effect
		else if (event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getClickedBlock().getType() == Material.CAKE) {
			event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 50, 0));
			event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 0));
			return true;
		}
		return false;
	}

	public boolean armorsmithInteractBlock(Player player, PlayerInventory inventory, PlayerInteractEvent event) {
		// Armorsmith Class
		if (inventory.getItemInMainHand().getType() == Material.OCELOT_SPAWN_EGG) {
			event.setCancelled(true);
			player.teleport(new Location(Bukkit.getWorld("MvsK"), Bukkit.getWorld("MvsK").getSpawnLocation().getX(),
					Bukkit.getWorld("MvsK").getSpawnLocation().getY(),
					Bukkit.getWorld("MvsK").getSpawnLocation().getZ()));
			player.setGameMode(GameMode.SURVIVAL);
			armorsmith.setClass(player, nobles, king, peenut, dmac, zatrick, hottub);
			return true;
		}
		return false;
	}

	public boolean weaponsmithInteractBlock(Player player, PlayerInventory inventory, PlayerInteractEvent event) {
		// Weaponsmith
		if (inventory.getItemInMainHand().getType() == Material.SKELETON_HORSE_SPAWN_EGG) {
			event.setCancelled(true);
			player.teleport(new Location(Bukkit.getWorld("MvsK"), Bukkit.getWorld("MvsK").getSpawnLocation().getX(),
					Bukkit.getWorld("MvsK").getSpawnLocation().getY(),
					Bukkit.getWorld("MvsK").getSpawnLocation().getZ()));
			player.setGameMode(GameMode.SURVIVAL);
			weaponsmith.setClass(player, nobles, king, peenut, dmac, zatrick, hottub);
			return true;
		} else if (inventory.getItemInMainHand().getType() == Material.WHITE_DYE
				&& (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR)) {
			List<Block> los = event.getPlayer().getLineOfSight(null, 5);
			for (Block b : los) {
				if (b.getType() == Material.LAVA) {
					inventory.getItemInMainHand().setAmount(inventory.getItemInMainHand().getAmount() - 1);
					inventory.addItem(new ItemStack(Material.IRON_INGOT, 1));
					event.setCancelled(true);
				}
			}
			return true;
		}
		return false;
	}

	public boolean torchmanInteractBlock(Player player, PlayerInventory inventory, PlayerInteractEvent event) {
		// Torchman
		if (inventory.getItemInMainHand().getType() == Material.TROPICAL_FISH_SPAWN_EGG) {
			event.setCancelled(true);
			player.teleport(new Location(Bukkit.getWorld("MvsK"), Bukkit.getWorld("MvsK").getSpawnLocation().getX(),
					Bukkit.getWorld("MvsK").getSpawnLocation().getY(),
					Bukkit.getWorld("MvsK").getSpawnLocation().getZ()));
			player.setGameMode(GameMode.SURVIVAL);
			torchman.setClass(player, nobles, king, peenut, dmac, zatrick, hottub);
			return true;
		} else if (event.getClickedBlock() != null && event.getClickedBlock().getType() == Material.FIRE
				&& event.getAction() == Action.LEFT_CLICK_BLOCK) {
			if (inventory.getItemInMainHand().getType() == Material.STICK) {
				inventory.getItemInMainHand().setAmount(inventory.getItemInMainHand().getAmount() - 1);
				inventory.addItem(new ItemStack(Material.TORCH, 1));
				event.setCancelled(true);
			}
			return true;
		}
		return false;
	}

	public boolean zombieInteractBlock(Player player, PlayerInventory inventory, PlayerInteractEvent event) {
		// ZombieClass
		if (inventory.getItemInMainHand().getType() == Material.ZOMBIE_SPAWN_EGG) {
			event.setCancelled(true);
			if (releaseMonsters) {
				player.getInventory().clear();
				MonsterTeleport(player);
				player.setGameMode(GameMode.SURVIVAL);
				if ((double) deathCounter / (double) instance.getServer().getOnlinePlayers().size() < 0.5) {
					zombieClass.setClass(player, true);
				} else {
					zombieClass.setClass(player, false);
				}
			}
			return true;
		}
		return false;
	}

	public boolean skeletonInteractBlock(Player player, PlayerInventory inventory, PlayerInteractEvent event) {
		// Skeleton Class
		if (inventory.getItemInMainHand().getType() == Material.SKELETON_SPAWN_EGG) {
			event.setCancelled(true);
			if (releaseMonsters) {
				player.getInventory().clear();
				MonsterTeleport(player);
				player.setGameMode(GameMode.SURVIVAL);
				if ((double) deathCounter / (double) instance.getServer().getOnlinePlayers().size() < 0.5) {
					skeletonClass.setClass(player, true);
				} else {
					skeletonClass.setClass(player, false);
				}
			}
			return true;
		}
		return false;
	}

	public boolean creeperInteractBlock(Player player, PlayerInventory inventory, PlayerInteractEvent event) {
		// Creeper Class
		if (inventory.getItemInMainHand().getType() == Material.CREEPER_SPAWN_EGG) {
			event.setCancelled(true);
			if (releaseMonsters) {
				player.getInventory().clear();
				MonsterTeleport(player);
				player.setGameMode(GameMode.SURVIVAL);
				if ((double) deathCounter / (double) instance.getServer().getOnlinePlayers().size() < 0.5) {
					creeperClass.setClass(player, true);
				} else {
					creeperClass.setClass(player, false);
				}
			}
			return true;
		} else if (inventory.getItemInMainHand().getType() == Material.GUNPOWDER
				&& (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
			Location loc = player.getLocation();
			player.getWorld().createExplosion(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ(), 4, false, true);
			player.damage(1000);
			return true;
		}
		return false;
	}

	public boolean dragonInteractBlock(Player player, PlayerInventory inventory, PlayerInteractEvent event) {
		// Dragon Class
		if (inventory.getItemInMainHand().getType() == Material.DRAGON_EGG
				&& (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
			event.setCancelled(true);
			if (System.currentTimeMillis() / 1000 - dragonFireballCooldown >= 5) {
				dragonClass.dragonFireball(player);
				dragonFireballCooldown = System.currentTimeMillis() / 1000;
			} else {
				instance.getServer().getPlayer(player.getDisplayName()).sendMessage("Cooldown: "
						+ (5 - (System.currentTimeMillis() / 1000 - dragonFireballCooldown)) + " seconds.");
			}
			return true;
		} else if (inventory.getItemInMainHand().getType() == Material.MAGMA_CREAM
				&& (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
			event.setCancelled(true);
			if (System.currentTimeMillis() / 1000 - dragonSecondaryFireballCooldown >= 60) {
				dragonClass.regularFireball(player);
				dragonSecondaryFireballCooldown = System.currentTimeMillis() / 1000;
			} else {
				instance.getServer().getPlayer(player.getDisplayName()).sendMessage("Cooldown: "
						+ (60 - (System.currentTimeMillis() / 1000 - dragonSecondaryFireballCooldown)) + " seconds.");
			}
			return true;
		} else if (inventory.getItemInMainHand().getType() == Material.FIRE_CHARGE
				&& (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
			event.setCancelled(true);
			if (System.currentTimeMillis() / 1000 - dragonLavaCooldown >= 10) {
				dragonClass.lavaAttack(player);
				dragonLavaCooldown = System.currentTimeMillis() / 1000;
			} else {
				instance.getServer().getPlayer(player.getDisplayName()).sendMessage(
						"Cooldown: " + (10 - (System.currentTimeMillis() / 1000 - dragonLavaCooldown)) + " seconds.");
			}
			return true;
		}
		return false;
	}

	@EventHandler
	public void onPlayerInteractBlock(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		PlayerInventory inventory = player.getInventory();

		if (inventory.getItemInMainHand().getType() == Material.BOW
				&& (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK)) {
			ItemStack arrows = new ItemStack(Material.ARROW, 8);
			player.getInventory().addItem(arrows);
		}

		if (last5 == true) {
			for (Player p : Bukkit.getOnlinePlayers()) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 10000, 1));
			}
			last5 = false;
		}

		if (kingInteractBlock(player, inventory, event)) {
			return;
		} else if (peenutInteractBlock(player, inventory, event)) {
			return;
		} else if (dmacInteractBlock(player, inventory, event)) {
			return;
		} else if (zatrickInteractBlock(player, inventory, event)) {
			return;
		} else if (hottubInteractBlock(player, inventory, event)) {
			return;
		} else if (builderInteractBlock(player, inventory, event)) {
			return;
		} else if (bakerInteractBlock(player, inventory, event)) {
			return;
		} else if (armorsmithInteractBlock(player, inventory, event)) {
			return;
		} else if (weaponsmithInteractBlock(player, inventory, event)) {
			return;
		} else if (torchmanInteractBlock(player, inventory, event)) {
			return;
		} else if (zombieInteractBlock(player, inventory, event)) {
			return;
		} else if (skeletonInteractBlock(player, inventory, event)) {
			return;
		} else if (creeperInteractBlock(player, inventory, event)) {
			return;
		} else if (dragonInteractBlock(player, inventory, event)) {
			return;
		}
	}

	@EventHandler
	public void onPlayerKick(PlayerKickEvent event) {
		if (event.getReason().toLowerCase().contains("interact with self")) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onEntityDamage(EntityDamageEvent event) {
		if (event.getEntity() == dragonPlayer && deathCounter < dragonDeathNumber) {
			event.setDamage(0);
			dragonPlayer.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200, 0));
			dragonPlayer.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 200, 0));
		} else if (event.getCause() == DamageCause.FIRE || event.getCause() == DamageCause.FIRE_TICK) {
			event.setDamage(2.5);
		} else if (event instanceof Fireball) {
			event.setDamage(6);
		}
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {

		if (event.getBlock().getType() == Material.STONECUTTER) { // For Armorsmith
			drills = builder.getDrills();
			if (drills.containsKey(event.getPlayer().getDisplayName()) && drills.get(event.getPlayer().getDisplayName())
					.getBlockLocation().equals(event.getBlock().getLocation())) {
				Drill drill = drills.get(event.getPlayer().getDisplayName());
				drill.setPlaced(false);
				drill.setBlockLocation(new Location(event.getPlayer().getWorld(), 0, 0, 0));
			} else {
				event.setCancelled(true);
			}
		} else if (event.getBlock().getType() == Material.OAK_LOG) { // For Torchman
			Block block = event.getBlock();
			Location tempLoc = block.getLocation();
			tempLoc.getBlock().setType(Material.AIR);
			tempLoc.getWorld().dropItemNaturally(tempLoc, new ItemStack(Material.OAK_PLANKS, 1));
			event.setCancelled(true);
		} else if (event.getBlock().getType() == Material.BIRCH_LOG) { // For Torchman
			Block block = event.getBlock();
			Location tempLoc = block.getLocation();
			tempLoc.getBlock().setType(Material.AIR);
			tempLoc.getWorld().dropItemNaturally(tempLoc, new ItemStack(Material.OAK_PLANKS, 1));
			event.setCancelled(true);
		} else if (event.getBlock().getType() == Material.ACACIA_LOG) { // For Torchman
			Block block = event.getBlock();
			Location tempLoc = block.getLocation();
			tempLoc.getBlock().setType(Material.AIR);
			tempLoc.getWorld().dropItemNaturally(tempLoc, new ItemStack(Material.OAK_PLANKS, 1));
			event.setCancelled(true);
		} else if (event.getBlock().getType() == Material.DARK_OAK_LOG) { // For Torchman
			Block block = event.getBlock();
			Location tempLoc = block.getLocation();
			tempLoc.getBlock().setType(Material.AIR);
			tempLoc.getWorld().dropItemNaturally(tempLoc, new ItemStack(Material.OAK_PLANKS, 1));
			event.setCancelled(true);
		} else if (event.getBlock().getType() == Material.NETHERRACK) {
			Block block = event.getBlock();
			Location tempLoc = block.getLocation();
			tempLoc.getBlock().setType(Material.AIR);

			ItemStack stack = new ItemStack(Material.NETHERRACK, 1);
			ItemMeta meta = stack.getItemMeta();
			meta.setDisplayName("Compressed Coal");
			stack.setItemMeta(meta);

			tempLoc.getWorld().dropItemNaturally(tempLoc, stack);
			event.setCancelled(true);
		} else if (!event.getPlayer().isOp() && (event.getBlock().getType() == Material.STONE
				|| event.getBlock().getType() == Material.GRANITE || event.getBlock().getType() == Material.DIORITE
				|| event.getBlock().getType() == Material.ANDESITE)) { // So players cannot dig down.
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		if (event.getBlock().getType() == Material.STONECUTTER) {
			drills = builder.getDrills();
			if (drills.containsKey(event.getPlayer().getDisplayName())
					&& !drills.get(event.getPlayer().getDisplayName()).isPlaced()) {
				Drill drill = drills.get(event.getPlayer().getDisplayName());
				drill.setPlaced(true);
				drill.setBlockLocation(event.getBlock().getLocation());
			} else {
				event.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onPistonExtend(BlockPistonExtendEvent event) {
		if (event.getDirection() == BlockFace.UP) {
			for (final Block block : event.getBlocks()) {
				if (block.getType() == Material.COAL_BLOCK) {
					Random rand = new Random();
					if (rand.nextInt(3) == 1) {
						MonstersVsKingsmen.scheduleSyncDelayedTask(new Runnable() {
							public void run() {
								Location tempLoc = block.getLocation().add(0, 1, 0);
								tempLoc.getBlock().setType(Material.NETHERRACK);
							}
						}, 1);
					}
				} else if (block.getType() == Material.NETHERRACK) {
					MonstersVsKingsmen.scheduleSyncDelayedTask(new Runnable() {

						public void run() {
							Location tempLoc = block.getLocation().add(0, 1, 0);
							tempLoc.getBlock().setType(Material.AIR);
							tempLoc.getWorld().dropItem(tempLoc, new ItemStack(Material.COAL, 5));
						}

					}, 1);
				}
			}
		}
	}

	@EventHandler
	public void changeFoodLevelChange(FoodLevelChangeEvent event) {
		if (event instanceof Player) {
			Player player = (Player) event.getEntity();

			if (event.getItem().getType() == Material.COOKIE) {
				player.setFoodLevel(player.getFoodLevel() + 6);
				player.setSaturation(15);
			} else if (event.getItem().getType() == Material.BREAD) {
				player.setSaturation(10);
			}
		}
	}

	@EventHandler
	public void onFurnaceSmelt(FurnaceSmeltEvent event) {
		ItemStack cookie = new ItemStack(Material.COOKIE);
		ItemStack cake = new ItemStack(Material.CAKE);
		ItemStack bread = new ItemStack(Material.BREAD);

		if (event.getSource().getType() == Material.WHEAT) {
			Random rand = new Random();
			int odds = rand.nextInt(6);

			if (odds == 0) {
				event.setResult(cookie);
			} else if (odds == 1 || odds == 2) {
				event.setResult(cake);
			} else {
				event.setResult(bread);
			}
		}
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		event.getPlayer().getInventory().clear();
		event.getPlayer().teleport(new Location(Bukkit.getWorld("world"), 0, 5, 0, 180, 0));
		if (bar != null && !systemReset) {
			bar.addPlayer(event.getPlayer());
		}
		if (!systemReset) {
			ItemStack spawn = new ItemStack(Material.ZOMBIE_SPAWN_EGG, 1);
			event.getPlayer().getInventory().addItem(spawn);
		}
	}

	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent event) {
		if (alive.contains(event.getPlayer())) {
			alive.remove(event.getPlayer());
		}
		deathCounter += 1;
	}

	@EventHandler
	public void onPlayerMunching(PlayerItemConsumeEvent event) {
		if (event.getItem().getType() == Material.BREAD) {
			event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 50, 0));
		} else if (event.getItem().getType() == Material.COOKIE) {
			event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 50, 0));
			event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 0));
			event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 150, 0));
		}
	}

	@EventHandler
	public void onAnvilCraft(PrepareAnvilEvent event) {
		AnvilInventory inv = event.getInventory();
		if (inv.contains(Material.WOODEN_SWORD) && inv.contains(Material.IRON_INGOT)) {
			event.setResult(new ItemStack(Material.IRON_SWORD, 1));
		} else if (inv.contains(Material.IRON_SWORD) && inv.contains(Material.DIAMOND)) {
			event.setResult(new ItemStack(Material.DIAMOND_SWORD, 1));
		}
	}

	@EventHandler
	public void anvilClick(InventoryClickEvent event) {
		ItemStack item1 = event.getInventory().getItem(0);
		ItemStack item2 = event.getInventory().getItem(1);

		if (item1 == null || item2 == null) {
			return;
		}

		if (event.getInventory().getType().equals(InventoryType.ANVIL)
				&& ((item1.getType() == Material.WOODEN_SWORD && item2.getType() == Material.IRON_INGOT)
						|| (item2.getType() == Material.WOODEN_SWORD && item1.getType() == Material.IRON_INGOT))) {
			ItemStack result = new ItemStack(Material.IRON_SWORD);
			event.getInventory().setItem(2, result);

			if (event.getSlotType().equals(InventoryType.SlotType.RESULT) && event.getClick().equals(ClickType.LEFT)
					|| event.getClick().equals(ClickType.SHIFT_LEFT)) {
				event.getWhoClicked().setItemOnCursor(result);
				event.getInventory().clear();
			}
		} else if (event.getInventory().getType().equals(InventoryType.ANVIL)
				&& ((item1.getType() == Material.IRON_SWORD && item2.getType() == Material.DIAMOND)
						|| (item2.getType() == Material.IRON_SWORD && item1.getType() == Material.DIAMOND))) {
			ItemStack result = new ItemStack(Material.DIAMOND_SWORD);
			event.getInventory().setItem(2, result);

			if (event.getSlotType().equals(InventoryType.SlotType.RESULT) && event.getClick().equals(ClickType.LEFT)
					|| event.getClick().equals(ClickType.SHIFT_LEFT)) {
				event.getWhoClicked().setItemOnCursor(result);
				event.getInventory().clear();
			}
		}
	}

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		event.getDrops().clear();

		drills = builder.getDrills();
		if (drills.containsKey(event.getEntity().getPlayer().getDisplayName())) {
			drills.remove(event.getEntity().getPlayer().getDisplayName());
		}

		if (alive.contains(event.getEntity().getPlayer())) {
			alive.remove(event.getEntity().getPlayer());
			deathCounter++;
		}

		if (deathCounter >= dragonDeathNumber && releaseMonsters == false && dragonActive == true) {
			releaseMonsters = true;
			Bukkit.broadcastMessage(ChatColor.DARK_RED + "The monsters have been released.");
			dragonPlayer.setAllowFlight(false);
			dragonPlayer.setHealth(0);
			dragonPlayer = null;
			dragonActive = false;

			bar = Bukkit.getServer().createBossBar("Time Until Reinforcements", BarColor.WHITE, BarStyle.SEGMENTED_20);
			bar.addFlag(BarFlag.CREATE_FOG);
			bar.addFlag(BarFlag.DARKEN_SKY);
			bar.setProgress(1.0);
			for (Player p : Bukkit.getOnlinePlayers()) {
				bar.addPlayer(p);
			}
			bar.setVisible(true);
			Thread thread = new Thread() {
				public void run() {
					double totalTime = 1200;
					double timeLeft = totalTime;
					while (timeLeft > 0) {
						try {
							TimeUnit.SECONDS.sleep(1);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						timeLeft -= 1;
						bar.setProgress(timeLeft / totalTime);

						if (timeLeft == 300) {
							last5 = true;
						}

						if (systemReset) {
							break;
						}
					}

					if (!systemReset) {
						Bukkit.broadcastMessage(ChatColor.GREEN + "**The Kingsmen have won!!!!**");
						Bukkit.broadcastMessage(ChatColor.GREEN + "**The Kingsmen have won!!!!**");
						Bukkit.broadcastMessage(ChatColor.GREEN + "**The Kingsmen have won!!!!**");
						Bukkit.broadcastMessage(ChatColor.GREEN + "**The Kingsmen have won!!!!**");
						Bukkit.broadcastMessage(ChatColor.GREEN + "**The Kingsmen have won!!!!**");
						for (Player p : Bukkit.getOnlinePlayers()) {
							bar.removePlayer(p);
						}
					}
				}
			};
			thread.start();
		}
	}

	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		event.getPlayer().setGameMode(GameMode.ADVENTURE);
		if (!systemReset) {
			Inventory inv = event.getPlayer().getInventory();
			Random rand = new Random();
			int monster = rand.nextInt(75);
			if (monster >= 0 && monster < 25) {
				ItemStack spawn = new ItemStack(Material.ZOMBIE_SPAWN_EGG, 1);
				inv.addItem(spawn);
			} else if (monster >= 25 && monster < 50) {
				ItemStack spawn = new ItemStack(Material.SKELETON_SPAWN_EGG, 1);
				inv.addItem(spawn);
			} else {
				ItemStack spawn = new ItemStack(Material.CREEPER_SPAWN_EGG, 1);
				inv.addItem(spawn);
			}
		}
	}

	public void removeRecipes() {
		Iterator<Recipe> it = getServer().recipeIterator();
		Recipe recipe;
		while (it.hasNext()) {
			recipe = it.next();
			if (recipe != null && recipe.getResult().getType() == Material.BREAD) {
				it.remove();
			} else if (recipe != null && recipe.getResult().getType() == Material.COOKIE) {
				it.remove();
			} else if (recipe != null && recipe.getResult().getType() == Material.CAKE) {
				it.remove();
			} else if (recipe != null && recipe.getResult().getType() == Material.TORCH) {
				it.remove();
			} else if (recipe != null && recipe.getResult().getType() == Material.DIAMOND_SWORD) {
				it.remove();
			} else if (recipe != null && recipe.getResult().getType() == Material.IRON_SWORD) {
				it.remove();
			} else if (recipe != null && recipe.getResult().getType() == Material.BLACK_BED) {
				it.remove();
			} else if (recipe != null && recipe.getResult().getType() == Material.BLUE_BED) {
				it.remove();
			} else if (recipe != null && recipe.getResult().getType() == Material.BROWN_BED) {
				it.remove();
			} else if (recipe != null && recipe.getResult().getType() == Material.CYAN_BED) {
				it.remove();
			} else if (recipe != null && recipe.getResult().getType() == Material.GRAY_BED) {
				it.remove();
			} else if (recipe != null && recipe.getResult().getType() == Material.GREEN_BED) {
				it.remove();
			} else if (recipe != null && recipe.getResult().getType() == Material.LIGHT_BLUE_BED) {
				it.remove();
			} else if (recipe != null && recipe.getResult().getType() == Material.LIGHT_GRAY_BED) {
				it.remove();
			} else if (recipe != null && recipe.getResult().getType() == Material.LIME_BED) {
				it.remove();
			} else if (recipe != null && recipe.getResult().getType() == Material.MAGENTA_BED) {
				it.remove();
			} else if (recipe != null && recipe.getResult().getType() == Material.ORANGE_BED) {
				it.remove();
			} else if (recipe != null && recipe.getResult().getType() == Material.PINK_BED) {
				it.remove();
			} else if (recipe != null && recipe.getResult().getType() == Material.PURPLE_BED) {
				it.remove();
			} else if (recipe != null && recipe.getResult().getType() == Material.RED_BED) {
				it.remove();
			} else if (recipe != null && recipe.getResult().getType() == Material.WHITE_BED) {
				it.remove();
			} else if (recipe != null && recipe.getResult().getType() == Material.YELLOW_BED) {
				it.remove();
			}
		}
	}

	public void recordAllKingsmen() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			alive.add(p);
		}
	}

	public void startGameCommand() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 37000, 1));
		}
		deathCounter = 0;
		systemReset = false;
		SetUpLobby setup = new SetUpLobby();
		Bukkit.getWorld("MvsK").setTime(0);
		setup.assignRoles();
		setup.assignNobles(instance);
		nobles = setup.getNobles();
		setDragonDeathNumber();
		recordAllKingsmen();
		MonstersVsKingsmen.scheduleSyncDelayedTask(new Runnable() {
			public void run() {
				int random = new Random().nextInt(instance.getServer().getOnlinePlayers().size() - 1);
				for (Player dragon : Bukkit.getOnlinePlayers()) {
					if (random == 0 && deathCounter <= dragonDeathNumber) {
						dragonPlayer = dragon;
						MonsterTeleport(dragonPlayer);
						dragonPlayer.getInventory().clear();
						dragonClass.setClass(dragonPlayer);
						dragonActive = true;

						drills = builder.getDrills();
						if (drills.containsKey(dragonPlayer.getDisplayName())) {
							drills.remove(dragonPlayer.getDisplayName());
						}

						Bukkit.broadcastMessage(ChatColor.RED + "**THE DRAGON IS COMING!!**");
						Bukkit.broadcastMessage(ChatColor.RED + "**THE DRAGON IS COMING!!**");
						Bukkit.broadcastMessage(ChatColor.RED + "**THE DRAGON IS COMING!!**");
						Bukkit.broadcastMessage(ChatColor.RED + "**THE DRAGON IS COMING!!**");
						Bukkit.broadcastMessage(ChatColor.RED + "**THE DRAGON IS COMING!!**");
						alive.remove(dragonPlayer);
						deathCounter += 1;
					}
					random -= 1;
				}
				if (deathCounter > dragonDeathNumber) {
					releaseMonsters = true;
					Bukkit.broadcastMessage(ChatColor.DARK_RED + "The monsters have been released.");

					bar = Bukkit.getServer().createBossBar("Time Until Reinforcements", BarColor.WHITE,
							BarStyle.SEGMENTED_20);
					bar.addFlag(BarFlag.CREATE_FOG);
					bar.addFlag(BarFlag.DARKEN_SKY);
					bar.setProgress(1.0);
					for (Player p : Bukkit.getOnlinePlayers()) {
						bar.addPlayer(p);
					}
					bar.setVisible(true);
					Thread thread = new Thread() {
						public void run() {
							double totalTime = 1200;
							double timeLeft = totalTime;
							while (timeLeft > 0) {
								try {
									TimeUnit.SECONDS.sleep(1);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								timeLeft -= 1;
								bar.setProgress(timeLeft / totalTime);

								if (timeLeft == 300) {
									last5 = true;
								}

								if (systemReset) {
									break;
								}
							}

							if (!systemReset) {
								Bukkit.broadcastMessage(ChatColor.GREEN + "**The Kingsmen have won!!!!**");
								Bukkit.broadcastMessage(ChatColor.GREEN + "**The Kingsmen have won!!!!**");
								Bukkit.broadcastMessage(ChatColor.GREEN + "**The Kingsmen have won!!!!**");
								Bukkit.broadcastMessage(ChatColor.GREEN + "**The Kingsmen have won!!!!**");
								Bukkit.broadcastMessage(ChatColor.GREEN + "**The Kingsmen have won!!!!**");
								for (Player p : Bukkit.getOnlinePlayers()) {
									bar.removePlayer(p);
								}
							}
						}
					};
					thread.start();
				}
			}
		}, 37000); // 37000 time for 1 1/2 days
	}

	public void destroyWorldCommand() {
		deathCounter = 0;
		systemReset = true;
		releaseMonsters = false;
		alive.removeAll(alive);

		for (Player p : Bukkit.getOnlinePlayers()) {
			p.getInventory().clear();
			p.setHealth(0);
			if (bar != null) {
				bar.removePlayer(p);
			}
		}

		bar = null;

		if (Bukkit.getWorld("MvsK") != null) {
			Bukkit.unloadWorld("MvsK", false);
		}
		File gameWorld = new File("MvsK");
		try {
			FileUtils.deleteDirectory(gameWorld);
			Bukkit.broadcastMessage("[Server]: World destroyed");
		} catch (IOException e) {
			Bukkit.broadcastMessage("[Server]: World could not be destroyed");
		}
	}

	public void buildWorldCommand() {
		WorldCreator wc = new WorldCreator("MvsK");
		wc.environment(World.Environment.NORMAL);
		wc.type(WorldType.NORMAL);
		wc.generateStructures(false);
		setMapCode(wc);
		wc.createWorld();
		Bukkit.broadcastMessage("[Server]: World generated");

		setMapBorder();
	}

	public void spawnDragonCommand() {
		int random = new Random().nextInt(instance.getServer().getOnlinePlayers().size() - 1);
		for (Player dragon : Bukkit.getOnlinePlayers()) {
			if (random == 0) {
				dragonPlayer = dragon;
				MonsterTeleport(dragonPlayer);
				dragonClass.setClass(dragonPlayer);

				drills = builder.getDrills();
				if (drills.containsKey(dragonPlayer.getDisplayName())) {
					drills.remove(dragonPlayer.getDisplayName());
				}

				Bukkit.broadcastMessage(ChatColor.RED + "**THE DRAGON IS COMING!!**");
				Bukkit.broadcastMessage(ChatColor.RED + "**THE DRAGON IS COMING!!**");
				Bukkit.broadcastMessage(ChatColor.RED + "**THE DRAGON IS COMING!!**");
				Bukkit.broadcastMessage(ChatColor.RED + "**THE DRAGON IS COMING!!**");
				Bukkit.broadcastMessage(ChatColor.RED + "**THE DRAGON IS COMING!!**");
				alive.remove(dragonPlayer);
				deathCounter += 1;
			}
			random -= 1;
		}
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (cmd.getName().equalsIgnoreCase("rules")) {
				player.getInventory().addItem(ruleBook);
			} else if (player.isOp() && cmd.getName().equalsIgnoreCase("startgame")) {
				startGameCommand();
			} else if (player.isOp() && cmd.getName().equalsIgnoreCase("destroyworld")) {
				destroyWorldCommand();
			} else if (player.isOp() && cmd.getName().equalsIgnoreCase("buildworld")) {
				buildWorldCommand();
			} else if (player.isOp() && cmd.getName().equalsIgnoreCase("spawndragon")) {
				spawnDragonCommand();
			}
		}
		return true;
	}

	public void setDragonDeathNumber() {
		double numPlayers = instance.getServer().getOnlinePlayers().size();
		numPlayers *= 0.35;
		dragonDeathNumber = (int) numPlayers + 1;
	}

	public void makeRuleBook() {
		List<String> pages = new ArrayList<String>();
		pages.add("Welcome to Monsters vs Kingsmen!!\n\n" + "This is how to play the game.");
		pages.add(
				"The game starts in set up phase. Each player is assigned a role and each role has a specific duty to fulfill. "
						+ "For the first two days, everyone must work together to get geared up and ready to fight.");
		pages.add("At the start of the second night, one player will be randomly selected to spawn in as a dragon. "
				+ "The dragon's job is to kill as many players as it can in order to start the horde of monsters.");
		pages.add("For the rest of the game, its the monsters job to fight against the kingsmen until all are dead. "
				+ "Meanwhile the kingsmen are fighting to stay alive.");

		ruleBook = new ItemStack(Material.WRITTEN_BOOK, 1);
		BookMeta bookMeta = (BookMeta) ruleBook.getItemMeta();
		bookMeta.setTitle("Monsters vs Kingsmen");
		bookMeta.setAuthor("K1ng");
		bookMeta.setPages(pages);
		ruleBook.setItemMeta(bookMeta);
	}

	public void MonsterTeleport(Player monster) {
		if (mapCode == 1) {
			monster.teleport(new Location(Bukkit.getWorld("MvsK"), 9, 88, 9, 180, 0));
		} else if (mapCode == 2) {
			monster.teleport(new Location(Bukkit.getWorld("MvsK"), -333, 81, -380, -50, 0));
		} else if (mapCode == 3) {
			monster.teleport(new Location(Bukkit.getWorld("MvsK"), 192, 79, -238, 0, 0));
		} else if (mapCode == 4) {
			monster.teleport(new Location(Bukkit.getWorld("MvsK"), -16, 71, -391, 0, 0));
		} else if (mapCode == 5) {
			monster.teleport(new Location(Bukkit.getWorld("MvsK"), 28, 92, 270, 100, 0));
		}
	}

	public void setMapCode(WorldCreator wc) {
		mapCode++;

		if (mapCode == 6) {
			mapCode = 1;
		}

		if (mapCode == 1) {
			wc.seed(12542);
		} else if (mapCode == 2) {
			wc.seed(3227028068011494221L);
		} else if (mapCode == 3) {
			wc.seed(-5256854541870744071L);
		} else if (mapCode == 4) {
			wc.seed(-737846020L);
		} else if (mapCode == 5) {
			wc.seed(7000);
		}
	}

	public void setMapBorder() {
		if (mapCode == 1) {
			World world = Bukkit.getWorld("MvsK");
			WorldBorder border = world.getWorldBorder();
			border.setSize(100);
			border.setCenter(54.0, -164.0);
			border.setDamageAmount(0.0);
		} else if (mapCode == 2) {
			World world = Bukkit.getWorld("MvsK");
			WorldBorder border = world.getWorldBorder();
			border.setSize(100);
			border.setCenter(-207.0, -248.0);
			border.setDamageAmount(0.0);
		} else if (mapCode == 3) {
			World world = Bukkit.getWorld("MvsK");
			WorldBorder border = world.getWorldBorder();
			border.setSize(100);
			border.setCenter(185.0, -48.0);
			border.setDamageAmount(0.0);
		} else if (mapCode == 4) {
			World world = Bukkit.getWorld("MvsK");
			WorldBorder border = world.getWorldBorder();
			border.setSize(100);
			border.setCenter(-35.0, -239.0);
			border.setDamageAmount(0.0);
		} else if (mapCode == 5) {
			World world = Bukkit.getWorld("MvsK");
			WorldBorder border = world.getWorldBorder();
			border.setSize(100);
			border.setCenter(-120.0, 234.0);
			border.setDamageAmount(0.0);
		}
	}
}
