package cn.decision01.danmakuvote.event;

public abstract class VoteEvent {
    int count;

    public abstract void addCount();
    public abstract int getCount();
    public abstract void effect();
    public abstract String getEventName();
    public abstract String getDescription();
}
