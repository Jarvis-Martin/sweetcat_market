package com.sweetcat.secondkill.domain.commodity.vo;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/27-14:53
 * @version: 1.0
 */
public class SecondKillTimeZone {

    private static final Integer MAXHOURSPERDAY = 24;
    private static volatile SecondKillTimeZone instance;
    private static List<Tuple<Integer, Integer>> zones;

    private SecondKillTimeZone() {
        zones = new ArrayList<>(16);
        for (int i = 0; i < MAXHOURSPERDAY; i += 2) {
            zones.add(new Tuple<Integer, Integer>(i, i + 2));
        }
    }

    public static SecondKillTimeZone getInstance() {
        if (instance == null) {
            synchronized (SecondKillTimeZone.class) {
                if (instance == null) {
                    instance = new SecondKillTimeZone();
                }
            }
        }
        return instance;
    }

    public Tuple<Integer, Integer> zoneOf(Instant now) {
        final int hour = LocalDateTime.ofInstant(now, ZoneId.systemDefault()).getHour();
        Tuple<Integer, Integer> tuple = zones.stream().filter(
                zone -> zone.startTime().compareTo(hour) >= 0 && zone.deadline().compareTo(hour) >= 0
        ).findFirst().get();
        return tuple;
    }

    public class Tuple<S, D> {
        /**
         * 开始时间
         */
        private S startTime;
        /**
         * 接收时间
         */
        private D deadline;

        public Tuple(S startTime, D deadline) {
            this.startTime = startTime;
            this.deadline = deadline;
        }

        public S startTime() {
            return startTime;
        }

        public D deadline() {
            return deadline;
        }

        public void startTime(S startTime) {
            this.startTime = startTime;
        }

        public void deadline(D deadline) {
            this.deadline = deadline;
        }
    }
}
