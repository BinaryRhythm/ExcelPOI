package com.intyt.sheet;


public class BoardConfBean {

	long ID;

	String NAME; // Ƶ������
	long CHANNEL_TYPE; // ͨ������ 1.���� 2.��̳ 3.����
	long SITE_ID; // վ��ID����gg_siteinfo��id��Ӧ��
	String ENTRY_URL; // Ƶ�����URL�������ԡ�http://����ʼ��������ڵ�ַȥ��
	String URL; // ���� URL {���Բ�����Ч}
	long PROXY_ENABLE; // ��0ʱ��ʹ��HTTP����ɼ���Ƶ�� 1 http 2 sock5 3 sock4
	String CHARSET; // Ƶ������ҳ��������
	long JS_ENABLE; // ��0ʱ���Ƶ����ҳִ��Javascript����
	String DOC_URL_REGEXP; // ����URL�ж�����������ʽ�� {����������Ч}
	String DOC_URL_REGEXP_EX; // ��������URL�ų�����������ʽ��{����������Ч}
	String COMMENT_URL_REGEXP; // ����URL�ж�����������ʽ����Ϊ�����ò����ۣ� {����������Ч}
	String PAGE_DOWN_REGEXP; // ��ҳURL�ж�����������ʽ��{�������š���̳��Ч}
	String DOC_PAGEDOWN_REX; // �����еķ�ҳ����
	long VRSSGATHERENABLE; // �Ƿ�rss�ɼ�
	long URL_GATHER_ENABLE; // �Ƿ���Ҫ��url�ɼ�(���rss����ΪժҪ){���Բ�����Ч}
	long ENTRY_URL_VALID; // 1Ϊ��Ч��2 ��Ч�� Ĭ��ֵΪ0
	long DOC_URL_VALID; // 0����Ҫ��֤��1Ϊ��Ч��2 ��Ч�� Ĭ��ֵΪ0
	long PAGE_DOWN_VALID; // ��ҳ�����Ƿ���Ч 0Ϊ��Ч��1Ϊ��Ч��Ĭ��ֵΪ0
	long WRAPPER_VALID; // ģ���Ƿ���Ч 0����Ҫ��֤��1Ϊ��Ч��2 ��Ч�� Ĭ��ֵΪ0
	long COMMENT_URL_VALID; // ���������Ƿ���Ч 0����Ҫ��֤��1Ϊ��Ч��2 ��Ч�� Ĭ��ֵΪ0
	long DOC_PAGEDOWN_VALID; // 0����Ҫ��֤��1Ϊ��Ч��2 ��Ч�� Ĭ��ֵΪ0
	long WRAPPER_ID; // ��Դ��Ӧ��WRAPPER ID
	long WRAPPER_VER; // ��Ӧ��WRAPPER VERSION ����HISTORY ��ʹ�ã�������������Ҫ��
	long CONSTOR_ID; // ��Դ��Ӧ��CONSTOR ID
	long CONSTER_VER; // CONSTOR VERSION ����HISTORY ��ʹ�ã�������������Ҫ
//	Date LAST_VERIFY_TIME; // DATE Yes 27 ���һ����֤ʱ��
//	Date LAST_UPDATE_TIME; // DATE No SYSDATE 28 ��ǰ����������ʱ��
//	Date CREATE_TIME; // No SYSDATE 29 ����ʱ��
	long FLAG;// �Ƿ�Ϊ���ύ��������(0�����룬1������δЧ�飬2��У�飬-1У�鲻�ɹ�)
	long WEIGHT;// No 0 31 ��Ҫ��0-9 ����Խ�󼶱�Խ��
	String IMG_URL_REGEXP;// (200 BYTE) Yes 32 ͼƬ����
	String IMG_URL_REGEXP_EX;// (200 BYTE) Yes 33 ͼƬ�ų�����
	long IMG_URL_VALID;// (1,0) Yes 0 34
	long IS_SITE_HOMEPAGE;// (1,0) Yes 0 35 �Ƿ���վ����ҳ
	String BOARD_URL_REGEXP;// (200 BYTE) Yes 36 ���Ű����жϹ���
	long BOARD_URL_VALID;// (1,0) Yes 0 37 0����Ҫ��֤��1Ϊ��Ч��2 ��Ч�� Ĭ��ֵΪ0
	String DESCRIPTION;// (512 BYTE) Yes 38 ��Դ������Ϣ
	long CLASSIFIED;// (1,0) Yes 039 �Ƿ��ܣ�����Ϊ1
	long REFERID;// ) Yes "040 �ϲ���Դʱ���£�Ӧ���ο�����Դ
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
