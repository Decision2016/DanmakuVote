package cn.decision01.bilibilivote.event;

import cn.decision01.bilibilivote.Enums.EventEnum;

public class PlayerEvent implements VoteEvent {
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
}
