package com.fuqiang.springbootmail.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fuqiang.springbootmail.mapper.UserEntityMapper;
import com.fuqiang.springbootmail.model.UserEntity;
import com.fuqiang.springbootmail.service.UserEntityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * <p>Title: UserEntityServiceImpl</p>
 * <p>Description: UserEntityServiceImpl</p>
 * <p>Copyright: Xi An BestTop Technologies, ltd. Copyright(c) 2018/p>
 *
 * @author Fuqiang
 * @version 0.0.0.1
 * <pre>Histroy:
 *       2020/1/6 0006 16:14 Create by Fuqiang
 * </pre>
 */
@Service
@Slf4j
public class UserEntityServiceImpl extends ServiceImpl<UserEntityMapper, UserEntity> implements UserEntityService {

    @Autowired
    private UserEntityMapper userEntityMapper;

    @Override
    public void addUserEntity(UserEntity userEntity) {
        userEntity.setId(UUID.randomUUID().toString().replace("-", ""));
        userEntityMapper.insert(userEntity);
        log.info("数据插入成功");
    }
}
