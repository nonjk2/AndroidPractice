package com.random.twojo.controller;



import com.google.gson.JsonObject;
import com.random.twojo.dao.MemberDao;
import com.random.twojo.model.vo.InsertVO;
import com.random.twojo.model.vo.MatchingVo;
import com.random.twojo.model.vo.MessageVO;

import com.random.twojo.service.MemberService;

import com.sun.org.apache.bcel.internal.generic.NEW;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@CrossOrigin
@RestController
public class MemberController {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberDao memberDao;

    @PostMapping("/member/registerMem")
    public String  registerMember(@RequestBody InsertVO vo){
        System.out.println(vo);


        JsonObject result = new JsonObject();
        result.addProperty("d",memberService.postRegister(vo));

        return  result.toString();
    }
    @PostMapping("/matching/insert")
    public String matching(InsertVO vo, Principal principal, HttpSession session){

        return memberService.matching(vo , principal,session);
    }
    @PostMapping("/message/insert")
    public String MessageEnter(@RequestBody MessageVO vo, Principal principal){

         JsonObject result = new JsonObject();
         result.addProperty("e",memberService.MessageEnter(vo , principal));
         return result.toString();
    }

    @PostMapping("/message/list")
    public List<MessageVO> Messages(@RequestBody MessageVO vo ){



        return memberService.Messages(vo);
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
