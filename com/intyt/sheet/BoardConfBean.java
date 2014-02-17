package com.intyt.sheet;


public class BoardConfBean {

	long ID;

	String NAME; // 频道名称
	long CHANNEL_TYPE; // 通道类型 1.新闻 2.论坛 3.博客
	long SITE_ID; // 站点ID（与gg_siteinfo中id对应）
	String ENTRY_URL; // 频道入口URL（必须以“http://”开始），以入口地址去重
	String URL; // 博客 URL {仅对博客有效}
	long PROXY_ENABLE; // 非0时需使用HTTP代理采集该频道 1 http 2 sock5 3 sock4
	String CHARSET; // 频道的网页编码类型
	long JS_ENABLE; // 非0时需对频道网页执行Javascript解析
	String DOC_URL_REGEXP; // 文章URL判定规则（正则表达式） {仅对新闻有效}
	String DOC_URL_REGEXP_EX; // 新闻文章URL排除规则（正则表达式）{仅对新闻有效}
	String COMMENT_URL_REGEXP; // 评论URL判定规则（正则表达式）（为空则不用采评论） {仅对新闻有效}
	String PAGE_DOWN_REGEXP; // 翻页URL判定规则（正则表达式）{仅对新闻、论坛有效}
	String DOC_PAGEDOWN_REX; // 正文中的翻页正则
	long VRSSGATHERENABLE; // 是否rss采集
	long URL_GATHER_ENABLE; // 是否需要按url采集(针对rss返回为摘要){仅对博客有效}
	long ENTRY_URL_VALID; // 1为有效，2 无效， 默认值为0
	long DOC_URL_VALID; // 0不需要验证，1为有效，2 无效， 默认值为0
	long PAGE_DOWN_VALID; // 翻页正则是否有效 0为无效，1为有效，默认值为0
	long WRAPPER_VALID; // 模板是否有效 0不需要验证，1为有效，2 无效， 默认值为0
	long COMMENT_URL_VALID; // 评论正则是否有效 0不需要验证，1为有效，2 无效， 默认值为0
	long DOC_PAGEDOWN_VALID; // 0不需要验证，1为有效，2 无效， 默认值为0
	long WRAPPER_ID; // 信源对应的WRAPPER ID
	long WRAPPER_VER; // 对应的WRAPPER VERSION （在HISTORY 中使用，正常关联不需要）
	long CONSTOR_ID; // 信源对应的CONSTOR ID
	long CONSTER_VER; // CONSTOR VERSION ，在HISTORY 中使用，正常关联不需要
//	Date LAST_VERIFY_TIME; // DATE Yes 27 最近一次验证时间
//	Date LAST_UPDATE_TIME; // DATE No SYSDATE 28 当前配置最后更新时间
//	Date CREATE_TIME; // No SYSDATE 29 创建时间
	long FLAG;// 是否为新提交配置申请(0新申请，1已配置未效验，2已校验，-1校验不成功)
	long WEIGHT;// No 0 31 重要度0-9 数字越大级别越高
	String IMG_URL_REGEXP;// (200 BYTE) Yes 32 图片正则
	String IMG_URL_REGEXP_EX;// (200 BYTE) Yes 33 图片排除正则
	long IMG_URL_VALID;// (1,0) Yes 0 34
	long IS_SITE_HOMEPAGE;// (1,0) Yes 0 35 是否是站点首页
	String BOARD_URL_REGEXP;// (200 BYTE) Yes 36 新闻版面判断规则
	long BOARD_URL_VALID;// (1,0) Yes 0 37 0不需要验证，1为有效，2 无效， 默认值为0
	String DESCRIPTION;// (512 BYTE) Yes 38 信源描述信息
	long CLASSIFIED;// (1,0) Yes 039 是否保密，保密为1
	long REFERID;// ) Yes "040 合并信源时记下，应当参考的信源
	long DETAIL_WRAPPER_ID;// (10,0) Yes 41
	long DETAIL_WRAPPER_VER;// (10,0) Yes 42
	long DETAIL_WRAPPER_VALID;// (1,0) Yes "043
	
