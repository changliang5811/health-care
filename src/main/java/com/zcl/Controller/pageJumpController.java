package com.zcl.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @ClassName pageJumpController
 * @Description: 研电赛页面跳转
 * @Author changliang
 * @Date 2020-08-16 9:53
 * @Version V1.0
 **/
@Controller
@RequestMapping("/gamesJump")
public class pageJumpController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/index")
    public String index(){
        return "qdstsIndex";
    }

    @RequestMapping("/loginPage")
    public String loginPage(){
        return "login";
    }

    @RequestMapping("/historyPage")
    public String historyPage(){
        return "historyPage";
    }

    @RequestMapping("/QhistoryPage")
    public String QhistoryPage(){
        return "QhistoryPage";
    }

    @RequestMapping("/dataPage")
    public String dataPage(){
        return "dataPage";
    }

    @RequestMapping("/exit")
    public String exit(){
        return "qdstsIndex";
    }


}
