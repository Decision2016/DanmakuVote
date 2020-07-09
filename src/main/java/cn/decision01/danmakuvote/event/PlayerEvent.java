package cn.decision01.danmakuvote.event;

import cn.decision01.danmakuvote.Enums.EventEnum;

public class PlayerEvent extends VoteEvent {
    private String eventName = null;
    private String description = null;
    protected EventEnum EventType;
    int count;

    public PlayerEvent(String _eventName, String _description, EventEnum _type) {
        eventName = _eventName;
        description = _description;
        EventType = _type;
        count = 0;
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
}
