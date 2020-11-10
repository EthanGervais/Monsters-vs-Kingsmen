package plugin.classes;

import java.util.HashMap;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.DragonFireball;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.projectiles.ProjectileSource;

import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;

public class DragonClass {
	public void giveItems(Player player) {
		MobDisguise disguise = new MobDisguise(DisguiseType.ENDER_DRAGON);
		disguise.setEntity(player);
		disguise.startDisguise();
		disguise.setSelfDisguiseVisible(false);
		
		player.setFlying(true);
		
		EntityDamageEvent event = new EntityDamageEvent(player, EntityDamageEvent.DamageCause.ENTITY_ATTACK, 0);
		event.setCancelled(true);
		
		Inventory inventory = player.getInventory();
		inventory.clear();
		ItemStack stack = new ItemStack(Material.DRAGON_EGG);
		inventory.addItem(stack);
		giveLavaBucket(player);
	}
	
	public void regularFireball(Player player) {
		Fireball fireball = player.getWorld().spawn(player.getLocation(), Fireball.class);
		fireball.setShooter((ProjectileSource) player);
		fireball.setVelocity(player.getLocation().getDirection().multiply(2));
		fireball.setIsIncendiary(true);
		fireball.setYield(5);
	}
	
	public void dragonFireball(Player player) {
		DragonFireball fireball = player.getWorld().spawn(player.getLocation(), DragonFireball.class);
		fireball.setShooter((ProjectileSource) player);
		fireball.setVelocity(player.getLocation().getDirection().multiply(2));
		fireball.setIsIncendiary(false);
		fireball.setYield(5);
	}
	
	public void giveLavaBucket(Player player) {
		Inventory inventory = player.getInventory();
		
		if (inventory.contains(Material.LAVA_BUCKET)) {
			return;
		} else {
			ItemStack lava = new ItemStack(Material.LAVA_BUCKET);
			
			if (inventory.contains(new ItemStack(Material.BUCKET))) {
				inventory.remove(Material.BUCKET);
			}
			
			inventory.addItem(lava);
		}
		
	}
	
	public void lavaAttack(Player player, PlayerInteractEvent event) {
		player = event.getPlayer();
		PlayerInventory inventory = player.getInventory();
		
		if (player.getWorld().getBlockAt(player.getTargetBlock((Set<Material>) null, 200).getLocation().add(0, 1, 0)).getType() == Material.AIR) {
			player.getWorld().getBlockAt(player.getTargetBlock((Set<Material>) null, 200).getLocation()).getRelative(BlockFace.UP).setType(Material.LAVA);
			inventory.setItemInMainHand(new ItemStack(Material.BUCKET));
		}
	}
}
