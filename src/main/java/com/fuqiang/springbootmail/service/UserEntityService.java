package com.fuqiang.springbootmail.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fuqiang.springbootmail.model.UserEntity;

/**
 * <p>Title: UserEntityService</p>
 * <p>Description: UserEntityService</p>
 * <p>Copyright: Xi An BestTop Technologies, ltd. Copyright(c) 2018/p>
 *
 * @author Fuqiang
 * @version 0.0.0.1
 * <pre>Histroy:
 *       2020/1/6 0006 16:13 Create by Fuqiang
 * </pre>
 */
public interface UserEntityService extends IService<UserEntity> {

    void addUserEntity(UserEntity userEntity);
}
