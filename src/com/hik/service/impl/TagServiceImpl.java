/**
 * 
 */
package com.hik.service.impl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hik.dao.BaseDAO;
import com.hik.entity.PageBean;
import com.hik.entity.Tag;
import com.hik.service.TagService;
import com.hik.util.StringUtil;

/**
 * @ClassName: TagServiceImpl
 * @Description: TODO
 * @author jed
 * @date 2017年3月5日下午7:47:48
 *
 */
@Service("tagService")
public class TagServiceImpl implements TagService{

	@Resource
	private BaseDAO<Tag> baseDao;
	@Override
	public List<Tag> findTagList(Tag tag, PageBean pageBean) {
		List<Object> param = new LinkedList<Object>();
		StringBuffer sb = new StringBuffer(" from Tag");
		if(tag!=null){
			if(StringUtil.isNotEmpty(tag.getName())){
				sb.append(" and name like ?");
				param.add("%"+tag.getName()+"%");
			}
		}
		if(pageBean!=null){
			return baseDao.find(sb.toString().replaceFirst("and", "where"), param, pageBean);
		}else{
			return baseDao.find(sb.toString().replaceFirst("and", "where"));
		}
	}
	
	@Override
	public Tag getTagById(int tagId) {
		return baseDao.get(Tag.class, tagId);
	}
	
	@Override
	public Long getTagCount(Tag tag) {
		List<Object> param = new LinkedList<Object>();
		StringBuffer  sb = new StringBuffer("select count(*) from Tag ");
		if(tag!=null){
			if(StringUtil.isNotEmpty(tag.getName())){
				sb.append(" and name like ?");
				param.add("%"+tag.getName()+"%");
			}
		}
		return baseDao.count(sb.toString().replaceFirst("and", "where"), param);
	}
	@Override
	public void saveTag(Tag tag) {
		baseDao.merge(tag);
	}
	@Override
	public void delete(Tag tag) {
		baseDao.delete(tag);
	}

}
