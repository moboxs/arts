package com.github.lhh.nettybox.nettyserializationdemo.protocol;

import lombok.Data;

import java.io.Serializable;

@Data
public class Response implements Serializable {
    private static final long serialVersionUID = -8917029631138697344L;
    private Long requestId;
    private String error;
    private Object result;
}
