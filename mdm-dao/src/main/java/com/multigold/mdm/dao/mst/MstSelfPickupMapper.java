package com.multigold.mdm.dao.mst;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.multigold.common.dao.BaseMapper;
import com.multigold.common.entity.BaseEntity;
import com.multigold.mdm.entity.mst.MstSelfPickup;


public interface MstSelfPickupMapper<T extends BaseEntity, ID extends Serializable> extends BaseMapper<T, ID> {

	public List<MstSelfPickup> queryListByEntity(MstSelfPickup mstSelfPickup);
	
	/**
	 * 批量导入
	 * @param tempMPList
	 */
	public void insertMstSelfPickupList(List<MstSelfPickup> tempMPList);
	
	/**
	 * 根据自提点和buid查询记录是否存在
	 * @param selfSiteCode
	 * @param buid
	 * @return
	 */
	public int queryCountByLspCodeAndSiteCode(@Param("selfSiteCode") String selfSiteCode,@Param("lsp") String lsp);
}
