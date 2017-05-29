/**
 * 
 */
package com.hik.service.impl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hik.dao.BaseDAO;
import com.hik.entity.Notice;
import com.hik.entity.PageBean;
import com.hik.service.NoticeService;

/**
 * @ClassName: NoticeServiceImpl
 * @Description: TODO
 * @author jed
 * @date 2017年3月5日下午8:02:00
 *
 */
@Service("noticeService")
public class NoticeServiceImpl implements NoticeService{
	
	@Resource
	private BaseDAO<Notice> baseDao;

	@Override
	public List<Notice> findNoticeList(Notice notice, PageBean pageBean) {
		List<Object> param = new LinkedList<Object>();
		StringBuffer sb = new StringBuffer(" from Notice");
		if(pageBean!=null){
			return baseDao.find(sb.toString(), param, pageBean);
		}else{
			return null;
		}
	}

	@Override
	public Notice getNoticeById(int noticeId) {
		return baseDao.get(Notice.class, noticeId);
	}

}
