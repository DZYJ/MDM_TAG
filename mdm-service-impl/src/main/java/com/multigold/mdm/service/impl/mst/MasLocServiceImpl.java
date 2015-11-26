/**
 * 
 */
package com.multigold.mdm.service.impl.mst;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multigold.mdm.dao.mst.HubProcessMatrixMapper;
import com.multigold.mdm.dao.mst.MasLocMapper;
import com.multigold.mdm.entity.mst.MasLoc;
import com.multigold.common.dao.BaseMapper;
import com.multigold.common.entity.BaseEntity;
import com.multigold.common.service.impl.BaseServiceImpl;
import com.multigold.mdm.service.api.mst.MasLocService;

/**
 * @author mayanhu
 *
 */
@Service
public class MasLocServiceImpl<T extends BaseEntity, ID extends Serializable>
extends BaseServiceImpl<T, ID> implements MasLocService<T, ID> {
	

	@Autowired
	MasLocMapper<T, ID> masLocMapper;
	
	@Autowired
	HubProcessMatrixMapper<T, ID> hubProcessMatrixMapper;

	@Override
	public BaseMapper<T, ID> getMapper() {
		return masLocMapper;
	}
	
	/**
	 * 取得所有仓库名（用于下拉列表框）
	 * 
	 * @return List<MasLoc> 仓库名列表
	 */
	public List<MasLoc> getMasLocListCombobox(){
		List<MasLoc> masLocList = masLocMapper.getMasLocListCombobox();
		return masLocList;
	}
	
	@Override
	public void importDataList(List<MasLoc> tempMLList) {
		masLocMapper.insertMasLocList(tempMLList);
	}

	@Override
	public List<MasLoc> getExportList(MasLoc masLoc) {
		return masLocMapper.queryListByEntity(masLoc);
	}
}
