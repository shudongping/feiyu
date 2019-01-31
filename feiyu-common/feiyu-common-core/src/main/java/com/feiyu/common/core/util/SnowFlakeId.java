package com.feiyu.common.core.util;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Kevin
 * @date 9/27/2017
 * 雪花ID生成器
 */
@Component
public class SnowFlakeId {

    /**
     * 起始的时间戳
     * 1483200000000L: 2017.01.01 北京时间毫秒数
     */
    private final static long START_STAMP = 1483200000000L;

    /**
     * 序列号占用的位数
     */
    private final static int SEQUENCE_BIT = 12;

    /**
     * 机器标识占用的位数
     */
    private final static int MACHINE_BIT = 5;

    /**
     * 数据中心占用的位数
     */
    private final static int DATACENTER_BIT = 5;

    /**
     * 每一部分的最大值
     */
    private final static long MAX_DATACENTER_NUM = -1L ^ (-1L << DATACENTER_BIT);

    private final static long MAX_MACHINE_NUM = -1L ^ (-1L << MACHINE_BIT);

    private final static long MAX_SEQUENCE = -1L ^ (-1L << SEQUENCE_BIT);

    /**
     * 每一部分向左的位移
     */
    private final static int MACHINE_LEFT = SEQUENCE_BIT;

    private final static int DATACENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;

    private final static int TIMESTAMP_LEFT = DATACENTER_LEFT + DATACENTER_BIT;

    /**
     * 数据中心
     */
    @Value("${spring.sequence.datacenter-id}")
    private long datacenterId;

    /**
     * 机器标识
     */
    @Value("${spring.sequence.machine-id}")
    private long machineId;

    /**
     * 序列号
     */
    private long sequence = 0L;

    /**
     * 上一次时间戳
     */
    private long lastStamp = -1L;


//    public SnowFlakeId(long datacenterId, long machineId) {
//        if (datacenterId > MAX_DATACENTER_NUM || datacenterId < 0) {
//            throw new IllegalArgumentException("datacenterId can't be greater than MAX_DATACENTER_NUM or less than 0");
//        }
//
//        if (machineId > MAX_MACHINE_NUM || machineId < 0) {
//            throw new IllegalArgumentException("machineId can't be greater than MAX_MACHINE_NUM or less than 0");
//        }
//
//        this.datacenterId = datacenterId;
//        this.machineId = machineId;
//    }

    /**
     * 产生下一个ID
     *
     * @return
     */
    public synchronized long nextId() {
        long currStmp = getNewstmp();

        if (currStmp < lastStamp) {
            throw new RuntimeException("Clock moved backwards.  Refusing to generate id");
        }

        if (currStmp == lastStamp) {
            //相同毫秒内，序列号自增
            sequence = (sequence + 1) & MAX_SEQUENCE;

            //同一毫秒的序列数已经达到最大
            if (sequence == 0L) {
                currStmp = getNextMill();
            }
        } else {
            //不同毫秒内，序列号置为0
            sequence = 0L;
        }

        lastStamp = currStmp;

        return (currStmp - START_STAMP) << TIMESTAMP_LEFT //时间戳部分
                | datacenterId << DATACENTER_LEFT      //数据中心部分
                | machineId << MACHINE_LEFT            //机器标识部分
                | sequence;                            //序列号部分
    }

    private long getNextMill() {
        long mill = getNewstmp();
        while (mill <= lastStamp) {
            mill = getNewstmp();
        }
        return mill;
    }

    private long getNewstmp() {
        return System.currentTimeMillis();
    }

    /**
     * 根据提供的时间格式以及时间字符串表达式转换为时间并获取该时间的毫秒数
     *
     * @param dataFormat 时间的表达式格式，例如：yyyy-MM-dd
     * @param dataStr    时间表达式，例如：2017-01-01
     * @return
     * @throws ParseException
     */
    public static long getTimestamp(String dataFormat, String dataStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(dataFormat);

        Date date = sdf.parse(dataStr);
        long longDate = date.getTime();

        return longDate;
    }

    /**
     * 根据雪花ID反向获取创建ID时的序列号
     *
     * @param snowFlakeId
     * @return
     */
    public static long getSequenceById(long snowFlakeId) {
        return snowFlakeId & MAX_SEQUENCE;
    }

    /**
     * 根据雪花ID反向获取创建该ID时所在机器
     *
     * @param snowFlakeId
     * @return
     */
    public static long getMachineById(long snowFlakeId) {
        return snowFlakeId >> MACHINE_LEFT & MAX_MACHINE_NUM;
    }

    /**
     * 根据雪花ID反向获取创建该ID时所在数据中心
     *
     * @param snowFlakeId
     * @return
     */
    public static long getDataCenterById(long snowFlakeId) {
        return snowFlakeId >> DATACENTER_LEFT & MAX_DATACENTER_NUM;
    }

    /**
     * 根据雪花ID反向获取该ID的创建时间
     *
     * @param snowFlakeId
     * @return
     */
    public static long getTimestampById(long snowFlakeId) {
        return (snowFlakeId >> TIMESTAMP_LEFT) + START_STAMP;
    }

    /**
     * 仅用于测试
     *
     * @param args
     */
//    public static void main(String[] args) {
//        SnowFlakeId snowFlake = new SnowFlakeId(31, 19);
//
//        for (int i = 0; i < (1 << 12); i++) {
//            System.out.println(snowFlake.nextId());
//        }
//    }
}
