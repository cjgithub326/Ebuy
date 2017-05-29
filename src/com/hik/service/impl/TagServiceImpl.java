/**
 * 
 */
package com.hik.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hik.dao.BaseDAO;
import com.hik.entity.Tag;
import com.hik.service.TagService;

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
	public List<Tag> findTagList() {
		StringBuffer sb = new StringBuffer(" from Tag");
		return baseDao.find(sb.toString());
	}

}
