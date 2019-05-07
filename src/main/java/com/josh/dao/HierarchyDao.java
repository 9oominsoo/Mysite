package com.josh.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.josh.dto.HierarchyDto;

@Repository
public class HierarchyDao {

  @Autowired
  private SqlSession sqlSession;

  public List<HierarchyDto> getList(){
      List<HierarchyDto> list =  sqlSession.selectList("hierarchy.getList");
      System.out.println(list.toString());
      return list;
  }

  public int insertNew(HierarchyDto hierarchyDto){
    return sqlSession.insert("hierarchy.insertNew", hierarchyDto);
  }

  public int insertReply(HierarchyDto hierarchyDto) {
	  return sqlSession.insert("hierarchy.insertReply", hierarchyDto);
  }

  public int updateNo(int groupNo, int orderNo) {
	  Map<String, Object> map = new HashMap<String, Object>();
	  map.put("groupNo", groupNo);
	  map.put("orderNo", orderNo);
	  return sqlSession.update("hierarchy.updateNo", map);
  }

  public int deleteReply(HierarchyDto hierarchyDto){
    return sqlSession.update("hierarchy.deleteReply", hierarchyDto);
  }
}
