package com.multigold.mdm.service.rest.lsp;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multigold.common.rest.response.GenericResponse;
import com.multigold.common.rest.response.GenericResponseHeader;
import com.multigold.common.rest.util.RequestWorker;
import com.multigold.mdm.dto.lsp.EMapAnalysisRespDto;
import com.multigold.mdm.dto.lsp.EMapAnalysisReqDto;
//import com.multigold.mdm.service.api.lsp.ILspDistributionService;

/**
 * @author wangweijin
 * 
 */
@Path("mdm1")
@Component
public class EmapAnalysisResource {
	
	private static final Logger logger = LoggerFactory.getLogger(EmapAnalysisResource.class);
	
	//@Autowired
	//private ILspDistributionService lspDistributionService;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/emapAnalysisService")
	public GenericResponse analysisDivsion(final EMapAnalysisReqDto eMapAnalysisReqDto) {
		logger.debug("analysisDivsion entry...");
		try {
			RequestWorker<EMapAnalysisRespDto> workder = new RequestWorker<EMapAnalysisRespDto>() {
				protected EMapAnalysisRespDto processRequest() throws Exception {
					Map map = null;
							//lspDistributionService.analysisDivsion(eMapAnalysisReqDto);
					
					EMapAnalysisRespDto resp = new EMapAnalysisRespDto();
					resp.setStateCode((String)map.get("stateCode"));
					
					return resp;
				}
			};
			
			logger.debug("analysisDivsion exit...");
			return workder.execute();

		} catch (Exception ex) {
			logger.error("analysisDivsion error:", ex);
			return GenericResponse.buildErrorResponse();
		}
	}

}
