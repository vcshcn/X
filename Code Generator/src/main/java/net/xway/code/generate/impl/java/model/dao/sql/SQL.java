package net.xway.code.generate.impl.java.model.dao.sql;

import java.util.List;

import net.xway.code.generate.impl.java.model.JavaField;
import net.xway.code.generate.impl.java.model.dto.DTO;
import net.xway.code.model.type.ComponentType;
import net.xway.code.model.type.PrimaryKeyType;

public class SQL {

	public final static String CRLF = "\r\n";
	
	private final String[] ALIAS = new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U","V", "W", "X", "Y", "Z"};
	
	int aliasIndex = 0;
	
	public String getFreeAlias() {
		if (aliasIndex < ALIAS.length) {
			return ALIAS[aliasIndex++];
		}
		else {
			return ALIAS[aliasIndex % ALIAS.length] + (aliasIndex++ / ALIAS.length - 1);
		}
	}
	
	public String result(DTO dto) {
		aliasIndex = 0;
		SQLTable leaf = new SQLTable(dto, getFreeAlias());
		leaf.where(dto.getPrimaryKey(), "=", "#{id}");
		SQLTable cur = leaf;
		
		for (JavaField jField : dto.getFields()) {
			if (jField.getField().getType() instanceof ComponentType) {
				DTO ref = (DTO) jField.getType();
				SQLTable parent = new SQLTable(ref, getFreeAlias());
				
				if (jField.getField().isNotNull()) {
					JoinTable jtable = new JoinTable(JoinTable.Type.INNER, cur, leaf.findField(jField.getField().getJdbcName()), getFreeAlias());
					parent.join(jtable);
				}
				else {
					JoinTable jtable = new JoinTable(JoinTable.Type.LEFT, cur, leaf.findField(jField.getField().getJdbcName()), getFreeAlias());
					parent.join(jtable);
				}
				cur = parent;
			}
		}
		
		StringBuilder sb = new StringBuilder();		
		for (JavaField field : dto.getFields()) {
			if (field.getField().getType() instanceof PrimaryKeyType) {
				sb.append("<id property=\"");
				sb.append(field.getPropertyName());
				sb.append("\" column=\"");
				sb.append(cur.findField(field).getAlias());
				sb.append("\"/>");
				sb.append(CRLF);

			}
			else  {
				if (field.getField().getType() instanceof ComponentType) {
					DTO key = (DTO) field.getType();
					for (JavaField field2 : key.getFields()) {
						sb.append("<result property=\"");
						sb.append(field.getPropertyName());
						sb.append(".");
						sb.append(field2.getPropertyName());
						sb.append("\" column=\"");
						sb.append(cur.findField(field2).getAlias());
						sb.append("\"/>");
						sb.append(CRLF);
					}
				}
				else {
					sb.append("<result property=\"");
					sb.append(field.getPropertyName());
					sb.append("\" column=\"");
					sb.append(cur.findField(field).getAlias());
					sb.append("\"/>");
					sb.append(CRLF);
				}
			}
			
		}

		return sb.toString();
	}

	public String findByID(DTO dto) {
		aliasIndex = 0;
		
		SQLTable leaf = new SQLTable(dto, getFreeAlias());
		leaf.where(dto.getPrimaryKey(), "=", "#{id}");
		SQLTable cur = leaf;
		
		for (JavaField jField : dto.getFields()) {
			if (jField.getField().getType() instanceof ComponentType) {
				DTO ref = (DTO) jField.getType();
				SQLTable parent = new SQLTable(ref, getFreeAlias());
				
				if (jField.getField().isNotNull()) {
					JoinTable jtable = new JoinTable(JoinTable.Type.INNER, cur, leaf.findField(jField.getField().getJdbcName()), getFreeAlias());
					parent.join(jtable);
				}
				else {
					JoinTable jtable = new JoinTable(JoinTable.Type.LEFT, cur, leaf.findField(jField.getField().getJdbcName()), getFreeAlias());
					parent.join(jtable);
				}
				cur = parent;
			}
		}
		
		return cur.getSelectSQL();
		
	}
	
