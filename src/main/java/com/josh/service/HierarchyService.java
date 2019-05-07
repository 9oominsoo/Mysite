package com.josh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.josh.dao.HierarchyDao;
import com.josh.dto.HierarchyDto;

@Service
public class HierarchyService {

    @Autowired
    private HierarchyDao dao;

    /* 리스트 출력 */
    public List<HierarchyDto> getList(){
      return dao.getList();
    }

    public int insertNew(HierarchyDto hierarchyDto){
      return dao.insertNew(hierarchyDto);
    }

    public void insertReply(HierarchyDto hierarchyDto, int groupNo, int orderNo, int parentDepth) {
    	hierarchyDto.setGroupNo(groupNo);
    	System.out.println(hierarchyDto.toString());
    	dao.updateNo(groupNo, orderNo);

    	hierarchyDto.setOrderNo(orderNo + 1);
    	hierarchyDto.setDepth(parentDepth+1);
    	dao.insertReply(hierarchyDto);
    }

    public int deleteReply(HierarchyDto hierarchyDto){
      return dao.deleteReply(hierarchyDto);
    }

}
