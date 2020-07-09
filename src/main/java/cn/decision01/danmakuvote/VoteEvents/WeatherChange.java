package cn.decision01.danmakuvote.VoteEvents;

import cn.decision01.danmakuvote.Enums.EventEnum;
import cn.decision01.danmakuvote.event.WorldEvent;
import org.bukkit.Bukkit;
import org.bukkit.World;

public class WeatherChange extends WorldEvent {
    private World world;

    public WeatherChange(String _worldName, String _eventName, String _description, EventEnum _type) {
        super(_worldName, _eventName, _description, _type);
        world = Bukkit.getWorld(super.worldName);
    }

    public void setStorm() {
        world.setStorm(true);
    }

    public void setThunder() {
        world.setThundering(true);
    }

    public void setSunny() {
        if (world.hasStorm()) {
            world.setStorm(false);
        }
        if (world.isThundering()) {
            world.setThundering(false);
        }
    }

    public void effect() {
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
