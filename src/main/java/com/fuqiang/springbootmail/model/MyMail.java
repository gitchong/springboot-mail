package com.fuqiang.springbootmail.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>Title: MyMail</p>
 * <p>Description: MyMail</p>
 * <p>Copyright: Xi An BestTop Technologies, ltd. Copyright(c) 2018/p>
 *
 * @author Fuqiang
 * @version 0.0.0.1
 * <pre>Histroy:
 *       2019/12/30 0030 17:33 Create by Fuqiang
 * </pre>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyMail implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String to;
    private String subject;
    private String content;
}
