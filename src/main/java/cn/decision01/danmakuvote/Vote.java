package cn.decision01.danmakuvote;

import cn.decision01.danmakuvote.Enums.EventEnum;
import cn.decision01.danmakuvote.event.EventFactory;
import cn.decision01.danmakuvote.event.VoteEvent;
import cn.decision01.danmakuvote.utils.DanmakuListener;
import org.bukkit.Bukkit;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Vote {
    VoteEvent[] events = null;
    private int totalCount = 0;
    private Scoreboard scoreboard;

    Vote() {
        events = new VoteEvent[3];
    }

    private void choseEvent() throws IOException {
        ArrayList<EventEnum> list = new ArrayList<EventEnum>();
        EventEnum now;
        int length = EventEnum.values().length;

        Random r = new Random();
        for (int i = 0; i < 3; i++) {
            while (true) {
                now = EventEnum.values()[r.nextInt(length)];
                if (list.indexOf(now) != -1) {
                    list.add(now);
                    break;
                }
            }
            events[i] = EventFactory.generateEvent(now, "DIM-1");
            // todo: 修改事件名称，载入OP设置的世界
        }

        DanmakuListener danmakuListener = new DanmakuListener();
        danmakuListener.setTime(System.currentTimeMillis(), 30 * 1000);

        totalCount = danmakuListener.listenLiving();
    }

    private void showInformation() {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        scoreboard = manager.getNewScoreboard();

        scoreboard.notifyAll();
    }
}
