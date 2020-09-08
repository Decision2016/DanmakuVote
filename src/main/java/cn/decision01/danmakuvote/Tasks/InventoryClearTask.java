package cn.decision01.danmakuvote.Tasks;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class InventoryClearTask extends BukkitRunnable {
    private Player player;

    public InventoryClearTask(Player player) {
        this.player = player;
    }

    @Override
    public void run() {
        player.getInventory().clear();
    }
}
