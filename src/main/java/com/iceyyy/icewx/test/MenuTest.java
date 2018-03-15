package com.iceyyy.icewx.test;

import java.util.ArrayList;
import java.util.List;

import com.icexxx.icewx.menu.MenuService;
import com.icexxx.icewx.menu.button.ClickButton;
import com.icexxx.icewx.menu.button.LocationButton;
import com.icexxx.icewx.menu.button.ParentButton;
import com.icexxx.icewx.menu.button.PicButton;
import com.icexxx.icewx.menu.button.ScancodeButton;
import com.icexxx.icewx.menu.button.ViewButton;
import com.icexxx.icewx.menu.button.WxButton;
import com.icexxx.icewx.menu.button.WxMenu;
import com.icexxx.icewx.menu.type.Pic;
import com.icexxx.icewx.menu.type.Scancode;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;

public class MenuTest {
    private static final Log log = LogFactory.get();

    public static void main(String[] args) {
        WxMenu menu = new WxMenu();
        List<WxButton> button = new ArrayList<WxButton>();
        WxButton wxButton1 = createWxButton1();
        WxButton wxButton2 = createWxButton2();
        WxButton wxButton3 = createWxButton3();
        button.add(wxButton3);
        button.add(wxButton2);
        button.add(wxButton1);
        menu.setButton(button);
        boolean check = MenuService.create(menu);
        if (check) {
            log.info("菜单创建成功");
        } else {
            log.info("菜单创建失败");
        }
    }

    private static WxButton createWxButton3() {
        ParentButton wxButton = new ParentButton();
        wxButton.setName("Hutool");
        List<WxButton> subButton = new ArrayList<WxButton>();
        subButton.add(createWxButton11());
        subButton.add(createWxButton12());
        subButton.add(createWxButton13());
        subButton.add(createWxButton14());
        subButton.add(createWxButton15());
        wxButton.setSub_button(subButton);
        return wxButton;
    }

    private static WxButton createWxButton11() {
        ClickButton button = new ClickButton();
        button.setName("Hutool简介");
        button.setMessage("A set of tools that keep Java sweet.");
        return button;
    }

    private static WxButton createWxButton12() {
        ViewButton button = new ViewButton();
        button.setName("参考文档");
        button.setUrl("http://hutool.mydoc.io/");
        return button;
    }

    private static WxButton createWxButton13() {
        ViewButton button = new ViewButton();
        button.setName("API文档");
        button.setUrl("http://www.hutool.cn/apidocs/4.0.7/");
        return button;
    }

    private static WxButton createWxButton14() {
        ViewButton button = new ViewButton();
        button.setName("gitee");
        button.setUrl("https://gitee.com/loolly/hutool/");
        return button;
    }

    private static WxButton createWxButton15() {
        ViewButton button = new ViewButton();
        button.setName("github");
        button.setUrl("https://github.com/looly/hutool/");
        return button;
    }

    private static WxButton createWxButton2() {
        ParentButton wxButton = new ParentButton();
        wxButton.setName("主要菜单");
        List<WxButton> subButton = new ArrayList<WxButton>();
        subButton.add(createWxButton21());
        subButton.add(createWxButton22());
        subButton.add(createWxButton23());
        subButton.add(createWxButton24());
        subButton.add(createWxButton25());
        wxButton.setSub_button(subButton);
        return wxButton;
    }
    // private static WxButton createWxButton2() {
    // ClickButton button=new ClickButton();
    //// button.setKey(key);
    // button.setName("第二菜单");
    // button.setMessage("水彩笔芯");
    // return button;
    // }

    private static WxButton createWxButton21() {
        ScancodeButton wxButton = new ScancodeButton();
        wxButton.setName("二维码");
        wxButton.setType(Scancode.SCANCODE_WAITMSG);
        return wxButton;
    }

    private static WxButton createWxButton22() {
        ViewButton button = new ViewButton();
        button.setName("官网");
        button.setUrl("http://www.hutool.cn/");
        return button;
    }

    private static WxButton createWxButton23() {
        PicButton wxButton = new PicButton();
        wxButton.setName("图片菜单");
        wxButton.setType(Pic.PIC_WEIXIN);
        return wxButton;
    }

    private static WxButton createWxButton24() {
        ScancodeButton wxButton = new ScancodeButton();
        wxButton.setName("PUSH");
        wxButton.setType(Scancode.SCANCODE_PUSH);
        return wxButton;
    }

    private static WxButton createWxButton25() {
        ScancodeButton wxButton = new ScancodeButton();
        wxButton.setName("WAITMSG");
        wxButton.setType(Scancode.SCANCODE_WAITMSG);
        return wxButton;
    }

    private static WxButton createWxButton1() {
        ClickButton button = new ClickButton();
        button.setName("最新版本");
        button.setMessage("V 4.0.7");
        return button;
    }

}
