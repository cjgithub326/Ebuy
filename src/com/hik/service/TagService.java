/**
 * 
 */
package com.hik.service;

import java.util.List;

import com.hik.entity.PageBean;
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
	 * @Description: 分页获取所有标签
	 * @author jed
	 * @date 2017年3月5日下午7:47:00
	 * @param @return    
	 * @return List<Tag>    返回类型
	 * @return
	 *
	 */
	public List<Tag> findTagList(Tag tag, PageBean pageBean);
	
	/**
	 * 
	 * @MethodName: getTagById
	 * @Description: 获取标签信息
	 * @author jed
	 * @date 2017年6月25日下午4:44:15
	 * @param @param tagId
	 * @param @return    
	 * @return Tag    返回类型
	 * @param tagId
	 * @return
	 *
	 */
	public Tag getTagById(int tagId);
	
	/**
	 * 
	 * @MethodName: getTagCount
	 * @Description: 获取标签数量
	 * @author jed
	 * @date 2017年6月25日下午4:44:33
	 * @param @param tag
	 * @param @return    
	 * @return Long    返回类型
	 * @param tag
	 * @return
	 *
	 */
	public Long getTagCount(Tag tag);
	
	/**
	 * 
	 * @MethodName: saveTag
	 * @Description: 保存标签
	 * @author jed
	 * @date 2017年6月25日下午4:44:46
	 * @param @param tag    
	 * @return void    返回类型
	 * @param tag
	 *
	 */
	public void saveTag(Tag tag);
	
	/**
	 * 
	 * @MethodName: delete
	 * @Description: 删除标签
	 * @author jed
	 * @date 2017年6月25日下午4:45:00
	 * @param @param tag    
	 * @return void    返回类型
	 * @param tag
	 *
	 */
	public void delete(Tag tag);

}
