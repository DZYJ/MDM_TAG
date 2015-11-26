/**
 * 
 */
package com.multigold.mdm.service.impl.address;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multigold.common.dao.BaseMapper;
import com.multigold.common.entity.BaseEntity;
import com.multigold.common.model.DivisionTree;
import com.multigold.common.service.impl.BaseServiceImpl;
import com.multigold.mdm.dao.address.DivisionMapper;
import com.multigold.mdm.entity.address.Division;
import com.multigold.mdm.service.api.address.DivisionService;
import com.google.common.collect.Lists;

/**
 * 
 * @author guoqiushi
 *
 * @param <T>
 * @param <ID>
 */
@Service
public class DivisionServiceImpl<T extends BaseEntity, ID extends Serializable> extends BaseServiceImpl<T, ID> implements DivisionService<T, ID> {
	
	@Autowired
	DivisionMapper<T, ID> divisionMapper;

	@Override
	public BaseMapper<T, ID> getMapper() {
		return divisionMapper;
	}

	/**
	 * 获取所有一级区域（省）
	 * @return
	 */
	@Override
	public List<Division> getFirstLevel() {
		return divisionMapper.getFirstLevelList();
	}
    
	/**
	 * 根据parent_division_code获取所有下级区域
	 * parent_division_code
	 * @return
	 */
	@Override
	public List<Division> getChildListByPCode(String parent_division_code) {
		return divisionMapper.getChildListByPCode(parent_division_code);
	}
	
	/**
	 * 根据division_code查询区域信息
	 */
	public Division queryByCode(String division_code){
		return divisionMapper.queryById(division_code);
	}
	
	@Override
	public List<DivisionTree> getTreeList() {
		List<DivisionTree> rootList = divisionToTree(divisionMapper.getFirstLevelList());
		return rootList;
	}
	
	@Override
	public List<DivisionTree> getChildList(String childId) {
		List<DivisionTree> rootList = divisionToTree(divisionMapper.getChildListByPCode(childId));
		return rootList;
	}
	
	/**
	 * 将资源列表转化成页面可展现成easyui-tree
	 * @param resources
	 * @return
	 */
	private List<DivisionTree> divisionToTree(List<Division> divisions) {

		List<DivisionTree> trees = Lists.newArrayList();

		for (Division division : divisions) {
			DivisionTree tree = new DivisionTree(division.getDivisionCode(),
					division.getParentDivisionCode(), division.getDivisionName(), division.getDivLevel());
			//如果是第三层节点则是子节点
			if(tree.getDivLevel()==3){
				tree.setState("open");
			}
			trees.add(tree);
		}
		return trees;
	}
	
	/**
	 * 递归遍历所有选择的节点，获取所有选择的四级节点
	 * @param 
	 * @return
	 */
	
	@Override
	public List<Division> recursiveTree(List<Division> divisions,List<Division> result) {
		
		for (Division division : divisions) {
			
			if(division.getDivLevel()<4){
				List<Division> childList = divisionMapper.getChildListByPCode(division.getDivisionCode()); 
				recursiveTree(childList,result);
			}
			 
			if(division.getDivLevel()==4){
				result.add(division);
			}
		}
		return result;
	}
	
	/**
	 * 递归遍历所有选择的节点，获取所有选择的三级节点
	 * @param 
	 * @return
	 */
	
	@Override
	public List<Division> recursiveTree3(List<Division> divisions,List<Division> result) {
		
		for (Division division : divisions) {
			
			if(division.getDivLevel()<3){
				List<Division> childList = divisionMapper.getChildListByPCode(division.getDivisionCode()); 
				recursiveTree(childList,result);
			}
			 
			if(division.getDivLevel()==3){
				result.add(division);
			}
		}
		return result;
	}

	@Override
	public void importDataList(List<Division> tempDList) {
		
	}

	@Override
	public List<Division> getExportList(Division division) {
		if (division.getIsActivity().equals("N"))
			division.setIsActivity("否");
		division.setIsActivity("是");
		division.getDivisionCode4().replace(",", "");
		return divisionMapper.queryListByEntity(division);
	}

	@Override
	public int createEntity(T t) {
		
		int count = divisionMapper.queryCountByDivisionCode(((Division)t).getDivisionCode());
		int i = 0;
		if(count==0){//没有该自提点和承运商相关信息
	         i = super.createEntity(t);
	    }
		return i;
	}
}
