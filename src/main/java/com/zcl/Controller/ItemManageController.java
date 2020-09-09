package com.zcl.Controller;

import com.zcl.entity.TrainInfo;
import com.zcl.entity.TrainItem;
import com.zcl.entity.TrainLog;
import com.zcl.entity.TrainPlan;
import com.zcl.service.ItemManageService;
import  java.lang.Object;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author changliang
 * 动作项目管理控制层
 */
@RequestMapping(value = "/itemManage")
@RestController
public class ItemManageController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ItemManageService itemManageService;

    /**
     * 添加用户更新的阈值，为空则更新操作,不为空则添加
     * train_type为（fastTrain/customTrain）
     * e.g.user_account=test&item_id=201&update_time=2020-05-22 12:00:00&maxValue=600&train_type=customTrain
     * @param user_account
     * @param maxValue
     * @return hashMap
     */
    @RequestMapping(value = "/insertItemPlanCoef",method = RequestMethod.POST)
    public Map<String, Object> insertItemPlanCoef(String user_account, String item_id, String update_time, String maxValue, String train_type){
        Map<String,Object> hashMap = new HashMap<>();
        try{
            TrainPlan trainPlan = itemManageService.selectTopItemPlanByAccountAndItemId(user_account,item_id,train_type);
            Double threshold = Float.parseFloat(maxValue)*0.7;//此处先写死，后期再更改
            trainPlan.setUpdate_time(update_time);
            Integer i = 0;
            String coef = trainPlan.getCoefficient()==null?"":trainPlan.getCoefficient();
            String thre = trainPlan.getThreshold()==null?"":trainPlan.getThreshold();
            if (coef.equals("")||thre.equals("")){
                trainPlan.setCoefficient(String.valueOf(0.7));
                trainPlan.setThreshold(String.format("%.2f",threshold));
                i = itemManageService.updateItemCoefByAccountAndItem(trainPlan);
            }
            else{
                trainPlan.setCoefficient(String.valueOf(0.7));
                trainPlan.setThreshold(String.format("%.2f",threshold));
                i = itemManageService.insertItemPlan(trainPlan);
            }
            if (i>0){
                hashMap.put("success",true);
                hashMap.put("message","添加用户更新阈值成功");
                logger.info("添加用户更新阈值成功");
            }else{
                hashMap.put("success",false);
                hashMap.put("message","找不到该用户！");
                logger.info("添加用户更新阈值失败");
            }

        }catch (Exception e){
            logger.error("添加用户更新阈值失败，程序出错！",e);
            hashMap.put("success",false);
            hashMap.put("message","添加用户更新阈值失败，程序出错！");
        }
        return hashMap;

    }

    /**
     * 列出所有康复动作
     * @return
     */
    @RequestMapping(value = "/selectAllItem",method = RequestMethod.GET)
    public Map<String,Object> selectAllItem(){
        Map<String,Object> hashMap = new HashMap<>();
        try{
            List<TrainItem> trainItems = itemManageService.selectAllItem();
            if (trainItems!=null){
                hashMap.put("success",true);
                hashMap.put("trainItems",trainItems);
                hashMap.put("message","所有康复动作查询成功！");
                logger.info("所有康复动作查询成功！");
            }else{
                hashMap.put("success",false);
                hashMap.put("trainItems",null);
                hashMap.put("message","康复动作为空！");
                logger.info("康复动作为空！");
            }
        }catch (Exception e){
            hashMap.put("success",false);
            hashMap.put("message","查询康复动作列表失败，程序异常！");
            logger.error("查询康复动作列表失败，程序异常！",e);
        }
        return hashMap;
    }

    /**
     * 用户为完成康复计划的训练计划，包括快速训练和自定义训练
     * e.g.user_account=test
     * @param user_account
     * @return
     */
    @RequestMapping(value = "/queryTrainItemPlanByAccount",method = RequestMethod.GET)
    public Map<String,Object> queryTrainItemPlanByAccount(String user_account){
        Map<String,Object> hashMap = new HashMap<>();
        try{
            List<TrainPlan> trainPlans = itemManageService.queryTopItemPlanByAccount(user_account);
            if (trainPlans!=null){
                hashMap.put("success",true);
                hashMap.put("trainPlans",trainPlans);
                hashMap.put("message","用户康复计划训练查询成功！");
                logger.info("所有康复动作查询成功！");
            }else{
                hashMap.put("success",false);
                hashMap.put("trainPlans", null);
                hashMap.put("message","康复计划训练为空！");
                logger.info("康复计划训练为空！");
            }
        }catch (Exception e){
            hashMap.put("success",false);
            hashMap.put("message","用户康复计划训练查询失败，程序异常！");
            logger.error("用户康复计划训练查询失败，程序异常！",e);
        }
        return hashMap;
    }
    /**
     * 添加自定义训练项目,train_type="customTrain"
     * 自定义训练的day_num可以先设为1，action_num设为10
     * e.g.user_account=test&train_type=customTrain&item_id=201&update_time=2020-05-24&group_num=10
     * @param trainPlan
     * @return
     */
    @RequestMapping(value = "/insertTrainItemPlan",method = RequestMethod.POST)
    public Map<String,Object> insertTrainItemPlan(TrainPlan trainPlan){
        Map<String,Object> hashMap = new HashMap<>();
        try{
            trainPlan.setDay_num(1);
            trainPlan.setAction_num(10);
            trainPlan.setBreak_time(5);
            //先寻找一个阈值以作备份，然后添加自定义训练数据，紧接着把准备好的阈值添加进去，如阈值不存在，则先插入数据，然后提示用户添加阈值
            TrainPlan trainPlanToThreshold = itemManageService.selectTopItemPlanByAccountAndItemId(trainPlan.getUser_account(),trainPlan.getItem_id(),null);
            trainPlan.setItem_name(itemManageService.selectItemNameByItemId(trainPlan.getItem_id()));
            if (trainPlanToThreshold==null||trainPlanToThreshold.getThreshold() == null) {
                Integer i = itemManageService.insertItemPlan(trainPlan);
                hashMap.put("success",false);
                hashMap.put("message","对不起，您没有对该动作设定阈值，请返回个人中心进行阈值的设定");
                logger.info("添加用户训练动作项目成功,但动作阈值需要额外添加");
                return hashMap;
            }else {
                TrainPlan trainPlan1 = itemManageService.selectTopItemPlanByAccountAndItemId(trainPlan.getUser_account(),trainPlan.getItem_id(),trainPlan.getTrain_type());
                trainPlan.setThreshold(trainPlanToThreshold.getThreshold());//为确保阈值成功添加，选取无类型查找的阈值
                trainPlan.setCoefficient(String.valueOf(0.7));
                //检查当天时候已添加过训练计划
                if ((trainPlan1!=null)&&(trainPlan1.getUpdate_time().contains(trainPlan.getUpdate_time().substring(0,10)))){// 当天已添加过训练计划
                    Integer groupNum = trainPlan1.getGroup_num();//训练计划中已存在的组数
                    Integer newGroupNum = groupNum + trainPlan.getGroup_num();
                    //Integer updateStatus = itemManageService.updateGroupNum(newGroupNum,trainPlan.getUpdate_time(),trainPlan1.getPlan_id());
                    trainPlan.setGroup_num(newGroupNum);
                    trainPlan.setPlan_id(trainPlan1.getPlan_id());// 选择最新的plan_id
                    Integer updateStatus = itemManageService.updateItemCoefByAccountAndItem(trainPlan);
                }else{
                    Integer i = itemManageService.insertItemPlan(trainPlan);
                }
                hashMap.put("success",true);
                hashMap.put("message","添加用户自定义训练动作项目数据成功");
                logger.info("添加用户自定义训练动作项目成功");
            }

        }catch (Exception e){
            hashMap.put("success",false);
            hashMap.put("message", "添加用户自定义训练动作项目失败，程序异常！");
            logger.error("添加用户自定义训练动作项目失败，程序异常！",e);
        }
        return hashMap;
    }

    /**
     * 插入用户训练信息(点击开始按钮，没有end_time)
     * 其中group_info要求2-2形式，即第二次第二组（一天内）
     * train_type要求为fastTrain或者customTrain
     * plan_id为trainplan中的训练计划编号
     * e.g.user_account=test&plan_id=120&group_info=2-1&start_time=2020-5-14 12:00:00&train_type=customTrain
     * @param trainInfo
     * @return
     */
    @RequestMapping(value = "/insertTrainInfo",method = RequestMethod.POST)
    public Map<String, Object> insertTrainInfo(TrainInfo trainInfo){
        Map<String,Object> hashMap = new HashMap<>();
        try{
            Integer i = itemManageService.insertTrainInfo(trainInfo);
            Integer trainId = itemManageService.selectTrain_id(trainInfo.getUser_account(),trainInfo.getPlan_id(),trainInfo.getGroup_info());
            if (i>0){
                hashMap.put("success",true);
                hashMap.put("message","添加用户训练信息成功");
                hashMap.put("Train_id",trainId);
                logger.info("添加用户训练信息成功");
            }else{
                hashMap.put("success",false);
                hashMap.put("message","找不到该用户！");
                logger.info("添加用户训练信息失败");
            }
        }catch (Exception e){
            hashMap.put("success",false);
            hashMap.put("message","添加用户训练信息失败，程序异常！");
            logger.error("添加用户训练信息失败，程序异常！",e);
        }
        return hashMap;
    }
    /**
     * 插入用户训练信息结束时间end_time
     * plan_id为trainplan中的训练计划编号
     * e.g.user_account=test&plan_id=120&end_time=2020-5-14 13:00:00
     * @param user_account,plan_id,end_time
     * @return
     */
    @RequestMapping(value = "/insertTrainInfoEndTime",method = RequestMethod.POST)
    public Map<String, Object> insertTrainInfoEndTime(String user_account,String plan_id,String end_time,String group_info){
        Map<String,Object> hashMap = new HashMap<>();
        try{
            TrainInfo trainInfo = itemManageService.queryTopHistoryByAccount(user_account,plan_id,group_info);
            String endTime = trainInfo.getEnd_time()==null?"":trainInfo.getEnd_time();
            //if (endTime.equals("")){
                Integer i = itemManageService.updateEndTimeInTrainInfoByTrainId(end_time,trainInfo.getTrain_id());
                if (i>0){
                    hashMap.put("success",true);
                    hashMap.put("message","插入用户训练信息结束时间成功");
                    logger.info("插入用户训练信息结束时间成功");
                }else{
                    hashMap.put("success",false);
                    hashMap.put("message","找不到该用户！");
                    logger.info("插入用户训练信息结束时间失败");
                }
           //}

        }catch (Exception e){
            hashMap.put("success",false);
            hashMap.put("message","插入用户训练信息结束时间失败，程序异常！");
            logger.error("插入用户训练信息结束时间失败，程序异常！",e);
        }
        return hashMap;
    }
    /**
     * 插入用户训练详细数据
     * 其中status达到阈值返回1，达不到返回0
     * train_id为训练组数编号
     * @param trainLog
     * @return
     */
    @RequestMapping(value = "/insertTrainLog",method = RequestMethod.POST)
    public Map<String, Object> insertTrainLog(TrainLog trainLog){
        Map<String,Object> hashMap = new HashMap<>();
        try{
            Integer i = itemManageService.insertTrainLog(trainLog);
            if (i>0){
                hashMap.put("success",true);
                hashMap.put("message","插入用户训练详细数据成功");
                logger.info("插入用户训练详细数据成功");
            }else{
                hashMap.put("success",false);
                hashMap.put("message","找不到该用户！");
                logger.info("插入用户训练详细数据失败");
            }
        }catch (Exception e){
            hashMap.put("success",false);
            hashMap.put("message","插入用户训练详细数据失败，程序异常！");
            logger.error("插入用户训练详细数据失败，程序异常！",e);
        }
        return hashMap;
    }

    /**
     * 查询个人训练历史
     * @param user_account
     * @param start_time
     * @param end_time
     * @return
     */
    @RequestMapping(value = "/selectHistory",method = RequestMethod.GET)
    public Map<String,Object> selectHistory(String user_account, String start_time, String end_time){
        Map hashMap = new HashMap<String,Object>();
        try{
            List<TrainPlan> list  = itemManageService.selectHistoryRecentWeekByAccount(user_account,start_time,end_time);
            if (list!=null){
                hashMap.put("success",true);
                hashMap.put("message","查询个人训练历史成功");
                hashMap.put("listHistory",list);
                logger.info("查询个人训练历史成功");
            }else{
                hashMap.put("success",false);
                hashMap.put("message","无信息！");
            }
        }catch (Exception e){
            logger.error("查询个人训练历史失败，程序出错！",e);
            hashMap.put("success",false);
            hashMap.put("message","查询个人训练历史失败，程序出错！");
        }
        return hashMap;
    }


    /**
     * @Author: changliang
     * @MethodName: queryTrainItemPlanOfFastTrainByAccount
     * @Description: 查询个人快速训练历史记录
     * @Param: [user_account,start_time,end_time]
     * @Return: java.util.Map<java.lang.String,java.lang.Object>
     * @Date: 2020/6/3 23:15
    **/
    @RequestMapping(value = "/queryFastTrain",method = RequestMethod.GET)
    public Map<String,Object> queryTrainItemPlanOfFastTrainByAccount(String user_account,String start_time, String end_time){
        Map<String,Object> hashMap = new HashMap<>();
        try{
            List<TrainPlan> trainPlans = itemManageService.queryTopItemPlanOfFastTrainByAccount(user_account);//查询快速训练记录
            if (trainPlans!=null){
                for (TrainPlan trainPlan:trainPlans) {
                    List<TrainInfo> trainInfos = itemManageService.queryTrainInfoByTrainPlan(trainPlan.getPlan_id(),trainPlan.getUser_account(),start_time,end_time);
                    trainPlan.setTrainInfo(trainInfos);
                }
                hashMap.put("success",true);
                hashMap.put("message","查询个人快速训练历史成功");
                hashMap.put("listHistory",trainPlans);
                logger.info("查询个人快速训练历史成功");
            }else{
                hashMap.put("success",false);
                hashMap.put("listHistory", null);
                hashMap.put("message","快速训练为空，您可选择自定义训练或联系医师添加训练计划！");
                logger.info("快速训练为空！");
            }
        }catch (Exception e){
            hashMap.put("success",false);
            hashMap.put("message","查询用户快速训练历史记录失败，程序异常！");
            logger.error("查询用户快速训练历史记录失败，程序异常！",e);
        }
        return hashMap;
    }

    /**
     * 通过用户id和动作id共同查询某个动作有没有设定阈值
     * @param user_account
     * @param item_id
     * @return
     */
    @RequestMapping(value = "/queryThreshold",method = RequestMethod.GET)
    public Map<String,Object> queryThreshold(String user_account,String item_id){
        Map hashMap = new HashMap<String,Object>();
        try{
            Map returnData = itemManageService.queryThreshold(user_account,item_id);
            if (returnData!=null){
                hashMap.put("success",true);
                hashMap.put("message","查询某个动作有没有设定阈值成功");
                hashMap.put("threshold",returnData.get("threshold"));
                logger.info("查询某个动作有没有设定阈值成功");
            }else{
                hashMap.put("success",false);
                hashMap.put("message","查询阈值为空");
                logger.info("查询阈值为空");
            }
        }catch (Exception e){
            hashMap.put("success",false);
            hashMap.put("message","查询某个动作有没有设定阈值失败，程序出错");
            logger.info("查询某个动作有没有设定阈值失败，程序出错");
        }
        return hashMap;
    }

   /**
    * @Author: changliang
    * @MethodName: insertDefaultFastTrain
    * @Description: 插入用户的默认快速训练计划
    * @Param: [user_account, update_time]
    * @Return: java.util.Map<java.lang.String,java.lang.Object>
    * @Date: 2020-08-16 15:53
   **/
    @RequestMapping(value = "/insertDefaultFastTrain",method = RequestMethod.POST)
    public Map<String,Object> insertDefaultFastTrain(String user_account,String update_time){
        Map<String,Object> hashMap = new HashMap<>();
        TrainPlan trainPlan = new TrainPlan();
        try {
            trainPlan.setUser_account(user_account);
            trainPlan.setUpdate_time(update_time);
            trainPlan.setItem_id(String.valueOf(101));
            trainPlan.setItem_name("肘关节屈曲，前臂旋前(联带运动期)");
            trainPlan.setDay_num(1);
            trainPlan.setGroup_num(10);
            trainPlan.setAction_num(10);
            trainPlan.setBreak_time(5);
            trainPlan.setCoefficient(String.valueOf(0.7));
            trainPlan.setThreshold(String.valueOf(2.5));
            trainPlan.setTrain_type("fastTrain");
            Integer i = itemManageService.insertDefaultFastTrain(trainPlan);
            hashMap.put("success",true);
            if (i==0){
                logger.info("用户"+user_account+"的快速训练已存在，不需要自动添加");
                hashMap.put("message","快速训练已存在，不需要自动添加");
            }else{
                logger.info("用户"+user_account+"的快速训练添加成功");
                hashMap.put("message","插入用户快速训练计划成功");
            }

        }catch (Exception e){
            hashMap.put("success",false);
            hashMap.put("message","插入用户快速训练计划失败");
            logger.info("添加用户快速训练计划失败，程序出错");
        }
        return hashMap;
    }
}
