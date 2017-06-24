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
import com.hik.util.StringUtil;

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
		if(notice!=null){
			if(StringUtil.isNotEmpty(notice.getTitle())){
				sb.append(" and title like ?");
				param.add("%"+notice.getTitle()+"%");
			}
		}
		if(pageBean!=null){
			return baseDao.find(sb.toString().replaceFirst("and", "where"), param, pageBean);
		}else{
			return null;
		}
	}

	@Override
	public Notice getNoticeById(int noticeId) {
		return baseDao.get(Notice.class, noticeId);
	}

	@Override
	public Long getNoticeCount(Notice notice) {
		List<Object> param = new LinkedList<Object>();
		StringBuffer sb = new StringBuffer(" select count(*) from Notice");
		if(notice!=null){
			if(StringUtil.isNotEmpty(notice.getTitle())){
				sb.append(" and title like ?");
				param.add("%"+notice.getTitle()+"%");
			}
		}
		return baseDao.count(sb.toString().replaceFirst("and", "where"), param);
	}

	@Override
	public void saveNotice(Notice notice) {
		baseDao.merge(notice);
	}

	@Override
	public void delete(Notice notice) {
		baseDao.delete(notice);
	}

}
