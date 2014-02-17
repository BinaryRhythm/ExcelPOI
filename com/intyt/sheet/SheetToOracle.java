package com.intyt.sheet;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * read data from oracle and then store it to oracle
 * 
 * @author William
 * 
 */
public class SheetToOracle {

	private Connection conn;
	private Workbook wb = null;
	List<String> list = null; // 放所有的属性
	List<Integer> boardClassLocation = null;
	List<Integer> tagClassLocation = null;
	List<String> params;

	public SheetToOracle(String path, String user, String password)
			throws InvalidFormatException, IOException, ClassNotFoundException,
			SQLException {
		File file = new File(path);
		wb = WorkbookFactory.create(file);
		conn = buildConn("typztest", "typztest");
		init();
	}

	// inition
	public void init() {
		params = new ArrayList<String>();
		params.add("NAME");
		params.add("CHANNEL_TYPE");
		params.add("SITE_ID");
		params.add("ENTRY_URL");
		params.add("URL");
		params.add("PROXY_ENABLE");
		params.add("CHARSET");
		params.add("JS_ENABLE");
		params.add("DOC_URL_REGEXP");
		params.add("DOC_URL_REGEXP_EX");
		params.add("COMMENT_URL_REGEXP");
		params.add("PAGE_DOWN_REGEXP");
		params.add("DOC_PAGEDOWN_REX");
		params.add("RSSGATHERENABLE");
		params.add("URL_GATHER_ENABLE");
		params.add("ENTRY_URL_VALID");
		params.add("DOC_URL_VALID");
		params.add("PAGE_DOWN_VALID");
		params.add("WRAPPER_VALID");
		params.add("COMMENT_URL_VALID");
		params.add("DOC_PAGEDOWN_VALID");
		params.add("WRAPPER_ID");
		params.add("WRAPPER_VER");
		params.add("CONSTOR_ID");
		params.add("CONSTER_VER");
		params.add("FLAG");
		params.add("WEIGHT");
		params.add("IMG_URL_REGEXP");
		params.add("IMG_URL_REGEXP_EX");
		params.add("IMG_URL_VALID");
		params.add("IS_SITE_HOMEPAGE");
		params.add("BOARD_URL_REGEXP");
		params.add("BOARD_URL_VALID");
		params.add("DESCRIPTION");
		params.add("CLASSIFIED");
		params.add("REFERID");
		params.add("DETAIL_WRAPPER_ID");
		params.add("DETAIL_WRAPPER_VER");
		params.add("DETAIL_WRAPPER_VALID");

	}

	// connect oracle
	protected static Connection buildConn(String username, String password)
			throws ClassNotFoundException, SQLException {
		String loadDriver = "oracle.jdbc.driver.OracleDriver";
		String getConn = "jdbc:oracle:thin:@10.1.1.4:1521:qysq";

		Class.forName(loadDriver);
		System.out.println("Load driver success!");
		Connection conn = DriverManager.getConnection(getConn, username,
				password);
		System.out.println("conncet oracle success!");
		return conn;

	}

	// judge the class name is in the boardconf table
	protected boolean isInGG_BOARDCONF(Connection conn, String className)
			throws SQLException {
		Statement stat = conn.createStatement();
		String sql = "select count(column_name) from cols where table_name = upper('gg_boardconf') and column_name = upper('"
				+ className + "')";
		ResultSet rs = stat.executeQuery(sql);
		rs.next();
		int num = rs.getInt(1);
		rs.close();
		stat.close();
		if (num > 0) {
			return true;
		}
		return false;
	}

	// judge the field is exist
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

	// judge insert data to database is success or not
	protected static boolean isInsertSuccess(String sql, Statement stat)
			throws SQLException {
		boolean isExecuteSuccess = false;
		// if not occurred any exception then insert success
		try {
			stat.executeUpdate(sql);
			isExecuteSuccess = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			isExecuteSuccess = false;
		} finally {
			stat.close();
		}
		return isExecuteSuccess;
	}

	// add data to table by read add
	protected boolean addToGG_TAG_CLASS(Connection conn, String tableName,
			String columnName, List<String> field) throws IOException,
			SQLException {
		PreparedStatement ps = conn.prepareStatement("insert into " + tableName
				+ " (" + columnName + ") values(?)");
		int i = 0;
		int k = 0; // record how many records insert to database
		for (; i < field.size(); i++) {
			String name = field.get(i);
			if (name != null && name != "")
				if (!params.contains(name.toUpperCase())) // 这里可以改进 提高速度
					if (!isExist(conn, tableName, columnName, name)) {
						ps.setString(1, name);
						ps.addBatch();
						k++;
					}
		}
		if (k > 0)
			if (ps.executeBatch().length == k) {
				ps.close();
				return true;
			}
		ps.close();
		return false;
	}

