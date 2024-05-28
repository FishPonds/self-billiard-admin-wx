package com.lingdu.common.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件创建人：波波
 * 日期：2024/5/24
 * 时间：15:53
 * 类名：TimeSlotUtil  时间段生成工具类
 *
 * @author 猛男波波
 */
public class TimeSlotUtil {

    /**
     * 生成从startTime到endTime的30分钟时间段
     */
    public static List<TimeSlot> generateTimeSlots(LocalDateTime startTime, LocalDateTime endTime) {
        List<TimeSlot> timeSlots = new ArrayList<>();
        LocalDateTime currentTime = LocalDateTime.now();

        // 从当天00:00到次日00:00
        LocalDateTime startOfDay = startTime.toLocalDate().atStartOfDay();
        LocalDateTime endOfDay = endTime.toLocalDate().plusDays(1).atStartOfDay();

        LocalDateTime nextTimeSlotStart = startOfDay;
        while (!nextTimeSlotStart.isEqual(endOfDay)) {
            LocalDateTime nextTimeSlotEnd = nextTimeSlotStart.plusMinutes(30);

            // 确定时间段的状态
            int state = nextTimeSlotEnd.isBefore(currentTime) ? -1 : 0;

            // 确定 dayIndex
            int dayIndex = nextTimeSlotStart.toLocalDate().equals(startTime.toLocalDate()) ? 0 : 1;

            // 添加时间段
            addTimeSlot(timeSlots, dayIndex, state, nextTimeSlotStart, nextTimeSlotEnd);

            nextTimeSlotStart = nextTimeSlotEnd;
        }

        return timeSlots;
    }

    /**
     * 向列表中添加时间段
     *
     * @param timeSlots
     * @param index
     * @param state     0:可选 -1:不可选 1:已选
     * @param startTime
     * @param endTime
     */
    private static void addTimeSlot(List<TimeSlot> timeSlots, int index, int state, LocalDateTime startTime, LocalDateTime endTime) {
        String timeStr = startTime.format(DateTimeFormatter.ofPattern("H:mm")) + "-" + endTime.format(DateTimeFormatter.ofPattern("H:mm"));
        long startMillis = startTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long endMillis = endTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        timeSlots.add(new TimeSlot(index, startTime.getHour(), state, timeStr, startMillis, endMillis, startTime.toLocalDate()));
    }

    /**
     * 将时间向上舍入到下一个半小时或整点
     *
     * @param time
     * @return
     */
    private static LocalDateTime roundUpToNextHalfHour(LocalDateTime time) {
        int minute = time.getMinute();
        if (minute == 0 || minute == 30) {
            // 如果已经是整点或半小时点，不做处理
            return time;
        } else if (minute < 30) {
            // 如果在0到30之间，舍入到30分钟
            minute = 30;
        } else {
            // 如果在30到60之间，舍入到整点，并增加一小时
            time = time.plusHours(1);
            minute = 0;
        }
        return time.withMinute(minute).withSecond(0).withNano(0);
    }

    /**
     * 生成开始小时数组，不包含实际时间以前的小时。
     *
     * @param now 当前时间
     * @return 开始小时数组
     */
    public static String[] generateBeginHours(LocalDateTime now) {
        List<String> hours = new ArrayList<>();
        LocalDate today = LocalDate.now();

        if (now.toLocalDate().equals(today)) {
            int currentHour = now.getHour();
            // 循环生成从当前小时到23时的小时列表
            for (int i = currentHour; i < 24; i++) {
                hours.add(i + "时");
            }
        } else {
            // 如果不是当天，则生成从0时到23时的小时列表
            for (int i = 0; i < 24; i++) {
                hours.add(i + "时");
            }
        }

        // 将列表转换为数组并返回
        return hours.toArray(new String[0]);
    }

    /**
     * 生成分钟数组，每10分钟一个间隔。
     *
     * @return 分钟数组
     */
    public static String[] generateTenMinuteIntervals() {
        List<String> minutes = new ArrayList<>();
        // 添加每10分钟一个间隔的分钟
        for (int i = 0; i < 60; i += 10) {
            minutes.add(i + "分");
        }
        // 将列表转换为数组并返回
        return minutes.toArray(new String[0]);
    }

    /**
     * 生成结束小时数组，包含从0时到次日23时的小时。
     *
     * @return 结束小时数组
     */
    public static String[] generateEndHours(LocalDateTime now) {
        List<String> hours = new ArrayList<>();
        // 获取当前小时
        int currentHour = now.getHour();
        // 循环生成从当前时到23时的小时列表
        for (int i = currentHour; i < 24; i++) {
            hours.add(i + "时");
        }
        // 循环生成从次日0时到次日当前时的小时列表
        for (int i = 0; i <= currentHour; i++) {
            hours.add("次日" + i + "时");
        }
        // 将列表转换为数组并返回
        return hours.toArray(new String[0]);
    }

    /**
     * 将字符串数组转换为逗号分隔的字符串表示。
     *
     * @param array 字符串数组
     * @return 字符串表示
     */
    public static String arrayToString(String[] array) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if (i < array.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * 将时间舍入到最近的10分钟
     *
     * @param time
     * @return
     */
    public static LocalDateTime roundToNearestTimeSlot(LocalDateTime time) {
        int minute = time.getMinute();
        int roundedMinute;
        if (minute % 10 < 5) {
            // 向下舍入到最近的 10 分钟
            roundedMinute = (minute / 10) * 10;
        } else {
            // 向上舍入到最近的 10 分钟
            roundedMinute = ((minute / 10) + 1) * 10;
        }
        if (roundedMinute >= 60) {
            time = time.plusHours(1).withMinute(0);
        } else {
            time = time.withMinute(roundedMinute).withSecond(0).withNano(0);
        }
        return time;
    }

    /**
     * 时间段类
     */
    public static class TimeSlot {
        private final int index;
        private final int hour;
        private int state;
        private final String timeStr;
        private final long startTime;
        private final long endTime;
        private final LocalDate reservationDate;

        // 构造函数
        public TimeSlot(int index, int hour, int state, String timeStr, long startTime, long endTime, LocalDate reservationDate) {
            this.index = index;
            this.hour = hour;
            this.state = state;
            this.timeStr = timeStr;
            this.startTime = startTime;
            this.endTime = endTime;
            this.reservationDate = reservationDate;
        }

        public int getIndex() {
            return index;
        }

        public int getHour() {
            return hour;
        }

        public int getState() {
            return state;
        }

        public String getTimeStr() {
            return timeStr;
        }

        public long getStartTime() {
            return startTime;
        }

        public long getEndTime() {
            return endTime;
        }

        public LocalDate getReservationDate() {
            return reservationDate;
        }

        @Override
        public String toString() {
            return "{" +
                    "\"index\": " + index +
                    ", \"hour\": " + hour +
                    ", \"state\": " + state +
                    ", \"timestr\": \"" + timeStr + "\"" +
                    ", \"startTime\": \"/Date(" + startTime + ")/\"" +
                    ", \"endTime\": \"/Date(" + endTime + ")/\"" +
                    ", \"reservationDate\": \"" + reservationDate + "\"" +
                    '}';
        }
    }
}