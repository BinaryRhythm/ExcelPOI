/**
 * the class which used to read spreadsheet to oracle database
 * @author William
 * 2012-7-23
 */
package com.intyt.sheet;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class SheetReader {

	private XSSFSheet spreadsheet = null;

	/**
	 * path -- the spreadsheet file path;
	 * sheetNum -- the sheet you want to read in the spreadsheet.
	 * @param path
	 * @param sheetNum
	 */
	public SheetReader(String path, int sheetNum) {
		try {
			spreadsheet = readSpreadsheet(path, sheetNum);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * store data to oracle
	 * 
	 * @param username
	 * @param password
	 */
	public void storeToOracle(String username, String password) {
		Connection conn = buildConn(username, password);
		if (conn != null) {
			try {
				
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
				System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
				
				retrieve_add_GG_TAG_CLASS(conn, "GG_TAG_CLASS", "NAME");
				retrieve_add_GG_BOARDCONF(conn, "GG_BOARDCONF", "ENTRY_URL");
				retrieve_add_GG_TAG(conn, "GG_TAG");
				retrieve_add_GG_TAG_INDEX(conn, "GG_TAG_INDEX");
				
//				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
				System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				System.out.println(e.getMessage());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
			}
		} else {
			System.out.println("connect oracle failed!");
		}
	}

	/**
	 * read the spreadsheet
	 * 
	 * @param path
	 * @param sheetNum
	 * @return
	 * @throws IOException
	 */
	protected XSSFSheet readSpreadsheet(String path, int sheetNum)
			throws IOException {
		FileInputStream in = new FileInputStream(path);
		XSSFWorkbook xwb = new XSSFWorkbook(in);
		return xwb.getSheetAt(sheetNum);
	}

	/**
	 * connect oracle
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
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

	/**
	 * judge the field is exist or not , and the filed is String type
	 * 
	 * @param conn
	 * @param tableName
	 * @param columName
	 * @param field
	 * @return
	 * @throws SQLException
	 */
	protected boolean isExist(Connection conn, String tableName,
			String columName, String field) throws SQLException {
		if (field != null || field != "") {
			Statement statement = conn.createStatement();
			if (field == null)
				return false; // judge the null
			int result = statement.executeUpdate("select * from " + tableName
					+ " where " + columName + "='" + field + "'");
			statement.close();
			if (result > 0) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	/**
	 * judge the field is exist or not ,and the field type is int
	 * 
	 * @param conn
	 * @param tableName
	 * @param columName
	 * @param field
	 * @return
	 * @throws SQLException
	 */
	protected boolean isExist(Connection conn, String tableName,
			String columName, int field) throws SQLException {
		if (field >= 0) {
			Statement statement = conn.createStatement();
			int result = statement.executeUpdate("select * from " + tableName
					+ " where " + columName + "=" + field);
			statement.close();
			if (result > 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * get table id by class name
	 * 
	 * @param conn
	 * @param tableName
	 * @param columnName
	 * @param className
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	protected int getClassID(Connection conn, String tableName,
			String columnName, String className) throws SQLException,
			IOException {
		// get id by className
		Statement statement = conn.createStatement();
		String str = "select ID from " + tableName + " where " + columnName
				+ "='" + className + "'";
		if (statement.executeUpdate(str) > 0) {
			ResultSet rs = statement.executeQuery(str);
			rs.next();
			int id = rs.getInt(1);
			rs.close();
			statement.close();
			return id;
		}
		// it is not exist then add it
		if (addToGG_TAG_CLASS(conn, tableName, columnName, className))
			return getClassID(conn, tableName, columnName, className);
		statement.close();
		return -1;
	}

	/**
	 * get the gg_tag table id by class name and class id
	 * 
	 * @param conn
	 * @param tableName
	 * @param name
	 * @param nameValue
	 * @param classID
	 * @param classIdValue
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	protected int getClassID(Connection conn, String tableName, String name,
			String nameValue, String classID, int classIdValue)
			throws SQLException, IOException {
		Statement statement = conn.createStatement();
		String str = "select ID from " + tableName + " where " + name + "='"
				+ nameValue + "' and " + classID + "=" + classIdValue;
		if (statement.executeUpdate(str) > 0) {
			ResultSet rs = statement.executeQuery(str);
			rs.next();
			int id = rs.getInt(1);
			rs.close();
			statement.close();
			return id;
		}
		// 如果name 不在 表中要添加 暂未处理
		statement.close();
		return -1;
	}

	/**
	 * insert data to database and return it is success or not
	 * 
	 * @param sql
	 * @param stat
	 * @return
	 * @throws SQLException
	 */
	protected boolean isInsertSuccess(String sql, Statement stat)
			throws SQLException {
		boolean isExecuteSuccess = false;
		//judge the data is exists or not ,
		try {
			stat.executeUpdate(sql);
			isExecuteSuccess = true;
		} catch (SQLException e) {
			isExecuteSuccess = false;
		} catch (Exception e) {
			isExecuteSuccess = false;
			System.out.println(e.getMessage());
		} finally {
			stat.close();
		}
		return isExecuteSuccess;
	}

	/**
	 * judge the class name is in the gg_boardconf table
	 * 
	 * @param conn
	 * @param className
	 * @return
	 * @throws SQLException
	 */
	protected boolean isInGG_BOARDCONF(Connection conn, String className)
			throws SQLException {
		Statement stat = conn.createStatement();
		String sql = "select count(column_name) from cols where table_name = upper('gg_boardconf') and column_name = upper('"
				+ className + "')";
		ResultSet rs = stat.executeQuery(sql);
		rs.next();
		if (rs.getInt(1) > 0) {
			rs.close();
			stat.close();
			return true;
		}
		rs.close();
		stat.close();
		return false;
	}

	/**
	 * according the entry_url to get the BOARDCONF_ID
	 * 
	 * @param conn
	 * @param entry_url
	 * @return
	 * @throws SQLException
	 */
	protected int getBoardConfIdByEntry_url(Connection conn, String entry_url)
			throws SQLException {
		Statement stat = conn.createStatement();
		String sql_board = "select ID from GG_BOARDCONF where ENTRY_URL='"
				+ entry_url + "'";
		ResultSet rs = stat.executeQuery(sql_board);
		rs.next();
		if (rs.getInt(1) > 0) {
			int BOARDCONF_ID = rs.getInt("ID");
			rs.close();
			stat.close();
			return BOARDCONF_ID;
		}
		rs.close();
		stat.close();
		return -1;
	}

	/**
	 * according the name and the class to get the TAG_ID
	 * 
	 * @param conn
	 * @param name
	 * @param className
	 * @return 
	 * @throws SQLException
	 * @throws IOException
	 */
	protected int getTagIdByNameClass(Connection conn, String name,
			String className) throws SQLException, IOException {
		Statement stat = conn.createStatement();
		int classID = getClassID(conn, "GG_TAG_CLASS", "NAME", className);
		if (classID != -1) {
			String sql = "select ID from GG_TAG where NAME='" + name
					+ "' and CLASS=" + classID;
			ResultSet rs = stat.executeQuery(sql);
			if (rs.next()) {
				int TAG_ID = rs.getInt("ID");
				stat.close();
				return TAG_ID;
			}
			rs.close();
		}
		stat.close();
		return -1;
	}

	/**
	 * add data to the table gg_tag_class
	 * 
	 * @param conn
	 * @param tableName
	 * @param columName
	 * @param field
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	protected boolean addToGG_TAG_CLASS(Connection conn, String tableName,
			String columName, String field) throws IOException, SQLException {
		Statement statement = conn.createStatement();
		String str = "insert into "
				+ "\"TYPZTEST\".\"GG_TAG_CLASS\" (ID, NAME)" + " values('','"
				+ field + "')";
		return isInsertSuccess(str, statement);
	}

	/**
	 * add data to the table gg_boardconf
	 * 
	 * @param conn
	 * @param tableName
	 * @param columName
	 * @param name
	 * @param entry_url
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	protected boolean addToGG_BOARDCONF(Connection conn, String tableName,
			String columName, String name, String entry_url)
			throws IOException, SQLException {
		Statement statement = conn.createStatement();
		String str = "insert into " + "\"TYPZTEST\".\"GG_BOARDCONF\" (NAME,"
				+ "ENTRY_URL" + ") values('" + name + "','" + entry_url + "')";
		return isInsertSuccess(str, statement);
	}

	/**
	 * add data to the table gg_tag
	 * 
	 * @param conn
	 * @param tableName
	 * @param classID
	 * @param name
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	protected boolean addToGG_TAG(Connection conn, String tableName,
			int classID, String name) throws SQLException, IOException {
		Statement statement = conn.createStatement();
		if (classID != -1) {
			String str = "insert into " + "\"TYPZTEST\".\"GG_TAG\" (NAME,"
					+ "CLASS" + ") values('" + name + "','" + classID + "')";
			return isInsertSuccess(str, statement);
		}
		
//		PreparedStatement d = conn.prepareStatement("insert into gg_tag(name,class) values(?,?)");
//		while(){
//			d.setString(1, name);
//			d.setInt(2, classID);
//			d.addBatch();
//		}
//		d.executeBatch();
		statement.close();
		return false;
	}

	/**
	 * add data to the table gg_tab_index
	 * 
	 * @param conn
	 * @param tableName
	 * @param tagID
	 * @param boardID
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	protected boolean addToGG_TAG_INDEX(Connection conn, String tableName,
			int tagID, int boardID) throws SQLException, IOException {
		Statement statement = conn.createStatement();
		if (tagID != -1 && boardID != -1) {
			String str = "insert into "
					+ "\"TYPZTEST\".\"GG_TAG_INDEX\" (TAG_ID," + "BOARD_ID"
					+ ") values('" + tagID + "','" + boardID + "')";
			return isInsertSuccess(str, statement);
		}
		statement.close();
		return false;
	}

	/**
	 * retrieve gg_tag_class
	 * 
	 * @param conn
	 * @param tableName
	 * @param columName
	 * @throws IOException
	 * @throws SQLException
	 */
	protected void retrieve_add_GG_TAG_CLASS(Connection conn, String tableName,
			String columName) throws IOException, SQLException {
		// read the class data from excel
		XSSFRow row = spreadsheet.getRow(0);
		XSSFCell cell = null;
		for (int j = 0; j < 13; j++) {
			cell = row.getCell(j);
			if (cell != null) {
				// judge it is exist except the data must store to gg_boardconf ,if not store it
				String property = cell
				.getStringCellValue();
				if (!isExist(conn, tableName, columName,property )&& !isInGG_BOARDCONF(conn, property))
					addToGG_TAG_CLASS(conn, tableName, columName, cell
							.getStringCellValue());
			}
		}
	}

	/**
	 * store data to GG_BOARDCONF
	 * 
	 * @param conn
	 * @param tableName
	 * @param columName
	 * @throws IOException
	 * @throws SQLException
	 */
	protected void retrieve_add_GG_BOARDCONF(Connection conn, String tableName,
			String columName) throws IOException, SQLException {
		XSSFSheet sheet = spreadsheet;
		String name = null;
		String entry_url = null;
		// retrieve all rows ,start from the second row
		for (int i = 1; i < sheet.getLastRowNum(); i++) {
			// get NAME and ENTRY_URL
			XSSFRow row = sheet.getRow(i);
			// NAME
			XSSFCell cell_name = row.getCell(0);
			if (cell_name != null) {
				name = cell_name.getStringCellValue();
			}
			// ENTRY_URL
			XSSFCell cell_entry_url = row.getCell(1);
			if (cell_entry_url != null) {
				entry_url = cell_entry_url.getStringCellValue();
				if(entry_url == null) break;
				// retrieve GG_BOARDCONF and definite the entry_url is exist.
				if (!isExist(conn, tableName, columName, entry_url)) {
					if (addToGG_BOARDCONF(conn, tableName, columName, name,
							entry_url))
						System.out.println("insert success!"+ name + "----  "+ entry_url);
				}
			}
		}
	}

	/**
	 * store to GG_TAG
	 * 
	 * @param conn
	 * @param tableName
	 * @throws IOException
	 * @throws SQLException
	 */
	protected void retrieve_add_GG_TAG(Connection conn, String tableName)
			throws IOException, SQLException {
		XSSFSheet sheet = spreadsheet;
		XSSFRow row = sheet.getRow(sheet.getFirstRowNum()); // get the class row
		int classID;
		Statement statement;
		String cellContent;
		if (row != null) {
			// read the first cell to the last cell
			for (int j = row.getFirstCellNum() + 12; j < row.getLastCellNum(); j++) {
				if (row.getCell(j) == null) {
					break;
				}
				classID = getClassID(conn, "GG_TAG_CLASS", "NAME", row.getCell(
						j).getStringCellValue());
				// read the all columns
				for (int i = 1; i < sheet.getLastRowNum(); i++) {
					XSSFRow rows = sheet.getRow(i);
					// the first cell is class,use class get id
					XSSFCell cell = rows.getCell(j);
					if (cell != null) {
						if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
							cellContent = cell.getStringCellValue();
							if (cellContent != null && cellContent != "") {
								// retrieve all class in table GG_TAG and
								// find
								// name
								// which
								// not in
								// the GG_TAG the add it.
								statement = conn.createStatement();
								boolean isExist = statement
										.executeUpdate("select * from GG_TAG where CLASS="
												+ classID
												+ " and NAME='"
												+ cellContent + "'") <= 0;
								statement.close();
								// judge it is exist.
								if (isExist) {
									if (addToGG_TAG(conn, tableName, classID,
											cellContent))
										System.out.println("insert success!"
												+ "---" + cellContent);
								}
							}
						}
					}
				}
			}
		}// end for
		System.out.println("Over");
	}

	/**
	 * store to GG_TAG_INDEX
	 * 
	 * @throws IOException
	 * @throws SQLException
	 */
	protected void retrieve_add_GG_TAG_INDEX(Connection conn, String tableName)
			throws IOException, SQLException {

		// 读入Excel
		XSSFSheet sheet = spreadsheet;
		// 读入属性栏
		XSSFRow classRow = sheet.getRow(sheet.getFirstRowNum());

		int i = -1;
		int BOARDCONF_ID = -1;
		int TAG_ID = -1;

		// 从属性栏中搜寻 entry_url列位置
		while (classRow.getCell(++i) != null) {
			if (classRow.getCell(i).getStringCellValue().equals("entry_url")) {
				break;
			}
		}

		// entry_url列从上之下读取 其他属性列以从左至右读取至结束式的往下读取
		for (int t = sheet.getFirstRowNum() + 1; t < sheet.getLastRowNum(); t++) {
			XSSFRow row = sheet.getRow(t);

			if(t == 1000){
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
				System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
				break;
			}
			
			if (row != null) {
				// get entry_url
				XSSFCell entry_urlCell = row.getCell(i);// 得到entry_url属性栏的单元格
				if (entry_urlCell != null) {
					String entry_url = entry_urlCell.getStringCellValue();
					if (entry_url != null && entry_url != "") {
						BOARDCONF_ID = getBoardConfIdByEntry_url(conn,
								entry_url);
					}
				}

				// 对这排除了 entry_url的其他属性列做处理 要排除掉放在 gg_boardconf 表中的属性列

				// 读入各单元格内容
				for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
					XSSFCell cell = row.getCell(j);
					if (cell != null) {
						String contents = cell.getStringCellValue();
						if (contents != null && contents != "") {
							String contents_class = classRow.getCell(j)
									.getStringCellValue();
							// 如果contents 对应的属性列不在GG_BOARDCONF中 则查其class id
							if (!isInGG_BOARDCONF(conn, contents_class)) {
								int contentsClassId = getClassID(conn,
										"GG_TAG_CLASS", "NAME", classRow
												.getCell(j)
												.getStringCellValue());
								if (contentsClassId != -1)
									// 依据这个id 和 contents获取tag_id
									TAG_ID = getClassID(conn, "GG_TAG", "NAME",
											contents, "CLASS", contentsClassId);
								if (addToGG_TAG_INDEX(conn, "GG_TAG_INDEX",
										TAG_ID, BOARDCONF_ID))
									System.out
											.println("insert into gg_tag_index success");
							}
						}
					}
				}
			}
		}
	}
}
