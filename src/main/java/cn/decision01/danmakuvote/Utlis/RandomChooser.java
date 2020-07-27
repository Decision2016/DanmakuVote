package cn.decision01.danmakuvote.Utlis;

import cn.decision01.danmakuvote.DanmakuVote;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;

import java.util.List;
import java.util.Random;

public final class RandomChooser {

    public static int RandomTimeChoose(int floor, int ceil) {
        // type integer maybe overflow
        int delta = ceil - floor;
        Random random = new Random();
        int addValue = random.nextInt(delta);
        return floor + addValue;
    }

    private static EntityType RandomEntityChoose(DanmakuVote plugin, String _path) {
        FileConfiguration config = plugin.getConfig();
        List monsterList =  config.getStringList(_path);
        Random random = new Random();
        int listSize = monsterList.size();
        int randomInteger = random.nextInt(listSize);
        EntityType type = EntityType.valueOf(String.valueOf(monsterList.get(randomInteger)));
        return type;
    }

    public static EntityType RandomMonsterChoose(DanmakuVote plugin) {
        return RandomEntityChoose(plugin, "Monsters");
    }

    public static EntityType RandomAnimalChoose(DanmakuVote plugin) {
        return RandomEntityChoose(plugin, "Animals");
    }

}
