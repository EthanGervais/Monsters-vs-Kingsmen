package plugin.monstersvskingsmen;

import java.util.ArrayList;
import java.util.Collections;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SetUpLobby {
	private ItemStack stack;
	private ItemMeta meta;
	private ArrayList<ItemStack> nobleItems = new ArrayList<ItemStack>();
	private ArrayList<ItemStack> kingsmenItems = new ArrayList<ItemStack>();

	public SetUpLobby() {
		// K1ng Item
		stack = new ItemStack(Material.PUFFERFISH_SPAWN_EGG, 1);
		meta = stack.getItemMeta();
		meta.setDisplayName("K1ng");
		stack.setItemMeta(meta);
		nobleItems.add(stack);

		// Peenut Item
		stack = new ItemStack(Material.TURTLE_SPAWN_EGG, 1);
		meta = stack.getItemMeta();
		meta.setDisplayName("Peenut");
		stack.setItemMeta(meta);
		nobleItems.add(stack);
		
		// DMac Item
		stack = new ItemStack(Material.SALMON_SPAWN_EGG, 1);
		meta = stack.getItemMeta();
		meta.setDisplayName("DMac");
		stack.setItemMeta(meta);
		nobleItems.add(stack);

		// Zatrick Item
		stack = new ItemStack(Material.MOOSHROOM_SPAWN_EGG, 1);
		meta = stack.getItemMeta();
		meta.setDisplayName("Zatrick");
		stack.setItemMeta(meta);
		nobleItems.add(stack);
		
		// HotTub Item
		stack = new ItemStack(Material.ENDERMITE_SPAWN_EGG, 1);
		meta = stack.getItemMeta();
		meta.setDisplayName("HotTub");
		stack.setItemMeta(meta);
		nobleItems.add(stack);

		// Builder Item
		stack = new ItemStack(Material.ENDERMAN_SPAWN_EGG, 1);
		meta = stack.getItemMeta();
		meta.setDisplayName("Builder");
		stack.setItemMeta(meta);
		kingsmenItems.add(stack);

		// Armorsmith Item
		stack = new ItemStack(Material.OCELOT_SPAWN_EGG, 1);
		meta = stack.getItemMeta();
		meta.setDisplayName("Armorsmith");
		stack.setItemMeta(meta);
		kingsmenItems.add(stack);
		
		// Weaponsmith
		stack = new ItemStack(Material.SKELETON_HORSE_SPAWN_EGG, 1);
		meta = stack.getItemMeta();
		meta.setDisplayName("Weaponsmith");
		stack.setItemMeta(meta);
		kingsmenItems.add(stack);
		
		// Torchman
		stack = new ItemStack(Material.TROPICAL_FISH_SPAWN_EGG, 1);
		meta = stack.getItemMeta();
		meta.setDisplayName("Torchman");
		stack.setItemMeta(meta);
		kingsmenItems.add(stack);
		
		// Baker Item
		stack = new ItemStack(Material.ZOGLIN_SPAWN_EGG, 1);
		meta = stack.getItemMeta();
		meta.setDisplayName("Baker");
		stack.setItemMeta(meta);
		kingsmenItems.add(stack);
	}

	public void assignRoles() {
		int counter = 0;
		Collections.shuffle(kingsmenItems);
		Collections.shuffle(nobleItems);
		for (Player p : Bukkit.getOnlinePlayers()) {
			Inventory inv = p.getInventory();
			ItemStack spawn = kingsmenItems.get(counter);
			inv.addItem(spawn);
			if (counter >= kingsmenItems.size() - 1) {
				counter = 0;
			} else {
				counter++;
			}
		}
	}

}
