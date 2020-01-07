package com.fuqiang.springbootmail.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fuqiang.springbootmail.model.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * <p>Title: UserEntity</p>
 * <p>Description: UserEntity</p>
 * <p>Copyright: Xi An BestTop Technologies, ltd. Copyright(c) 2018/p>
 *
 * @author Fuqiang
 * @version 0.0.0.1
 * <pre>Histroy:
 *       2020/1/6 0006 15:59 Create by Fuqiang
 * </pre>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("login_user")
public class UserEntity extends BaseEntity<UserEntity> {

    private String username;
    private String password;
    private String account;
    private String mail;
}
