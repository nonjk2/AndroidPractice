package com.random.twojo.model.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Alias("RoomEntity")
@Getter
@Setter
@Builder
public class ChatroomEntity {
    private int room_idx;

}
