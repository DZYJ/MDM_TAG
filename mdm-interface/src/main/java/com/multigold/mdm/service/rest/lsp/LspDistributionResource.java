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
import com.multigold.mdm.dto.lsp.LspDistributionReqDto;
import com.multigold.mdm.dto.lsp.LspDistributionRespDto;
//import com.multigold.mdm.service.api.lsp.ILspDistributionService;


/**
 * @author wangweijin
 * 
 */
@Path("mdm2")
@Component
public class LspDistributionResource {
	
    private static final Logger logger = LoggerFactory.getLogger(LspDistributionResource.class);
	
	//@Autowired
	//private ILspDistributionService lspDistributionService;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/lspDistributionService")
	public GenericResponse distributionLsp(final LspDistributionReqDto lspDistributionReqDto) {
		logger.debug("distributionLsp entry...");
		try {
			RequestWorker<LspDistributionRespDto> workder = new RequestWorker<LspDistributionRespDto>() {
				protected LspDistributionRespDto processRequest() throws Exception {
					Map map = null;
							//lspDistributionService.distributionLsp(lspDistributionReqDto);
					
					LspDistributionRespDto resp = new LspDistributionRespDto();
					
					resp.setLspCode((String)map.get("lspCode"));
					resp.setLineHaul((String)map.get("lineHaul"));
					resp.setSortingCenter((String)map.get("sortingCenter"));
					resp.setDeliveryLeadTime((Double)map.get("deliveryLeadTime"));
					
					return resp;
				}
			};
			
			logger.debug("distributionLsp exit...");
			return workder.execute();

		} catch (Exception ex) {
			logger.error("distributionLsp error:", ex);
			return GenericResponse.buildErrorResponse();
		}
	}

}
