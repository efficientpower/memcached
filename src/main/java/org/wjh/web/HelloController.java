package org.wjh.web;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.spy.memcached.MemcachedClient;

@Controller
public class HelloController {
    @Autowired
    MemcachedClient memcachedClient;

    public HelloController() {
        System.out.println("HelloController 构造方法");
        // TODO Auto-generated constructor stub
    }

    @ResponseBody
    @RequestMapping("/wjh/hello.do")
    public Object hello() {
        String key = UUID.randomUUID().toString().replace("-", "");
        memcachedClient.set(key, 7200, key);
        String value = (String)memcachedClient.get(key);
        return value;
    }
}
