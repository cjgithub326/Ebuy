/**
 * 
 */
package com.hik.service;

import java.util.List;

import com.hik.entity.Tag;

/**
 * @ClassName: TagService
 * @Description: 标签接口
 * @author jed
 * @date 2017年3月5日下午7:44:52
 *
 */
public interface TagService {
	
	/**
	 * 
	 * @MethodName: findTagList
	 * @Description: 获取所有标签
	 * @author jed
	 * @date 2017年3月5日下午7:47:00
	 * @param @return    
	 * @return List<Tag>    返回类型
	 * @return
	 *
	 */
	public List<Tag> findTagList();

}
