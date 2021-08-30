package com.random.twojo.controller;


import com.random.twojo.dao.MemberDao;
import com.random.twojo.model.vo.InsertVO;
import com.random.twojo.model.vo.MatchingVo;
import com.random.twojo.model.vo.RoomInsertVo;
import com.random.twojo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class MemberController {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberDao memberDao;

    @PostMapping("/member/registerMem")
    public int registerMember(@RequestBody InsertVO vo){
        return memberService.postRegister(vo);
    }
    @PostMapping("/matching/insert")
    public int matching(@RequestBody InsertVO vo){

        return memberService.matching(vo);
    }

    @GetMapping("/test1")
    public MatchingVo SelectMatId(int mat_idx){
        return memberDao.SelectMatId(mat_idx);
    }


    @GetMapping("/member/testone")
    public List<InsertVO> list(){
        return memberDao.testList();
    }


}
