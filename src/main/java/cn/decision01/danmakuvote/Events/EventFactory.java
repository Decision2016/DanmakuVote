package cn.decision01.danmakuvote.Events;

import cn.decision01.danmakuvote.DanmakuVote;
import cn.decision01.danmakuvote.Enums.EventEnum;
import cn.decision01.danmakuvote.VoteEvents.*;

public class EventFactory {
    private DanmakuVote plugin;

    public EventFactory(DanmakuVote plugin) {
        this.plugin = plugin;
    }

    public VoteEvent generateEvent(EventEnum eventType, String worldName) {
        switch (eventType) {
            case WORLD_CHANGE_SUNNY: {
                return new WeatherChange(worldName, "WeatherClear",  EventEnum.WORLD_CHANGE_SUNNY, plugin);
            }
            case WORLD_CHANGE_STORM: {
                return new WeatherChange(worldName, "WeatherStorm",  EventEnum.WORLD_CHANGE_STORM, plugin);
            }
            case WORLD_CHANGE_THUNDER: {
                return new WeatherChange(worldName, "WeatherThunder",  EventEnum.WORLD_CHANGE_THUNDER, plugin);
            }
            case PLAYER_GENERATE_MOBS: {
                return new MonsterPut(worldName, "MonsterPut",  EventEnum.PLAYER_GENERATE_MOBS, plugin);
            }
            case PLAYER_TELEPORT: {
                return new PlayerTeleport(worldName, "PlayerTeleport",  EventEnum.PLAYER_TELEPORT, plugin);
            }
            case PLAYER_PUT_ANIMAL: {
                return new AnimalPut(worldName, "AnimalPut",  EventEnum.PLAYER_PUT_ANIMAL, plugin);
            }
            case PLAYER_FIRE: {
                return new FirePlayer(worldName, "FirePlayer", EventEnum.PLAYER_FIRE, plugin);
            }
            case PLAYER_THUNDER: {
                return new Thunder(worldName, "Thunder", EventEnum.PLAYER_THUNDER, plugin);
            }
            case INVENTORY_CLEAR: {
                return new InventoryClear(worldName, "InventoryClear", EventEnum.INVENTORY_CLEAR, plugin);
            }
            case BLOCK_DELETE: {
                return new ChunkDelete(worldName, "BlockDelete", EventEnum.BLOCK_DELETE, plugin);
            }
            /*
            case PLAYER_FREEZE: {
                return new FreezePlayer(worldName, "FreezePlayer", EventEnum.PLAYER_FREEZE, plugin);
            }
            case PLAYER_INVINCIBLE: {
                return new PlayerInvincible(worldName, "PlayerInvincible", EventEnum.PLAYER_INVINCIBLE, plugin);
            }
             */
        }
        return null;
    }
}
