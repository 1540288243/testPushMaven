package com.yupi.yuapiInterface.controller;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.yupi.yuapiInterfacesdk.model.User;
import com.yupi.yuapiInterfacesdk.utils.SignUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 名称 API
 *
 */
@RestController
@RequestMapping("/name")
public class NameController {

    @GetMapping("/get")
    public String getNameByGet(String name, HttpServletRequest request) {

        return "GET 你的名字是" + name;
    }

    @PostMapping("/post")
    public String getNameByPost(@RequestParam String name, HttpServletRequest request) {
        return "POST 你的名字是" + name;
    }

    @PostMapping("/user")
    public String getUsernameByPost(@RequestBody User user, HttpServletRequest request) {
        String accessKey = request.getHeader("accessKey");
        String nonce = request.getHeader("nonce");
        String timestamp = request.getHeader("timestamp");
        String sign = request.getHeader("sign");
        String body = request.getHeader("body");
        //todo 实际情况应该去数据库中查询
        if (!("b4cd959f7e54ea7394581ae903ead40d".equals(accessKey))) {
            throw new RuntimeException("无权限");
        }
        if(Long.parseLong(nonce)>10000){
            throw new RuntimeException("无权限");
        }
        // 时间和当前时间不能超过五分钟
        long timestampLong = Long.parseLong(timestamp);
        //校验时间戳与当前时间相差是否大于5分钟
        if (DateUtil.between(new Date(), DateUtil.date(timestampLong), DateUnit.MINUTE) > 5) {
            throw new RuntimeException("无权限");
        }
        //实际情况是从数据库中查询
        String serverSign = SignUtils.genSign(body, "262b7d4d553d0693683df68ce43c2029");

        if (!sign.equals(serverSign)) {
            throw new RuntimeException("无权限");
        }
        return "POST JSON 你的名字是" + user.getUserName();
    }

}
