/**
 * 
 */
package com.multigold.mdm.dao.mst;

import java.io.Serializable;
import java.util.List;

import com.multigold.common.dao.BaseMapper;
import com.multigold.common.entity.BaseEntity;
import com.multigold.mdm.entity.mst.TempLspHub;

/**
 * @author mayanhu
 *
 */
public interface TempLspHubMapper  <T extends BaseEntity, ID extends Serializable> extends BaseMapper<T, ID> {

	/**
	 * 上传导入的excel报表的数据
	 * 
	 * @return 
	 */
	public int insertTempLspHubList(List<TempLspHub> tempLspHubList);
	
	/**
	 * 删除全部数据
	 * 
	 * @return 
	 */
	public void deleteAll();
	
	/**
	 * 查询要插入HubProcessMatrix表中的数据
	 * 
	 * @return 
	 */
	public List<TempLspHub> getTempListToHubProcess();
	
	/**
	 * 获取全部数据
	 * 
	 * @return 
	 */
	public List<TempLspHub> getTempLspHubList();
	
	/**
	 * 1.检查有无不存在的仓库代码
	 * 
	 * @return 
	 */
	public List<String> getErrorMasList();
	
	/**
	 * 1-1.检查是否有多个对应的仓库代码的承运商
	 * 
	 * @return 
	 */
	public List<String> getMoreMasOfLineHaulList();
	
	/**
	 * 2.检查有无不存在的行政区域代码 
	 * 
	 * @return 
	 */
	public List<String> getErrorDivisionList();
	
	/**
	 * 3.检查有无不存在的干线承运商代码
	 * 
	 * @return 
	 */
	public List<String> getErrorLineList();
	
	/**
	 * 4.检查有无不存在的支线承运商代码
	 * 
	 * @return 
	 */
	public List<String> getErrorLspList();
	
	/**
	 * 5.检查货运方式ShipMethod
	 * 
	 * @return 
	 */
	public List<String> getErrorShipList();
	
	/**
	 * 6.检查特殊承运商分配标识TradeInIdentifier
	 * 
	 * @return 
	 */
	public List<String> getErrorTradeList();
	
	/**
	 * 7.检查支付方式PaymentTerm
	 * 
	 * @return 
	 */
	public List<String> getErrorPaymentList();
	
	/**
	 * 8.检查物流方向SUPPORT_DIRECTION
	 * 
	 * @return 
	 */
	public List<String> getErrorDirectionList();
	
	/**
	 * 9.检查自提标志SELF_PICKUP
	 * 
	 * @return 
	 */
	public List<String> getErrorPickupList();
	
	/**
	 * 10.检查上门取货标志GOODS_COLLECT_FLAG
	 * 
	 * @return 
	 */
	public List<String> getErrorCollectList();
	
	/**
	 * 11-1.判断SMI仓库不会在C4配置'3PP','3PKP'属性
	 * 
	 * @return 
	 */
	public List<String> getErrorSMIProductList();
	
	/**
	 * 11-2.判断3PP仓库不会在C4配置'SMI','PKP'属性
	 * 
	 * @return 
	 */
	public List<String> getError3ppProductList();
	
	
	/**
	 * 12.检查逆向配置与支持上门取货一致
	 * 
	 * @return 
	 */
	public long getErrorDifferenceList();
	
}
