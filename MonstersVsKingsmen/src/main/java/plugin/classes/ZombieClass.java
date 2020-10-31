package plugin.classes;

import org.bukkit.entity.Player;

import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;

public class ZombieClass {
	public void giveItems(Player player) {
		MobDisguise disguise = new MobDisguise(DisguiseType.ZOMBIE);
		disguise.setEntity(player);
		disguise.startDisguise();
		disguise.setSelfDisguiseVisible(false);
	}
}
