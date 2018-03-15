package cn.iceyyy.pic;

import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import com.icexxx.icewx.msg.MessageSender;
import com.icexxx.icewx.msg.TextProcessor;
import com.icexxx.icewx.util.IceWxUtil;
import com.iceyyy.icewx.test.MyUtil;
import com.iceyyy.nongli.NongLi;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import cn.hutool.script.ScriptRuntimeException;
import cn.hutool.script.ScriptUtil;

public class MyText implements TextProcessor {
    private static final Log log = LogFactory.get();

    @Override
    public String reply(String message, String userName, String createTime) {
        log.info("message = {}", message);
        if (message.equals("aa")) {
            return "[aaa]";
        } else if ("bb".equals(message)) {
            MessageSender.send("消息内容为:{" + message + "}");
            return null;
        } else if (message.equals("cc")) {
            MessageSender.send("消息内容为:(" + message + ")");
        }
        if ("今天星期几".equals(message)) {
            return message;
        }
        if ("time".equalsIgnoreCase(message)) {
            return message;
        }
        if ("date".equalsIgnoreCase(message)) {
            return message;
        }
        if ("你好".equalsIgnoreCase(message)) {
            return "你好";
        }
        if ("数组转List".equalsIgnoreCase(message)) {
            return "cn.hutool.core.collection.CollUtil \n public static <T> ArrayList<T> newArrayList(T... values)";
        }
        if (message.toLowerCase().contains("hello") && message.toLowerCase().contains("world")) {
            return "Hello World!";
        }

        if (message.contains("农历")) {
            String dateStr = message.replace("农历", "");
            if (MyUtil.isDate(dateStr)) {
                String nongli = NongLi.getDate(dateStr);
                MessageSender.send(nongli);
                return null;
            }
        }
        if (message.contains("nongli")) {
            String dateStr = message.replace("nongli", "");
            if (MyUtil.isDate(dateStr)) {
                String nongli = NongLi.getDate(dateStr);
                MessageSender.send(nongli);
                return null;
            }
        }
        if (message.startsWith("nl")) {
            String dateStr = StrUtil.removePrefix(message, "nl");
            if (MyUtil.isDate(dateStr)) {
                String nongli = NongLi.getDate(dateStr);
                MessageSender.send(nongli);
                return null;
            }
        }
        if (message.length() <= 9 && NumberUtil.isInteger(message)) {
            MessageSender.send(Convert.toInt(message) + 1 + "");
            return null;
        }
        if (message.length() > 9 && NumberUtil.isLong(message)) {
            MessageSender.send(Convert.toLong(message) + 1 + "");
            return null;
        }
        if (NumberUtil.isDouble(message)) {
            MessageSender.send(Convert.toDouble(message) + 1 + "");
            return null;
        }
        if (MyUtil.isExpression(message)) {
            String string = null;
            try {
                string = ScriptUtil.eval(message).toString();
                if (string != null) {
                    Double doubleValue = Convert.toDouble(string);
                    if (doubleValue != null) {
                        string = NumberUtil.toStr(doubleValue);
                        MessageSender.send(string);
                        return null;
                    }
                }
            } catch (ScriptRuntimeException e) {

            }
        }
        if (message.length() == 1 && ReUtil.isMatch("[\u4e00-\u9fa5]", message)) {
            try {
                String pinyin = PinyinHelper.convertToPinyinString(message, " ", PinyinFormat.WITHOUT_TONE);
                MessageSender.send(pinyin);
                return null;
            } catch (PinyinException e) {
                e.printStackTrace();
            }

        }
        if (IceWxUtil.isFace(message)) {
            MessageSender.send(IceWxUtil.convertFace(message));
            return null;
        }
        String keyWord = MessUtil.getKeyWord(message);
        try {
            String find = MessUtil.find(keyWord);
            if (find == null) {
                return keyWord;
            } else if ("".equals(find)) {
                return "没有找到对应的方法,请缩短关键词";
            } else {
                return find;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (MyUtil.isExpression(message)) {
            return "表达式消息:" + message;
        }
        if (message.length() < 15) {
            return message;
        }
        return null;
    }

}
