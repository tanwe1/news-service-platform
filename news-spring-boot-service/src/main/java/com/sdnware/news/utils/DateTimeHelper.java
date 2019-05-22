package com.sdnware.news.utils;

import java.time.LocalDateTime;

public class DateTimeHelper {

    /**
     * 存储MongoDB数据的时间以减过8小时
     * 故在此需增加8小时以保证时间同步
     * @param dateTime
     * @return
     */
    public static LocalDateTime convertToMongoDbDateTime(LocalDateTime dateTime) {
        return dateTime.plusHours(8);
    }
}
