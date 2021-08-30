package com.random.twojo.model.vo;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Alias("MatchingVO")
@Getter
@Setter
@ToString
public class MatchingVo {
    private int mat_idx;
    private int mem_idx;
}
