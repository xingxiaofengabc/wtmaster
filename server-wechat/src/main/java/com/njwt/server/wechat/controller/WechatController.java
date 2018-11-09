package com.njwt.server.wechat.controller;

import com.njwt.server.wechat.core.util.SignUtil;
import com.njwt.server.wechat.service.WechatService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("/wechatController")
public class WechatController {
    private static final  String WECHCT_TOKEN="xxf123456";

    @Resource
    private WechatService wechatService;

    @RequestMapping(params="wechat", method = RequestMethod.GET)
    public void wechatGet(HttpServletRequest request,
                          HttpServletResponse response,
                          @RequestParam(value = "signature") String signature,
                          @RequestParam(value = "timestamp") String timestamp,
                          @RequestParam(value = "nonce") String nonce,
                          @RequestParam(value = "echostr") String echostr) {


            if (SignUtil.checkSignature(WECHCT_TOKEN,signature,
                    timestamp, nonce)) {
                try {
                    response.getWriter().print(echostr);

                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
    }

    @RequestMapping(params = "wechat", method = RequestMethod.POST)
    public void wechatPost(HttpServletResponse response,
                           HttpServletRequest request) throws IOException {
        String respMessage = wechatService.coreService(request);
        PrintWriter out = response.getWriter();
        out.print(respMessage);
        out.close();
    }

}
