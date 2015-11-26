package com.multigold.mdm.service.api.mst;

import java.io.Serializable;

import com.multigold.common.entity.BaseEntity;
import com.multigold.common.service.BaseService;
import com.multigold.mdm.entity.mst.MasLoc;

/**
 * 
 * @author guoqiushi
 *
 * @param <T>
 * @param <ID>
 */
public interface SiteService <T extends BaseEntity, ID extends Serializable> extends BaseService<T, ID> {
	

	/**
	 * 根据站点code查询站点
	 */
	public MasLoc queryByCode(String siteCode); 

}
