package com.random.twojo.model.entity;


import com.random.twojo.model.vo.InsertVO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Alias("MemberEntity")
@Getter
@Setter
@Builder
public class MemberEntity {

    private int mem_idx;
    private String id;
    private String pw;
    private String name;
    private String addr;
    private String src;
    private int age;

    public InsertVO voChange() {

        InsertVO insertVO = new InsertVO();
        insertVO.setAddr(this.addr);
        insertVO.setId(this.id);
        insertVO.setMem_idx(this.mem_idx);
        insertVO.setName(this.name);
        insertVO.setPw(this.pw);
        insertVO.setSrc(this.src);
        insertVO.setAge(this.age);
        return insertVO;

    }
}
