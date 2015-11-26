package com.multigold.mdm.service.impl.mst;

import java.io.Serializable;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multigold.common.dao.BaseMapper;
import com.multigold.common.entity.BaseEntity;
import com.multigold.common.service.impl.BaseServiceImpl;
import com.multigold.mdm.dao.mst.DispatcherMapper;
import com.multigold.mdm.service.api.mst.DispatcherService;
import com.google.common.collect.Maps;

/**
 * 
 * @author guoqiushi
 *
 * @param <T>
 * @param <ID>
 */
@Service
public class DispatcherServiceImpl<T extends BaseEntity, ID extends Serializable> extends BaseServiceImpl<T, ID> implements DispatcherService<T, ID> {
	@Autowired
	DispatcherMapper<T, ID> dispatcherMapper;

	@Override
	public BaseMapper<T, ID> getMapper() {
		return dispatcherMapper;
	}
	
	/**
	 * 增加
	 * 
	 * @param t
	 */
	public int createEntity(T t) {
		this.dispatcherMapper.createMstMasLocUser(t);
		return this.dispatcherMapper.createEntity(t);
	}
	
	/**
	 * 更新
	 * 
	 * @param t
	 */
	public int updateEntity(T t) {
		this.dispatcherMapper.updateMstMasLocUser(t);
		return this.getMapper().updateEntity(t);
	}

	/**
	 * 删除
	 * 
	 * @param id
	 */
	public int deleteById(ID id) {
		T t = this.getMapper().queryById(id);
		this.dispatcherMapper.deleteMstMasLocUser(t);
		return this.getMapper().deleteById(id);
	}

	/**
	 * 根据ID数组删除对象
	 * 
	 * @param id
	 * @return
	 */
	public int deleteByIds(ID[] ids) {
		Map<String, Object> params = Maps.newHashMap();
		params.put("ids", ids);
		for (int i = 0; i < ids.length; i++) {
			T t = this.getMapper().queryById(ids[i]);
			this.dispatcherMapper.deleteMstMasLocUser(t);
		}
		return this.getMapper().deleteByIds(params);
	}
}
