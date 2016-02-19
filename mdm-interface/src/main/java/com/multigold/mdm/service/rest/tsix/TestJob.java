package com.multigold.mdm.service.rest.tsix;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multigold.common.util.JsonUtil;
import com.multigold.mdm.dto.common.GenericResponse;
import com.multigold.mdm.dto.common.GenericResponseBodyDto;
import com.multigold.mdm.dto.common.GenericResponseHeader;
import com.multigold.mdm.dto.common.GenericResponseTranDto;
import com.multigold.mdm.dto.sale.SaleReturnReq;
import com.multigold.mdm.dto.sale.SaleReturnStatusReq;
import com.multigold.mdm.dto.sale.SalesReturnReqDetails;
import com.multigold.mdm.dto.sale.SalesReturnReqOrderDetails;
import com.multigold.mdm.dto.sale.SalesReturnReqTran;
import com.multigold.mdm.dto.sale.SalesReturnStatusReqDetails;
import com.multigold.mdm.dto.sale.SalesReturnStatusReqTran;
import com.multigold.mdm.dto.tsix.E74.RecoveryCancelReq;
import com.multigold.mdm.dto.tsix.E74.RecoveryCancelReqDetail;
import com.multigold.mdm.dto.tsix.E74.RecoveryCancelReqTran;
import com.multigold.mdm.job.A9FtpJob;
import com.multigold.t6.service.api.sales.SalesService;
import com.multigold.t6.tsix.E74.RecoveryReq;
import com.multigold.t6.tsix.E74.RecoveryReqDetail;
import com.multigold.t6.tsix.E74.RecoveryReqTran;
import com.multigold.t6.vo.sales.SalesReturnVO;
import com.multigold.t6.vo.sales.SalesReturnsVO;

@Path("testJon")
@Component
public class TestJob implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4582469012158116034L;
	@Autowired
	A9FtpJob a9Job;
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/job1")
	public String daytimeOutBound(String request){
		String requestJSON = JsonUtil.objectToJSON(request);
		log.debug("<<<tsixservice/recovery请求消息>>>"+requestJSON);
		a9Job.daytimeOutBound();
		return "11111";
	}
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/job2")
	public String dayEndInventory(String request){
		String requestJSON = JsonUtil.objectToJSON(request);
		log.debug("<<<tsixservice/recovery请求消息>>>"+requestJSON);
		a9Job.dayEndInventory();
		return "2222";
	}
	
}
