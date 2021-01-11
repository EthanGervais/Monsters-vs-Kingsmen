package plugin.classes;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ZatrickClass extends PlayerClass {

	public ZatrickClass(ArrayList<Player> nobles, KingClass king, PeenutClass peenut, DMacClass dmac, ZatrickClass zatrick, HotTubClass hottub) {
		super(nobles, king, peenut, dmac, zatrick, hottub);
		ItemStack aoeStack = new ItemStack(Material.CRIMSON_ROOTS, 1);
		ItemMeta meta = aoeStack.getItemMeta();
		meta.setDisplayName("Heal");
		aoeStack.setItemMeta(meta);
		ArrayList<ItemStack> items = new ArrayList<ItemStack>();
		items.add(aoeStack);
		super.setItems(items);

		super.setPlayerAsNoble();
		super.setSpawnEgg(Material.MOOSHROOM_SPAWN_EGG);
	}

	public void setClass(Player player) {
		super.setPlayer(player);
		super.giveItems();
	}

	public void needHealing(Player player) {
		player.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 400, 1));
		for (Entity entity : player.getNearbyEntities(10, 10, 10)) {
			if (entity instanceof Player) {
				Player newPlayer = (Player) entity;
				newPlayer.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 400, 1));
			}
		}
	}

}
