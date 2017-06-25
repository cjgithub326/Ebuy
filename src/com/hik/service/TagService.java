/**
 * 
 */
package com.hik.service;

import java.util.List;

import com.hik.entity.PageBean;
import com.hik.entity.Tag;

/**
 * @ClassName: TagService
 * @Description: ��ǩ�ӿ�
 * @author jed
 * @date 2017��3��5������7:44:52
 *
 */
public interface TagService {
	
	/**
	 * 
	 * @MethodName: findTagList
	 * @Description: ��ҳ��ȡ���б�ǩ
	 * @author jed
	 * @date 2017��3��5������7:47:00
	 * @param @return    
	 * @return List<Tag>    ��������
	 * @return
	 *
	 */
	public List<Tag> findTagList(Tag tag, PageBean pageBean);
	
	/**
	 * 
	 * @MethodName: getTagById
	 * @Description: ��ȡ��ǩ��Ϣ
	 * @author jed
	 * @date 2017��6��25������4:44:15
	 * @param @param tagId
	 * @param @return    
	 * @return Tag    ��������
	 * @param tagId
	 * @return
	 *
	 */
	public Tag getTagById(int tagId);
	
	/**
	 * 
	 * @MethodName: getTagCount
	 * @Description: ��ȡ��ǩ����
	 * @author jed
	 * @date 2017��6��25������4:44:33
	 * @param @param tag
	 * @param @return    
	 * @return Long    ��������
	 * @param tag
	 * @return
	 *
	 */
	public Long getTagCount(Tag tag);
	
	/**
	 * 
	 * @MethodName: saveTag
	 * @Description: �����ǩ
	 * @author jed
	 * @date 2017��6��25������4:44:46
	 * @param @param tag    
	 * @return void    ��������
	 * @param tag
	 *
	 */
	public void saveTag(Tag tag);
	
	/**
	 * 
	 * @MethodName: delete
	 * @Description: ɾ����ǩ
	 * @author jed
	 * @date 2017��6��25������4:45:00
	 * @param @param tag    
	 * @return void    ��������
	 * @param tag
	 *
	 */
	public void delete(Tag tag);

}
