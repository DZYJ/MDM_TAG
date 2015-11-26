package com.multigold.mdm.service.impl.mst;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multigold.common.dao.BaseMapper;
import com.multigold.common.entity.BaseEntity;
import com.multigold.common.service.impl.BaseServiceImpl;
import com.multigold.mdm.dao.mst.DataDictionaryMapper;
import com.multigold.mdm.entity.mst.DataDictionary;
import com.multigold.mdm.service.api.mst.DataDictionaryService;

/**
 * 
 * @author guoqiushi
 *
 * @param <T>
 * @param <ID>
 */
@Service
public class DataDictionaryServiceImpl <T extends BaseEntity, ID extends Serializable> extends BaseServiceImpl<T, ID> implements DataDictionaryService<T,ID>{

	@Autowired
	private DataDictionaryMapper<T, ID> dataDictionaryMapper;
	
	@Override
	public BaseMapper<T, ID> getMapper() {
		return dataDictionaryMapper;
	}

	@Override
	public void importDataList(List<DataDictionary> tempDDList) {
		dataDictionaryMapper.insertMstDataDictionaryList(tempDDList);
	}

	@Override
	public List<DataDictionary> getExportList(DataDictionary dataDictionary) {
		return dataDictionaryMapper.queryListByEntity(dataDictionary);
	}
	
	
	
}
