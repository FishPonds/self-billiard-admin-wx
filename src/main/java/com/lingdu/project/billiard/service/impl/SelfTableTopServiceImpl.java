package com.lingdu.project.billiard.service.impl;

import com.lingdu.common.utils.TimeSlotUtil;
import com.lingdu.common.utils.text.Convert;
import com.lingdu.project.billiard.domain.SelfTableTop;
import com.lingdu.project.billiard.domain.SelfTableTopAlbum;
import com.lingdu.project.billiard.mapper.SelfTableTopAlbumMapper;
import com.lingdu.project.billiard.mapper.SelfTableTopMapper;
import com.lingdu.project.billiard.service.ISelfTableTopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 桌台信息Service业务层处理
 *
 * @author 猛男波波
 * @date 2024-05-18
 */
@Service
public class SelfTableTopServiceImpl implements ISelfTableTopService {
    @Autowired
    private SelfTableTopMapper selfTableTopMapper;

    @Autowired
    private SelfTableTopAlbumMapper selfTableTopAlbumMapper;

    /**
     * 查询桌台信息
     *
     * @param tableId 桌台信息主键
     * @return 桌台信息
     */
    @Override
    public SelfTableTop selectSelfTableTopByTableId(Long tableId) {
        return selfTableTopMapper.selectSelfTableTopByTableId(tableId);
    }

    /**
     * 查询桌台信息列表
     *
     * @param selfTableTop 桌台信息
     * @return 桌台信息
     */
    @Override
    public List<SelfTableTop> selectSelfTableTopList(SelfTableTop selfTableTop) {
        return selfTableTopMapper.selectSelfTableTopList(selfTableTop);
    }

    /**
     * 新增桌台信息
     *
     * @param selfTableTop 桌台信息
     * @return 结果
     */
    @Override
    public int insertSelfTableTop(SelfTableTop selfTableTop) {
        //先新增桌台，然后返回桌台主键，再新增桌台相册，根据selfTableTop.getImages，先把图片路径根据逗号分割成数组，然后循环插入
        int rows = selfTableTopMapper.insertSelfTableTop(selfTableTop);
        if (rows > 0) {
            Long tableId = selfTableTop.getTableId();
            String[] images = selfTableTop.getImages().split(",");
            for (String image : images) {
                SelfTableTopAlbum selfTableTopAlbum = new SelfTableTopAlbum();
                selfTableTopAlbum.setTableId(tableId);
                selfTableTopAlbum.setImageUrl(image);
                selfTableTopAlbumMapper.insertSelfTableTopAlbum(selfTableTopAlbum);
            }
        }
        return rows;
    }

    /**
     * 修改桌台信息
     *
     * @param selfTableTop 桌台信息
     * @return 结果
     */
    @Override
    public int updateSelfTableTop(SelfTableTop selfTableTop) {
        //先修改桌台，然后删除桌台相册，再新增桌台相册
        selfTableTopAlbumMapper.deleteSelfTableTopAlbumByTableId(new String[]{selfTableTop.getTableId().toString()});
        String[] images = selfTableTop.getImages().split(",");
        for (String image : images) {
            SelfTableTopAlbum selfTableTopAlbum = new SelfTableTopAlbum();
            selfTableTopAlbum.setTableId(selfTableTop.getTableId());
            selfTableTopAlbum.setImageUrl(image);
            selfTableTopAlbumMapper.insertSelfTableTopAlbum(selfTableTopAlbum);
        }
        return selfTableTopMapper.updateSelfTableTop(selfTableTop);
    }

    /**
     * 批量删除桌台信息
     *
     * @param tableIds 需要删除的桌台信息主键
     * @return 结果
     */
    @Override
    public int deleteSelfTableTopByTableIds(String tableIds) {
        //删除桌台相册
        selfTableTopAlbumMapper.deleteSelfTableTopAlbumByTableId(Convert.toStrArray(tableIds));
        return selfTableTopMapper.deleteSelfTableTopByTableIds(Convert.toStrArray(tableIds));
    }

