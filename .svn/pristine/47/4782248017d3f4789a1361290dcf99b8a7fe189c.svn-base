/**
 * 
 */
package com.multigold.mdm.service.impl.mst;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import oracle.sql.DATE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multigold.mdm.dao.address.DivisionMapper;
import com.multigold.mdm.dao.mst.HubProcessMatrixHistoryMapper;
import com.multigold.mdm.dao.mst.HubProcessMatrixMapper;
import com.multigold.mdm.dao.mst.MasLocMapper;
import com.multigold.mdm.dao.mst.MstLspDaAmountMapper;
import com.multigold.mdm.dao.mst.MstLspDaShareMapper;
import com.multigold.mdm.dao.mst.MstLspTransportMapper;
import com.multigold.mdm.dao.mst.TempLspHubMapper;
import com.multigold.mdm.entity.address.Division;
import com.multigold.mdm.entity.mst.HubProcessMatrix;
import com.multigold.mdm.entity.mst.MstLspDaAmount;
import com.multigold.mdm.entity.mst.MstLspDaShare;
import com.multigold.mdm.entity.mst.MstLspTransport;
import com.multigold.mdm.entity.mst.TempLspHub;
import com.multigold.common.dao.BaseMapper;
import com.multigold.common.entity.BaseEntity;
import com.multigold.common.service.impl.BaseServiceImpl;
import com.multigold.mdm.service.api.mst.HubProcessMatrixService;

/**
 * @author mayanhu
 *
 */
