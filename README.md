# OPQBot-Springboot
<div style="text-align: center; ">
<img src="https://avatars.githubusercontent.com/u/108680016?v=4"  style="zoom: 33%;"  alt="50%"/>
</div>

## 基于OPQ框架新版的QQ机器人，使用SpringBoot进行开发，使用更加方便

### 环境(全部基于测试环境)

| 工具         | 版本    |
|:-----------|-------|
| Jdk        | 17    |
| SpringBoot | 3.1.5 |
| Maven      | 3.9.2 |

***

# ❗使用前提说明

***

### 1. 安装OPQ框架 [OPQ](https://github.com/opq-osc/OPQ/releases)

### 2. 配置项目(参照官方配置)

***

## 功能特点

 ``1. 支持群消息关键词回复``

 ``2. 支持打开群红包``

`` 3. 支持发送图片``

`` 4. 支持发送文件``

`` 5. 已接入通义千问AI大模型``


***

## 📖 快速开始

### 1. 配置项目🤑

确保您已经正确配置了 Spring Boot 项目，并添加了 所有 依赖。

### 2. 创建 Bot 实例

在您将此项目拉取下来之后，在[applicantion.yml](src/main/resources/application.yml) 中添加Bot的ws地址和AI配置

```yaml
# websocket链接地址
base:
  #在此处修改为自己的ws地址
  ws: ws://127.0.0.1:9000/ws
forest:
  backend: okhttp3
  async-mode: platform
  connect-timeout: 300000
  read-timeout: 300000
  timeout: 300000
  variables:
    #  发送消息所需要的地址
    baseApi: 'http://127.0.0.1:9000'
    userAgent: 'Apifox/1.0.0 (https://apifox.com)'
    #    Bot账号
    qq: qqid
    #AI 通义千问apikey
    apiKey:
  log-enabled: false
  log-response-status: true
```

## ✌️很显然修改完之后就可以正常运行了

***

