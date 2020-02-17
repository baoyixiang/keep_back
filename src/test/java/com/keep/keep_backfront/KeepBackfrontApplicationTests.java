package com.keep.keep_backfront;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.keep.keep_backfront.dao.UserDao;
import com.keep.keep_backfront.entity.User;
import com.keep.keep_backfront.enums.Gender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootTest
class KeepBackfrontApplicationTests {

    @Autowired
    private UserDao userDao;

    @Test
    void contextLoads() {
        User user = new User();
        user.setWechatId("asasadefe");
        user.setAvatar("asfergwew");
        user.setName("baoyixiang");
        user.setPersonalSignature("你好啊");
        user.setGender(0);
        user.setLastLoginTime(new Date());
        user.setBeansCount(12);

        List<String> roles = new ArrayList<>(Arrays.asList("admin", "user"));
        user.setRoles(JSONArray.parseArray(JSON.toJSONString(roles)));
        List<String> aaa = JSON.parseArray(user.getRoles().toJSONString(), String.class);
        aaa.forEach(System.out::println);
        //userDao.insert(user);
    }

}
