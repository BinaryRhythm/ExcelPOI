package com.intyt.sheet;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinYin {
	private static HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
	static {
		format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		format.setVCharType(HanyuPinyinVCharType.WITH_U_AND_COLON);
	}

	public static String getPinYin(String zhongwen) {
		String zhongWenPinYin = "";
		char[] chars = zhongwen.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			String[] pinYin;
			try {
				pinYin = PinyinHelper
						.toHanyuPinyinStringArray(chars[i], format);
				if (pinYin != null) {
					zhongWenPinYin += pinYin[0].charAt(0);
				} else if ((chars[i] >= 'a' && chars[i] <= 'z')
						|| (chars[i] >= 'A' && chars[i] <= 'Z')) {
					zhongWenPinYin += chars[i];
				}
			} catch (BadHanyuPinyinOutputFormatCombination e) {
				// TODO Auto-generated catch block
				if ((chars[i] >= 'a' && chars[i] <= 'z')
						|| (chars[i] >= 'A' && chars[i] <= 'Z')) {
					zhongWenPinYin += chars[i];
				}
			}
			
		}
		return zhongWenPinYin;
	}

}
