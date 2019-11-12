package com.github.lhh.nettybox.nettyheartbeatdemo.model;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;

@Data
public class RequestInfo implements Serializable {
    private static final long serialVersionUID = 8601970518679430259L;

    private String ip;
    private HashMap<String, Object> cpuPercMap;
    private HashMap<String, Object> memoryMap;
}
