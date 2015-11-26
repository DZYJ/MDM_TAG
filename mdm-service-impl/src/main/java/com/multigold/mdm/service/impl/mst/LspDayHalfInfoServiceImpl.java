/**
 * 
 */
package com.multigold.mdm.service.impl.mst;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multigold.mdm.dao.mst.LspDayHalfInfoMapper;
import com.multigold.mdm.entity.mst.LspDayHalfInfo;
import com.multigold.common.dao.BaseMapper;
import com.multigold.common.entity.BaseEntity;
import com.multigold.common.service.impl.BaseServiceImpl;
import com.multigold.mdm.service.api.mst.LspDayHalfInfoService;

/**
 * @author mayanhu
 *
 */
@Service
public class LspDayHalfInfoServiceImpl<T extends BaseEntity, ID extends Serializable>
extends BaseServiceImpl<T, ID> implements LspDayHalfInfoService<T, ID> {
	
	@Autowired
	LspDayHalfInfoMapper<T, ID> lspDayHalfInfoMapper;

	@Override
	public BaseMapper<T, ID> getMapper() {
		return lspDayHalfInfoMapper;
	}

	@Override
	public int insertLspDayHalfList(List<LspDayHalfInfo> lspDayHalfInfoList) {
		String lspCode = lspDayHalfInfoList.get(0).getLspCode();
		lspDayHalfInfoMapper.deleteByLspCode(lspCode);
		return lspDayHalfInfoMapper.insertLspDayHalfList(lspDayHalfInfoList);
	}

	@Override
	public int deleteByLspCode(String lspCode) {
		return lspDayHalfInfoMapper.deleteByLspCode(lspCode);
	}

	@Override
	public List<LspDayHalfInfo> queryListByLsp(String lspCode) {
		return lspDayHalfInfoMapper.queryListByLsp(lspCode);
	}

}
