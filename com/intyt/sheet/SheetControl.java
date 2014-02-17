package com.intyt.sheet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class SheetControl {
  
	public static void main(String args[]) {
//		SheetReader sr = new SheetReader("src/新闻源汇总13002资讯组 0521.xlsx",5);
//		sr.storeToOracle("typztest", "typztest");
		
//		try {
//			SheetToOracle so = new SheetToOracle("src/新闻源汇总13002资讯组 0521.xls", null, null);
//			Calendar ca = Calendar.getInstance();
//			System.out.println(ca.get(Calendar.MINUTE)+"----"+ca.get(Calendar.SECOND));
//			so.readStore(5);	
//			Calendar ca1 = Calendar.getInstance();
//			System.out.println(ca1.get(Calendar.MINUTE)+"----"+ca1.get(Calendar.SECOND));
//		} catch (InvalidFormatException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		List<Integer> list = new ArrayList<Integer>();
		list.add(24649);
		list.add(24631);
		list.add(24633);
		list.add(17);
//		list.add(24);
//		list.add(5);
//		list.add(6);
//		list.add(7);
//		list.add(8);
//		list.add(9);
		
		OracleToSheet os = new OracleToSheet(list);
		try {
			os.produceClassRow();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
