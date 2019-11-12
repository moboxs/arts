package com.github.lhh.nettybox.nettyserializationdemo.protocol;

import lombok.Data;
import java.io.Serializable;

@Data
public class Request implements Serializable {
    private static final long serialVersionUID = -7336039497154209297L;
    private Long requestId;
    private Object parameters;
}
