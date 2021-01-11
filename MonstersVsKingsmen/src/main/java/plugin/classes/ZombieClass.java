package plugin.classes;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;

public class ZombieClass extends MonsterClass {
	
	public ZombieClass() {
		super();
		ItemStack swordStack = new ItemStack(Material.IRON_SWORD);
		ArrayList<ItemStack> items = new ArrayList<ItemStack>();
		items.add(swordStack);
		super.setItems(items);
	}
	
	public void setClass(Player player) {
		super.setPlayer(player);
		super.spawnMonster();
		
		ItemStack[] armorStack = new ItemStack[4];
		armorStack[0] = new ItemStack(Material.IRON_BOOTS);
		armorStack[1] = new ItemStack(Material.IRON_LEGGINGS);
		armorStack[2] = new ItemStack(Material.IRON_CHESTPLATE);
		armorStack[3] = new ItemStack(Material.IRON_HELMET);
		
		MobDisguise disguise = new MobDisguise(DisguiseType.ZOMBIE);
		disguise.setEntity(player);
		disguise.startDisguise();
		disguise.setSelfDisguiseVisible(false);
		
		for (int i = 0; i < armorStack.length; i++) {
			armorStack[i].addEnchantment(Enchantment.BINDING_CURSE, 1);
		}
		
		player.getInventory().setArmorContents(armorStack);
	}
}
