package com.multigold.mdm.vo.po;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.multigold.common.entity.BaseEntity;

/**
 * 入库单子表
 * @author zhanghua
 *
 */
public class RdrecordsVO extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4593122291291182886L;
	private String autoId;// 收发子表ID(SELECT MAX(ISNULL(autoID,0))+1 FROM
	// RdRecords)
	private int ID;// 收发主表ID(【主表ID值】)
	private String cInvCode;// 存货编码(存货编码，对应商品款式码)
	private BigDecimal iNum;// 辅计量数量(【0】，本项目无换算计量)
	private BigDecimal iQuantity;// 主计量数量()
	private BigDecimal iUnitCost;// 单价(无税单价)
	private BigDecimal iPrice;// 金额(无税金额)
	private BigDecimal iAPrice;// 暂估单价(采购暂估单价)
	private String cBatch;// 批号()
	private BigDecimal cFree1;// 自由项1(用于记录【克重】)
	private BigDecimal iSQuantity;// 累计结算数量(【0】)
	private String iSNum;// 累计结算件数(【0】)
	private BigDecimal iMoney;// 累计结算金额(【0】)
	private String cPosition;// 货位编码(【''】)
	private String cItem_Class;// 项目大类编码(【00】，核算项目大类编码【业务单号项目】)
	private String cItemCode;// 项目编码(项目编码【业务单号项目】)
	private int iPOsID;// 采购订单子表标识(记录对应的采购订单子表ID值)
	private BigDecimal fACost;// 暂估单价()
	private String cName;// 项目名称(项目名称【业务单号项目】)
	private String cItemCName;// 项目大类名称(项目大类名称【业务单号项目】)
	private BigDecimal iNQuantity;// 应收应发数量(【iQuantity】，取【数量】值)
	private BigDecimal iNNum;// 应收应发件数(【iNum】，取【件数】值)
	private String cBVenCode;// 供应商编码()
	private BigDecimal iTaxCost;// 本币含税单价()
	private BigDecimal iTaxPrice;// 本币税额(Round(iPrice*iTaxRate/100,2))
	private BigDecimal iTaxRate;// 表体税率(默认【17】,)
	private BigDecimal iSum;// 本币价税合计(【iTaxPrice+iPrice】)
	private int sOsID;// …(默认【0】)
	private int yCsID;// …(默认【0】)

	private BigDecimal sum_iQuantity;
	private BigDecimal sum_iNum;
	private BigDecimal sum_iPrice;

	private String cPOID;// 采购订单号(对应采购订单的单据编号【cPOID】)
	private String sku;// sku
	private BigDecimal spec;// spec

	private BigDecimal cDefine22;// 基础费用
	private BigDecimal cDefine23;// 加工费
	private BigDecimal cDefine24;// 运费+检测费+其他费用

	private String iPUnitCost;
	private BigDecimal iPPrice;
	private String cObjCode;
	private String cVouchCode;
	private String cFree2;
	private String iFlag;
	private Timestamp dSDate;
	private BigDecimal iTax;
	private BigDecimal iSOutQuantity;
	private BigDecimal iSOutNum;
	private BigDecimal iFNum;
	private BigDecimal iFQuantity;
	private Timestamp dVDate;
	private int iTrIds;
	private String cDefine25;
	private BigDecimal cDefine26;
	private BigDecimal cDefine27;
	private int iDLsID;
	private int iSBsID;
	private BigDecimal iSendQuantity;
	private BigDecimal iSendNum;
	private int iEnsID;
	private String cFree3;
	private String cFree4;
	private String cFree5;
	private String cFree6;
	private String cFree7;
	private String cFree8;
	private String cFree9;
	private String cFree10;
	private String cBarCode;
	private String cAssUnit;
	private Timestamp dMadeDate;
	private int iMassDate;
	private String cDefine28;
	private String cDefine29;
	private String cDefine30;
	private String cDefine31;
	private String cDefine32;
	private String cDefine33;
	private int cDefine34;
	private int cDefine35;
	private Timestamp cDefine36;
	private Timestamp cDefine37;
	private int iMPoIds;
	private int iCheckIds;
	private String cBVencode;
	private String cInVouchCode;
	private String bGsp;
	private String cGspState;
	private int iArrsId;
	private String cCheckCode;
	private int iCheckIdBaks;
	private String cRejectCode;
	private int iRejectIds;
	private String cCheckPersonCode;
	private Timestamp dCheckDate;
	private BigDecimal iTookQuantity;
	private BigDecimal iInvalidQuantity;
	private int iOMMPoIds;
	private BigDecimal iPBVQuantity;
	private BigDecimal iPBVyQuantity;
	private int iBalance;
	private BigDecimal iHXQuantity;
	private String cCAItemCode;
	private String cCBGJDXCode;
	private int bfilled;
	private int iarrptrintype;
	private int ichkautoid;
	private int irejid;
	private String crejtext;
	private BigDecimal iATaxCost;
	private BigDecimal iATaxPrice;
	private BigDecimal iATaxRate;
	private BigDecimal iASum;
	private BigDecimal MakePrice;
	private BigDecimal MakeMny;
	private BigDecimal HadBalanceQuantity;
	private BigDecimal HadBalanceMny;
	private BigDecimal OrgMakePrice;
	private String cBOMSoCode;
	private String csocode;
	private String ccbgjdxname;
	private BigDecimal TaxMakePrice;
	private BigDecimal iMakeTaxPrice;
	private BigDecimal iMakeTaxRate;
	private BigDecimal TaxMakeMny;
	private String YCcode;
	private int cID = 20;// (克重分类)
	private String cValue;// 克重()
	public String getAutoId() {
		return autoId;
	}
	public void setAutoId(String autoId) {
		this.autoId = autoId;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getcInvCode() {
		return cInvCode;
	}
	public void setcInvCode(String cInvCode) {
		this.cInvCode = cInvCode;
	}
	public BigDecimal getiNum() {
		return iNum;
	}
	public void setiNum(BigDecimal iNum) {
		this.iNum = iNum;
	}
	public BigDecimal getiQuantity() {
		return iQuantity;
	}
	public void setiQuantity(BigDecimal iQuantity) {
		this.iQuantity = iQuantity;
	}
	public BigDecimal getiUnitCost() {
		return iUnitCost;
	}
	public void setiUnitCost(BigDecimal iUnitCost) {
		this.iUnitCost = iUnitCost;
	}
	public BigDecimal getiPrice() {
		return iPrice;
	}
	public void setiPrice(BigDecimal iPrice) {
		this.iPrice = iPrice;
	}
	public BigDecimal getiAPrice() {
		return iAPrice;
	}
	public void setiAPrice(BigDecimal iAPrice) {
		this.iAPrice = iAPrice;
	}
	public String getcBatch() {
		return cBatch;
	}
	public void setcBatch(String cBatch) {
		this.cBatch = cBatch;
	}
	public BigDecimal getcFree1() {
		return cFree1;
	}
	public void setcFree1(BigDecimal cFree1) {
		this.cFree1 = cFree1;
	}
	public BigDecimal getiSQuantity() {
		return iSQuantity;
	}
	public void setiSQuantity(BigDecimal iSQuantity) {
		this.iSQuantity = iSQuantity;
	}
	public String getiSNum() {
		return iSNum;
	}
	public void setiSNum(String iSNum) {
		this.iSNum = iSNum;
	}
	public BigDecimal getiMoney() {
		return iMoney;
	}
	public void setiMoney(BigDecimal iMoney) {
		this.iMoney = iMoney;
	}
	public String getcPosition() {
		return cPosition;
	}
	public void setcPosition(String cPosition) {
		this.cPosition = cPosition;
	}
	public String getcItem_Class() {
		return cItem_Class;
	}
	public void setcItem_Class(String cItem_Class) {
		this.cItem_Class = cItem_Class;
	}
	public String getcItemCode() {
		return cItemCode;
	}
	public void setcItemCode(String cItemCode) {
		this.cItemCode = cItemCode;
	}
	public int getiPOsID() {
		return iPOsID;
	}
	public void setiPOsID(int iPOsID) {
		this.iPOsID = iPOsID;
	}
	public BigDecimal getfACost() {
		return fACost;
	}
	public void setfACost(BigDecimal fACost) {
		this.fACost = fACost;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public String getcItemCName() {
		return cItemCName;
	}
	public void setcItemCName(String cItemCName) {
		this.cItemCName = cItemCName;
	}
	public BigDecimal getiNQuantity() {
		return iNQuantity;
	}
	public void setiNQuantity(BigDecimal iNQuantity) {
		this.iNQuantity = iNQuantity;
	}
	public BigDecimal getiNNum() {
		return iNNum;
	}
	public void setiNNum(BigDecimal iNNum) {
		this.iNNum = iNNum;
	}
	public String getcBVenCode() {
		return cBVenCode;
	}
	public void setcBVenCode(String cBVenCode) {
		this.cBVenCode = cBVenCode;
	}
	public BigDecimal getiTaxCost() {
		return iTaxCost;
	}
	public void setiTaxCost(BigDecimal iTaxCost) {
		this.iTaxCost = iTaxCost;
	}
	public BigDecimal getiTaxPrice() {
		return iTaxPrice;
	}
	public void setiTaxPrice(BigDecimal iTaxPrice) {
		this.iTaxPrice = iTaxPrice;
	}
	public BigDecimal getiTaxRate() {
		return iTaxRate;
	}
	public void setiTaxRate(BigDecimal iTaxRate) {
		this.iTaxRate = iTaxRate;
	}
	public BigDecimal getiSum() {
		return iSum;
	}
	public void setiSum(BigDecimal iSum) {
		this.iSum = iSum;
	}
	public int getsOsID() {
		return sOsID;
	}
	public void setsOsID(int sOsID) {
		this.sOsID = sOsID;
	}
	public int getyCsID() {
		return yCsID;
	}
	public void setyCsID(int yCsID) {
		this.yCsID = yCsID;
	}
	public BigDecimal getSum_iQuantity() {
		return sum_iQuantity;
	}
	public void setSum_iQuantity(BigDecimal sum_iQuantity) {
		this.sum_iQuantity = sum_iQuantity;
	}
	public BigDecimal getSum_iNum() {
		return sum_iNum;
	}
	public void setSum_iNum(BigDecimal sum_iNum) {
		this.sum_iNum = sum_iNum;
	}
	public BigDecimal getSum_iPrice() {
		return sum_iPrice;
	}
	public void setSum_iPrice(BigDecimal sum_iPrice) {
		this.sum_iPrice = sum_iPrice;
	}
	public String getcPOID() {
		return cPOID;
	}
	public void setcPOID(String cPOID) {
		this.cPOID = cPOID;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public BigDecimal getSpec() {
		return spec;
	}
	public void setSpec(BigDecimal spec) {
		this.spec = spec;
	}
	public BigDecimal getcDefine22() {
		return cDefine22;
	}
	public void setcDefine22(BigDecimal cDefine22) {
		this.cDefine22 = cDefine22;
	}
	public BigDecimal getcDefine23() {
		return cDefine23;
	}
	public void setcDefine23(BigDecimal cDefine23) {
		this.cDefine23 = cDefine23;
	}
	public BigDecimal getcDefine24() {
		return cDefine24;
	}
	public void setcDefine24(BigDecimal cDefine24) {
		this.cDefine24 = cDefine24;
	}
	public String getiPUnitCost() {
		return iPUnitCost;
	}
	public void setiPUnitCost(String iPUnitCost) {
		this.iPUnitCost = iPUnitCost;
	}
	public BigDecimal getiPPrice() {
		return iPPrice;
	}
	public void setiPPrice(BigDecimal iPPrice) {
		this.iPPrice = iPPrice;
	}
	public String getcObjCode() {
		return cObjCode;
	}
	public void setcObjCode(String cObjCode) {
		this.cObjCode = cObjCode;
	}
	public String getcVouchCode() {
		return cVouchCode;
	}
	public void setcVouchCode(String cVouchCode) {
		this.cVouchCode = cVouchCode;
	}
	public String getcFree2() {
		return cFree2;
	}
	public void setcFree2(String cFree2) {
		this.cFree2 = cFree2;
	}
	public String getiFlag() {
		return iFlag;
	}
	public void setiFlag(String iFlag) {
		this.iFlag = iFlag;
	}
	public Timestamp getdSDate() {
		return dSDate;
	}
	public void setdSDate(Timestamp dSDate) {
		this.dSDate = dSDate;
	}
	public BigDecimal getiTax() {
		return iTax;
	}
	public void setiTax(BigDecimal iTax) {
		this.iTax = iTax;
	}
	public BigDecimal getiSOutQuantity() {
		return iSOutQuantity;
	}
	public void setiSOutQuantity(BigDecimal iSOutQuantity) {
		this.iSOutQuantity = iSOutQuantity;
	}
	public BigDecimal getiSOutNum() {
		return iSOutNum;
	}
	public void setiSOutNum(BigDecimal iSOutNum) {
		this.iSOutNum = iSOutNum;
	}
	public BigDecimal getiFNum() {
		return iFNum;
	}
	public void setiFNum(BigDecimal iFNum) {
		this.iFNum = iFNum;
	}
	public BigDecimal getiFQuantity() {
		return iFQuantity;
	}
	public void setiFQuantity(BigDecimal iFQuantity) {
		this.iFQuantity = iFQuantity;
	}
	public Timestamp getdVDate() {
		return dVDate;
	}
	public void setdVDate(Timestamp dVDate) {
		this.dVDate = dVDate;
	}
	public int getiTrIds() {
		return iTrIds;
	}
	public void setiTrIds(int iTrIds) {
		this.iTrIds = iTrIds;
	}
	public String getcDefine25() {
		return cDefine25;
	}
	public void setcDefine25(String cDefine25) {
		this.cDefine25 = cDefine25;
	}
	public BigDecimal getcDefine26() {
		return cDefine26;
	}
	public void setcDefine26(BigDecimal cDefine26) {
		this.cDefine26 = cDefine26;
	}
	public BigDecimal getcDefine27() {
		return cDefine27;
	}
	public void setcDefine27(BigDecimal cDefine27) {
		this.cDefine27 = cDefine27;
	}
	public int getiDLsID() {
		return iDLsID;
	}
	public void setiDLsID(int iDLsID) {
		this.iDLsID = iDLsID;
	}
	public int getiSBsID() {
		return iSBsID;
	}
	public void setiSBsID(int iSBsID) {
		this.iSBsID = iSBsID;
	}
	public BigDecimal getiSendQuantity() {
		return iSendQuantity;
	}
	public void setiSendQuantity(BigDecimal iSendQuantity) {
		this.iSendQuantity = iSendQuantity;
	}
	public BigDecimal getiSendNum() {
		return iSendNum;
	}
	public void setiSendNum(BigDecimal iSendNum) {
		this.iSendNum = iSendNum;
	}
	public int getiEnsID() {
		return iEnsID;
	}
	public void setiEnsID(int iEnsID) {
		this.iEnsID = iEnsID;
	}
	public String getcFree3() {
		return cFree3;
	}
	public void setcFree3(String cFree3) {
		this.cFree3 = cFree3;
	}
	public String getcFree4() {
		return cFree4;
	}
	public void setcFree4(String cFree4) {
		this.cFree4 = cFree4;
	}
	public String getcFree5() {
		return cFree5;
	}
	public void setcFree5(String cFree5) {
		this.cFree5 = cFree5;
	}
	public String getcFree6() {
		return cFree6;
	}
	public void setcFree6(String cFree6) {
		this.cFree6 = cFree6;
	}
	public String getcFree7() {
		return cFree7;
	}
	public void setcFree7(String cFree7) {
		this.cFree7 = cFree7;
	}
	public String getcFree8() {
		return cFree8;
	}
	public void setcFree8(String cFree8) {
		this.cFree8 = cFree8;
	}
	public String getcFree9() {
		return cFree9;
	}
	public void setcFree9(String cFree9) {
		this.cFree9 = cFree9;
	}
	public String getcFree10() {
		return cFree10;
	}
	public void setcFree10(String cFree10) {
		this.cFree10 = cFree10;
	}
	public String getcBarCode() {
		return cBarCode;
	}
	public void setcBarCode(String cBarCode) {
		this.cBarCode = cBarCode;
	}
	public String getcAssUnit() {
		return cAssUnit;
	}
	public void setcAssUnit(String cAssUnit) {
		this.cAssUnit = cAssUnit;
	}
	public Timestamp getdMadeDate() {
		return dMadeDate;
	}
	public void setdMadeDate(Timestamp dMadeDate) {
		this.dMadeDate = dMadeDate;
	}
	public int getiMassDate() {
		return iMassDate;
	}
	public void setiMassDate(int iMassDate) {
		this.iMassDate = iMassDate;
	}
	public String getcDefine28() {
		return cDefine28;
	}
	public void setcDefine28(String cDefine28) {
		this.cDefine28 = cDefine28;
	}
	public String getcDefine29() {
		return cDefine29;
	}
	public void setcDefine29(String cDefine29) {
		this.cDefine29 = cDefine29;
	}
	public String getcDefine30() {
		return cDefine30;
	}
	public void setcDefine30(String cDefine30) {
		this.cDefine30 = cDefine30;
	}
	public String getcDefine31() {
		return cDefine31;
	}
	public void setcDefine31(String cDefine31) {
		this.cDefine31 = cDefine31;
	}
	public String getcDefine32() {
		return cDefine32;
	}
	public void setcDefine32(String cDefine32) {
		this.cDefine32 = cDefine32;
	}
	public String getcDefine33() {
		return cDefine33;
	}
	public void setcDefine33(String cDefine33) {
		this.cDefine33 = cDefine33;
	}
	public int getcDefine34() {
		return cDefine34;
	}
	public void setcDefine34(int cDefine34) {
		this.cDefine34 = cDefine34;
	}
	public int getcDefine35() {
		return cDefine35;
	}
	public void setcDefine35(int cDefine35) {
		this.cDefine35 = cDefine35;
	}
	public Timestamp getcDefine36() {
		return cDefine36;
	}
	public void setcDefine36(Timestamp cDefine36) {
		this.cDefine36 = cDefine36;
	}
	public Timestamp getcDefine37() {
		return cDefine37;
	}
	public void setcDefine37(Timestamp cDefine37) {
		this.cDefine37 = cDefine37;
	}
	public int getiMPoIds() {
		return iMPoIds;
	}
	public void setiMPoIds(int iMPoIds) {
		this.iMPoIds = iMPoIds;
	}
	public int getiCheckIds() {
		return iCheckIds;
	}
	public void setiCheckIds(int iCheckIds) {
		this.iCheckIds = iCheckIds;
	}
	public String getcBVencode() {
		return cBVencode;
	}
	public void setcBVencode(String cBVencode) {
		this.cBVencode = cBVencode;
	}
	public String getcInVouchCode() {
		return cInVouchCode;
	}
	public void setcInVouchCode(String cInVouchCode) {
		this.cInVouchCode = cInVouchCode;
	}
	public String getbGsp() {
		return bGsp;
	}
	public void setbGsp(String bGsp) {
		this.bGsp = bGsp;
	}
	public String getcGspState() {
		return cGspState;
	}
	public void setcGspState(String cGspState) {
		this.cGspState = cGspState;
	}
	public int getiArrsId() {
		return iArrsId;
	}
	public void setiArrsId(int iArrsId) {
		this.iArrsId = iArrsId;
	}
	public String getcCheckCode() {
		return cCheckCode;
	}
	public void setcCheckCode(String cCheckCode) {
		this.cCheckCode = cCheckCode;
	}
	public int getiCheckIdBaks() {
		return iCheckIdBaks;
	}
	public void setiCheckIdBaks(int iCheckIdBaks) {
		this.iCheckIdBaks = iCheckIdBaks;
	}
	public String getcRejectCode() {
		return cRejectCode;
	}
	public void setcRejectCode(String cRejectCode) {
		this.cRejectCode = cRejectCode;
	}
	public int getiRejectIds() {
		return iRejectIds;
	}
	public void setiRejectIds(int iRejectIds) {
		this.iRejectIds = iRejectIds;
	}
	public String getcCheckPersonCode() {
		return cCheckPersonCode;
	}
	public void setcCheckPersonCode(String cCheckPersonCode) {
		this.cCheckPersonCode = cCheckPersonCode;
	}
	public Timestamp getdCheckDate() {
		return dCheckDate;
	}
	public void setdCheckDate(Timestamp dCheckDate) {
		this.dCheckDate = dCheckDate;
	}
	public BigDecimal getiTookQuantity() {
		return iTookQuantity;
	}
	public void setiTookQuantity(BigDecimal iTookQuantity) {
		this.iTookQuantity = iTookQuantity;
	}
	public BigDecimal getiInvalidQuantity() {
		return iInvalidQuantity;
	}
	public void setiInvalidQuantity(BigDecimal iInvalidQuantity) {
		this.iInvalidQuantity = iInvalidQuantity;
	}
	public int getiOMMPoIds() {
		return iOMMPoIds;
	}
	public void setiOMMPoIds(int iOMMPoIds) {
		this.iOMMPoIds = iOMMPoIds;
	}
	public BigDecimal getiPBVQuantity() {
		return iPBVQuantity;
	}
	public void setiPBVQuantity(BigDecimal iPBVQuantity) {
		this.iPBVQuantity = iPBVQuantity;
	}
	public BigDecimal getiPBVyQuantity() {
		return iPBVyQuantity;
	}
	public void setiPBVyQuantity(BigDecimal iPBVyQuantity) {
		this.iPBVyQuantity = iPBVyQuantity;
	}
	public int getiBalance() {
		return iBalance;
	}
	public void setiBalance(int iBalance) {
		this.iBalance = iBalance;
	}
	public BigDecimal getiHXQuantity() {
		return iHXQuantity;
	}
	public void setiHXQuantity(BigDecimal iHXQuantity) {
		this.iHXQuantity = iHXQuantity;
	}
	public String getcCAItemCode() {
		return cCAItemCode;
	}
	public void setcCAItemCode(String cCAItemCode) {
		this.cCAItemCode = cCAItemCode;
	}
	public String getcCBGJDXCode() {
		return cCBGJDXCode;
	}
	public void setcCBGJDXCode(String cCBGJDXCode) {
		this.cCBGJDXCode = cCBGJDXCode;
	}
	public int getBfilled() {
		return bfilled;
	}
	public void setBfilled(int bfilled) {
		this.bfilled = bfilled;
	}
	public int getIarrptrintype() {
		return iarrptrintype;
	}
	public void setIarrptrintype(int iarrptrintype) {
		this.iarrptrintype = iarrptrintype;
	}
	public int getIchkautoid() {
		return ichkautoid;
	}
	public void setIchkautoid(int ichkautoid) {
		this.ichkautoid = ichkautoid;
	}
	public int getIrejid() {
		return irejid;
	}
	public void setIrejid(int irejid) {
		this.irejid = irejid;
	}
	public String getCrejtext() {
		return crejtext;
	}
	public void setCrejtext(String crejtext) {
		this.crejtext = crejtext;
	}
	public BigDecimal getiATaxCost() {
		return iATaxCost;
	}
	public void setiATaxCost(BigDecimal iATaxCost) {
		this.iATaxCost = iATaxCost;
	}
	public BigDecimal getiATaxPrice() {
		return iATaxPrice;
	}
	public void setiATaxPrice(BigDecimal iATaxPrice) {
		this.iATaxPrice = iATaxPrice;
	}
	public BigDecimal getiATaxRate() {
		return iATaxRate;
	}
	public void setiATaxRate(BigDecimal iATaxRate) {
		this.iATaxRate = iATaxRate;
	}
	public BigDecimal getiASum() {
		return iASum;
	}
	public void setiASum(BigDecimal iASum) {
		this.iASum = iASum;
	}
	public BigDecimal getMakePrice() {
		return MakePrice;
	}
	public void setMakePrice(BigDecimal makePrice) {
		MakePrice = makePrice;
	}
	public BigDecimal getMakeMny() {
		return MakeMny;
	}
	public void setMakeMny(BigDecimal makeMny) {
		MakeMny = makeMny;
	}
	public BigDecimal getHadBalanceQuantity() {
		return HadBalanceQuantity;
	}
	public void setHadBalanceQuantity(BigDecimal hadBalanceQuantity) {
		HadBalanceQuantity = hadBalanceQuantity;
	}
	public BigDecimal getHadBalanceMny() {
		return HadBalanceMny;
	}
	public void setHadBalanceMny(BigDecimal hadBalanceMny) {
		HadBalanceMny = hadBalanceMny;
	}
	public BigDecimal getOrgMakePrice() {
		return OrgMakePrice;
	}
	public void setOrgMakePrice(BigDecimal orgMakePrice) {
		OrgMakePrice = orgMakePrice;
	}
	public String getcBOMSoCode() {
		return cBOMSoCode;
	}
	public void setcBOMSoCode(String cBOMSoCode) {
		this.cBOMSoCode = cBOMSoCode;
	}
	public String getCsocode() {
		return csocode;
	}
	public void setCsocode(String csocode) {
		this.csocode = csocode;
	}
	public String getCcbgjdxname() {
		return ccbgjdxname;
	}
	public void setCcbgjdxname(String ccbgjdxname) {
		this.ccbgjdxname = ccbgjdxname;
	}
	public BigDecimal getTaxMakePrice() {
		return TaxMakePrice;
	}
	public void setTaxMakePrice(BigDecimal taxMakePrice) {
		TaxMakePrice = taxMakePrice;
	}
	public BigDecimal getiMakeTaxPrice() {
		return iMakeTaxPrice;
	}
	public void setiMakeTaxPrice(BigDecimal iMakeTaxPrice) {
		this.iMakeTaxPrice = iMakeTaxPrice;
	}
	public BigDecimal getiMakeTaxRate() {
		return iMakeTaxRate;
	}
	public void setiMakeTaxRate(BigDecimal iMakeTaxRate) {
		this.iMakeTaxRate = iMakeTaxRate;
	}
	public BigDecimal getTaxMakeMny() {
		return TaxMakeMny;
	}
	public void setTaxMakeMny(BigDecimal taxMakeMny) {
		TaxMakeMny = taxMakeMny;
	}
	public String getYCcode() {
		return YCcode;
	}
	public void setYCcode(String yCcode) {
		YCcode = yCcode;
	}
	public int getcID() {
		return cID;
	}
	public void setcID(int cID) {
		this.cID = cID;
	}
	public String getcValue() {
		return cValue;
	}
	public void setcValue(String cValue) {
		this.cValue = cValue;
	}
	
}
