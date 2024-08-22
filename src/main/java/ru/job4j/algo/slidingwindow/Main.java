package ru.job4j.algo.slidingwindow;

import java.util.*;

class Interval {
    int start;
    int end;

    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

class Event {
    int time;
    boolean isStart;

    Event(int time, boolean isStart) {
        this.time = time;
        this.isStart = isStart;
    }
}

class EventComparator implements Comparator<Event> {
    @Override
    public int compare(Event o1, Event o2) {
        if (o1.time == o2.time) {
            return o1.isStart ? -1 : 1;
        }
        return Integer.compare(o1.time, o2.time);
    }
}

public class Main {

    public static int[] findMaxOverlapInterval(List<Interval> intervals) {
        int maxEvents = 0;
        int eventCount = 0;
        boolean flag = false;
        Event currentEvent = null;
        Comparator<Event> comparator = new EventComparator();
        Queue<Event> queue = new PriorityQueue<>(comparator);
        int[] result = {-1, -1};
        if (intervals.size() > 0) {
            for (Interval interval : intervals) {
                queue.add(new Event(interval.start, true));
                queue.add(new Event(interval.end, false));
            }
            while (!queue.isEmpty()) {
                currentEvent = queue.poll();
                if (currentEvent.isStart) {
                    eventCount++;
                    if (flag) {
                        while (!queue.isEmpty() && queue.peek().time == currentEvent.time) {
                            currentEvent = queue.poll();
                            if (currentEvent.isStart) {
                                eventCount++;
                            } else {
                                eventCount--;
                            }
                        }
                        if (!queue.peek().isStart) {
                            result[1] = currentEvent.time;
                            flag = false;
                        }
                    }
                    if (eventCount > maxEvents) {
                        maxEvents = eventCount;
                        result[0] = currentEvent.time;
                        flag = true;
                    }
                } else {
                    if (flag) {
                        result[1] = currentEvent.time;
                        flag = false;
                    }
                    eventCount--;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1, 4));
        intervals.add(new Interval(2, 6));
        intervals.add(new Interval(3, 5));
        intervals.add(new Interval(7, 8));

        int[] result = findMaxOverlapInterval(intervals);

        System.out.println("Interval that overlaps the maximum number of intervals: [" + result[0] + ", " + result[1] + "]");
    }
}
