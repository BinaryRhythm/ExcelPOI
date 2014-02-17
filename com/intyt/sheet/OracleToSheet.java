package com.intyt.sheet;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;

public class OracleToSheet {

	Connection conn;
	List<Integer> board_id;
	List<BoardConfBean> list_board = new ArrayList<BoardConfBean>(); //row num

	public OracleToSheet(List<Integer> ids) {
		board_id = ids;
		conn = buildConn("typztest", "typztest");
	}

	public Connection buildConn(String username, String password) {
		String loadDriver = "oracle.jdbc.driver.OracleDriver";
		String getConn = "jdbc:oracle:thin:@10.1.1.4:1521:qysq";

		try {
			Class.forName(loadDriver);
			System.out.println("Load driver success!");
			Connection conn = DriverManager.getConnection(getConn, username,
					password);
			System.out.println("conncet oracle success!");
			return conn;
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public void readBoardconf() throws SQLException {
		// read data
		if (board_id.size() > 0) {
			String str = String.valueOf(board_id.get(0));
			for (int i = 1; i < board_id.size(); i++) {
				str += "," + String.valueOf(board_id.get(i));
			}

			String sql_board = "select * from gg_boardconf where id in (" + str
					+ ")"; // 依据主属性查出数据

			String sql_tag = "select NAME,CATEGORY,CLASS from gg_tag where id in (select tag_id from gg_tag_index where board_id in ("
					+ str + ")) order by class"; // 从tag属性获取数据

			String sql_index = "select TAG_ID,BOARD_ID from gg_tag_index where BOARD_ID in ("
					+ str + ") order by board_id";

			Statement stat = conn.createStatement();
			ResultSet rs_board = stat.executeQuery(sql_board);
		
			while (rs_board.next()) {
				BoardConfBean e = new BoardConfBean();
				// e.set()
				// 全部放入list中

				list_board.add(e);

			}
			rs_board.close();

			// /////////////////
			ResultSet rs_index = stat.executeQuery(sql_index);
			while (rs_index.next()) {
				long front = rs_index.getLong("board_id");
				if (rs_index.next()) {
					long back = rs_index.getLong("board_id");
					if (back == front) {
						///处理相同的 放到同一个row

					}
				}
			}
			rs_index.close();
			// /////////////////////////////////
			ResultSet rs_tag = stat.executeQuery(sql_tag);
			while (rs_tag.next()) {

				// 组织成一个一个的数据列

			}
			rs_tag.close();

			stat.close();
		}
	}

	public void produceClassRow() throws SQLException {
		readBoardconf();
		//构造行
		//select gg_boardconf.* from gg_boardconf,gg_tag,gg_tag_index where gg_tag.id in (select tag_id from gg_tag_index where board_id in (24649)) 
		SheetExport se = new SheetExport("berry.xls");
		// row num
		for ( int i = 0;i < list_board.size();i++){
			HSSFRow row = se.createRow(i);
			for(int j = 0;j<list_board.get(i))
            BoardConfBean bcb = list_board.get(index)
			if( i == 0){
				se.setCell(row, i, list_board.get(index));
			}
		}
		//cell num
		
		
		
	}

}
