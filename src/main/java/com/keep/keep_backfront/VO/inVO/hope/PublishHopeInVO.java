package com.keep.keep_backfront.VO.inVO.hope;

import com.alibaba.fastjson.JSONArray;
import com.keep.keep_backfront.handler.exception.ParameterErrorException;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Data
public class PublishHopeInVO {
    private String wordContent;
    private List<String> images;
    private String voice;
    private Integer createUserId;
    private Boolean isAnonymous;
    private Boolean isSeeSelf;

    public void checkParam(){
        if(StringUtils.isEmpty(wordContent)){
            throw new ParameterErrorException("心愿内容不能为空");
        }
        if(createUserId==null){
            throw new ParameterErrorException("用户Id不能为空");
        }
        if(isAnonymous==null){
            throw new ParameterErrorException("请选择是否匿名");
        }
        if(isSeeSelf==null){
            throw new ParameterErrorException("请选择是否自己可见");
        }
    }
}