	public long getID() {
		return ID;
	}
	public void setID(long iD) {
		ID = iD;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public long getCHANNEL_TYPE() {
		return CHANNEL_TYPE;
	}
	public void setCHANNEL_TYPE(long cHANNELTYPE) {
		CHANNEL_TYPE = cHANNELTYPE;
	}
	public long getSITE_ID() {
		return SITE_ID;
	}
	public void setSITE_ID(long sITEID) {
		SITE_ID = sITEID;
	}
	public String getENTRY_URL() {
		return ENTRY_URL;
	}
	public void setENTRY_URL(String eNTRYURL) {
		ENTRY_URL = eNTRYURL;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public long getPROXY_ENABLE() {
		return PROXY_ENABLE;
	}
	public void setPROXY_ENABLE(long pROXYENABLE) {
		PROXY_ENABLE = pROXYENABLE;
	}
	public String getCHARSET() {
		return CHARSET;
	}
	public void setCHARSET(String cHARSET) {
		CHARSET = cHARSET;
	}
	public long getJS_ENABLE() {
		return JS_ENABLE;
	}
	public void setJS_ENABLE(long jSENABLE) {
		JS_ENABLE = jSENABLE;
	}
	public String getDOC_URL_REGEXP() {
		return DOC_URL_REGEXP;
	}
	public void setDOC_URL_REGEXP(String dOCURLREGEXP) {
		DOC_URL_REGEXP = dOCURLREGEXP;
	}
	public String getDOC_URL_REGEXP_EX() {
		return DOC_URL_REGEXP_EX;
	}
	public void setDOC_URL_REGEXP_EX(String dOCURLREGEXPEX) {
		DOC_URL_REGEXP_EX = dOCURLREGEXPEX;
	}
	public String getCOMMENT_URL_REGEXP() {
		return COMMENT_URL_REGEXP;
	}
	public void setCOMMENT_URL_REGEXP(String cOMMENTURLREGEXP) {
		COMMENT_URL_REGEXP = cOMMENTURLREGEXP;
	}
	public String getPAGE_DOWN_REGEXP() {
		return PAGE_DOWN_REGEXP;
	}
	public void setPAGE_DOWN_REGEXP(String pAGEDOWNREGEXP) {
		PAGE_DOWN_REGEXP = pAGEDOWNREGEXP;
	}
	public String getDOC_PAGEDOWN_REX() {
		return DOC_PAGEDOWN_REX;
	}
	public void setDOC_PAGEDOWN_REX(String dOCPAGEDOWNREX) {
		DOC_PAGEDOWN_REX = dOCPAGEDOWNREX;
	}
	public long getVRSSGATHERENABLE() {
		return VRSSGATHERENABLE;
	}
	public void setVRSSGATHERENABLE(long vRSSGATHERENABLE) {
		VRSSGATHERENABLE = vRSSGATHERENABLE;
	}
	public long getURL_GATHER_ENABLE() {
		return URL_GATHER_ENABLE;
	}
	public void setURL_GATHER_ENABLE(long uRLGATHERENABLE) {
		URL_GATHER_ENABLE = uRLGATHERENABLE;
	}
	public long getENTRY_URL_VALID() {
		return ENTRY_URL_VALID;
	}
	public void setENTRY_URL_VALID(long eNTRYURLVALID) {
		ENTRY_URL_VALID = eNTRYURLVALID;
	}
	public long getDOC_URL_VALID() {
		return DOC_URL_VALID;
	}
	public void setDOC_URL_VALID(long dOCURLVALID) {
		DOC_URL_VALID = dOCURLVALID;
	}
	public long getPAGE_DOWN_VALID() {
		return PAGE_DOWN_VALID;
	}
	public void setPAGE_DOWN_VALID(long pAGEDOWNVALID) {
		PAGE_DOWN_VALID = pAGEDOWNVALID;
	}
	public long getWRAPPER_VALID() {
		return WRAPPER_VALID;
	}
	public void setWRAPPER_VALID(long wRAPPERVALID) {
		WRAPPER_VALID = wRAPPERVALID;
	}
	public long getCOMMENT_URL_VALID() {
		return COMMENT_URL_VALID;
	}
	public void setCOMMENT_URL_VALID(long cOMMENTURLVALID) {
		COMMENT_URL_VALID = cOMMENTURLVALID;
	}
	public long getDOC_PAGEDOWN_VALID() {
		return DOC_PAGEDOWN_VALID;
	}
	public void setDOC_PAGEDOWN_VALID(long dOCPAGEDOWNVALID) {
		DOC_PAGEDOWN_VALID = dOCPAGEDOWNVALID;
	}
	public long getWRAPPER_ID() {
		return WRAPPER_ID;
	}
	public void setWRAPPER_ID(long wRAPPERID) {
		WRAPPER_ID = wRAPPERID;
	}
	public long getWRAPPER_VER() {
		return WRAPPER_VER;
	}
	public void setWRAPPER_VER(long wRAPPERVER) {
		WRAPPER_VER = wRAPPERVER;
	}
	public long getCONSTOR_ID() {
		return CONSTOR_ID;
	}
	public void setCONSTOR_ID(long cONSTORID) {
		CONSTOR_ID = cONSTORID;
	}
	public long getCONSTER_VER() {
		return CONSTER_VER;
	}
	public void setCONSTER_VER(long cONSTERVER) {
		CONSTER_VER = cONSTERVER;
	}
	
	public long getFLAG() {
		return FLAG;
	}
	public void setFLAG(long fLAG) {
		FLAG = fLAG;
	}
	public long getWEIGHT() {
		return WEIGHT;
	}
	public void setWEIGHT(long wEIGHT) {
		WEIGHT = wEIGHT;
	}
	public String getIMG_URL_REGEXP() {
		return IMG_URL_REGEXP;
	}
	public void setIMG_URL_REGEXP(String iMGURLREGEXP) {
		IMG_URL_REGEXP = iMGURLREGEXP;
	}
	public String getIMG_URL_REGEXP_EX() {
		return IMG_URL_REGEXP_EX;
	}
	public void setIMG_URL_REGEXP_EX(String iMGURLREGEXPEX) {
		IMG_URL_REGEXP_EX = iMGURLREGEXPEX;
	}
	public long getIMG_URL_VALID() {
		return IMG_URL_VALID;
	}
	public void setIMG_URL_VALID(long iMGURLVALID) {
		IMG_URL_VALID = iMGURLVALID;
	}
	public long getIS_SITE_HOMEPAGE() {
		return IS_SITE_HOMEPAGE;
	}
	public void setIS_SITE_HOMEPAGE(long iSSITEHOMEPAGE) {
		IS_SITE_HOMEPAGE = iSSITEHOMEPAGE;
	}
	public String getBOARD_URL_REGEXP() {
		return BOARD_URL_REGEXP;
	}
	public void setBOARD_URL_REGEXP(String bOARDURLREGEXP) {
		BOARD_URL_REGEXP = bOARDURLREGEXP;
	}
	public long getBOARD_URL_VALID() {
		return BOARD_URL_VALID;
	}
	public void setBOARD_URL_VALID(long bOARDURLVALID) {
		BOARD_URL_VALID = bOARDURLVALID;
	}
	public String getDESCRIPTION() {
		return DESCRIPTION;
	}
	public void setDESCRIPTION(String dESCRIPTION) {
		DESCRIPTION = dESCRIPTION;
	}
	public long getCLASSIFIED() {
		return CLASSIFIED;
	}
	public void setCLASSIFIED(long cLASSIFIED) {
		CLASSIFIED = cLASSIFIED;
	}
	public long getREFERID() {
		return REFERID;
	}
	public void setREFERID(long rEFERID) {
		REFERID = rEFERID;
	}
	public long getDETAIL_WRAPPER_ID() {
		return DETAIL_WRAPPER_ID;
	}
	public void setDETAIL_WRAPPER_ID(long dETAILWRAPPERID) {
		DETAIL_WRAPPER_ID = dETAILWRAPPERID;
	}
	public long getDETAIL_WRAPPER_VER() {
		return DETAIL_WRAPPER_VER;
	}
	public void setDETAIL_WRAPPER_VER(long dETAILWRAPPERVER) {
		DETAIL_WRAPPER_VER = dETAILWRAPPERVER;
	}
	public long getDETAIL_WRAPPER_VALID() {
		return DETAIL_WRAPPER_VALID;
	}
	public void setDETAIL_WRAPPER_VALID(long dETAILWRAPPERVALID) {
		DETAIL_WRAPPER_VALID = dETAILWRAPPERVALID;
	}
	
	
}
