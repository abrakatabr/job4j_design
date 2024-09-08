package ru.job4j.tasks.algo.slidingwindow;

import java.util.*;

public class BankMaxLoadTime {
    public static int[] findMaxLoadTime(List<int[]> visitTimes) {
        int maxLoadStartTime = 0;
        int maxLoadEndTime = 0;
        int maxEvents = 0;
        int eventCount = 0;
        boolean flag = false;
        Event currentEvent = null;
        Queue<Event> queue = new PriorityQueue<>();
        if (visitTimes.size() > 0) {
            for (int[] interval : visitTimes) {
                queue.add(new Event(interval[0], EventType.ARRIVAL));
                queue.add(new Event(interval[1], EventType.DEPARTURE));
            }
            while (!queue.isEmpty()) {
                currentEvent = queue.poll();
                if (currentEvent.type.equals(EventType.ARRIVAL)) {
                    eventCount++;
                } else {
                    eventCount--;
                }
                if (eventCount > maxEvents) {
                    maxEvents = eventCount;
                    maxLoadStartTime = currentEvent.time;
                    flag = true;
                }
                while (!queue.isEmpty() && queue.peek().time == currentEvent.time) {
                    currentEvent = queue.poll();
                    if (currentEvent.type.equals(EventType.ARRIVAL)) {
                        eventCount++;
                    } else {
                        eventCount--;
                    }
                }
                if (flag && eventCount < maxEvents && currentEvent.time != maxLoadStartTime) {
                    maxLoadEndTime = currentEvent.time;
                    flag = false;
                }
            }
        }
        return new int[]{maxLoadStartTime, maxLoadEndTime};
    }

        static class Event implements Comparable<Event> {
            int time;
            EventType type;

            Event(int time, EventType type) {
                this.time = time;
                this.type = type;
            }

            @Override
            public int compareTo(Event other) {
                if (this.time == other.time) {
                    return this.type == EventType.ARRIVAL ? -1 : 1;
                }
                return Integer.compare(this.time, other.time);
            }
        }

    enum EventType {
        ARRIVAL, DEPARTURE
    }
}
