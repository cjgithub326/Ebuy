/**
 * 
 */
package com.hik.service;

import java.util.List;

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
	 * @Description: ��ȡ���б�ǩ
	 * @author jed
	 * @date 2017��3��5������7:47:00
	 * @param @return    
	 * @return List<Tag>    ��������
	 * @return
	 *
	 */
	public List<Tag> findTagList();

}
