package cn.decision01.danmakuvote.event;

import cn.decision01.danmakuvote.Enums.EventEnum;

public class WorldEvent extends VoteEvent {
    protected String worldName = null;
    private String eventName = null;
    private String description = null;
    protected EventEnum EventType;

    public WorldEvent(String _worldName, String _eventName, String _description, EventEnum _type) {
        worldName = _worldName;
        eventName = _eventName;
        description = _description;
        EventType = _type;
        count = 0;
    }

    @Override
    public void addCount() {
        count++;
    }

    @Override
    public int getCount() {
        return count;
    }
}
