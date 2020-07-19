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
        EntityType[] MonsterArray = {EntityType.ENDERMAN, EntityType.SPIDER, EntityType.CREEPER, EntityType.SKELETON};
        Random random = new Random();
        return MonsterArray[random.nextInt(4)];
    }

    public static EntityType RandomAnimalChoose() {
        return EntityType.CAT;
    }
}