package cn.decision01.danmakuvote.Events;

import cn.decision01.danmakuvote.DanmakuVote;
import cn.decision01.danmakuvote.Enums.EventEnum;

public class WorldEvent extends VoteEvent {
    protected String worldName = null;
    private String eventName = null;
    private String description = null;
    protected EventEnum EventType;
    public DanmakuVote plugin;

    public WorldEvent(String _worldName, String _eventName, String _description, EventEnum _type, DanmakuVote _plugin) {
        worldName = _worldName;
        eventName = _eventName;
        description = _description;
        EventType = _type;
        count = 0;
        plugin = _plugin;
    }

    @Override
    public void addCount() {
        count++;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public void effect() {}

    @Override
    public String getEventName() {
        return eventName;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
