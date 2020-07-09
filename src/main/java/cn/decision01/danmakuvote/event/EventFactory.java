package cn.decision01.danmakuvote.event;

import cn.decision01.danmakuvote.Enums.EventEnum;
import cn.decision01.danmakuvote.VoteEvents.WeatherChange;

public class EventFactory {
    public static VoteEvent generateEvent(EventEnum eventType, String worldName) {
        switch (eventType) {
            case WORLD_CHANGE_SUNNY: {
                return new WeatherChange(worldName, "Weather Clear", "将天气修改为无天气", EventEnum.WORLD_CHANGE_SUNNY);
            }
            case WORLD_CHANGE_STORM: {
                return new WeatherChange(worldName, "Weather Storm", "将天气修改为雨天", EventEnum.WORLD_CHANGE_STORM);
            }
            case WORLD_CHANGE_THUNDER: {
                return new WeatherChange(worldName, "Weather Thunder", "将天气修改为雷雨天", EventEnum.WORLD_CHANGE_THUNDER);
            }
        }
        return null;
    }
}
