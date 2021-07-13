package com.spring.cloud.alibaba.remote.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UlityResponse {
    private Integer code;
    private String result;
    private String errorMsg;

    public static UlityResponse ok(String result){
        return new UlityResponse(200,result,"");
    }

    public static UlityResponse error(String result,String errorMsg){
        return new UlityResponse(-1,result,errorMsg);
    }
}
