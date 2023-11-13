package com.model.shedule.dtos;

import lombok.Data;

import java.io.Serializable;

/**
 * ClassName: Task
 * Description:
 * {@code @Author} 苏羽晨
 * {@code @Create} 2023/11/9 16:52
 * {@code @Version}  1.0
 */
@Data
public class Task implements Serializable {

    /**
     * 任务id
     */
    private Long taskId;
    /**
     * 类型
     */
    private Integer taskType;

    /**
     * 优先级
     */
    private Integer priority;

    /**
     * 执行id
     */
    private long executeTime;

    /**
     * task参数
     */
    private byte[] parameters;

}
