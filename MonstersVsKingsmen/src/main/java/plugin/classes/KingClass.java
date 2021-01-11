package plugin.classes;

import java.util.ArrayList;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class KingClass extends PlayerClass {

	public KingClass(ArrayList<Player> nobles, KingClass king, PeenutClass peenut, DMacClass dmac, ZatrickClass zatrick, HotTubClass hottub) {
		super(nobles, king, peenut, dmac, zatrick, hottub);

		ItemStack hammerStack = new ItemStack(Material.SLIME_BALL, 1);
		ItemMeta meta = hammerStack.getItemMeta();
		meta.setDisplayName("Mjölnir");
		hammerStack.setItemMeta(meta);
		ArrayList<ItemStack> items = new ArrayList<ItemStack>();
		items.add(hammerStack);
		super.setItems(items);

		super.setPlayerAsNoble();
		super.setSpawnEgg(Material.PUFFERFISH_SPAWN_EGG);
	}

	public void setClass(Player player) {
		super.setPlayer(player);
		super.giveItems();
	}

	public void thorsHammer(Player player) {
		player.getWorld().strikeLightning(player.getTargetBlock((Set<Material>) null, 200).getLocation());
	}
}
