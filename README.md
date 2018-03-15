# hutool-wx
hutool-wx

icewx教程

1. 申请微信公众号测试账号.
   申请地址:
    微信公众号测试账号申请地址:
    https://mp.weixin.qq.com/debug/cgi-bin/sandbox?t=sandbox/login
   微信开发文档地址:
   https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1472017492_58YV5
2.新建一个web工程
    在src文件夹下新建一个wx.properties,设置编码为UTF-8.
    wx.properties有如下配置:
    appID     -- 对应微信公众号平台的appID
    appsecret -- 对应微信公众号平台的appsecret
    url       -- 服务器部署的web项目的公网地址(与微信公众号平台一致)
    token     -- 微信公众号平台的token,相当于一个密码.
    其中 url token 在微信公众号平台上是需要你手动填的.
    appID appsecret由微信公众号平台提供,不可编辑.
    defaultMessage是关注微信号后默认的回复消息.[该项可选]
3. 配置web.xml
    <servlet>
        <servlet-name>iceWxServlet</servlet-name>
        <servlet-class>com.icexxx.icewx.servlet.WxServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>iceWxServlet</servlet-name>
        <url-pattern>/*</url-pattern>
    </servlet-mapping>
4. 为避免访问首页报404错误,建议在WebContent下添加一个index.jsp
5. 在lib下添加如下jar文件:
   icewx-1.0.3.jar
   hutool-all-4.0.5.jar
   log4j-1.2.17.jar
    icewx-1.0.3.jar的下载地址为:
    http://central.maven.org/maven2/com/icexxx/icewx/1.0.1/icewx-1.0.3.jar
   如果是maven,只需要引入:
   <!-- https://mvnrepository.com/artifact/com.icexxx/icewx -->
<dependency>
    <groupId>com.icexxx</groupId>
    <artifactId>icewx</artifactId>
    <version>1.0.3</version>
</dependency>


5. 直接启动项目
6. icewx提供了对指定的消息类型进行扩展.
   只需要实现对应的接口即可.
   这些接口都位于 com.icexxx.icewx.msg包下.
   所有接口列表如下:
   InitProcessor 容器初始化回调.
   发送消息回调
   TextProcessor文本消息回调.
   返回值即回复的消息,return null将不使用icewx默认的消息处理器.
   可以通过MessageSender.send方法发送文本消息.
   可以通过MessageSender.sendEmptyMessage()发送空消息,此时服务器不回复消息.
   ImageProcessor图片消息回调
   VoiceProcessor语音消息回调
   ShortvideoProcessor小视频消息回调
   VideoProcessor视频消息回调
   LocationProcessor位置消息回调
   FileProcessor其他文件消息回调
   LinkProcessor链接消息回调
   
  点击菜单按钮时的事件回调
  ClickEventProcessor点击事件回调
  LocationEventProcessor发送位置事件回调
  ScanEventProcessor扫描二维码事件回调
  ViewEventProcessor跳转网页事件回调
  其他事件回调
  SubscribeEventProcessor关注事件回调
  UnsubscribeEventProcessor取消关注事件回调
  
7. 创建菜单:
   参考示例创建WxMenu对象.
   然后调用MenuService.create方法创建菜单.
   创建菜单的工程和微信公众号工程可以不在同一台服务器,但是需要有相同的wx.properties配置文件.
   ClickButton可以通过setMessage方法设置点击按钮后回复的消息内容.
   以上接口均为可选实现,也可以全部不实现.
8. 该项目提供了上传图片永久素材的功能,详见示例.
