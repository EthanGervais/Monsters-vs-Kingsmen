package plugin.classes;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
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

	public void setClass(Player player, boolean buffed) {
		super.setPlayer(player);
		super.spawnMonster();

		if (buffed == true) {
			ItemStack[] armorStack = new ItemStack[4];
			armorStack[0] = new ItemStack(Material.CHAINMAIL_BOOTS);
			armorStack[1] = new ItemStack(Material.CHAINMAIL_LEGGINGS);
			armorStack[2] = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
			armorStack[3] = new ItemStack(Material.CHAINMAIL_HELMET);
			
			for (int i = 0; i < armorStack.length; i++) {
				armorStack[i].addEnchantment(Enchantment.BINDING_CURSE, 1);
			}
			
			player.getInventory().setArmorContents(armorStack);
		}

		MobDisguise disguise = new MobDisguise(DisguiseType.CREEPER);
		disguise.setEntity(player);
		disguise.startDisguise();
		disguise.setSelfDisguiseVisible(false);
	}
}
