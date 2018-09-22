package org.wjh.web;

import java.util.Date;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wjh.domain.User;

import net.spy.memcached.MemcachedClient;

@Controller
public class HelloController {

    Log logger = LogFactory.getLog(HelloController.class);

    @Autowired
    MemcachedClient memcachedClient;

    @ResponseBody
    @RequestMapping("/wjh/hello.do")
    public Object hello() {
        String key = UUID.randomUUID().toString().replace("-", "");
        User user = new User();
        user.setUserId(key);
        user.setName(key);
        user.setBirthday(new Date());
        memcachedClient.set(key, 7200, user);
        User value = (User) memcachedClient.get(key);
        return value;
    }
}
