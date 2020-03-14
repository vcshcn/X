package net.xway.code.generate.impl.java.model.dao.sql;

import net.xway.code.generate.impl.java.model.dto.DTO;

public class SQLTool {
	
	
	public String getFindByIDSQL(DTO dto) {
		SQL select = new SQL();
		return select.findByID(dto);
	}
	
	public String getFindByPage(DTO dto) {
		SQL select = new SQL();
		return select.findByPage(dto);
	}
	
	public String getFindByCount(DTO dto) {
		SQL select = new SQL();
		return select.findByCount(dto);
	}
	
	public String getInsert(DTO dto) {
		SQL insert = new SQL();
		return insert.insert(dto);
	}
	
	public String getUpdate(DTO dto) {
		SQL update = new SQL();
		return update.update(dto);
	}
	
	public String getDelete(DTO dto) {
		SQL delete = new SQL();
		return delete.delete(dto);
	}
	
	public String getResult(DTO dto) {
		SQL result = new SQL();
		return result.result(dto);
	}
	
}
