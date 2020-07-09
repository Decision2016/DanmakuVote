package cn.decision01.danmakuvote;

import cn.decision01.danmakuvote.Enums.EventEnum;
import cn.decision01.danmakuvote.event.EventFactory;
import cn.decision01.danmakuvote.event.VoteEvent;
import cn.decision01.danmakuvote.utils.DanmakuListener;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import static org.bukkit.Bukkit.getLogger;

public class Vote {
    private FileConfiguration config;
    private VoteEvent[] events = null;
    private int totalCount = 0;
    private Scoreboard scoreboard;
    private Objective objective;
    private String worldName;

    public Vote(String _worldName, FileConfiguration _config) {
        worldName = _worldName;
        config = _config;
        events = new VoteEvent[3];
    }

    private void choseEvent() throws IOException {
        ArrayList<EventEnum> list = new ArrayList<>();
        EventEnum now;
        int length = EventEnum.values().length;

        Random r = new Random();
        for (int i = 0; i < 3; i++) {
            now = EventEnum.values()[i];
            events[i] = EventFactory.generateEvent(now, worldName);
            // todo: 修改事件名称，载入OP设置的世界
        }
    }

    private int getResult() {
        int index = 0;
        for (int now = 1; now < 3; now ++) {
            if (events[now].getCount() > events[index].getCount()) index = now;
        }
        return index;
    }

    private void showInformation() {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        scoreboard = manager.getMainScoreboard();

        // todo: check objective existed
        objective = scoreboard.registerNewObjective("DanmakuVote", "dummy", "DanmakuVote");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        for(VoteEvent event: events) {
            String eventName = event.getEventName();
            Score score = objective.getScore(eventName);
            score.setScore(0);
        }
    }

    public void runVote() throws IOException {
        choseEvent();
        showInformation();
        DanmakuListener danmakuListener = new DanmakuListener(config.getInt("Bilibili.room_id"), events);
        danmakuListener.setTime(System.currentTimeMillis(), 30 * 1000);
        danmakuListener.listenLiving();

        objective.unregister();
        int index = getResult();
        Bukkit.broadcastMessage("观众选择了事件：" + events[index].getEventName());
        Bukkit.broadcastMessage("事件效果：" + events[index].getDescription());
        events[index].effect();
    }
}
