package com.random.twojo.model.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Alias("ChetJoinVO")
@Getter
@Setter
@ToString
public class ChetRoomJoinVo {
    private int join_idx;
    private int mem_idx;
    private int room_idx;

}
