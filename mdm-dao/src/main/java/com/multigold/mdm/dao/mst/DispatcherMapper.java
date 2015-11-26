package com.multigold.mdm.dao.mst;

import java.io.Serializable;

import com.multigold.common.dao.BaseMapper;
import com.multigold.common.entity.BaseEntity;

/**
 * 
 * @author guoqiushi
 *
 */
public interface DispatcherMapper <T extends BaseEntity, ID extends Serializable> extends BaseMapper<T, ID>{

	public int createMstMasLocUser(T t);
	public int deleteMstMasLocUser(T t);
	public int updateMstMasLocUser(T t);
}
