/**
 * 
 */
package com.multigold.mdm.service.api.system;

import java.io.Serializable;

import com.multigold.common.entity.BaseEntity;
import com.multigold.common.service.BaseService;

/**
 * @author zhanghaiyang
 *
 */
public interface OperationLogService<T extends BaseEntity, ID extends Serializable> extends BaseService<T, ID> {

}
