package cn.decision01.danmakuvote.VoteEvents;

import cn.decision01.danmakuvote.Enums.EventEnum;
import cn.decision01.danmakuvote.event.PlayerEvent;
import org.bukkit.Bukkit;

public class MonsterPut extends PlayerEvent {

    public MonsterPut(String _eventName, String _description, EventEnum _type) {
        super(_eventName, _description, _type);
    }

    public final void effect() {

    }
}
