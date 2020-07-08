package cn.decision01.bilibilivote;

import cn.decision01.bilibilivote.Enums.EventEnum;
import cn.decision01.bilibilivote.event.EventFactory;
import cn.decision01.bilibilivote.event.VoteEvent;
import org.bukkit.Bukkit;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.List;
import java.util.Random;

public class Vote {
    VoteEvent[] events = null;
    private int totalCount = 0;
    private Scoreboard scoreboard;

    Vote() {
        events = new VoteEvent[3];
    }

    private void choseEvent() {
        List<EventEnum> list;
        EventEnum now;
        int length = EventEnum.values().length;

        Random r = new Random();
        for (int i = 0; i < 3; i++) {
            now = EventEnum.values()[r.nextInt(length)];
            events[i] = EventFactory.generateEvent(now, "DIM-1");
            // todo：修改事件名称，载入OP设置的世界
        }
    }

    private void showInformation() {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        scoreboard = manager.getNewScoreboard();
    }
}
