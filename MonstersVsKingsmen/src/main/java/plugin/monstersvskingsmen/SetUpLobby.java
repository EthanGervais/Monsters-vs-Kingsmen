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
		stack = new ItemStack(Material.PUFFERFISH_SPAWN_EGG, 1);
		meta = stack.getItemMeta();
		meta.setDisplayName("K1ng");
		stack.setItemMeta(meta);
		nobleItems.add(stack);

		stack = new ItemStack(Material.TURTLE_SPAWN_EGG, 1);
		meta = stack.getItemMeta();
		meta.setDisplayName("Peenut");
		stack.setItemMeta(meta);
		nobleItems.add(stack);

		stack = new ItemStack(Material.ENDERMAN_SPAWN_EGG, 1);
		meta = stack.getItemMeta();
		meta.setDisplayName("Builder");
		stack.setItemMeta(meta);
		kingsmenItems.add(stack);

		stack = new ItemStack(Material.OCELOT_SPAWN_EGG, 1);
		meta = stack.getItemMeta();
		meta.setDisplayName("Armorsmith");
		stack.setItemMeta(meta);
		kingsmenItems.add(stack);
	}

	public void assignRoles() {

		int counter = 0;
		int totalCount = 0;
		int startNobleAssign = (int)(Math.random() * (Bukkit.getOnlinePlayers().size() + 1)) - 1;

		Collections.shuffle(kingsmenItems);
		Collections.shuffle(nobleItems);
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (totalCount == startNobleAssign) {
				counter = 0;
			}

			if (totalCount >= startNobleAssign && totalCount < startNobleAssign + nobleItems.size()) {
				Inventory inv = p.getInventory();
				ItemStack spawn = new ItemStack(nobleItems.get(counter));
				inv.addItem(spawn);
				counter++;
				if (counter > nobleItems.size()) {
					counter = 0;
				}
			} else {
				Inventory inv = p.getInventory();
				ItemStack spawn = new ItemStack(kingsmenItems.get(counter));
				inv.addItem(spawn);
				if (counter >= kingsmenItems.size() - 1) {
					counter = 0;
				} else {
					counter++;
				}
			}
			totalCount++;
		}
	}

}
