package com.intyt.sheet;

import java.util.List;

public class RowBean {

	int cellNum;
	List<BoardConfBean> boardClass;
	List<TagBean> tagClass;
	
	public RowBean(List<BoardConfBean> b,List<TagBean> t) {
		this.boardClass = b;
		this.tagClass = t;
		cellNum = b.size() + t.size();
	}
	
	
	
	
	
	             
	
}
