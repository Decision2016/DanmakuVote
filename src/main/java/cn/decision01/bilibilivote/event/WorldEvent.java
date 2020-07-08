package cn.decision01.bilibilivote.event;

import cn.decision01.bilibilivote.Enums.EventEnum;

public class WorldEvent implements VoteEvent {
    protected String worldName = null;
    private String eventName = null;
    private String description = null;
    protected EventEnum EventType;
    int count;

    public WorldEvent(String _worldName, String _eventName, String _description, EventEnum _type) {
        worldName = _worldName;
        eventName = _eventName;
        description = _description;
        EventType = _type;
        count = 0;
    }

    public void addCount() {
        count++;
    }
}