	public String findByPage(DTO dto) {
		aliasIndex = 0;
		
		SQLTable leaf = new SQLTable(dto, getFreeAlias());
		leaf.orderby(dto.getPrimaryKey(), "ASC").limit("#{OFFSET}, #{ROWS}");
		SQLTable cur = leaf;
		
		for (JavaField jField : dto.getFields()) {
			if (jField.getField().getType() instanceof ComponentType) {
				DTO ref = (DTO) jField.getType();
				SQLTable parent = new SQLTable(ref, getFreeAlias());
				
				if (jField.getField().isNotNull()) {
					JoinTable jtable = new JoinTable(JoinTable.Type.INNER, cur, leaf.findField(jField.getField().getJdbcName()), getFreeAlias());
					parent.join(jtable);
				}
				else {
					JoinTable jtable = new JoinTable(JoinTable.Type.LEFT, cur, leaf.findField(jField.getField().getJdbcName()), getFreeAlias());
					parent.join(jtable);
				}
				cur = parent;
			}
		}
		
		return cur.getSelectSQL();
	}
	
	public String findByCount(DTO dto) {
		aliasIndex = 0;
		
		SQLTable leaf = new SQLTable(dto, getFreeAlias());
		SQLTable cur = leaf;
		
		for (JavaField jField : dto.getFields()) {
			if (jField.getField().getType() instanceof ComponentType) {
				DTO ref = (DTO) jField.getType();
				SQLTable parent = new SQLTable(ref, getFreeAlias());
				
				if (jField.getField().isNotNull()) {
					JoinTable jtable = new JoinTable(JoinTable.Type.INNER, cur, leaf.findField(jField.getField().getJdbcName()), getFreeAlias());
					parent.join(jtable);
				}
				else {
					JoinTable jtable = new JoinTable(JoinTable.Type.LEFT, cur, leaf.findField(jField.getField().getJdbcName()), getFreeAlias());
					parent.join(jtable);
				}
				cur = parent;
			}
		}
		
		String s = cur.getSelectSQL();
		return "SELECT COUNT(*) FROM (" + s + ")";
	}
	
	public String insert(DTO dto) {
		List<JavaField> fs = dto.getFields();
		
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO ");
		sb.append(dto.getName().toUpperCase());
		sb.append("(");
		for (int i=0; i<fs.size(); i++) {
			JavaField f = fs.get(i);
			sb.append(f.getField().getJdbcName());
			if (i != fs.size() - 1) {
				sb.append(", ");
			}
		}
		sb.append(") VALUES(");
		for (int i=0; i<fs.size(); i++) {
			JavaField f = fs.get(i);
			sb.append("#{");
			if (f.getField().getType() instanceof ComponentType) {
				sb.append(f.getType().getPropertyName());
				sb.append(".");
				sb.append( ((DTO) f.getType()).getPrimaryKey().getPropertyName());
			}
			else {
				sb.append(f.getPropertyName());
			}
			sb.append("}");
			if (i != fs.size() - 1) {
				sb.append(", ");
			}
		}
		sb.append(")");
		return sb.toString();
	}
	
	public String update(DTO dto) {
		List<JavaField> fs = dto.getFields();
		for (int i=0; i<fs.size(); i++) {
			JavaField f = fs.get(i);
			if (f.getName().equals("createdtime")) {
				fs.remove(i);
				break ;
			}
		}

		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE ");
		sb.append(dto.getName().toUpperCase());
		sb.append(" SET ");
		for (int i=0; i<fs.size(); i++) {
			JavaField field = fs.get(i);
			if (field.getField().getType() instanceof PrimaryKeyType == false) {
				sb.append(field.getField().getJdbcName());
				sb.append("=#{");
				if (field.getField().getType() instanceof ComponentType) {
					sb.append(field.getType().getPropertyName());
					sb.append(".");
					sb.append(((DTO) field.getType()).getPrimaryKey().getPropertyName());
				}
				else {
					sb.append(field.getPropertyName());
				}
				sb.append("}");
				if (i != fs.size() - 1) {
					sb.append(", ");
				}
			}
		}
		sb.append(" WHERE ");
		sb.append(dto.getPrimaryKey().getField().getJdbcName());
		sb.append("=#{");
		sb.append(dto.getPrimaryKey().getPropertyName());
		sb.append("}");
		return sb.toString();
	}
	
	public String delete(DTO dto) {
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM ");
		sb.append(dto.getName().toUpperCase());
		sb.append(" WHERE ");
		sb.append(dto.getPrimaryKey().getField().getJdbcName());
		sb.append("=#{");
		sb.append(dto.getPrimaryKey().getPropertyName());
		sb.append("}");
		return sb.toString();
	}
	
}
