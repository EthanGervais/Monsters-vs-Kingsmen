package plugin.monstersvskingsmen;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.libs.org.apache.commons.io.FileUtils;
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
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.Recipe;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
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
	private long dragonCooldown = System.currentTimeMillis() / 1000;
	private boolean systemReset = false;

	private Hashtable<String, Drill> drills = new Hashtable<String, Drill>();

	public static MonstersVsKingsmen instance;

	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		removeRecipes();
		baker.addFurnaceRecipe(this);
		armorsmith.addFurnaceRecipe(this);
		instance = this;
	}

	@Override
	public void onDisable() {

	}

	public static int scheduleSyncDelayedTask(Runnable runnable, int delay) {
		return Bukkit.getScheduler().scheduleSyncDelayedTask(instance, runnable, delay);
	}

	@EventHandler
	public void onPlayerInteractBlock(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		PlayerInventory inventory = player.getInventory();

		// King Class
		if (inventory.getItemInMainHand().getType() == Material.SLIME_BALL) {
			event.setCancelled(true);
			if (System.currentTimeMillis() / 1000 - kingCooldown >= 20) {
				king.thorsHammer(player);
				kingCooldown = System.currentTimeMillis() / 1000;
			}
		} else if (inventory.getItemInMainHand().getType() == Material.PUFFERFISH_SPAWN_EGG) {
			event.setCancelled(true);

			player.teleport(new Location(Bukkit.getWorld("MvsK"), Bukkit.getWorld("MvsK").getSpawnLocation().getX(),
					Bukkit.getWorld("MvsK").getSpawnLocation().getY(),
					Bukkit.getWorld("MvsK").getSpawnLocation().getZ()));
			player.setGameMode(GameMode.SURVIVAL);
			king.giveItems(player);
		}

		// Peenut Class
		if (inventory.getItemInMainHand().getType() == Material.BLAZE_POWDER) {
			event.setCancelled(true);
			if (System.currentTimeMillis() / 1000 - peenutCooldown >= 1) {
				peenut.fireBall(player);
				peenutCooldown = System.currentTimeMillis() / 1000;
			}
		} else if (inventory.getItemInMainHand().getType() == Material.TURTLE_SPAWN_EGG) {
			event.setCancelled(true);
			player.teleport(new Location(Bukkit.getWorld("MvsK"), Bukkit.getWorld("MvsK").getSpawnLocation().getX(),
					Bukkit.getWorld("MvsK").getSpawnLocation().getY(),
					Bukkit.getWorld("MvsK").getSpawnLocation().getZ()));
			player.setGameMode(GameMode.SURVIVAL);
			peenut.giveItems(player);
		}

		// DMac Class
		if (inventory.getItemInMainHand().getType() == Material.END_CRYSTAL) {
			event.setCancelled(true);
			if (System.currentTimeMillis() / 1000 - dmacCooldown >= 90) {
				dmac.aoeEffect(player);
				dmacCooldown = System.currentTimeMillis() / 1000;
			}
		} else if (inventory.getItemInMainHand().getType() == Material.SALMON_SPAWN_EGG) {
			event.setCancelled(true);
			player.teleport(new Location(Bukkit.getWorld("MvsK"), Bukkit.getWorld("MvsK").getSpawnLocation().getX(),
					Bukkit.getWorld("MvsK").getSpawnLocation().getY(),
					Bukkit.getWorld("MvsK").getSpawnLocation().getZ()));
			player.setGameMode(GameMode.SURVIVAL);
			dmac.giveItems(player);
		}

		// Zatrick Class
		if (inventory.getItemInMainHand().getType() == Material.CRIMSON_ROOTS) {
			event.setCancelled(true);
			if (System.currentTimeMillis() / 1000 - zatrickCooldown >= 90) {
				zatrick.needHealing(player);
				zatrickCooldown = System.currentTimeMillis() / 1000;
			}
		} else if (inventory.getItemInMainHand().getType() == Material.MOOSHROOM_SPAWN_EGG) {
			event.setCancelled(true);
			player.teleport(new Location(Bukkit.getWorld("MvsK"), Bukkit.getWorld("MvsK").getSpawnLocation().getX(),
					Bukkit.getWorld("MvsK").getSpawnLocation().getY(),
					Bukkit.getWorld("MvsK").getSpawnLocation().getZ()));
			player.setGameMode(GameMode.SURVIVAL);
			zatrick.giveItems(player);
		}

		// HotTub Class
		if (inventory.getItemInMainHand().getType() == Material.END_PORTAL_FRAME) {
			event.setCancelled(true);
			if (System.currentTimeMillis() / 1000 - hottubCooldown >= 90) {
				hottub.invisibility(player);
				hottubCooldown = System.currentTimeMillis() / 1000;
			}
		} else if (inventory.getItemInMainHand().getType() == Material.ENDERMITE_SPAWN_EGG) {
			event.setCancelled(true);
			player.teleport(new Location(Bukkit.getWorld("MvsK"), Bukkit.getWorld("MvsK").getSpawnLocation().getX(),
					Bukkit.getWorld("MvsK").getSpawnLocation().getY(),
					Bukkit.getWorld("MvsK").getSpawnLocation().getZ()));
			player.setGameMode(GameMode.SURVIVAL);
			hottub.giveItems(player);
		}

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
		} else if (inventory.getItemInMainHand().getType() == Material.ENDERMAN_SPAWN_EGG) {
			event.setCancelled(true);
			player.teleport(new Location(Bukkit.getWorld("MvsK"), Bukkit.getWorld("MvsK").getSpawnLocation().getX(),
					Bukkit.getWorld("MvsK").getSpawnLocation().getY(),
					Bukkit.getWorld("MvsK").getSpawnLocation().getZ()));
			player.setGameMode(GameMode.SURVIVAL);
			builder.giveItems(player);
		}

		// Baker Class
		if (inventory.getItemInMainHand().getType() == Material.ZOGLIN_SPAWN_EGG) {
			event.setCancelled(true);
			player.teleport(new Location(Bukkit.getWorld("MvsK"), Bukkit.getWorld("MvsK").getSpawnLocation().getX(),
					Bukkit.getWorld("MvsK").getSpawnLocation().getY(),
					Bukkit.getWorld("MvsK").getSpawnLocation().getZ()));
			player.setGameMode(GameMode.SURVIVAL);
			baker.giveItems(player);
		}
		// Eat Cake Effect
		if (event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getClickedBlock().getType() == Material.CAKE) {
			event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 50, 0));
			event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 0));
		}

		// Armorsmith Class
		if (inventory.getItemInMainHand().getType() == Material.OCELOT_SPAWN_EGG) {
			event.setCancelled(true);
			player.teleport(new Location(Bukkit.getWorld("MvsK"), Bukkit.getWorld("MvsK").getSpawnLocation().getX(),
					Bukkit.getWorld("MvsK").getSpawnLocation().getY(),
					Bukkit.getWorld("MvsK").getSpawnLocation().getZ()));
			player.setGameMode(GameMode.SURVIVAL);
			armorsmith.giveItems(player);
		}

		// Weaponsmith
		if (inventory.getItemInMainHand().getType() == Material.SKELETON_HORSE_SPAWN_EGG) {
			event.setCancelled(true);
			player.teleport(new Location(Bukkit.getWorld("MvsK"), Bukkit.getWorld("MvsK").getSpawnLocation().getX(),
					Bukkit.getWorld("MvsK").getSpawnLocation().getY(),
					Bukkit.getWorld("MvsK").getSpawnLocation().getZ()));
			player.setGameMode(GameMode.SURVIVAL);
			weaponsmith.giveItems(player);
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
		}

		// Torchman
		if (inventory.getItemInMainHand().getType() == Material.TROPICAL_FISH_SPAWN_EGG) {
			event.setCancelled(true);
			player.teleport(new Location(Bukkit.getWorld("MvsK"), Bukkit.getWorld("MvsK").getSpawnLocation().getX(),
					Bukkit.getWorld("MvsK").getSpawnLocation().getY(),
					Bukkit.getWorld("MvsK").getSpawnLocation().getZ()));
			player.setGameMode(GameMode.SURVIVAL);
			torchman.giveItems(player);
		} else if (event.getClickedBlock() != null && event.getClickedBlock().getType() == Material.FIRE
				&& event.getAction() == Action.LEFT_CLICK_BLOCK) {
			if (inventory.getItemInMainHand().getType() == Material.STICK) {
				inventory.getItemInMainHand().setAmount(inventory.getItemInMainHand().getAmount() - 1);
				inventory.addItem(new ItemStack(Material.TORCH, 1));
				event.setCancelled(true);
			}
		}

		// ZombieClass
		if (inventory.getItemInMainHand().getType() == Material.ZOMBIE_SPAWN_EGG) {
			event.setCancelled(true);
			player.getInventory().clear();
			player.teleport(new Location(Bukkit.getWorld("MvsK"), 9, 88, 9));
			player.setGameMode(GameMode.SURVIVAL);
			zombieClass.giveItems(player);
		}

		// Skeleton Class
		if (inventory.getItemInMainHand().getType() == Material.SKELETON_SPAWN_EGG) {
			event.setCancelled(true);
			player.getInventory().clear();
			player.teleport(new Location(Bukkit.getWorld("MvsK"), 9, 88, 9));
			player.setGameMode(GameMode.SURVIVAL);
			skeletonClass.giveItems(player);
		}

		// Creeper Class
		if (inventory.getItemInMainHand().getType() == Material.CREEPER_SPAWN_EGG) {
			event.setCancelled(true);
			player.getInventory().clear();
			player.teleport(new Location(Bukkit.getWorld("MvsK"), 9, 88, 9));
			player.setGameMode(GameMode.SURVIVAL);
			creeperClass.giveItems(player);
		} else if (inventory.getItemInMainHand().getType() == Material.GUNPOWDER
				&& (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
			Location loc = player.getLocation();
			player.getWorld().createExplosion(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ(), 8, true, true);
			player.damage(1000);
		}

		// Dragon Class
		if (inventory.getItemInMainHand().getType() == Material.DRAGON_EGG
				&& (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
			event.setCancelled(true);
			dragonClass.dragonFireball(player);
		} else if (inventory.getItemInMainHand().getType() == Material.MAGMA_CREAM
				&& (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
			event.setCancelled(true);
			dragonClass.regularFireball(player);
		} else if (inventory.getItemInMainHand().getType() == Material.LAVA_BUCKET
				&& (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
			event.setCancelled(true);
			if (System.currentTimeMillis() / 1000 - dragonCooldown >= 5) {
				dragonClass.lavaAttack(player);
				dragonCooldown = System.currentTimeMillis() / 1000;
			}
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
		event.getDrops().clear(); // Not sure if you need this line
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
			}
		}
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.isOp() && cmd.getName().equalsIgnoreCase("startgame")) {
				systemReset = false;
				SetUpLobby setup = new SetUpLobby();
				setup.assignRoles();
				MonstersVsKingsmen.scheduleSyncDelayedTask(new Runnable() {
					public void run() {
						int random = new Random().nextInt(instance.getServer().getOnlinePlayers().size() - 1);
						for (Player dragon : Bukkit.getOnlinePlayers()) {
							if (random == 0) {
								player.teleport(new Location(Bukkit.getWorld("MvsK"), 9, 88, 9));
								dragonClass.giveItems(dragon);
							}
							random -= 1;
						}
					}
				}, 36000); // 36000 time for 1 1/2 days
			} else if (player.isOp() && cmd.getName().equalsIgnoreCase("resetgame")) {
				if (Bukkit.getWorld("MvsK") != null) {
					Bukkit.unloadWorld("MvsK", false);
					File gameWorld = new File("MvsK");
					try {
						FileUtils.deleteDirectory(gameWorld);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				systemReset = true;
				for (Player p : Bukkit.getOnlinePlayers()) {
					p.getInventory().clear();
					p.setHealth(0);
				}
				WorldCreator wc = new WorldCreator("MvsK");
				wc.environment(World.Environment.NORMAL);
				wc.type(WorldType.NORMAL);
				wc.generateStructures(false);
				wc.seed(12542);
				wc.createWorld();
			}
		}
		return true;
	}
}
