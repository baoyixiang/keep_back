package com.keep.keep_backfront.controller;

import com.keep.keep_backfront.entity.Custom;
import com.keep.keep_backfront.service.CustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author bao
 */
@RestController
@RequestMapping("api/user/custom")
public class CustomController {

    @Autowired
    private CustomService customService;

    @PostMapping("insertCustom")
    public ResponseEntity insertCustom(String title, Integer userId) {
        return customService.insertCustom(title, userId);
    }

    @PostMapping("getCustomListByUserId")
    public List<Custom> getListByUser(Integer userId) {
        return customService.getCustomByUser(userId);
    }


}
