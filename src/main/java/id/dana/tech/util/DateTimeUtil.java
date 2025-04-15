/**
 * DANA.id
 * Copyright (c) 2017‐2025 All Rights Reserved.
 */
package id.dana.tech.util;

import id.dana.tech.model.client.constant.CommonConstant;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author SLW (sumitro.wijaya@dana.id)
 * @version $Id: DateTimeUtil.java, v 0.1 2025‐03‐21 20.01 SLW Exp $$
 */
public class DateTimeUtil {

    private static final DateTimeFormatter DANA_API_TIMESTAMP_FORMATTER = DateTimeFormatter
        .ofPattern(CommonConstant.DANA_API_TIMESTAMP_FORMAT);

    public static ZonedDateTime getCurrentDateTime() {
        return ZonedDateTime.now(ZoneId.of(CommonConstant.DANA_API_TIMESTAMP_TIMEZONE)); // GMT+7
    }

    public static String getFormattedCurrentDateTime() {
        return getCurrentDateTime().format(DANA_API_TIMESTAMP_FORMATTER);
    }
}