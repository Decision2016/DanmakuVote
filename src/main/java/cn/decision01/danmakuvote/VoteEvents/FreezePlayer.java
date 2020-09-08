package cn.decision01.danmakuvote.VoteEvents;

import cn.decision01.danmakuvote.DanmakuVote;
import cn.decision01.danmakuvote.Enums.EventEnum;
import cn.decision01.danmakuvote.Events.VoteEvent;

public class FreezePlayer extends VoteEvent {
    public FreezePlayer(String _worldName, String _eventName, String _description, EventEnum _type, DanmakuVote _plugin) {
        super(_worldName, _eventName, _description, _type, _plugin);
    }
}