# 项目演示
### 🎈所有演示项目路径均为 => [Test](src/main/java/com/love/example/Test.java)
```java

@Component
public class Test {
    /**
     * 令牌桶限流规则
     */
    private final RateLimiter rateLimiter = RateLimiter.create(0.2); // 限制为每秒0.3次
    @Resource
    private SendMessageApiClient client;
    @Resource
    private OtherApi api;
    @Value("${forest.variables.qq}")
    private Long qq;

    /**
     * 抢完红包之后随机发送感谢语伪装
     * @return
     */
    public String RandomThanks() {
        int i = new Random().nextInt(4);
        String message = "终于抢到了一次呜呜呜呜呜,谢谢老板";
        switch (i) {
            case 0 -> message = "谢谢老板谢谢老板谢谢老板，给你磕头";
            case 1 -> message = "wcwcwc刚进来就有红包吗";
            case 2 -> message = "发红包的最帅，爱你爱你爱你";
            case 3 -> message = "抢了这么多吗";
        }
        return message;
    }

    /**
     * 测试群消息是否正常发送
     * @param event
     * 
     *         消息发送方法
     *         MessageRequest messageRequest = new MessageRequest(msgHead.getFromUin(), "你好", atUinLists);
     *         client.sendMessage(messageRequest);
     */
    @EventListener
    public void send(GroupMessageEvent event) {
        MsgHeadVO msgHead = event.getMsgBodyVO().getCurrentPacket().getEventData().getMsgHead();
        // 判断是否触发关键词
        if (!MessageEquals(event, "测试")) return;
        System.out.println(msgHead.getFromUin());
        List<MessageRequest.UserInfo> atUinLists = new ArrayList<>();
        atUinLists.add(new MessageRequest.UserInfo("0.0", 3092179918L));
        MessageRequest messageRequest = new MessageRequest(msgHead.getFromUin(), "你好", atUinLists);
        client.sendMessage(messageRequest);
    }

    /**
     * 进行发送图片功能，
     * compressAndEncodeToBase64Thumbnails()压缩图片质量方法
     * @param event
     */
    @Async
    @EventListener
    @SneakyThrows
    public void TestSend(GroupMessageEvent event) {
        if (!MessageEquals(event, "/img")) return;
        MsgHeadVO msgHead = event.getMsgBodyVO().getCurrentPacket().getEventData().getMsgHead();
        // 令牌桶限流，防止调用太频繁导致低配置服务器宕机
        if (!rateLimiter.tryAcquire()) {
            MessageRequest messageRequest = new MessageRequest(msgHead.getFromUin(), "调用太频繁，触发限流规则", null);
            client.sendMessage(messageRequest);
            return;
        }

        Long fromUin = event.getMsgBodyVO().getCurrentPacket().getEventData().getMsgHead().getFromUin();
        //图片发送前需要对其进行上传，获取到fileId，fileMd5，fileSize
        UpLoadFile upLoadFile = new UpLoadFile(new UpLoadFile.CgiRequest(2, compressAndEncodeToBase64Thumbnails(api.getPc(), 1.0f)));
        UploadResponse uploadResponse = client.UpLoadFile(upLoadFile);
        log.info("上传图片: {}", new Gson().toJson(uploadResponse));
        ResponseDataVO responseData = uploadResponse.getResponseData();
        Long fileId = responseData.getFileId();
        if (fileId == null) return;
        String fileMd5 = responseData.getFileMd5();
        Integer fileSize = responseData.getFileSize();
        //发送图片传入fileId，fileMd5，fileSize，构造图片消息链进行发送
        List<MessageImageRequest.ImageInfo> imageInfos = new ArrayList<>();
        imageInfos.add(new MessageImageRequest.ImageInfo(fileId, fileMd5, fileSize));
        MessageImageRequest messageImageRequest = new MessageImageRequest(fromUin, imageInfos);
        log.info("发送结果---> %s".formatted(client.sendMessage(messageImageRequest)));
    }


    /**
     * 快速抢红包功能，目前官方的只能支持拼手气红包和口令红包，就做了这个功能，后续还会继续跟进
     * @param event
     */

    @SneakyThrows
    @EventListener
    public void RedBagQuick(GroupMessageEvent event) {
        MsgHeadVO msgHead = event.getMsgBodyVO().getCurrentPacket().getEventData().getMsgHead();
        RedBagRequest.CgiRequest redBagVO = new RedBagRequest.CgiRequest();
        RedBagRequest redBagRequest = new RedBagRequest();
        RedBagVO redBag = event.getMsgBodyVO().getCurrentPacket().getEventData().getMsgBody().getRedBag();
        if (redBag == null) return;
        redBagVO.setWishing(redBag.getWishing());
        redBagVO.setDes(redBag.getDes());
        redBagVO.setRedType(redBag.getRedType());
        redBagVO.setListid(redBag.getListid());
        redBagVO.setAuthkey(redBag.getAuthkey());
        redBagVO.setChannel(redBag.getChannel());
        redBagVO.setStingIndex(redBag.getStingIndex());
        redBagVO.setTransferMsg(redBag.getTransferMsg());
        redBagVO.setToken_17_2(redBag.getToken_17_2());
        redBagVO.setToken_17_3(redBag.getToken_17_3());
        redBagVO.setFromUin(redBag.getFromUin());
        redBagVO.setFromType(redBag.getFromType());
        redBagRequest.setCgiRequest(redBagVO);
        double money = new Gson().fromJson(client.sendMessage(redBagRequest), JsonObject.class).getAsJsonObject("ResponseData").get("GetMoney").getAsDouble();
        if (redBag.getRedType().intValue() == 12) {
            client.sendMessage(new MessageRequest(msgHead.getFromUin(), redBag.getWishing(), null));
            Thread.sleep(10000);
            client.sendMessage(new MessageRequest(msgHead.getFromUin(), RandomThanks(), null));
        } else {
            Thread.sleep(10000);
            client.sendMessage(new MessageRequest(msgHead.getFromUin(), RandomThanks(), null));
        }
        log.info("领取红包金额: {} 元", money / 100.0);
    }
}
```
***
# 感谢