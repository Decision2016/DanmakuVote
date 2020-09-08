package cn.decision01.danmakuvote.VoteEvents;

import cn.decision01.danmakuvote.DanmakuVote;
import cn.decision01.danmakuvote.Enums.EventEnum;
import cn.decision01.danmakuvote.Events.VoteEvent;

public class PlayerInvincible extends VoteEvent {
    public PlayerInvincible(String _worldName, String _configName, EventEnum _type, DanmakuVote _plugin) {
        super(_worldName, _configName, _type, _plugin);
    }
}
