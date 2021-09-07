package com.random.twojo.service;

import com.google.gson.JsonObject;
import com.random.twojo.dao.MemberDao;
import com.random.twojo.model.entity.MemberEntity;
import com.random.twojo.model.vo.*;
import com.random.twojo.util.MathingRetore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Service
public class MemberService {
    @Autowired
    MemberDao memberDAO;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    MathingRetore mathingRetore;

    public int postRegister(InsertVO vo) {

        vo.pwEncode(passwordEncoder);
        return memberDAO.register(vo);

    }

    public String matching(InsertVO vo, Principal principal, HttpSession session) {
        MemberEntity memberEntity = findPrincipal(principal);
        vo.setMem_idx(memberEntity.getMem_idx());
        RoomInsertVo vo1 = new RoomInsertVo();
        ChetRoomJoinVo vo3 = new ChetRoomJoinVo();
        ChetRoomJoinVo vo4 = new ChetRoomJoinVo();
        MatchingVo vo2 = new MatchingVo(); //mat_idx,mem_idx

        vo2.setMem_idx(vo.getMem_idx()); // vo에 담긴 mem_idx vo2에 set
        memberDAO.matchInsert(vo2); //대기열 db 추가
        MatchingVo matID = memberDAO.SelectMatIdOne(vo2.getMem_idx()); //mat_idx
        vo2.setMat_idx(matID.getMat_idx());
        System.out.println("-------------" + vo2.getMat_idx() + "-------------");
        MatchingVo another1 = memberDAO.SelectMatId(vo2.getMat_idx()); // mat_idx+1
        MatchingVo another2 = memberDAO.SelectMatIdMinus(vo2.getMat_idx()); // mat_idx-1
        boolean check = true;
        if (matID.getMat_idx() % 2 == 0) {
            System.out.println("짝수입니다");
            while (true) {
                System.out.println("여기 들어옴짝수");
                MatchingVo matID2 = memberDAO.SelectMatIdOne(vo2.getMem_idx());

                if (matID2 == null) {

                    break;
                }
            }
            vo3 = memberDAO.Selectroom(matID.getMat_idx());


            //System.out.println("===========================짝수쪽=================");


//            System.out.println("=============== 짝수의 아이디 값 :" +vo2.getMem_idx()+ " ====================");
//            System.out.println("=============== 짝수의 룸 아이디 값 :" +vo1.getRoom_idx()+" ====================");
//            System.out.println("=============== 공통 룸 아이디 값 :" +(vo1.getRoom_idx()+1)+" ====================");


        } else if (matID.getMat_idx() % 2 == 1) {
            System.out.println("홀수입니다");
            while (check) {
                System.out.println("여기 들어옴");

                if (another2 != null) {
                    break;
                } else if (another1 != null) {
                    System.out.println("여기 들어옴2");
                    break;
                }
            }
            //System.out.println("===========================홀수쪽=================");

            memberDAO.chatInsert(vo1);

            vo3.setMem_idx(vo2.getMem_idx());
            vo3.setRoom_idx(vo1.getRoom_idx());
            vo3.setMat_idx(matID.getMat_idx());
            vo4.setMat_idx(another2.getMat_idx());
            vo4.setMem_idx(another2.getMem_idx());
            vo4.setRoom_idx(vo1.getRoom_idx());
            System.out.println("=============== 홀수 의 아이디 값 :" +vo2.getMem_idx()+ " ====================");
            System.out.println("=============== 홀수의 룸 아이디 값 :" +vo1.getRoom_idx()+" ====================");
            System.out.println("=============== 공통 룸 아이디 값 :" +(vo1.getRoom_idx())+" ====================");
            memberDAO.chetRoomJoin(vo3);
            memberDAO.chetRoomJoin(vo4);
            memberDAO.matchDelete2(vo2);
            memberDAO.matchDelete(vo2);

        }

        return chatJoinRoom(vo3);
    }

    public String chatJoinRoom(ChetRoomJoinVo vo) {

        JsonObject result = new JsonObject();
        result.addProperty("room_idx",vo.getRoom_idx());
        return result.toString();

    }


    public MemberEntity selectMemberOne(int idx){
        return memberDAO.selectMember(idx);
    }

    public MemberEntity authMember(String id){
        return memberDAO.authMember(id);
    }
    public InsertVO selectoneMember(MemberEntity member, Principal principal) {

        MemberEntity memberEntity = findPrincipal(principal);
        return  memberEntity.voChange();

    }
    public MemberEntity findPrincipal(Principal principal){
        return memberDAO.authMember(principal.getName());
    }

    public int MessageEnter(MessageVO vo, Principal principal) {
        MemberEntity memberEntity = findPrincipal(principal);
        vo.setMem_idx(memberEntity.getMem_idx());


        return memberDAO.MessageEnter(vo);
    }

    public List<MessageVO> Messages(MessageVO vo) {
        List<MessageVO> messageVOList = memberDAO.selectMessage(vo);

        return messageVOList;
    }
}
