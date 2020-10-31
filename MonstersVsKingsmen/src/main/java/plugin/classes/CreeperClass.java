package plugin.classes;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;

public class CreeperClass {
	public void giveItems(Player player) {
		MobDisguise disguise = new MobDisguise(DisguiseType.CREEPER);
		disguise.setEntity(player);
		disguise.startDisguise();
		disguise.setSelfDisguiseVisible(false);
		
		Inventory inventory = player.getInventory();
		
		ItemStack gunPowder = new ItemStack(Material.GUNPOWDER, 1);
		inventory.addItem(gunPowder);
	}
}
