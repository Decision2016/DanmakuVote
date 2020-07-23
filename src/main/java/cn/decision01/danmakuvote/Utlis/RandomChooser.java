package cn.decision01.danmakuvote.Utlis;

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
        return MonsterArray[random.nextInt(MonsterArray.length)];
    }

    public static EntityType RandomAnimalChoose() {
        EntityType[] AnimalSet = {EntityType.SHEEP, EntityType.PIG, EntityType.COW, EntityType.COW, EntityType.WOLF};
        Random random = new Random();
        return AnimalSet[random.nextInt(AnimalSet.length)];
    }
}
