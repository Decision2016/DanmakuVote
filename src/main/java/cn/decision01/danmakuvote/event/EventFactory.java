package cn.decision01.danmakuvote.event;

import cn.decision01.danmakuvote.DanmakuVote;
import cn.decision01.danmakuvote.Enums.EventEnum;
import cn.decision01.danmakuvote.VoteEvents.MonsterPut;
import cn.decision01.danmakuvote.VoteEvents.PlayerTeleport;
import cn.decision01.danmakuvote.VoteEvents.WeatherChange;

public class EventFactory {
    private DanmakuVote plugin;

    public EventFactory(DanmakuVote plugin) {
        this.plugin = plugin;
    }

    public VoteEvent generateEvent(EventEnum eventType, String worldName) {
        switch (eventType) {
            case WORLD_CHANGE_SUNNY: {
                return new WeatherChange(worldName, "Weather Clear", "将天气修改为无天气", EventEnum.WORLD_CHANGE_SUNNY, plugin);
            }
            case WORLD_CHANGE_STORM: {
                return new WeatherChange(worldName, "Weather Storm", "将天气修改为雨天", EventEnum.WORLD_CHANGE_STORM, plugin);
            }
            case WORLD_CHANGE_THUNDER: {
                return new WeatherChange(worldName, "Weather Thunder", "将天气修改为雷雨天", EventEnum.WORLD_CHANGE_THUNDER, plugin);
            }
            case PLAYER_GENERATE_MOBS: {
                return new MonsterPut(worldName, "Put Monster", "放置一个怪物到一个随机玩家身边", EventEnum.PLAYER_GENERATE_MOBS, plugin);
            }
            case PLAYER_TELEPORT: {
                return new PlayerTeleport(worldName, "Teleport Player", "随机选取一名幸运玩家传送到一个随机位置", EventEnum.PLAYER_TELEPORT, plugin);
            }
        }
        return null;
    }
}
