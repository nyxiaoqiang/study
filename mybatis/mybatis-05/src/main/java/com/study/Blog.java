package com.study;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Blog implements Serializable {
    private String id;
    private String title;
    private String authorName;
    private Date createTime;
    private int views;
}
