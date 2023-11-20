package com.article.interceptor;

import com.model.user.pojo.ApUser;
import com.utils.thread.ApThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * ClassName: WmTokenInterceptor
 * Description:
 * {@code @Author} 苏羽晨
 * {@code @Create} 2023/11/6 15:48
 * {@code @Version}  1.0
 */
@Slf4j
public class ApTokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userId = request.getHeader("userId");
        Optional<String> optional = Optional.ofNullable(userId);
        if (optional.isPresent()) {
            ApUser apUser = new ApUser();
            apUser.setId(Integer.valueOf(userId));
            ApThreadLocalUtil.setUser(apUser);
            log.info("apTokenFilter设置用户信息到thread-local中...");
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("清理threadlocal...");
        ApThreadLocalUtil.clear();
    }
}
