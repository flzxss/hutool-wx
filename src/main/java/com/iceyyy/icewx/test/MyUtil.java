package com.iceyyy.icewx.test;

import cn.hutool.core.date.DateException;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.CharUtil;
import cn.hutool.core.util.StrUtil;

public class MyUtil {

    public static boolean isExpression(String message) {
        if (StrUtil.isBlank(message)) {
            return false;
        }
        for (int i = 0; i < message.length(); i++) {
            char ch = message.charAt(i);
            if (!CharUtil.isAsciiPrintable(ch)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isDate(String dateStr) {
        try {
            DateUtil.parse(dateStr);
            return true;
        } catch (DateException e) {
            return false;
        }
    }
}
