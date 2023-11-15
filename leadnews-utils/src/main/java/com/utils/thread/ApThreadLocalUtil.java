package com.utils.thread;

import com.model.user.pojo.ApUser;

/**
 * ClassName: thread
 * Description:
 * {@code @Author} 苏羽晨
 * {@code @Create} 2023/11/6 15:46
 * {@code @Version}  1.0
 */
public class ApThreadLocalUtil {

    private final static ThreadLocal<ApUser> AP_USER_THREAD_LOCAL = new ThreadLocal<>();

    /**
     * 添加用户
     * @param apUser
     */
    public static void  setUser(ApUser apUser){
        AP_USER_THREAD_LOCAL.set(apUser);
    }

    /**
     * 获取用户
     */
    public static ApUser getUser(){
        return AP_USER_THREAD_LOCAL.get();
    }

    /**
     * 清理用户
     */
    public static void clear(){
        AP_USER_THREAD_LOCAL.remove();
    }
}