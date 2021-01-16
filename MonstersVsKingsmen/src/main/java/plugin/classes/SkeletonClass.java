package plugin.classes;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;

public class SkeletonClass extends MonsterClass {
	
	public SkeletonClass() {
		super();
		ItemStack bowStack = new ItemStack(Material.BOW);
		ArrayList<ItemStack> items = new ArrayList<ItemStack>();
		items.add(bowStack);
		super.setItems(items);
	}
	
	public void setClass(Player player, boolean buffed) {
		super.setPlayer(player);
		super.spawnMonster();
		
		ItemStack[] armorStack = new ItemStack[4];
		armorStack[0] = new ItemStack(Material.LEATHER_BOOTS);
		armorStack[1] = new ItemStack(Material.LEATHER_LEGGINGS);
		armorStack[2] = new ItemStack(Material.LEATHER_CHESTPLATE);
		armorStack[3] = new ItemStack(Material.LEATHER_HELMET);
		
		MobDisguise disguise = new MobDisguise(DisguiseType.SKELETON);
		disguise.setEntity(player);
		disguise.startDisguise();
		disguise.setSelfDisguiseVisible(false);
		
		if (buffed == true) {
			for (int i = 0; i < armorStack.length; i++) {
				armorStack[i].addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
			}
		}
		
		for (int i = 0; i < armorStack.length; i++) {
			armorStack[i].addEnchantment(Enchantment.BINDING_CURSE, 1);
		}
		
		player.getInventory().setArmorContents(armorStack);
	}
}
