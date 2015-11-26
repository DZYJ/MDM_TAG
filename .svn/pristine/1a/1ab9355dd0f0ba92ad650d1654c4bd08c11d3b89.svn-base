package com.multigold.mdm.dao.mst;

import java.io.Serializable;
import java.util.List;

import com.multigold.common.dao.BaseMapper;
import com.multigold.common.entity.BaseEntity;
import com.multigold.mdm.entity.mst.DataDictionary;
/**
 * 
 * @author guoqiushi
 *
 * @param <T>
 * @param <ID>
 */
public interface DataDictionaryMapper<T extends BaseEntity, ID extends Serializable> extends BaseMapper<T, ID>{

	/**
	 * 查询导出的数据 
	 * 
	 * @return 
	 */
	public List<DataDictionary> queryListByEntity(DataDictionary dataDictionary);

	/**
	 * 批量导入
	 * @param tempDDList
	 */
	public void insertMstDataDictionaryList(List<DataDictionary> tempDDList);
}
