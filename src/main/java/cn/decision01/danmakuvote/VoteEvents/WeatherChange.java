package cn.decision01.danmakuvote.VoteEvents;

import cn.decision01.danmakuvote.DanmakuVote;
import cn.decision01.danmakuvote.Enums.EventEnum;
import cn.decision01.danmakuvote.Events.VoteEvent;
import org.bukkit.Bukkit;
import org.bukkit.World;

public class WeatherChange extends VoteEvent {
    private World world;

    public WeatherChange(String _worldName, String _configName, EventEnum _type, DanmakuVote _plugin) {
        super(_worldName, _configName, _type, _plugin);
        world = Bukkit.getWorld(_worldName);
    }

    private void setStorm() {
        world.setStorm(true);
    }

    private void setThunder() {
        world.setThundering(true);
    }

    private void setSunny() {
        if (world.hasStorm()) {
            world.setStorm(false);
        }
        if (world.isThundering()) {
            world.setThundering(false);
        }
    }

    public final void effect() {
        switch (super.EventType) {
            case WORLD_CHANGE_STORM: {
                setStorm();
                break;
            }
            case WORLD_CHANGE_THUNDER: {
                setThunder();
                break;
            }
            case WORLD_CHANGE_SUNNY: {
                setSunny();
                break;
            }
        }
    }
}
