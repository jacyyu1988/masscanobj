package com.hc.mapper;

import org.springframework.cache.annotation.CachePut;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author yusj
 * @date 2018/5/16
 */

public interface User {

    public List<HashMap<String,Object>> all();
    public HashMap<String,Object> one(long id);
}