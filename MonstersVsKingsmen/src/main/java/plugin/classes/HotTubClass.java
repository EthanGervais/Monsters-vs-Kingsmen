package plugin.classes;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class HotTubClass extends PlayerClass {

	public HotTubClass() {
		super();

		ItemStack invisStack = new ItemStack(Material.BLAZE_ROD, 1);
		ItemMeta meta = invisStack.getItemMeta();
		meta.setDisplayName("Going Ghost!");
		invisStack.setItemMeta(meta);
		ArrayList<ItemStack> items = new ArrayList<ItemStack>();
		items.add(invisStack);
		super.setItems(items);

		super.setPlayerAsNoble();
		super.setSpawnEgg(Material.ENDERMITE_SPAWN_EGG);
	}

	public void setClass(Player player, ArrayList<Player> nobles, KingClass king, PeenutClass peenut, DMacClass dmac, ZatrickClass zatrick, HotTubClass hottub) {
		super.setPlayer(player);
		super.giveItems(nobles, king, peenut, dmac, zatrick, hottub);
	}

	public void invisibility(Player player) {
		player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 400, 2));
	}
}
