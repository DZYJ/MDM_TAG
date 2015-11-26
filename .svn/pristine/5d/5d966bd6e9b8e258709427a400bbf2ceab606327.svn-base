/**
 * 
 */
package com.multigold.mdm.dao.mst;

import java.io.Serializable;
import java.util.List;

import com.multigold.common.dao.BaseMapper;
import com.multigold.common.entity.BaseEntity;
import com.multigold.mdm.entity.mst.LspDayHalfInfo;

/**
 * @author mayanhu
 *
 */
public interface LspDayHalfInfoMapper  <T extends BaseEntity, ID extends Serializable> extends BaseMapper<T, ID> {
	
	/**
	 * 批量插入数据
	 * 
	 * @return 
	 */
	public int insertLspDayHalfList(List<LspDayHalfInfo> lspDayHalfInfoList);
	
	/**
	 * 按照lspCode批量删除
	 * 
	 * @return 
	 */
	public int deleteByLspCode(String lspCode);
	/**
	 * 查询
	 * @param lspCode
	 * @return 
	 */
	public List<LspDayHalfInfo> queryListByLsp(String lspCode);

}