    /**
     * 根据桌台编码及门店id查重
     *
     * @param selfTableTop 桌台信息
     * @return 桌台信息集合
     */
    @Override
    public int selectCountSelfTableTopByTableCode(SelfTableTop selfTableTop) {
        return selfTableTopMapper.selectCountSelfTableTopByTableCode(selfTableTop);
    }

    /**
     * 查询桌球预约信息
     *
     * @param tableId    桌台id
     * @param tableCode  桌台编码
     * @param changeTime 当前时间
     */
    @Override
    public Map<String, Object> checkRoomTimeSlot(Long tableId, String tableCode, String changeTime) {
        Map<String, Object> map = new HashMap<>();
        //获取当前时间到次日08:00的时间段并标记预约状态
//        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime now = LocalDateTime.parse(changeTime, formatter);
        LocalDateTime endTime = LocalDateTime.of(now.toLocalDate().plusDays(1), LocalDateTime.MAX.toLocalTime());

        List<TimeSlotUtil.TimeSlot> timeSlots = TimeSlotUtil.generateTimeSlots(now, endTime);
//        markReservedSlots(timeSlots, now.toLocalDate());
        map.put("TimeRangeInfo", timeSlots);
        map.put("BeginHours", TimeSlotUtil.generateBeginHours(now));
        map.put("BeginMinutes", TimeSlotUtil.generateTenMinuteIntervals());
        map.put("EndHours", TimeSlotUtil.generateEndHours(now));
        map.put("EndMinutes", TimeSlotUtil.generateTenMinuteIntervals());
        // 向上取整
        LocalDateTime beginTime = TimeSlotUtil.roundToNearestTimeSlot(now);
        map.put("BeginTime", beginTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        // 例如设定结束时间为当前时间的4小时后
        LocalDateTime endTimeExample = now.plusHours(4);
        LocalDateTime endTimeRounded = TimeSlotUtil.roundToNearestTimeSlot(endTimeExample);
        map.put("EndTime", endTimeRounded.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        return map;
    }

    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime endTime = LocalDateTime.of(now.toLocalDate().plusDays(1), LocalTime.of(8, 0));

        List<TimeSlotUtil.TimeSlot> timeSlots = TimeSlotUtil.generateTimeSlots(now, endTime);
        timeSlots.forEach(System.out::println);
    }

//    public static void main(String[] args) {
//        LocalDateTime now = LocalDateTime.now();
////
////        // 生成开始小时列表（不包含实际时间以前的小时）
////        List<String> beginHours = TimeSlotUtil.generateBeginHours(now);
////        // 生成分钟列表（每10分钟一个间隔）
////        List<String> beginMinutes = TimeSlotUtil.generateTenMinuteIntervals();
////        // 生成结束小时列表（包含当天和次日的小时）
////        List<String> endHours = TimeSlotUtil.generateEndHours();
////        // 生成分钟列表（每10分钟一个间隔）
////        List<String> endMinutes = TimeSlotUtil.generateTenMinuteIntervals();
////
////        // 打印生成的数据结构
////        System.out.println("BeginHours: " + beginHours);
////        System.out.println("BeginMinutes: " + beginMinutes);
////        System.out.println("EndHours: " + endHours);
////        System.out.println("EndMinutes: " + endMinutes);
//    }

    /**
     * 标记已有预约的时间段
     *
     * @param timeSlots
     * @param date
     */
    private void markReservedSlots(List<TimeSlotUtil.TimeSlot> timeSlots, LocalDate date) {
        // 查找并标记预约的时间段
//        for (TimeSlotUtil.TimeSlot slot : timeSlots) {
//            if (slot.getId() == reservedSlotId) {
//                slot.setStatus("booked");
//                break;
//            }
//        }
    }

}