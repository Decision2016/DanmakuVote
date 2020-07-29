package cn.decision01.danmakuvote.Events;

import cn.decision01.danmakuvote.DanmakuVote;
import cn.decision01.danmakuvote.Enums.EventEnum;

public class VoteEvent {
    protected String worldName = null;
    private String eventName = null;
    private String description = null;
    protected EventEnum EventType;
    public DanmakuVote plugin;
    public int count;

    public VoteEvent(String _worldName, String _eventName, String _description, EventEnum _type, DanmakuVote _plugin) {
        worldName = _worldName;
        eventName = _eventName;
        description = _description;
        EventType = _type;
        count = 0;
        plugin = _plugin;
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
}
