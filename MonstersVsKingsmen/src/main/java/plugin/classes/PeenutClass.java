package plugin.classes;

import java.util.ArrayList;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PeenutClass extends PlayerClass {

	public PeenutClass(ArrayList<Player> nobles, KingClass king, PeenutClass peenut, DMacClass dmac, ZatrickClass zatrick, HotTubClass hottub) {
		super(nobles, king, peenut, dmac, zatrick, hottub);
		ItemStack fireStack = new ItemStack(Material.BLAZE_POWDER, 1);
		ItemMeta meta = fireStack.getItemMeta();
		meta.setDisplayName("Flame on!");
		fireStack.setItemMeta(meta);
		ArrayList<ItemStack> items = new ArrayList<ItemStack>();
		items.add(fireStack);
		super.setItems(items);

		super.setPlayerAsNoble();
		super.setSpawnEgg(Material.TURTLE_SPAWN_EGG);
	}

	public void setClass(Player player) {
		super.setPlayer(player);
		super.giveItems();
	}

	public void fireBall(Player player) {
		if (player.getWorld().getBlockAt(player.getTargetBlock((Set<Material>) null, 200).getLocation().add(0, 1, 0))
				.getType() == Material.AIR) {
			player.getWorld().getBlockAt(player.getTargetBlock((Set<Material>) null, 200).getLocation())
					.getRelative(BlockFace.UP).setType(Material.FIRE);
		}
	}
}
