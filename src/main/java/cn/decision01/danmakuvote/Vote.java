package cn.decision01.danmakuvote;

import cn.decision01.danmakuvote.Enums.EventEnum;
import cn.decision01.danmakuvote.Events.EventFactory;
import cn.decision01.danmakuvote.Events.VoteEvent;
import cn.decision01.danmakuvote.Utlis.DanmakuListener;
import cn.decision01.danmakuvote.Utlis.DanmakuTaskMonitor;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scoreboard.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Vote {
    private final DanmakuVote plugin;
    private FileConfiguration config;
    private VoteEvent[] events = null;
    private int totalCount = 0;
    private Scoreboard scoreboard;
    private Objective objective;
    private String worldName;

    public Vote(String _worldName, FileConfiguration _config, DanmakuVote plugin) {
        this.worldName = _worldName;
        this.config = _config;
        this.events = new VoteEvent[3];
        this.plugin = plugin;
    }

    private void choseEvent() throws IOException {
        ArrayList<EventEnum> list = new ArrayList<>();
        EventEnum now;
        int length = EventEnum.values().length;

        Random r = new Random();
        EventFactory factory = new EventFactory(plugin);

        while (list.size() < 3) {
            // todo: 此处的代码逻辑需要等待测试
            now = EventEnum.values()[r.nextInt(length)];
            if (list.lastIndexOf(now) == -1) {
                events[list.size()] = factory.generateEvent(now, worldName);
                list.add(now);
            }
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
        Bukkit.getScheduler().runTask(plugin, () -> {
            // switch to sync thread
            if (! Bukkit.getOnlinePlayers().isEmpty()) {
                Bukkit.broadcastMessage("观众选择了事件：" + events[index].getEventName());
                Bukkit.broadcastMessage("事件效果：" + events[index].getDescription());
                events[index].effect();
            }
            plugin.threadFinished();
            DanmakuTaskMonitor monitor = new DanmakuTaskMonitor(plugin);
            monitor.addLaterTask();
        });
    }
}
