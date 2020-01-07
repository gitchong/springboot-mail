package com.fuqiang.springbootmail.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fuqiang.springbootmail.common.BaseController;
import com.fuqiang.springbootmail.common.ResultResponse;
import com.fuqiang.springbootmail.service.mq.MQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Title: MQController</p>
 * <p>Description: MQController</p>
 * <p>Copyright: Xi An BestTop Technologies, ltd. Copyright(c) 2018/p>
 *
 * @author Fuqiang
 * @version 0.0.0.1
 * <pre>Histroy:
 *       2020/1/6 0006 15:38 Create by Fuqiang
 * </pre>
 */
@RestController
@RequestMapping("/mq")
public class MQController extends BaseController {

    @Autowired
    private MQProducer mqProducer;

    @PostMapping("/send")
    public ResultResponse send(@RequestParam String content) {
        try {
            mqProducer.send(content);
            return okResponse("ok");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return errorResponse("error");
        }
    }
}
