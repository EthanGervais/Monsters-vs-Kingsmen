package plugin.classes;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.libraryaddict.disguise.disguisetypes.DisguiseType;

public class CreeperClass extends MonsterClass {

	public CreeperClass() {
		super();

		ItemStack boomStack = new ItemStack(Material.GUNPOWDER, 1);
		ArrayList<ItemStack> items = new ArrayList<ItemStack>();
		items.add(boomStack);
		super.setItems(items);

		super.setDisguise(DisguiseType.CREEPER);
	}

	public void setClass(Player player) {
		super.setPlayer(player);
		super.spawnMonster();
	}
}
