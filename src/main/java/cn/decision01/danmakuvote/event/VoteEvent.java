package cn.decision01.danmakuvote.event;

public abstract class VoteEvent {
    int count;

    abstract void addCount();
    abstract int getCount();
}
