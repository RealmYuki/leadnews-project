package com.utils.thread;

import com.model.wemedia.pojos.WmUser;

/**
 * ClassName: thread
 * Description:
 * {@code @Author} 苏羽晨
 * {@code @Create} 2023/11/6 15:46
 * {@code @Version}  1.0
 */
public class WmThreadLocalUtil {

    private final static ThreadLocal<WmUser> WM_USER_THREAD_LOCAL = new ThreadLocal<>();

    /**
     * 添加用户
     * @param wmUser
     */
    public static void  setUser(WmUser wmUser){
        WM_USER_THREAD_LOCAL.set(wmUser);
    }

    /**
     * 获取用户
     */
    public static WmUser getUser(){
        return WM_USER_THREAD_LOCAL.get();
    }

    /**
     * 清理用户
     */
    public static void clear(){
        WM_USER_THREAD_LOCAL.remove();
    }
}