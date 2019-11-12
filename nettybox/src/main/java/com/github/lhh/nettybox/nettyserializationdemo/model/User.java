package com.github.lhh.nettybox.nettyserializationdemo.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private static final long serialVersionUID = 2356736774794775651L;
    private int id;
    private String name;
}
