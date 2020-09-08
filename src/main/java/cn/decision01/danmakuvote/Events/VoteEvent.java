package cn.decision01.danmakuvote.Events;

import cn.decision01.danmakuvote.DanmakuVote;
import cn.decision01.danmakuvote.Enums.EventEnum;
import cn.decision01.danmakuvote.Utils.FileUtil;
import org.bukkit.configuration.file.FileConfiguration;

public class VoteEvent {
    public final String configFileName = "events.yml";

    protected String worldName = null;
    protected EventEnum EventType;
    private String eventName = null;
    private String description = null;
    public String configName = null;
    public DanmakuVote plugin;
    public int count;

    public VoteEvent(String _worldName, String _configName, EventEnum _type, DanmakuVote _plugin) {
        worldName = _worldName;
        configName = _configName;
        EventType = _type;
        count = 0;
        plugin = _plugin;

        FileConfiguration config = FileUtil.getFileConfig(_plugin, configFileName);
        eventName = config.getString(String.format("%s.name", configName));
        description = config.getString(String.format("%s.description", configName));
    }

    public void addCount() {
        count++;
    }

    public int getCount() {
        return count;
    }

    public void effect() {}

    public String getEventName() {
        return eventName;
    }

    public String getDescription() {
        return description;
    }

    public boolean getEventSwitch() {
        FileConfiguration config = FileUtil.getFileConfig(plugin, configFileName);
        return config.getBoolean(String.format("%s.switch", configName));
    }
}
