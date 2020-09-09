package com.zcl.Controller;


import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import com.zcl.entity.Doctors;
import com.zcl.entity.Stage;
import com.zcl.entity.StageHistory;
import com.zcl.entity.Users;
import com.zcl.service.UserManageService;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author changliang
 * 用户管理控制层
 */
@RequestMapping(value = "/userManage")
@RestController
public class UserManageController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserManageService userManageService;

    /**
     * 插入用户信息
     * e.g.user_account=test111&user_name=zcl1&phone=111111&gender=男&birth=1996-03-26&stage=正常阶段&doctor_account=114
     * @param users
     * @return
     */
    @RequestMapping(value = "/insertUser",method = RequestMethod.POST,produces = "application/json;charset = UTF-8")
    public Map<String,Object> insertUser(Users users){
        Map hashMap = new HashMap<String,Object>();
        try{
            Integer i = userManageService.insertUser(users);
            if (i>0){
                Integer infoFlag = userManageService.updateUser(users);
                if (infoFlag>0){
                    hashMap.put("success",true);
                    hashMap.put("message","插入用户信息成功");
                    logger.info("插入用户信息成功");
                }
            }else{
                hashMap.put("success",false);
                hashMap.put("message","该账户名已存在");
            }
        }catch (Exception e){
            logger.error("插入用户信息失败",e);
            hashMap.put("success",false);
            hashMap.put("message","插入用户信息失败，程序出错！");
        }
        return hashMap;
    }

    /**
     * 更新用户信息
     * user_account和user_name必选，其余可选
     * e.g.user_account=test1&user_name=小星星&phone=2222222&gender=男&birth=1996-03-26&updateTime=2020-05-03&doctor_account=114
     * @param users
     * @return
     */
    @RequestMapping(value = "/updateUser",method = RequestMethod.POST)
    public Map<String,Object> updateUser(String updateTime,Users users){
        Map hashMap = new HashMap<String,Object>();
        try{
            String s = userManageService.selectStageByAccount(users.getUser_account());
            String oldStage = s==null?"":s;
            if (users.getStage()!=null){
                if (!users.getStage().equals(oldStage)){ //康复信息变化了
                    StageHistory stageHistory = new StageHistory();
                    stageHistory.setStage_name(users.getStage());
                    stageHistory.setUser_account(users.getUser_account());
                    stageHistory.setUpdate_time(updateTime);
                    Integer stageStatus = userManageService.insertStageChange(stageHistory);
                    if (stageStatus>0){
                        logger.info("用户康复阶段变化添加成功!");
                    }else{
                        logger.info("用户康复阶段没有发生变化！");
                    }
                }
            }
            Integer i = userManageService.updateUser(users);
            if (i>0){
                hashMap.put("success",true);
                hashMap.put("message","更新用户信息成功");
                logger.info("更新用户信息成功");
            }else{
                hashMap.put("success",false);
                hashMap.put("message","找不到该用户！");
            }
        }catch (Exception e){
            logger.error("更新用户信息失败，程序出错！",e);
            hashMap.put("success",false);
            hashMap.put("message","更新用户信息失败，程序出错！");
        }
        return hashMap;
    }
    /**
     * 根据用户帐户查询用户信息
     * @param user_account
     * @return
     */
    @RequestMapping(value = "/queryUserByAccount",method = RequestMethod.GET)
    public Map<String,Object> queryUserByAccount(String user_account){
        Map hashMap = new HashMap<String,Object>();
        try{
            Users users = userManageService.queryUserByAccount(user_account);
            if (users!=null){
                hashMap.put("success",true);
                hashMap.put("message","根据账户查询用户信息成功");
                hashMap.put("user",users);
                logger.info("根据账户查询用户信息成功");
            }else{
                hashMap.put("success",false);
                hashMap.put("message","该账户信息为空！");
            }
        }catch (Exception e){
            logger.error("根据账户查询用户信息失败，程序出错！",e);
            hashMap.put("success",false);
            hashMap.put("message","根据账户查询用户信息失败，程序出错！");
        }
        return hashMap;
    }
    /**
     * 查询医生信息
     * @return
     */
    @RequestMapping(value = "/selectDoctor",method = RequestMethod.GET)
    public Map<String,Object> selectDoctor(){
        Map hashMap = new HashMap<String,Object>();
        try{
            List<Doctors> doctors  = userManageService.selectDoctor();
            if (doctors!=null){
                hashMap.put("success",true);
                hashMap.put("message","查询医生信息成功");
                hashMap.put("doctors",doctors);
                logger.info("查询医生信息成功");
            }else{
                hashMap.put("success",false);
                hashMap.put("message","无信息！");
            }
        }catch (Exception e){
            logger.error("查询医生信息失败，程序出错！",e);
            hashMap.put("success",false);
            hashMap.put("message","查询医生信息失败，程序出错！");
        }
        return hashMap;
    }
    /**
     * 查询全部康复阶段信息
     * @return
     */
    @RequestMapping(value = "/selectAllStage",method = RequestMethod.GET)
    public Map<String,Object> selectAllStage(){
        Map hashMap = new HashMap<String,Object>();
        try{
            List<Stage> stages  = userManageService.selectAllStage();
            if (stages!=null){
                hashMap.put("success",true);
                hashMap.put("message","查询全部康复阶段信息成功");
                hashMap.put("stages",stages);
                logger.info("查询医生信息成功");
            }else{
                hashMap.put("success",false);
                hashMap.put("message","无信息");
            }
        }catch (Exception e){
            logger.error("查询全部康复阶段信息失败，程序出错！",e);
            hashMap.put("success",false);
            hashMap.put("message","查询全部康复阶段信息失败，程序出错！");
        }
        return hashMap;
    }

    /**
     * 获取用户登录的唯一标识码open_id
     * e.g.js_code=033A5b3m0fMubq1cii3m0ok93m0A5b3L
     * @param js_code
     * @return
     */
    @RequestMapping(value = "/getOpenId",method = RequestMethod.GET)
    public Map<String,Object> getOpenId(String js_code){
        Map hashMap = new HashMap<String,Object>();
        CloseableHttpClient client = null;
        HttpGet httpGet = null;
        HttpResponse response = null;
        try{
            String url = "https://api.weixin.qq.com/sns/jscode2session";
            String charset = "UTF-8";
            client = HttpClientBuilder.create().build();
            httpGet = new HttpGet(url);
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(50000).setConnectTimeout(50000).build();
            httpGet.setConfig(requestConfig);
            httpGet.addHeader("Content-Type", "application/x-www-form-urlencoded");
            httpGet.addHeader("charset", charset);
            //httpGet.setURI(URI.create(url + "?appid=wx2b190ac2b4871b22&secret=f12d2662446e1046f472ba84a8396c76&grant_type=authorization_code&js_code="+js_code));
            httpGet.setURI(URI.create(url + "?appid=wxa7c08382e22795e7&secret=0fe5a36d9202c480fc0987ca7e56082b&grant_type=authorization_code&js_code="+js_code));
            response = (HttpResponse) client.execute(httpGet);
            String res = EntityUtils.toString(response.getEntity());
            hashMap.put("success",true);
            hashMap.put("info",JSONObject.parseObject(res));
            logger.info("获取用户登录的唯一标识码成功");
        }catch (Exception e){
            logger.error("获取用户登录的唯一标识码失败，程序出错！",e);
            hashMap.put("success",false);
            hashMap.put("message","获取用户登录的唯一标识码失败，程序出错！");
        }finally {
            httpGet.releaseConnection();
            try {
                if (client != null) {
                    client.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return hashMap;
    }


}
