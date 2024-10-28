package com.scofen.l78z.xiaochuan.controller.request;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * @Description: TODO
 * @Author gaofeng
 * @Date 2023/9/3 8:40 PM
 **/
@Data
public class BasketParam {


    private String visitId;

    private List<String> productIds;


}
