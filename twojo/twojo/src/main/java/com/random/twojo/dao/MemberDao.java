package com.random.twojo.dao;

import com.random.twojo.model.vo.ChetRoomJoinVo;
import com.random.twojo.model.vo.InsertVO;
import com.random.twojo.model.vo.MatchingVo;
import com.random.twojo.model.vo.RoomInsertVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class MemberDao {

    @Autowired
    SqlSession sqlSession;

    public int register(InsertVO vo) {
        return sqlSession.insert("m.register", vo);
    }

    public List<InsertVO> testList() {
        return sqlSession.selectList("m.test");
    }

    public int matchInsert(MatchingVo vo){
        return sqlSession.insert("m.insertMatch",vo);
    }

    public int matchDelete(MatchingVo vo){
        return sqlSession.delete("m.deleteMatch1",vo);
    }
    public int matchDelete2(MatchingVo vo){
        return sqlSession.delete("m.deleteMatch2",vo);
    }

    public int chatInsert(ChetRoomJoinVo vo) {
        return sqlSession.insert("m.chatroominsert", vo);
    }
    public MatchingVo SelectMatId(int mat_idx){
        return sqlSession.selectOne("m.selectmatid",mat_idx);
    }

    public MatchingVo SelectMatIdOne(int mem_idx){
        return sqlSession.selectOne("m.selectmatidone",mem_idx);
    }
    public MatchingVo SelectMatIdMinus(int mat_idx){
        return sqlSession.selectOne("m.selectmatidm",mat_idx);
    }
    public int chetRoomJoin(ChetRoomJoinVo vo){
        return sqlSession.insert("m.chetroomjoin",vo);
    }
}
