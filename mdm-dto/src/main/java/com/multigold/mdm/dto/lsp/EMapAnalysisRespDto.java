package com.multigold.mdm.dto.lsp;

import java.io.Serializable;

/**
 * @author wangweijin
 * 
 */
public class EMapAnalysisRespDto implements Serializable{

	private static final long serialVersionUID = 3025512676500733519L;
	
	private String stateCode ;//四级地址代码

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

}
