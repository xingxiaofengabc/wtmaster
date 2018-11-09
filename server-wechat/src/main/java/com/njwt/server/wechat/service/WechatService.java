package com.njwt.server.wechat.service;

import com.njwt.server.wechat.core.message.resp.Article;
import com.njwt.server.wechat.core.message.resp.NewsMessageResp;
import com.njwt.server.wechat.core.message.resp.TextMessageResp;
import com.njwt.server.wechat.core.util.LogUtil;
import com.njwt.server.wechat.core.util.MessageUtil;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.*;

@Service
public class WechatService {

    public String coreService(HttpServletRequest request) {
        String respMessage = null;
        try {
            // 默认返回的文本消息内容
            String respContent = "请求处理异常，请稍候尝试！";
            // xml请求解析
            Map<String, String> requestMap = MessageUtil.parseXml(request);
            // 发送方帐号（open_id）
            String fromUserName = requestMap.get("FromUserName");
            // 公众帐号
            String toUserName = requestMap.get("ToUserName");
            // 消息类型
            String msgType = requestMap.get("MsgType");
            String msgId = requestMap.get("MsgId");
            //消息内容
            String content = requestMap.get("Content");
            LogUtil.info("------------微信客户端发送请求---------------------   |   fromUserName:"+fromUserName+"   |   ToUserName:"+toUserName+"   |   msgType:"+msgType+"   |   msgId:"+msgId+"   |   content:"+content);
            //根据微信ID,获取配置的全局的数据权限ID
            LogUtil.info("-toUserName--------"+toUserName);
            //String sys_accountId = weixinAccountService.findByToUsername(toUserName).getId();
            String sys_accountId="gh_36c63147edb5";
            LogUtil.info("-sys_accountId--------"+sys_accountId);
            ResourceBundle bundler = ResourceBundle.getBundle("sysConfig");
            // 默认回复此文本消息
            TextMessageResp textMessage = new TextMessageResp();
            textMessage.setToUserName(fromUserName);
            textMessage.setFromUserName(toUserName);
            textMessage.setCreateTime(new Date().getTime());
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
            textMessage.setContent(this.getMainMenu());
            // 将文本消息对象转换成xml字符串
            respMessage = MessageUtil.textMessageToXml(textMessage);
            //【微信触发类型】文本消息
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
                LogUtil.info("------------微信客户端发送请求------------------【微信触发类型】文本消息---");
                respMessage = doTextResponse(content,toUserName,textMessage,bundler,
                        sys_accountId,respMessage,fromUserName,request,msgId,msgType);
            }
            //【微信触发类型】图片消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
                respContent = "您发送的是图片消息！";
            }
            //【微信触发类型】地理位置消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
                respContent = "您发送的是地理位置消息！";
            }
            //【微信触发类型】链接消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
                respContent = "您发送的是链接消息！";
            }
            //【微信触发类型】音频消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
                respContent = "您发送的是音频消息！";
            }
            //【微信触发类型】事件推送
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
                LogUtil.info("------------微信客户端发送请求------------------【微信触发类型】事件推送---");
                // 事件类型
                String eventType = requestMap.get("Event");
                // 订阅
                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
                   // respMessage = doDingYueEventResponse(requestMap, textMessage, bundler, respMessage, toUserName, fromUserName, respContent, sys_accountId);
                }
                // 取消订阅
                else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
                    // TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
                }
                // 自定义菜单点击事件
                else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
                    //respMessage = doMyMenuEvent(requestMap, textMessage, bundler, respMessage, toUserName, fromUserName, respContent, sys_accountId, request);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respMessage;
    }
    /**
     * Q译通使用指南
     *
     * @return
     */
    public static String getTranslateUsage() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("微译使用指南").append("\n\n");
        buffer.append("微译为用户提供专业的多语言翻译服务，目前支持以下翻译方向：").append("\n");
        buffer.append("    中 -> 英").append("\n");
        buffer.append("    英 -> 中").append("\n");
        buffer.append("    日 -> 中").append("\n\n");
        buffer.append("使用示例：").append("\n");
        buffer.append("    翻译我是中国人").append("\n");
        buffer.append("    翻译dream").append("\n");
        buffer.append("    翻译さようなら").append("\n\n");
        buffer.append("回复“?”显示主菜单");
        return buffer.toString();
    }


    /**
     * 针对文本消息
     * @param content
     * @param toUserName
     * @param textMessage
     * @param bundler
     * @param sys_accountId
     * @param respMessage
     * @param fromUserName
     * @param request
     * @throws Exception
     */
    String doTextResponse(String content,String toUserName,TextMessageResp textMessage,ResourceBundle bundler,
                          String sys_accountId,String respMessage,String fromUserName,HttpServletRequest request,String msgId,String msgType) throws Exception{
        //=================================================================================================================


        //=================================================================================================================
        //Step.1 判断关键字信息中是否管理该文本内容。有的话优先采用数据库中的回复
        LogUtil.info("------------微信客户端发送请求--------------Step.1 判断关键字信息中是否管理该文本内容。有的话优先采用数据库中的回复---");

                //根据返回消息key，获取对应的文本消息返回给微信客户端

                textMessage.setContent("abc");
                respMessage = MessageUtil.textMessageToXml(textMessage);


        return respMessage;
    }

    public String getMainMenu(){
        return "getMainMenu";
    }


}
