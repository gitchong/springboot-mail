package com.fuqiang.springbootmail.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fuqiang.springbootmail.common.BaseController;
import com.fuqiang.springbootmail.common.ResultResponse;
import com.fuqiang.springbootmail.model.UserEntity;
import com.fuqiang.springbootmail.service.UserEntityService;
import com.fuqiang.springbootmail.service.mq.MQProducer;
import com.fuqiang.springbootmail.util.MailServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>Title: UserController</p>
 * <p>Description: UserController</p>
 * <p>Copyright: Xi An BestTop Technologies, ltd. Copyright(c) 2018/p>
 *
 * @author Fuqiang
 * @version 0.0.0.1
 * <pre>Histroy:
 *       2020/1/6 0006 16:09 Create by Fuqiang
 * </pre>
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserEntityService userEntityService;
    @Autowired
    private MQProducer producer;

    @PostMapping("/add")
    public ResultResponse add(@RequestBody UserEntity userEntity) {
        userEntityService.addUserEntity(userEntity);
        return okResponse();
    }

    /**
     * 用户注册
     */
    @PostMapping("/register")
    public ResultResponse register(@RequestBody UserEntity userEntity) {
//        ExecutorService executorService = Executors.newFixedThreadPool(1);
//        mailServer.setTo("fuqiangvn@163.com");
//        mailServer.setSubject("haahahaha");
//        mailServer.setContent("xxxxxxxxxxxxxxxxxx");
//        executorService.submit(mailServer);
//        return okResponse("ok");
        try {
            producer.sendUser(userEntity);
            return okResponse("ok");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return errorResponse("error");
        }
    }
}
