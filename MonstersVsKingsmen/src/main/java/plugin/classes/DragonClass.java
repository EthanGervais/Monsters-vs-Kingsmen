package plugin.classes;

import org.bukkit.Material;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;

public class DragonClass {
	public void giveItems(Player player) {
		MobDisguise disguise = new MobDisguise(DisguiseType.ENDER_DRAGON);
		disguise.setEntity(player);
		disguise.startDisguise();
		disguise.setSelfDisguiseVisible(false);
		
		EntityDamageEvent event = new EntityDamageEvent(player, EntityDamageEvent.DamageCause.ENTITY_ATTACK, 0);
		event.setCancelled(true);
		
		Inventory inventory = player.getInventory();
		ItemStack stack = new ItemStack(Material.DRAGON_EGG);
		inventory.addItem(stack);
	}
	
	public void dragonFireball(Player player) {
		Fireball fireball = player.getWorld().spawn(player.getLocation(), Fireball.class);
		fireball.setShooter(player);
		fireball.setVelocity(player.getLocation().getDirection().multiply(2));
		fireball.setIsIncendiary(false);
		fireball.setYield(0);
	}
}
