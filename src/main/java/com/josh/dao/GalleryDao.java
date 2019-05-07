package com.josh.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.josh.dto.GalleryDto;

@Repository
public class GalleryDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int restore(GalleryDto dto) {
		return sqlSession.insert("gallery.insert", dto);
	}
	
	public List<GalleryDto> getGallery() {
		List<GalleryDto> list = sqlSession.selectList("gallery.getGallery");
		return list;
	}
	
	public GalleryDto detailLook(int no) {
		GalleryDto galleryDto = sqlSession.selectOne("gallery.detailLook", no);
		return galleryDto;
	}
	
	public int delete(GalleryDto dto) {
		return sqlSession.delete("gallery.delete", dto);
	}
}
