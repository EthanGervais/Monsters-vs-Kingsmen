package plugin.classes;

import java.util.ArrayList;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.DragonFireball;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.projectiles.ProjectileSource;

import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;

public class DragonClass extends MonsterClass {

	public DragonClass() {
		super();

		ItemStack dragonEgg = new ItemStack(Material.DRAGON_EGG);
		ItemStack magmaCream = new ItemStack(Material.MAGMA_CREAM);
		ItemStack lavaBucket = new ItemStack(Material.FIRE_CHARGE);
		ArrayList<ItemStack> items = new ArrayList<ItemStack>();
		items.add(dragonEgg);
		items.add(magmaCream);
		items.add(lavaBucket);
		super.setItems(items);
	}

	public void setClass(Player player) {
		player.setAllowFlight(true);
		MobDisguise disguise = new MobDisguise(DisguiseType.ENDER_DRAGON);
		disguise.setEntity(player);
		disguise.startDisguise();
		disguise.setSelfDisguiseVisible(false);
		super.setPlayer(player);
		super.spawnMonster();
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

	public void lavaAttack(Player player) {
		if (player.getWorld().getBlockAt(player.getTargetBlock((Set<Material>) null, 200).getLocation().add(0, 1, 0))
				.getType() == Material.AIR) {
			player.getWorld().getBlockAt(player.getTargetBlock((Set<Material>) null, 200).getLocation())
					.getRelative(BlockFace.UP).setType(Material.LAVA);
		}
	}

}
