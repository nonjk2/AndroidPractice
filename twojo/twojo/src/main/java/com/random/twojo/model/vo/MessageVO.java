package com.random.twojo.model.vo;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.sql.Timestamp;
import java.util.Date;

@Alias("MessageVO")
@Getter
@Setter
@ToString
public class MessageVO {


    private int message_idx;
    private String message;
    private int mem_idx;
    private int room_idx;
    private Timestamp TIME;
}
