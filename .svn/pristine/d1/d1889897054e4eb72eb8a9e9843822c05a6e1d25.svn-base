package com.multigold.mdm.web.controller.mst;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.multigold.common.service.BaseService;
import com.multigold.common.web.controller.BaseCRUDAction;
import com.multigold.mdm.entity.address.Division;
import com.multigold.mdm.entity.mst.MstTransCapacity;
import com.multigold.mdm.service.api.address.DivisionService;
import com.multigold.mdm.service.api.mst.MstTransCapacityService;
import com.multigold.mdm.web.controller.util.UserSessionProvider;
import com.multigold.upms.entity.security.User;
import com.google.common.collect.Maps;
/**
 * 
 * @author mayanhu
 * Date:2014/8/25
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/transCapacity")
public class MstTransCapacityController extends BaseCRUDAction<MstTransCapacity, Integer> {
	
	@Autowired
	MstTransCapacityService<MstTransCapacity, Integer> mstTransCapacityService;
	
	@Autowired
	DivisionService<Division, Integer> divisionService;
	

	@Override
	public BaseService<MstTransCapacity, Integer> getBaseService() {
		return mstTransCapacityService;
	}

	@Override
	protected void setDefaultValue(HttpServletRequest request,MstTransCapacity mstTransCapacity, String operateFlag) {
		if(operateFlag.equalsIgnoreCase("update")){
			User user=UserSessionProvider.getUserSerssion(request);
			String modifiedBy = "";
			if(null!=user ){
				modifiedBy = user.getName();
			}
			mstTransCapacity.setModifiedBy(modifiedBy);
		}
//		if(operateFlag.equalsIgnoreCase("create")){
	}
	
	/**
	 * 获取时间规则下拉列表
	 * @return
	 */
	@RequestMapping(value = "getLotSeqListCombobox", method = RequestMethod.GET)
	@ResponseBody
	public List<MstTransCapacity> getLotSeqListCombobox() {
		return mstTransCapacityService.getLotSeqListCombobox();
	}
	
	@RequestMapping(value = "getLotBySeq", method = RequestMethod.POST)
	@ResponseBody
	public MstTransCapacity getLotBySeq(@RequestParam("lotSeq") String lotSeq){
		return mstTransCapacityService.getLotBySeq(lotSeq);
	}
	
	/**
	 * 添加半日达信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "insertTransCapacity", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> insertTransCapacity(@RequestParam("lspCodeAdd") String lspCodeAdd,
			@RequestParam("masLocAdd") String masLocAdd,@RequestParam("lotSeq") String lotSeq,
			@RequestParam("transQty") String transQty,@RequestParam("deliveryTimeLot") String deliveryTimeLot,
			@RequestParam("enabled") String enabled,
			@RequestParam("divisionCode") String divisionCode,HttpServletRequest request) {

		MstTransCapacity transCapacity = new MstTransCapacity();
		transCapacity.setLspCode(lspCodeAdd);
		transCapacity.setMasLoc(masLocAdd);
		transCapacity.setLotSeq(lotSeq);
		transCapacity.setTransQty(Integer.parseInt(transQty));
		transCapacity.setDeliveryTimeLot(Float.parseFloat(deliveryTimeLot));
		transCapacity.setEnabled(Integer.parseInt(enabled));
		transCapacity.setDivisionCode(divisionCode);

		mstTransCapacityService.createEntity(transCapacity);//.insertTransCapacity(transCapacity);
		Map<String, Object> map = Maps.newHashMap();
		map.put("state", "success");
		map.put("msg", "保存成功！");
		return map;
	}
	
	/**
	 * 修改半日达信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "updateTransCapacity", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateTransCapacity(
			@RequestParam("id") String id,@RequestParam("lspCodeAdd") String lspCodeAdd,
			@RequestParam("masLocAdd") String masLocAdd,@RequestParam("lotSeq") String lotSeq,
			@RequestParam("transQty") String transQty,@RequestParam("deliveryTimeLot") String deliveryTimeLot,
			@RequestParam("enabled") String enabled,HttpServletRequest request) {

		MstTransCapacity transCapacity = new MstTransCapacity();
		transCapacity.setId(Integer.parseInt(id));
		transCapacity.setLspCode(lspCodeAdd);
		transCapacity.setMasLoc(masLocAdd);
		transCapacity.setLotSeq(lotSeq);
		transCapacity.setTransQty(Integer.parseInt(transQty));
		transCapacity.setDeliveryTimeLot(Float.parseFloat(deliveryTimeLot));
		transCapacity.setEnabled(Integer.parseInt(enabled));

		mstTransCapacityService.updateEntity(transCapacity);
		Map<String, Object> map = Maps.newHashMap();
		map.put("state", "success");
		map.put("msg", "修改成功！");
		return map;
	}
	
}
