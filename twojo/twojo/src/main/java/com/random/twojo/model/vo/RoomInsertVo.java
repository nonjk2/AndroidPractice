package com.random.twojo.model.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Alias("RoomInsertVO")
@Getter
@Setter
@ToString
public class RoomInsertVo {

    private int room_idx;

}
