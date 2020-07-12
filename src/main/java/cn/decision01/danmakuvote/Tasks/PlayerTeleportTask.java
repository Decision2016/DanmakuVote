package cn.decision01.danmakuvote.Tasks;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerTeleportTask extends BukkitRunnable {
    private Player player;
    private Location location;

    public PlayerTeleportTask(Player player, Location location) {
        this.player = player;
        this.location = location;
    }

    @Override
    public void run() {
        player.teleport(location);
    }
}
