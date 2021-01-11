package plugin.classes;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;

public class CreeperClass extends MonsterClass {

	public CreeperClass() {
		super();

		ItemStack boomStack = new ItemStack(Material.GUNPOWDER, 1);
		ArrayList<ItemStack> items = new ArrayList<ItemStack>();
		items.add(boomStack);
		super.setItems(items);
	}

	public void setClass(Player player) {
		MobDisguise disguise = new MobDisguise(DisguiseType.CREEPER);
		disguise.setEntity(player);
		disguise.startDisguise();
		disguise.setSelfDisguiseVisible(false);
		super.setPlayer(player);
		super.spawnMonster();
	}
}
