package com.multigold.mdm.dao.mst;

import java.io.Serializable;

import com.multigold.common.dao.BaseMapper;
import com.multigold.common.entity.BaseEntity;
import com.multigold.mdm.entity.mst.Site;

/**
 * 
 * @author guoqiushi
 *
 * @param <T>
 * @param <ID>
 */
public interface SiteMapper<T extends BaseEntity, ID extends Serializable> extends BaseMapper<T, ID> {
	
	/**
	 * 根据站点code查询站点名称
	 */
	public Site queryByCode(String siteCode); 

}
