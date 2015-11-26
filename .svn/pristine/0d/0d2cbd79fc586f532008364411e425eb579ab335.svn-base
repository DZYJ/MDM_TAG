/**
 * 
 */
package com.multigold.mdm.service.impl.mst;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multigold.mdm.dao.mst.TempLspHubMapper;
import com.multigold.mdm.entity.mst.TempLspHub;
import com.multigold.common.dao.BaseMapper;
import com.multigold.common.entity.BaseEntity;
import com.multigold.common.service.impl.BaseServiceImpl;
import com.multigold.mdm.service.api.mst.TempLspHubService;

/**
 * @author mayanhu
 *
 */
@Service
public class TempLspHubServiceImpl<T extends BaseEntity, ID extends Serializable>
extends BaseServiceImpl<T, ID> implements TempLspHubService<T, ID> {
	
	@Autowired
	TempLspHubMapper<T, ID> tempLspHubMapper;

	@Override
	public BaseMapper<T, ID> getMapper() {
		return tempLspHubMapper;
	}
	
	/**
	 * 上传导入的excel报表的数据
	 * 
	 * @return 
	 */
	public void insertTempLspHubList(List<TempLspHub> tempLspHubList){
		tempLspHubMapper.insertTempLspHubList(tempLspHubList);
	}
	
	/**
	 * 删除全部数据
	 * 
	 * @return 
	 */
	public void deleteAll(){
		tempLspHubMapper.deleteAll();
	}
	
	/**
	 * 获取全部数据
	 * 
	 * @return 
	 */
	public List<TempLspHub> getTempLspHubList(){
		return tempLspHubMapper.getTempLspHubList();
	}

	@Override
	public List<String> getErrorMasList() {
		return tempLspHubMapper.getErrorMasList();
	}

	@Override
	public List<String> getMoreMasOfLineHaulList() {
		return tempLspHubMapper.getMoreMasOfLineHaulList();
	}

	@Override
	public List<String> getErrorDivisionList() {
		return tempLspHubMapper.getErrorDivisionList();
	}

	@Override
	public List<String> getErrorLineList() {
		return tempLspHubMapper.getErrorLineList();
	}

	@Override
	public List<String> getErrorLspList() {
		return tempLspHubMapper.getErrorLspList();
	}

	@Override
	public List<String> getErrorShipList() {
		return tempLspHubMapper.getErrorShipList();
	}

	@Override
	public List<String> getErrorTradeList() {
		return tempLspHubMapper.getErrorTradeList();
	}

	@Override
	public List<String> getErrorPaymentList() {
		return tempLspHubMapper.getErrorPaymentList();
	}

	@Override
	public List<String> getErrorDirectionList() {
		return tempLspHubMapper.getErrorDirectionList();
	}

	@Override
	public List<String> getErrorPickupList() {
		return tempLspHubMapper.getErrorPickupList();
	}

	@Override
	public List<String> getErrorCollectList() {
		return tempLspHubMapper.getErrorCollectList();
	}

	@Override
	public List<String> getErrorSMIProductList() {
		return tempLspHubMapper.getErrorSMIProductList();
	}

	@Override
	public List<String> getError3ppProductList() {
		return tempLspHubMapper.getError3ppProductList();
	}
	
	/**
	 * 12.检查逆向配置与支持上门取货一致
	 * 
	 * @return 
	 */
	public long getErrorDifferenceList(){
		return tempLspHubMapper.getErrorDifferenceList();
	}
	
}
