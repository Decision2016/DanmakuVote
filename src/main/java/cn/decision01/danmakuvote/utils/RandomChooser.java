package cn.decision01.danmakuvote.utils;

import org.bukkit.entity.EntityType;

import java.util.Random;

public final class RandomChooser {
    public static int RandomTimeChoose(int floor, int ceil) {
        // type integer maybe overflow
        int delta = ceil - floor;
        Random random = new Random();
        int addValue = random.nextInt(delta);
        return floor + addValue;
    }

    public static EntityType RandomMonsterChoose() {
        // todo: 完善随机选取
        return EntityType.CAVE_SPIDER;
    }

    public static EntityType RandomAnimalChoose() {
        return EntityType.CAVE_SPIDER;
    }
}