@Service
public class HubProcessMatrixServiceImpl<T extends BaseEntity, ID extends Serializable>
extends BaseServiceImpl<T, ID> implements HubProcessMatrixService<T, ID> {
	
	@Autowired
	HubProcessMatrixMapper<T, ID> hubProcessMatrixMapper;
	
	@Autowired
	HubProcessMatrixHistoryMapper<T, ID> hubProcessMatrixHistoryMapper;
	
	@Autowired
	TempLspHubMapper<T, ID> tempLspHubMapper;
	
	@Autowired
	DivisionMapper<T, ID> divisionMapper;
	
	@Autowired
	MasLocMapper<T, ID> masLocMapper;
	
	@Autowired
	MstLspTransportMapper<T, ID> mstLspTransportMapper;

	@Override
	public BaseMapper<T, ID> getMapper() {
		return hubProcessMatrixMapper;
	}
	
	public String foreachList(List<String> strList){
		StringBuffer sb = new StringBuffer();
		for (int j = 0; j < strList.size(); j++) {
			sb.append(strList.get(j)).append(",");
		}
		String str=sb.substring(0, sb.length()-1);
		return str;
	}
	
	public String importHubProcessMatrix(List<TempLspHub> tempLspHubList,String insertBy){
		String msg = "";
		//上传的excel首先保存到临时表
		int i = tempLspHubMapper.insertTempLspHubList(tempLspHubList);
		//插入成功后检查数据是否正确
		if(i>0){
			List<String> masList = tempLspHubMapper.getErrorMasList();
			if(masList!=null && masList.size()>0){
				msg="仓库"+foreachList(masList)+"配置失败！";
				//数据错误删除tempLspHub临时表中的数据
				tempLspHubMapper.deleteAll();
				return msg;
			}
			List<String> moreMasOfLineHaulList = tempLspHubMapper.getMoreMasOfLineHaulList();
			if(moreMasOfLineHaulList!=null && moreMasOfLineHaulList.size()>0){
				msg="承运商"+foreachList(moreMasOfLineHaulList)+"有多个对应的仓库！";
				//数据错误删除tempLspHub临时表中的数据
				tempLspHubMapper.deleteAll();
				return msg;
			}
			List<String> divisionList = tempLspHubMapper.getErrorDivisionList();
			if(divisionList!=null && divisionList.size()>0){
				msg="行政区域"+foreachList(divisionList)+"不存在！";
				//数据错误删除tempLspHub临时表中的数据
				tempLspHubMapper.deleteAll();
				return msg;
			}
			List<String> lineList = tempLspHubMapper.getErrorLineList();
			if(lineList!=null && lineList.size()>0){
				msg="干线承运商"+foreachList(lineList)+"不存在！";
				//数据错误删除tempLspHub临时表中的数据
				tempLspHubMapper.deleteAll();
				return msg;
			}
			List<String> lspList = tempLspHubMapper.getErrorLspList();
			if(lspList!=null && lspList.size()>0){
				msg="支线承运商"+foreachList(lspList)+"不存在！";
				//数据错误删除tempLspHub临时表中的数据
				tempLspHubMapper.deleteAll();
				return msg;
			}
			List<String> shipList = tempLspHubMapper.getErrorShipList();
			if(shipList!=null && shipList.size()>0){
				msg="货运方式"+foreachList(shipList)+"配置错误！";
				//数据错误删除tempLspHub临时表中的数据
				tempLspHubMapper.deleteAll();
				return msg;
			}
			List<String> tradeList = tempLspHubMapper.getErrorTradeList();
			if(tradeList!=null && tradeList.size()>0){
				msg="特殊承运商分配标识"+foreachList(tradeList)+"配置错误！";
				//数据错误删除tempLspHub临时表中的数据
				tempLspHubMapper.deleteAll();
				return msg;
			}
			List<String> paymentList = tempLspHubMapper.getErrorPaymentList();
			if(paymentList!=null && paymentList.size()>0){
				msg="支付方式"+foreachList(paymentList)+"配置错误！";
				//数据错误删除tempLspHub临时表中的数据
				tempLspHubMapper.deleteAll();
				return msg;
			}
			List<String> directionList = tempLspHubMapper.getErrorDirectionList();
			if(directionList!=null && directionList.size()>0){
				msg="物流方向"+foreachList(directionList)+"配置错误！";
				//数据错误删除tempLspHub临时表中的数据
				tempLspHubMapper.deleteAll();
				return msg;
			}
			List<String> pickupList = tempLspHubMapper.getErrorPickupList();
			if(pickupList!=null && pickupList.size()>0){
				msg="自提标志"+foreachList(pickupList)+"配置错误！";
				//数据错误删除tempLspHub临时表中的数据
				tempLspHubMapper.deleteAll();
				return msg;
			}
			List<String> collectList = tempLspHubMapper.getErrorCollectList();
			if(collectList!=null && collectList.size()>0){
				msg="上门取货标志"+foreachList(collectList)+"配置错误！";
				//数据错误删除tempLspHub临时表中的数据
				tempLspHubMapper.deleteAll();
				return msg;
			}
			List<String> sMIProductList = tempLspHubMapper.getErrorSMIProductList();
			if(sMIProductList!=null && sMIProductList.size()>0){
				msg="SMI仓库不会在C4配置"+foreachList(sMIProductList)+"属性！";
				//数据错误删除tempLspHub临时表中的数据
				tempLspHubMapper.deleteAll();
				return msg;
			}
			List<String> error3ppProductList = tempLspHubMapper.getError3ppProductList();
			if(error3ppProductList!=null && error3ppProductList.size()>0){
				msg="3PP仓库不会在C4配置"+foreachList(error3ppProductList)+"属性！";
				//数据错误删除tempLspHub临时表中的数据
				tempLspHubMapper.deleteAll();
				return msg;
			}
			long differenceList = tempLspHubMapper.getErrorDifferenceList();
			if(differenceList>0){
				msg=differenceList+"条数据逆向配置与支持上门取货不一致！";
				return msg;
			}
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("mapList", hubProcessMatrixMapper.getInsetHistoryList());//查询要导入历史表中的数据
			params.put("name", insertBy);
			params.put("dt", new DATE());
			//插入历史表
			hubProcessMatrixHistoryMapper.insertHubProcessMatrixHistoryList(params);
			//删除主表中的与上传相关的数据
			deleteRepeat();
			Date now = new Date();
			//读取临时表中的数据
			List<TempLspHub> tlhList = tempLspHubMapper.getTempListToHubProcess();
			//循环插入主表中
			for (int j = 0; j < tlhList.size(); j++) {
				String divisionCode = tlhList.get(j).getDivisionCode();
				String lspCode = tlhList.get(j).getLspCode();
				String masLoc = tlhList.get(j).getMasLoc();
				
				String daId = tlhList.get(j).getDaId();
				if(daId==null || daId.length()<1){
					List<Division> division = divisionMapper.getChildListByPCode(tlhList.get(j).getParentDivisionCode());
					daId = division.get(0).getDaId();
				}
				List<String> mac = masLocMapper.getMasLoc(lspCode);
				
				String supportDirection = tlhList.get(j).getSupportDirection();
				String[] strA=supportDirection.split(",");
				for(int m=0;i<strA.length;m++){
					HubProcessMatrix hubProcessMatrix = new HubProcessMatrix();
					hubProcessMatrix.setRuleName("CCC_RULE");
					hubProcessMatrix.setColumn1(masLoc);
					hubProcessMatrix.setColumn2(divisionCode);
					hubProcessMatrix.setColumn3("ALL");
					hubProcessMatrix.setColumn4(strA[m]);
					hubProcessMatrix.setColumn5("2010-01-01 00:00:00");
					hubProcessMatrix.setColumn6("2010-01-01 23:59:59");
					hubProcessMatrix.setColumn7(tlhList.get(j).getShipMethod());
					hubProcessMatrix.setColumn8(tlhList.get(j).getSelfPickup());
					hubProcessMatrix.setColumn9(tlhList.get(j).getPaymentTerm());
					hubProcessMatrix.setColumn10(tlhList.get(j).getTradeInIdentifier());
					hubProcessMatrix.setColumn11("2099-12-31 00:00:00");
					hubProcessMatrix.setColumn12("ALL");
					hubProcessMatrix.setColumn13(daId);
					hubProcessMatrix.setColumn14(tlhList.get(j).getGoodsCollectFlag());
					hubProcessMatrix.setColumn15("ALL");
					hubProcessMatrix.setOperationCode(lspCode);
					hubProcessMatrix.setHubCode("C001");
					hubProcessMatrix.setMasLoc(masLoc);
					hubProcessMatrix.setLineHaul(tlhList.get(j).getLineHaul());
					hubProcessMatrix.setCarrierCode(null);
					hubProcessMatrix.setPriority(1);
					hubProcessMatrix.setSortingCenterCode(mac.get(0));
					hubProcessMatrix.setDaId(null);
					hubProcessMatrix.setSelfPickupFlag(tlhList.get(j).getSelfPickup());
					hubProcessMatrix.setInsertBy(insertBy);
					hubProcessMatrix.setInsertDate(now);
					hubProcessMatrix.setModifiedBy(insertBy);
					hubProcessMatrix.setModifiedDate(now);
					hubProcessMatrix.setLineHaul2(null);
					hubProcessMatrix.setDeliverymanId(null);
					hubProcessMatrix.setEnabled(1);
					hubProcessMatrix.setDeliveryLeadTime(tlhList.get(j).getDeliveryLeadTime());
					hubProcessMatrixMapper.createEntity((T) hubProcessMatrix);
				}
				 //--需要在所有区域上兼容邮寄方式退回，即配置VLSP的PKP
				if(tlhList.get(j).getHubType().equals("SMI")){
					Map<String, Object> param = new HashMap<String, Object>();
					param.put("divisionCode", divisionCode);
					param.put("masLoc", masLoc);
					long count = hubProcessMatrixMapper.getCount(param);
					if(count==0){
						HubProcessMatrix hubProcessMatrix = new HubProcessMatrix();
						hubProcessMatrix.setRuleName("CCC_RULE");
						hubProcessMatrix.setColumn1(masLoc);
						hubProcessMatrix.setColumn2(divisionCode);
						hubProcessMatrix.setColumn3("ALL");
						hubProcessMatrix.setColumn4("PKP");
						hubProcessMatrix.setColumn5("2010-01-01 00:00:00");
						hubProcessMatrix.setColumn6("2010-01-01 23:59:59");
						hubProcessMatrix.setColumn7("ALL");
						hubProcessMatrix.setColumn8("ALL");
						hubProcessMatrix.setColumn9("ALL");
						hubProcessMatrix.setColumn10("ALL");
						hubProcessMatrix.setColumn11("2099-12-31 00:00:00");
						hubProcessMatrix.setColumn12("ALL");
						hubProcessMatrix.setColumn13(daId);
						hubProcessMatrix.setColumn14("ALL");
						hubProcessMatrix.setColumn15("ALL");
						hubProcessMatrix.setOperationCode("VLSP");
						hubProcessMatrix.setHubCode("C001");
						hubProcessMatrix.setMasLoc(masLoc);
						hubProcessMatrix.setLineHaul("VLSP");
						hubProcessMatrix.setCarrierCode(null);
						hubProcessMatrix.setPriority(1);
						hubProcessMatrix.setSortingCenterCode("P001");
						hubProcessMatrix.setDaId(null);
						hubProcessMatrix.setSelfPickupFlag("N");
						hubProcessMatrix.setInsertBy(insertBy);
						hubProcessMatrix.setInsertDate(now);
						hubProcessMatrix.setModifiedBy(insertBy);
						hubProcessMatrix.setModifiedDate(now);
						hubProcessMatrix.setLineHaul2(null);
						hubProcessMatrix.setDeliverymanId(null);
						hubProcessMatrix.setEnabled(1);
						hubProcessMatrix.setDeliveryLeadTime(0);
						hubProcessMatrixMapper.createEntity((T) hubProcessMatrix);
					}
				}
				
				//--运能设置（只设置支线）
				Map<String, Object> paramAmount = new HashMap<String, Object>();
				paramAmount.put("lspCode", lspCode);
				paramAmount.put("daId", daId);
				paramAmount.put("divisionCode", divisionCode);
				//查看是否有此承运商的运能配置
				long countAmount = mstLspTransportMapper.getCount(paramAmount);
				if(countAmount == 0){
					MstLspTransport transport= new MstLspTransport();
					transport.setLsp(tlhList.get(j).getLineHaul());
					transport.setDaId(daId);
					transport.setMinAmount(0);
					transport.setMaxAmount(999999999);
					transport.setPriority(1);
					transport.setMaxOrdQty(999999999);
					transport.setMinDivisor(1);
					transport.setInsertBy(insertBy);
					transport.setInsertDate(now);
					transport.setOrderDownloadBeginTime("00:00");
					transport.setOrderDownloadEndTime("24:00");
					transport.setDivisionCode(divisionCode);
					mstLspTransportMapper.createEntity((T)transport);
				}
			}
			return msg;
		}else{
			msg="导入数据失败！请检查数据重新导入!";
			return msg;
		}
		
	}
	
	/**
	 * 上批量删除与上传的数据重复的数据
	 * 
	 * @return 
	 */
	public void deleteRepeat(){
		hubProcessMatrixMapper.deleteRepeat();
	}
	/**
	 * 查询导出的数据 
	 * 
	 * @return 
	 */
	@Override
	public List<HubProcessMatrix> getExportList(String masLoc,List<String> divisionList) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("masLoc", masLoc);
		params.put("divisionCode", divisionList);
		return hubProcessMatrixMapper.getExportList(params);
	}
	/**
	 * 查询要插入历史表的数据
	 * 
	 * @return 
	 */
	@Override
	public List<HubProcessMatrix> getInsetHistoryList() {
		return hubProcessMatrixMapper.getInsetHistoryList();
	}
	
}
