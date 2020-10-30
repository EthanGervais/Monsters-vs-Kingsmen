package plugin.monstersvskingsmen;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.AnvilInventory;
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
		} else if (inventory.getItemInMainHand().getType() == Material.ENDERMITE_SPAWN_EGG) {
			event.setCancelled(true);
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
			builder.giveItems(player);
		}

		// Baker Class
		if (inventory.getItemInMainHand().getType() == Material.ZOGLIN_SPAWN_EGG) {
			event.setCancelled(true);
			baker.giveItems(player);
		}

		// Armorsmith Class
		if (inventory.getItemInMainHand().getType() == Material.OCELOT_SPAWN_EGG) {
			event.setCancelled(true);
			armorsmith.giveItems(player);
		}

		// Weaponsmith
		if (inventory.getItemInMainHand().getType() == Material.SKELETON_HORSE_SPAWN_EGG) {
			event.setCancelled(true);
			weaponsmith.giveItems(player);
		} else if (inventory.getItemInMainHand().getType() == Material.WHITE_DYE && (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR)) {
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
			torchman.giveItems(player);
		} else if (event.getClickedBlock() != null && event.getClickedBlock().getType() == Material.FIRE
				&& event.getAction() == Action.LEFT_CLICK_BLOCK) {
			if (inventory.getItemInMainHand().getType() == Material.STICK) {
				inventory.getItemInMainHand().setAmount(inventory.getItemInMainHand().getAmount() - 1);
				inventory.addItem(new ItemStack(Material.TORCH, 1));
				event.setCancelled(true);
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
		} else if (event.getItem().getType() == Material.CAKE) {
			event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 50, 0));
			event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 0));
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
				SetUpLobby setup = new SetUpLobby();
				setup.assignRoles();
			}
		}
		return true;
	}
}
