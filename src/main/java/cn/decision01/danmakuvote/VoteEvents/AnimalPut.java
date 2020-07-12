package cn.decision01.danmakuvote.VoteEvents;

import cn.decision01.danmakuvote.DanmakuVote;
import cn.decision01.danmakuvote.Enums.EventEnum;
import cn.decision01.danmakuvote.event.PlayerEvent;

public class AnimalPut extends PlayerEvent {

    public AnimalPut(String _eventName, String _description, EventEnum _type, DanmakuVote _plugin) {
        super(_eventName, _description, _type, _plugin);
    }
}
