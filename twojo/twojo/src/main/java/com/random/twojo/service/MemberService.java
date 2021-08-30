package com.random.twojo.service;

import com.random.twojo.dao.MemberDao;
import com.random.twojo.model.entity.MemberEntity;
import com.random.twojo.model.vo.ChetRoomJoinVo;
import com.random.twojo.model.vo.InsertVO;
import com.random.twojo.model.vo.MatchingVo;
import com.random.twojo.model.vo.RoomInsertVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
    MemberDao memberDAO;
    @Autowired
    PasswordEncoder passwordEncoder;

    public int postRegister(InsertVO vo) {

        vo.pwEncode(passwordEncoder);
        return memberDAO.register(vo);

    }

    public int matching(InsertVO vo) {
        RoomInsertVo vo1 = new RoomInsertVo();
        ChetRoomJoinVo vo3 = new ChetRoomJoinVo();
        MatchingVo vo2 = new MatchingVo(); //mat_idx,mem_idx
        vo2.setMem_idx(vo.getMem_idx()); // vo에 담긴 mem_idx vo2에 set
        memberDAO.matchInsert(vo2); //대기열 db 추가
        MatchingVo matID = memberDAO.SelectMatIdOne(vo2.getMem_idx()); //mat_idx
        vo2.setMat_idx(matID.getMat_idx());
        System.out.println("-------------" + vo2.getMat_idx() + "-------------");
        MatchingVo another1 = memberDAO.SelectMatId(vo2.getMat_idx()); // mat_idx+1
        MatchingVo another2 = memberDAO.SelectMatIdMinus(vo2.getMat_idx()); // mat_idx-1
        boolean check = true;

        if (matID.getMat_idx() % 2 == 1) {
            System.out.println("홀수입니다");
            while (check) {
                System.out.println("여기 들어옴");
                if (another2 != null) {
                    check = false;
                } else if (another1 != null) {
                    System.out.println("여기 들어옴2");
                    break;
                }
            }
            memberDAO.matchDelete2(vo2);
            memberDAO.matchDelete(vo2);
            memberDAO.chatInsert(vo3);

            vo3.setRoom_idx(vo1.getRoom_idx());
            vo3.setMem_idx(matID.getMem_idx());

        } else if (matID.getMat_idx() % 2 == 0) {
            System.out.println("짝수입니다");
            while (true) {
                if (matID == null) {
                    break;
                }
            }
            memberDAO.chatInsert(vo3);

        }

        return chatJoinRoom(vo3);
    }

    public int chatJoinRoom(ChetRoomJoinVo vo3) {

        System.out.println();
        System.out.println(vo3.getMem_idx());
        return 0;
    }

}
