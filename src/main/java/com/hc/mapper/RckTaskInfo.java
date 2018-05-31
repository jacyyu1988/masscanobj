package com.hc.mapper;

import com.hc.entity.RckTaskInfoEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 备案信息
 * @author yusj
 * @date 2018/5/17
 */
public interface RckTaskInfo {
    public RckTaskInfoEntity one(String taskId);
    public int update(RckTaskInfoEntity param);
}
