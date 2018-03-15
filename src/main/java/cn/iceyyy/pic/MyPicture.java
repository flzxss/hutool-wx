package cn.iceyyy.pic;

import com.icexxx.icewx.msg.ImageProcessor;
import com.icexxx.icewx.msg.MessageSender;

public class MyPicture implements ImageProcessor {

    @Override
    public String reply(String mediaId, String userName, String createTime, String picUrl) {
        String str = "";
        str += mediaId + ">>" + userName + ">>" + createTime + ",picUrl=" + picUrl;
        MessageSender.send(str);
        return str;
    }

}
