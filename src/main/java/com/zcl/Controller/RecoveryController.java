package com.zcl.Controller;

import com.zcl.entity.TrainInfo;
import com.zcl.entity.TrainLog;
import com.zcl.service.RecoveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: health-care
 * @Package: com.zcl.Controller
 * @ClassName: RecoveryController
 * @Author: zheng
 * @Description: 训练记录
 * @Date: 2020/8/16 17:07
 * @Version: 1.0
 */
@RequestMapping(value = "/Recovery")
@Controller
public class RecoveryController {
    @Autowired
    private RecoveryService recoveryService;

    @RequestMapping(value = "/Training",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> selectTrainLog(){
        Map<String,Object> hashMap = new HashMap<>();
        String user_account = "oQvs_5Zxv6f_fYw7m5gaAHpPBX5U";
        try{
            List<TrainInfo> trainInfoList= recoveryService.selectLatestTrainInfo(user_account);
            List<TrainLog> trainLogList = recoveryService.selectTrainLogByTrainIdAndUser(user_account,trainInfoList.get(0).getTrain_id());
            if (trainLogList != null){

                hashMap.put("success",true);
                //trainLogList.get(0).get
                hashMap.put("trainLog",trainLogList);
                hashMap.put("message","康复训练记录查询成功！");

            }else{
                hashMap.put("success",false);
                hashMap.put("trainLog",null);
                hashMap.put("message","康复训练记录为空！");
            }
        }catch (Exception e){
            hashMap.put("success",false);
            hashMap.put("message","查询康复训练记录列表失败，程序异常！");
        }
        //model.addAttribute("data",hashMap);
        return hashMap;
    }

    @RequestMapping(value = "/Trainlog",method = RequestMethod.GET)
    public String index(){
        return "/Trainlog";
    }
}