	// handle the class column
	protected void handleClass(int index) throws IOException, SQLException {
		Row row = null;
		if (wb != null) {
			if (wb instanceof XSSFWorkbook) {
				XSSFSheet sheet = (XSSFSheet) wb.getSheetAt(index);
				row = sheet.getRow(sheet.getFirstRowNum());
			} else {
				HSSFSheet sheet = (HSSFSheet) wb.getSheetAt(index);
				row = sheet.getRow(sheet.getFirstRowNum());
			}
		}
		// read class column and store it in list
		if (row != null) {
			list = new ArrayList<String>();
			boardClassLocation = new ArrayList<Integer>();
			tagClassLocation = new ArrayList<Integer>();
			for (Cell cell : row) {
				if (cell != null) {
					if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
						String name = cell.getStringCellValue();
						if (name != null && name != "") {
							// record the class name in the board
							if (params.contains(name.toUpperCase()))
								boardClassLocation.add(list.size());
							else
								tagClassLocation.add(list.size());
							list.add(name);
						}
					}
				}
			}
		}

		// store the contents in list to gg_tag_class if it not exist
		if (list.size() > 0) {
			// exist or not
			if (addToGG_TAG_CLASS(conn, "GG_TAG_CLASS", "NAME", list))
				System.out.println("insert class success!");
			else
				System.out.println("insert class failed!");
		}
	}

	@SuppressWarnings("null")
	protected int addToGG_TAG(Connection conn, String name, int classID,
			String index_word) throws SQLException, IOException {
		Statement statement = conn.createStatement();
		if (classID != -1) {
			String str = "insert into GG_TAG (NAME,CLASS,INDEX_WORD) select '"
					+ name
					+ "',"
					+ classID
					+ ",'"
					+ index_word
					+ "' from dual where not exists(select 1 from gg_tag where name='"
					+ name + "' and class=" + classID + ")";
			ResultSet st = statement.executeQuery(str);
			if (st != null) {
				st.next();
				int m = st.getInt(1);
				st.close();
				statement.close();
				return m;
			}
			st.close();
		}
		statement.close();
		return -1;
	}

	protected int getClassID(Connection conn, String tableName, String name,
			String nameValue, String classID, int classIdValue,
			String index_word, String index) throws SQLException, IOException {
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
		} else {
			// 如果name 不在 表中要添加 暂未处理
			String sql = "insert into " + tableName
					+ " (NAME,CLASS,INDEX_WORD) VALUES('" + nameValue + "',"
					+ classIdValue + ",'" + index + "')";
			statement.execute(sql);
			statement.close();
			return getClassID(conn, tableName, name, nameValue, classID,
					classIdValue, index_word, index);
		}
	}

	// to handle the gg_tag_class
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
		List<String> arr = new ArrayList<String>();
		arr.add(className);
		if (addToGG_TAG_CLASS(conn, tableName, columnName, arr))
			return getClassID(conn, tableName, columnName, className);
		statement.close();
		return -1;
	}

	// read and store that not exist
	@SuppressWarnings("null")
	public void readStore(int index) throws SQLException, IOException {
		// read the class rows
		Date d = new Date();
		long jjj = d.getTime();
		handleClass(index);
		// read the other rows
		Sheet sheet = null;
		int rows = 0; // the total number of rows except the class row

		if (wb != null) {
			sheet = wb.getSheetAt(index);
			rows = sheet.getPhysicalNumberOfRows();
		}

		// read every data row
		if (rows > 1) {
			// start parser ,scan every row
			for (int i = 1; i < rows; i++) {
				Row row = sheet.getRow(i);
				int[] tag_ids = null;
				int board_id = -1;
				if (row != null)
					if (list.size() > 0) {
						if (boardClassLocation.size() > 0) {
							String str = null;
							String value = null;
							String sql_BOARDCONF = "insert into GG_BOARDCONF (";
							PreparedStatement ps_BOARDCONF;
							String entry_url = null;
							Statement stat = conn.createStatement();
							// conn.setAutoCommit(false);
							// read all class that must be stored into boardconf
							// table
							for (int j = 0; j < boardClassLocation.size(); j++) {
								Cell cell = row
										.getCell((int) boardClassLocation
												.get(j));
								if (cell != null)
									if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
										// handle the board class cells
										String boardconfClass = list
												.get(boardClassLocation.get(j)); // 属性
										if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
											String boardconfClassValue = cell
													.getStringCellValue(); // 属性值
											if (boardconfClass
													.equals("entry_url")
													|| boardconfClass
															.equals("ENTRY_URL"))
												entry_url = boardconfClassValue;
											if (j == 0) {
												str = boardconfClass;
												value = "'"
														+ boardconfClassValue
														+ "'";
											} else {
												str += "," + boardconfClass;
												value += ",'"
														+ boardconfClassValue
														+ "'";
											}
										} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {

											double boardconfClassValue = cell
													.getNumericCellValue();// 属性值

											if (j == 0) {
												str = boardconfClass;
												value = String
														.valueOf(boardconfClassValue);
											} else {
												str += "," + boardconfClass;
												value += ","
														+ String
																.valueOf(boardconfClassValue);
											}
										}
									}
							}// end for cell

							if (str != null && value != null) {

								sql_BOARDCONF += str
										+ ") select "
										+ value
										+ " from dual where not exists(select 1 from gg_boardconf where entry_url='"
										+ entry_url + "')";

								ps_BOARDCONF = conn
										.prepareStatement(sql_BOARDCONF);
								ResultSet rs = null;
								try {
									rs = ps_BOARDCONF.executeQuery();
									rs.next();
									board_id = rs.getInt(1);
								} catch (SQLException e) {

								} finally {
									rs.close();
									ps_BOARDCONF.close();
								}
								// if the record is exist,then get it's id
								if (board_id == 0 || board_id == -1) {
									rs = stat
											.executeQuery("select id from gg_boardconf where entry_url='"
													+ entry_url + "'");
									if (rs.next()) {
										board_id = rs.getInt(1);
									}
									rs.close();
									stat.close();
								}

							}

						}// end board if

						if (tagClassLocation.size() > 0) {
							int m = 0;
							tag_ids = new int[tagClassLocation.size()];
							// read all class tha must be stored in gg_tag
							for (int j = 0; j < tagClassLocation.size(); j++) {
								Cell cell = row.getCell((int) tagClassLocation
										.get(j));
								// handle the tag class cells
								if (cell != null)
									if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {

										if (cell.getCellType() == Cell.CELL_TYPE_STRING) {

											String nameCell = cell
													.getStringCellValue();
											if (nameCell != null) {
												String tagClass = list
														.get(tagClassLocation
																.get(j)); // 属性
												int classId = getClassID(conn,
														"GG_TAG_CLASS", "NAME",
														tagClass);
												String index_word = PinYin
														.getPinYin(nameCell);

												String str = "insert into GG_TAG (NAME,CLASS,INDEX_WORD) select '"
														+ nameCell
														+ "',"
														+ classId
														+ ",'"
														+ index_word
														+ "' from dual where not exists(select 1 from gg_tag where name='"
														+ nameCell
														+ "' and class="
														+ classId + ")";
												// 做tag表的添加操作
												int ids = test(conn, nameCell,
														classId, index_word);
												if (ids != -1)
													tag_ids[m++] = ids;

											}
										}
									}
							}// end for cell
						}// end tag if
						// store to the table gg_tag_index ; board_id tag_ids
						String sql_TAG_INDEX = "insert into GG_TAG_INDEX (board_id,tag_id) values(?,?)";
						PreparedStatement ps_TAG_INDEX = conn
								.prepareStatement(sql_TAG_INDEX);

						if (tag_ids != null && board_id > 0) {
							for (int t : tag_ids) {
								if (t > 0) {
									ps_TAG_INDEX.setInt(1, board_id);
									ps_TAG_INDEX.setInt(2, t);
									ps_TAG_INDEX.addBatch();
								}
							}

							try {
								ps_TAG_INDEX.executeBatch();
								ps_TAG_INDEX.close();
							} catch (SQLException e) {
								System.out.println("over..");
							}
						}
					}
			}// end for row
		}
		Date kkk = new Date();
		long mmm = kkk.getTime();
		System.out.println(mmm - jjj);
		conn.close();
	}

	public int test(Connection conn, String l1, int l2, String l3) {
		try {
			Statement s = conn.createStatement();
			String str = "insert into GG_TAG (NAME,CLASS,INDEX_WORD) select '"
					+ l1
					+ "',"
					+ l2
					+ ",'"
					+ l3
					+ "' from dual where not exists(select 1 from gg_tag where name='"
					+ l1 + "' and class=" + l2 + ")";
			s.execute(str);
			s.close();
			// get id
			return getClassID(conn, "gg_tag", "name", l1, "class", l2,
					"index_word", l3);
		} catch (Exception e) {
			return -1;
		}
	}
}
