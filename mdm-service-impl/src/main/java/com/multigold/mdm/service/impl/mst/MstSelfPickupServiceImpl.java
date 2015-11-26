package com.multigold.mdm.service.impl.mst;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multigold.common.dao.BaseMapper;
import com.multigold.common.entity.BaseEntity;
import com.multigold.common.service.impl.BaseServiceImpl;
import com.multigold.mdm.dao.mst.MstSelfPickupMapper;
import com.multigold.mdm.entity.mst.MstSelfPickup;
import com.multigold.mdm.service.api.mst.MstSelfPickupService;


/**
 * 
 * @author guoqiushi
 *
 * @param <T>
 * @param <ID>
 */
@Service
public class MstSelfPickupServiceImpl<T extends BaseEntity, ID extends Serializable> extends BaseServiceImpl<T, ID> implements MstSelfPickupService<T, ID> {
	
	@Autowired
	MstSelfPickupMapper<T, ID> mstSelfPickupMapper;
	
	@Override
	public BaseMapper<T, ID> getMapper() {
		return mstSelfPickupMapper;
	}

	@Override
	public int createEntity(T t) {
		
		int count = mstSelfPickupMapper.queryCountByLspCodeAndSiteCode(((MstSelfPickup)t).getSelfSiteCode(), ((MstSelfPickup)t).getLsp());
		int i = 0;
		if(count==0){//没有该自提点和承运商相关信息
	         i = super.createEntity(t);
	    }
		return i;
	}

	@Override
	public int updateEntity(T t) {
		return super.updateEntity(t);
	}

	@Override
	public int deleteByIds(ID[] ids) {
		return super.deleteByIds(ids);
	}
	
	@Override
	public void importDataList(List<MstSelfPickup> tempMSPList) {
		mstSelfPickupMapper.insertMstSelfPickupList(tempMSPList);
	}

	@Override
	public List<MstSelfPickup> getExportList(MstSelfPickup mstSelfPickup) {
		return mstSelfPickupMapper.queryListByEntity(mstSelfPickup);
	}

}
