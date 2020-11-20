package plugin.classes;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class DMacClass extends PlayerClass {

	public DMacClass() {
		super();

		ItemStack aoeStack = new ItemStack(Material.END_CRYSTAL, 1);
		ItemMeta meta = aoeStack.getItemMeta();
		meta.setDisplayName("LET'S GO!");
		aoeStack.setItemMeta(meta);
		ArrayList<ItemStack> items = new ArrayList<ItemStack>();
		items.add(aoeStack);
		super.setItems(items);

		super.setPlayerAsNoble();
		super.setSpawnEgg(Material.SALMON_SPAWN_EGG);
	}

	public void setClass(Player player) {
		super.setPlayer(player);
		super.giveItems();
	}

	public void aoeEffect(Player player) {
		player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 400, 1));
		player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 400, 1));
		for (Entity entity : player.getNearbyEntities(10, 10, 10)) {
			if (entity instanceof Player) {
				Player newPlayer = (Player) entity;
				newPlayer.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 400, 1));
				newPlayer.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 400, 1));
			}
		}
	}
}
