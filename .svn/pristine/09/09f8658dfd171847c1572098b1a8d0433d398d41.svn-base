package com.multigold.mdm.service.api.mst;

import java.io.Serializable;
import java.util.List;

import com.multigold.common.entity.BaseEntity;
import com.multigold.common.service.BaseService;
import com.multigold.mdm.entity.mst.DataDictionary;

/**
 * 
 * @author guoqiushi
 *
 * @param <T>
 * @param <ID>
 */
public interface DataDictionaryService <T extends BaseEntity, ID extends Serializable> extends BaseService<T, ID>{

	/**
	 * 导入数据集合
	 * @param tempDDList
	 * @param name
	 * @return
	 */
	public void importDataList(List<DataDictionary> tempDDList);
	
	/**
	 * 查询导出的数据 
	 * 
	 * @return 
	 */
	public List<DataDictionary> getExportList(DataDictionary dataDictionary);
}
