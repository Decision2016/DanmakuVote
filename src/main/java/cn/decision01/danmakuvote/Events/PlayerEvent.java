package cn.decision01.danmakuvote.Events;

import cn.decision01.danmakuvote.DanmakuVote;
import cn.decision01.danmakuvote.Enums.EventEnum;

public class PlayerEvent extends VoteEvent {
    private String eventName = null;
    private String description = null;
    private EventEnum EventType;
    private int count;
    private DanmakuVote plugin;

    public PlayerEvent(String _eventName, String _description, EventEnum _type, DanmakuVote _plugin) {
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