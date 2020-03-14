package net.xway.code.generate.impl.java.model.dao.sql;

import java.util.ArrayList;
import java.util.List;

import net.xway.code.generate.impl.java.model.JavaField;
import net.xway.code.generate.impl.java.model.dto.DTO;

public class SQLTable extends AbstractTable {


	public static final String SELECT = "SELECT ";
	public static final String FROM = " FROM ";
	public static final String WHERE = " WHERE";
	public static final String ORDERBY = " ORDER BY";
	public static final String LIMIT = " LIMIT ";

	protected JoinTable join = null;
	protected List<Where> wheres = new ArrayList<>();
	protected List<OrderBy> orders = new ArrayList<>();
	protected String limit;
	
	public SQLTable(DTO dto, String tableAlias) {
		super(dto.getComponent().getProject().getTablePrefix() + dto.getName().toUpperCase() + dto.getComponent().getProject().getTableSuffix(), tableAlias.toUpperCase());
		fields = getSQLField(dto.getFields());
	}
	
	public JoinTable getJoinTable() {
		return join;
	}

	public SQLTable join(JoinTable table) {
		this.join = table;
		return this;
	}
	
	public SQLTable where(JavaField field, String op, String val) {
		SQLField sField = findField(field);
		wheres.add(new Where(sField, op, val));
		
		return this;
	}
	
	public SQLTable orderby(JavaField field, String order) {
		SQLField sField = findField(field);
		orders.add(new OrderBy(sField, order));
		return this;
	}
	
	public SQLTable limit(String limit) {
		this.limit = limit;
		return this;
	}

	public List<SQLField> getAllSQLFields() {
		List<SQLField> fs = new ArrayList<>();
		
		fs.addAll(fields);
		if (join != null) {
			fs.addAll(join.getAllSQLFields());
		}
		
		return fs;
	}

	public SQLField getPrimaryKey() {
		for (SQLField f : fields) {
			if (f.isPrimaryKey()) {
				return f;
			}
		}
		return null;
	}
	
	public SQLField findField(String name) {
		name = name.toUpperCase();
		List<SQLField> fs = getAllSQLFields();
		for (SQLField f : fs) {
			if (f.getName().equals(name)) {
				return f;
			}
		}
		return null;
	}
	
	public SQLField findField(JavaField jField) {
		List<SQLField> fs = getAllSQLFields();
		for (SQLField f : fs) {
			if (f.getJavaField() == jField) {
				return f;
			}
		}
		return null;
	}

	public String getSelectSQL() {
		StringBuffer sb = new StringBuffer();
		sb.append(SELECT);
		sb.append(getSelectFieldsSQL());
		
		if (join != null) {
			sb.append(COMMA);
			sb.append(join.getSelectFieldsSQL());
			sb.append(FROM);
			sb.append(" ( ");
			sb.append(join.getChildTable().getSelectSQL());
			sb.append(" ) ");
			sb.append(join.getTableAlias());
			sb.append(SPACE);
			sb.append(join.getJoinSQL());
			sb.append(SPACE);
			sb.append(getTableName());
			sb.append(SPACE);
			sb.append(getTableAlias());
			sb.append(" ON ");
			sb.append(join.getTableAlias());
			sb.append(DOT);
			sb.append(join.findFieldByChildField().getName());
			sb.append(" = ");
			sb.append(getTableAlias());
			sb.append(DOT);
			sb.append(getPrimaryKey().getName());
		}
		else {
			sb.append(FROM);
			sb.append(getTableName());
			sb.append(SPACE);
			sb.append(getTableAlias());
			if (wheres.isEmpty() == false) {
				sb.append(WHERE);
				for (Where w : wheres) {
					sb.append(SPACE);
					sb.append(getTableAlias());
					sb.append(".");
					sb.append(w.getField().getName());
					sb.append(w.getOp());
					sb.append(w.getVal());
				}
			}
			if (orders.isEmpty() == false) {
				sb.append(ORDERBY);
				for (OrderBy orderby : orders) {
					sb.append(SPACE);
					sb.append(getTableAlias());
					sb.append(".");
					sb.append(orderby.getField().getName());
					sb.append(SPACE);
					sb.append(orderby.getOrder());
				}
			}
			if (limit != null && limit.length() > 0) {
				sb.append(LIMIT);
				sb.append(limit);
			}
		}
		
		return sb.toString();
	}
	
	private List<SQLField> getSQLField(List<JavaField> jFields) {
		ArrayList<SQLField> fields = new ArrayList<>(jFields.size());
		jFields.forEach(e-> {
			String name = e.getField().getJdbcName();
			SQLField f = new SQLField(e, this, name);
			fields.add(f);
		});
		return fields;
	}

}
