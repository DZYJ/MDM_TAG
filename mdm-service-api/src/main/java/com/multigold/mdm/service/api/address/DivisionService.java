package com.multigold.mdm.service.api.address;

import java.io.Serializable;
import java.util.List;

import com.multigold.common.entity.BaseEntity;
import com.multigold.common.model.DivisionTree;
import com.multigold.common.service.BaseService;
import com.multigold.mdm.entity.address.Division;

/**
 * 
 * @author guoqiushi
 *
 * @param <T>
 * @param <ID>
 */
public interface DivisionService<T extends BaseEntity, ID extends Serializable> extends BaseService<T, ID> {
    
	/**
	 * 获取所有一级区域（省）
	 * @return
	 */
	public List<Division> getFirstLevel();
	
	/**
	 * 根据parent_division_code获取下级区域
	 * parent_division_code
	 * @return
	 */
	public List<Division> getChildListByPCode(String parent_division_code);
	
	/**
	 * 根据division_code查询区域信息
	 */
	public Division queryByCode(String division_code);
	
	public List<DivisionTree> getTreeList();
	
	public List<DivisionTree> getChildList(String childId);
	/**
	 * 递归遍历所有选择的节点，获取所有选择的四级节点
	 * @param 
	 * @return
	 */
	public List<Division> recursiveTree(List<Division> divisions,List<Division> result);
	/**
	 * 递归遍历所有选择的节点，获取所有选择的三级节点
	 * @param 
	 * @return
	 */
	public List<Division> recursiveTree3(List<Division> divisions,List<Division> result);
	
	
	/**
	 * 导入数据集合
	 * @param tempDList
	 * @param name
	 * @return
	 */
	public void importDataList(List<Division> tempDList);
	
	/**
	 * 查询导出的数据 
	 * 
	 * @return 
	 */
	public List<Division> getExportList(Division division);
	
}
