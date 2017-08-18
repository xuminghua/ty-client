package com.songouhe.base.dao.impl;

import com.alibaba.druid.pool.DruidDataSource;
import com.mysql.jdbc.AbandonedConnectionCleanupThread;
import com.songouhe.base.dao.BaseDao;
import com.songouhe.base.dao.DaoSysException;
import com.songouhe.base.dao.entity.RecordWithTotalCount;
import com.songouhe.base.dao.tool.DataBaseTypeEnum;
import com.songouhe.base.dao.tool.DatabaseContextHolder;
import com.songouhe.base.util.service.ToolUtil;
import flexjson.JSONSerializer;
import flexjson.transformer.BasicDateTransformer;
import flexjson.transformer.DateTransformer;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.sql.Date;
import java.util.*;

/**
 * @author zhanghao
 * 2015/12/21
 *
 * */
@Repository("baseDao")
public class BaseDaoImpl implements BaseDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private boolean autoCommit;

	private final static Logger logger = LoggerFactory.getLogger(BaseDaoImpl.class);


	public boolean isAutoCommit() {
		return autoCommit;
	}

	public void setAutoCommit(boolean autoCommit) {
		this.autoCommit = autoCommit;
	}

//=======================================add=================================================
	/**
	 * sql = "insert into user (name,age) values(?,?)"
	 * */
	public void addBySql(String sql,final Object[] params) {
		//--------------methods01------------------------------------//
		//jdbcTemplate.execute(sql);
		//-----------------or methods02------------------------------//
		jdbcTemplate.update(sql,
				new PreparedStatementSetter() {
					public void setValues(PreparedStatement ps)
							throws SQLException {
					}
				});
	}
	//-------------------------------------创建部分--------------------------------------------------------------------------
	/**
	 * 在数据库里新建一条记录
	 * @param obj_array
	 *      描述记录的对象,每个对象必须是数据库中某张表对应的JavaBean对象
	 * @return
	 *      新建成功返回真，否则返回假
	 * @throws Exception
	 */
	public boolean createByObj(Object[] obj_array) throws DaoSysException{
		// 获取数据库连接
		DataSource dataSource = jdbcTemplate.getDataSource();
		Connection dbCon = DataSourceUtils.getConnection(dataSource);
		String tableName = "";
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			dbCon.setAutoCommit(this.isAutoCommit());
			for(Object obj: obj_array){
				// 构建sql语句
				map = getObjectFieldValue(obj);
				if(map.size() == 0){
					logger.error("error","get obj fields fail!");
					continue; }
				tableName = obj.getClass().getName();
				tableName = tableName.substring(tableName.lastIndexOf(".") + 1);
				StringBuilder sb = new StringBuilder("insert into " + tableName);
				addCreateString(sb, map);
//            PreparedStatement  stmt = DBConnectionManager.getConnection()
//                    .prepareStatement(sb.toString());

				Object[] sqlValues = getSqlValue( map,false);
				int count = jdbcTemplate.update(sb.toString(),sqlValues);
				if(count>0){
					printLoggerMsg("info", "insert", tableName, null, map);
				}
				else{
					String errorMsg = printLoggerMsg("error", "insert", tableName, null, map);
					throw new Exception(errorMsg);
				}
			}
			dbCon.commit();
			return true;

		} catch (Exception e) {
			String errorMsg = printLoggerMsg("error", "insert", tableName, e.getMessage(), map);
			throw new DaoSysException("DaoSysException - BaseDaoImpl error :",e);
		}
	}
	/**
	 * 更新指定表的记录
	 * @param obj
	 *      用来确定表的Java对象
	 * @param map
	 *      要设置的字段值，key：字段（字符串）,value：值
	 * @param condition
	 *      筛选条件
	 * @return
	 */
	public boolean update(Object obj,Map<Object,Object> map,Map<Object, Object>condition) throws Exception {

		//组建sql
		String tableName = obj.getClass().getName();
		tableName = tableName.substring(tableName.lastIndexOf(".") + 1);
		StringBuilder sb = new StringBuilder("update " + tableName );
		addSetString(sb,map);
		addConditionString(sb, condition);
		System.out.println(sb.toString());
		try {
			// 获取数据库连接
			DataSource dataSource = jdbcTemplate.getDataSource();
			Connection dbCon = DataSourceUtils.getConnection(dataSource);

			dbCon.setAutoCommit(this.isAutoCommit());
			List<Object> values =  new ArrayList<Object>();
			getValuesToList(values,map);
			getValuesToList(values, condition);
			Object[] valuesArray = values.toArray();

			int count = jdbcTemplate.update(sb.toString(),valuesArray);

			if(count > 0){
				printLoggerMsg("info", "update", tableName, null, map);
				return true;
			}
			else{
				String errorMsg = printLoggerMsg("error", "update", tableName, null, map);
				throw new Exception(errorMsg);
			}
		} catch ( Exception e) {
			printLoggerMsg("error", "update", tableName, e.getMessage(), map);
			throw new DaoSysException("DaoSysException - BaseDaoImpl error :",e);
		}

	}

//=======================================update end=============================================
//=======================================delete=================================================
	/**
	 * sql = "delete user where id =? and name = ?;"
	 * */
	public void deleteBySql(String sql,final Object[] params) {
		//----------just like update---------------//
		jdbcTemplate.update(sql, params);
	}
	/**
	 * 在指定表中删除给定ID的记录
	 * @param obj
	 *      表示要删除的表
	 * @param condition
	 *      删除的条件 key:字段(一般为字符串) value:值
	 * @return
	 */
	public boolean delete(Object obj,Map<Object,Object> condition) throws Exception{
		//构建sql语句
		String tableName = obj.getClass().getName();
		tableName = tableName.substring(tableName.lastIndexOf(".") + 1);
		StringBuilder sb = new StringBuilder("delete from "+tableName.toLowerCase());
		addConditionString(sb, condition);
		try {
			DataSource dataSource = jdbcTemplate.getDataSource();
			Connection dbCon = DataSourceUtils.getConnection(dataSource);
			dbCon.setAutoCommit(this.isAutoCommit());
			Object[] sqlValues = getSqlValue( condition,false);
			int count = jdbcTemplate.update(sb.toString(), sqlValues);

			if(count > 0){
				String errorMsg = printLoggerMsg("info", "delete", tableName, null, condition);
				return true;
			}
			else{
				String errorMsg = printLoggerMsg("error", "delete", tableName, null, condition);
				throw new Exception(errorMsg);
			}
		} catch (Exception e) {
			printLoggerMsg("error", "delete", tableName, e.getMessage(), condition);
			throw new DaoSysException("DaoSysException - BaseDaoImpl error :",e);
		}
	}

//=======================================delete end=============================================
//=======================================update=================================================
	/**
	 * sql = “update table_name set name=?,age=?”
	 * */
	public void updateBySql(String sql,final Object[] params) {
		//--------------methods01------------------------------------//
		jdbcTemplate.update(sql,params);
		//-----------------or methods02------------------------------//
		//jdbcTemplate.update(sql, params);
	}
//=======================================update end=============================================
//=======================================search=================================================
	/**
	 * RowMapper接口封装返回集合，可自定义
	 * */
	public List getQueryByObj(Object obj) throws DaoSysException {
		try{
			// 构建sql语句
			String tableName = obj.getClass().getName();
			tableName = tableName.substring(tableName.lastIndexOf(".") + 1).toLowerCase();
			String sql = "select * from " + tableName;
			return jdbcTemplate.query(sql,
					new BeanPropertyRowMapper(obj.getClass()));

		}catch(Exception e){
			throw new DaoSysException("DaoSysException - BaseDaoImpl error :",e);
		}
	}

	/**
	 * 对指定的表执行指定的sql查询语句
	 * @param obj
	 *          以该表对应的JavaBean对象决定表
	 * @param query
	 *          要对该表执行的sql语句
	 * @return
	 * @throws Exception
	 */
	public List<Object> getQueryBySqlString(Object obj, String query) throws DaoSysException {
		// TODO Auto-generated method stub
		List<Object> list = null;
		try {
			//执行传递的sql语句
			return jdbcTemplate.query(query,
					new BeanPropertyRowMapper(obj.getClass()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new DaoSysException("DaoSysException - BaseDaoImpl error :",e);

		}

	}

	/**
	 * 按条件对指定表进行查询
	 * @param obj
	 *      以该表对应的JavaBean对象决定表
	 * @param condition
	 *      查询的条件，key：条件        value：值
	 *
	 * @param isFuzzy
	 *      是否支持模糊查询
	 * @return
	 * @throws Exception
	 */
	public List<Object> getQueryByCondition(Object obj, Map<Object, Object> condition, String start, String limit,
											String order, boolean isFuzzy) throws DaoSysException {
		List<Object> list = new ArrayList<Object>();



		try {
			String tableName = obj.getClass().getName();
			tableName = tableName.substring(tableName.lastIndexOf(".") + 1).toLowerCase();
			StringBuilder sb = new StringBuilder("select * from " + tableName);
			PreparedStatement state = null;
			if(isFuzzy){
				addFuzzyConditionString(sb, condition);
			}
			else{
				addConditionString(sb, condition);
			}
			sb.append(" order by ");
			sb.append(order);
			if(limit != "")sb.append(" limit " + start + "," + limit);
			System.out.println(sb.toString());

			Object[] sqlValues = getSqlValue(condition,false);
			return jdbcTemplate.query(sb.toString(),sqlValues,
					new BeanPropertyRowMapper(obj.getClass()));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new DaoSysException("DaoSysException - BaseDaoImpl error :",e);
		}
	}
	public RecordWithTotalCount getQueryAndCountByCondition(Object obj, Map<Object, Object> condition, String start, String limit,
															String order, boolean isFuzzy) throws DaoSysException {
		RecordWithTotalCount result = new RecordWithTotalCount();

		try {
			String tableName = obj.getClass().getName();
			tableName = tableName.substring(tableName.lastIndexOf(".") + 1).toLowerCase();
			StringBuilder sb = new StringBuilder("select ");
			addFieldString(sb, obj.getClass());
			sb.append(" from " + tableName);
			if(isFuzzy){
				addFuzzyConditionString(sb, condition);
			}
			else{
				addConditionString(sb, condition);
			}
			sb.append(" order by ");
			sb.append(order);
			if(limit != "")sb.append(" limit " + start + "," + limit);
			System.out.println(sb.toString());

			Object[] sqlValues = getSqlValue(condition,false);
			result.setItems(jdbcTemplate.query(sb.toString(), sqlValues,
					new BeanPropertyRowMapper(obj.getClass())));
			result.setTotalCount(jdbcTemplate.queryForObject(
					"select FOUND_ROWS()", Integer.class));
			return result;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new DaoSysException("DaoSysException - BaseDaoImpl error :",e);
		}
	}

//=======================================search end=============================================

	public javax.sql.DataSource getDatasource(){
		return jdbcTemplate.getDataSource();
	}

	public void open(String[] inDatabaseTypes) throws DaoSysException {
		for(String databaseType: inDatabaseTypes){
			DatabaseContextHolder.clearDatabaseType();
			DatabaseContextHolder.setDateBaseType(DataBaseTypeEnum.valueOf(databaseType));
			try{
				DataSource ds = jdbcTemplate.getDataSource();
				Connection conn = ds.getConnection();
				DruidDataSource pds = (DruidDataSource) ds;
				conn.close();
				logger.info("Open DataSource '" + databaseType + "', current connections number is " +
						pds.getConnectCount());
			}catch(Exception e){
				throw new DaoSysException("DataSource " + databaseType + " open error :",e);
			}
		}
	}

	public void closeDatasource(String[] inDatabaseTypes) throws DaoSysException{
		for(String databaseType: inDatabaseTypes){
			DatabaseContextHolder.clearDatabaseType();
			DatabaseContextHolder.setDateBaseType(DataBaseTypeEnum.valueOf(databaseType));
			try{
				DataSource ds = jdbcTemplate.getDataSource();
				if(ds != null ){
					DruidDataSource pds = (DruidDataSource) ds;
					pds.close();
					logger.info("close DataSource '" + databaseType + "'");
				}

			}catch(Exception e){
				throw new DaoSysException("DataSource " + databaseType + " close error :",e);
			}
		}
		try {
			AbandonedConnectionCleanupThread.shutdown();
		} catch (InterruptedException e) {
			throw new DaoSysException("AbandonedConnectionCleanupThread shutdown error :",e);
		}

		Enumeration<Driver> drivers = DriverManager.getDrivers();
		while(drivers.hasMoreElements()) {
			try {
				Driver driver = drivers.nextElement();
				DriverManager.deregisterDriver(driver);
			} catch (SQLException e) {
				throw new DaoSysException("DAO destroy driver error :",e);

			}
		}

	}


	//
//
//    //-------------------------------------删除部分----------------------------------------------------------------------------
//    /**
//     * 删除表中所有记录
//     * @param obj
//     *          JavaBean对象，决定要删除的是哪张表
//     * @return
//     *          删除成功返回真，否则返回假
//     */
//    public boolean deleteAll(Object obj) throws Exception{
//        String delete_sql = "delete from "+getTableName(obj);
//        PreparedStatement pstmt;
//        try {
//            pstmt = DBConnectionManager.getConnection().prepareStatement(delete_sql);
//            int count = pstmt.executeUpdate();
//            if(count>0){
//                return true;
//            }
//            else{
//                return false;
//            }
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//            throw e;
//        }
//        finally{
//            DBConnectionManager.closeConnection();
//        }
//
//    }
//

	//
//    //-------------------------------------更新部分----------------------------------------------------------------------------
//
	private List<Object> getValuesToList(List<Object> values,Map<Object, Object> map) {

		for (Iterator<Object> it = map.keySet().iterator(); it.hasNext();) {
			Object value =  map.get(it.next());
			values.add(value);
		}
		return values;
	}
//
//
//
//

	//
	private void addSetString(StringBuilder sb, Map<Object, Object> map) {
		// TODO Auto-generated method stub
		if (!map.isEmpty()) {
			sb.append(" set ");
			for (Iterator<Object> it = map.keySet().iterator(); it.hasNext();) {
				String key = (String) it.next();
				if (null == key) {
					continue;
				}
				sb.append(key+"=?,");
			}
			sb.deleteCharAt(sb.lastIndexOf(","));
		}
	}
//
	/**
	 * 获取JavaBean对象的字段值放到容器中
	 * @param obj
	 *          JavaBean对象
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private Map<Object, Object> getObjectFieldValue(Object obj)
			throws IllegalAccessException, InvocationTargetException {
		Map<Object, Object> map = new HashMap<Object,Object>();

		Field[] fields = obj.getClass().getDeclaredFields();

		Method[] methods = obj.getClass().getMethods();
		if(fields.length == 0)return map;
		// 给字段设置值
		for (int i = 0; i < fields.length; i++) {
			// 用字段的名字构建set方法的名字
			fields[i].setAccessible(true);    //这句话必须要有,否则会抛出异常.
			String fieldName = fields[i].getName();
			if(fieldName.equals("id"))continue;

			String methodName = "get" + fieldName.substring(0, 1).toUpperCase()
					+ fieldName.substring(1);

			// 查找对应的方法
			Method method = null;
			for (int j = 0; j < methods.length; j++) {
				if (methods[j].getName().equals(methodName)) {
					method = methods[j];
					break;
				}
			}
			// 如果存在这个方法
			if (method != null) {
				// 开始执行方法，获取当前的JavaBean对象字段值
				Object value = method.invoke(obj, new Object[] { });
				if(value != null)
					map.put(fieldName, value);
			}
		}
		return map;
	}

	/**
	 * 在语句中根据class c的字段设置sql需要选取的字段
	 * @param sb
	 * @param c
	 */
	private void addFieldString(StringBuilder sb, Class c){
		sb.append("SQL_CALC_FOUND_ROWS ");
		Field[] fields = c.getDeclaredFields();
		if(fields.length == 0){
			sb.append(" * ");
			return;
		}
		int i = 0;
		for(Field f: fields){
			if(i ++ != 0)sb.append(",");
			sb.append(f.getName().toLowerCase());
		}
	}

	/**
	 * 在现有sql创建语句后面追加字段名和占位符
	 * @param sb
	 * @param map
	 */
	private void addCreateString(StringBuilder sb,Map<Object, Object> map) {
		if(map.size() == 0)return;
		// TODO Auto-generated method stub
		sb.append("(");
		int count = 0;
		for (Iterator<Object> it = map.keySet().iterator(); it.hasNext();) {
			String key = (String) it.next();
			if (null == key) {
				continue;
			}
			sb.append(key.toLowerCase() + ",");
			count++;
		}
		sb.deleteCharAt(sb.lastIndexOf(","));
		sb.append(") values(");
		for (int i = 0; i < count; i++) {
			sb.append("?,");
		}
		sb.deleteCharAt(sb.lastIndexOf(","));
		sb.append(")");
		System.out.println(sb.toString());
	}

	/**
	 * 在现有sql查询语句后面追加判断条件
	 * @param sb            where之前的sql
	 * @param condition     条件(key:字段名；value:值)
	 * @return  使用占位符的sql语句
	 */
	private void addConditionString(StringBuilder sb, Map<Object,Object> condition) {
		if (!condition.isEmpty()) {
			sb.append(" where ");
			int i = 0;

			for (Iterator<Object> it = condition.keySet().iterator(); it.hasNext();) {
				String key = (String) it.next();
				if (null == key) {
					continue;
				}
				if (i != 0) {
					sb.append(" and ");
				}
				if (key.indexOf("<") != -1 || key.indexOf(">") != -1
						|| key.indexOf("=") != -1) {
					sb.append(key + "?");
				} else {
					sb.append(key + "=" + "?");
				}

				i++;

			}

		}
	}
	private String addOrder(StringBuilder sb, List<String> orderList){
		if (!orderList.isEmpty()) {
			sb.append(" order by ");
			int i = 0;

			for (String it : orderList) {
				if (null == it) {
					continue;
				}
				if (i != 0) {
					sb.append(" , ");
				}

				sb.append(it);
				i++;
			}
		}
		System.out.println(sb.toString());
		return sb.toString();
	}

	private String addFuzzyConditionString(StringBuilder sb,Map<Object,Object>condition) {
		if (!condition.isEmpty()) {
			sb.append(" where ");
			int i = 0;

			for (Iterator<Object> it = condition.keySet().iterator(); it.hasNext();) {
				String key = (String) it.next();
				if (null == key) {
					continue;
				}
				if (i != 0) {
					sb.append(" or ");
				}
				sb.append(key + " like " +"?");
				i++;
			}

		}
		System.out.println(sb.toString());
		return sb.toString();
	}

	private String printLoggerMsg(String logType, String operation, String tableName, String errorMsg, Object obj){
		String result,strObj = "";
		if(obj != null){
			JSONSerializer serializer = new JSONSerializer().exclude("*.class")
					.transform(new BasicDateTransformer(), Timestamp.class)
					.transform(new DateTransformer("yyyy-MM-dd"), Timestamp.class);
			strObj = serializer.deepSerialize(obj) ;
		}

		if("error".equals(logType)){
			result = "***********" + operation + " 1 record false:table:["
					+ tableName + "],error msg:[" +errorMsg +"],params:[" + strObj +  "]*************";
			logger.error(logType, result);

		}else {
			result = "***********"+ operation + " record :table:[" +
					tableName + "], params:[" + obj+ "]*************";
			logger.info(result);

		}
		return result;
	}

	/**
	 * 设置现有sql语句的占位符的值
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	private Object[] getSqlValue( Map<Object,Object> map,boolean isFuzzy)
			throws SQLException {
		List result = new ArrayList();
		if (!map.isEmpty()) {
			int i = 1;
			for (Iterator<Object> it = map.keySet().iterator(); it.hasNext();) {
				Object value = map.get(it.next());
				if(isFuzzy){
					result.add("%" + value + "%");
				}else{
					result.add( value);
				}
			}

		}
		return result.toArray();
	}
//    private Object[] setSqlValue(PreparedStatement state, List<Object> values)
//            throws SQLException {
//        if (!values.isEmpty()) {
//            int i = 1;
//            for (Iterator<Object> it = values.iterator(); it.hasNext();) {
//                Object value = it.next();
//                state.setObject(i++, value);
//            }
//
//        }
//        return state;
//    }
//
//    private String getTableName(Object obj) {
//        String tablename = obj.getClass().getName();
//        tablename = tablename.substring(tablename.lastIndexOf(".") + 1);
//        return tablename;
//    }
//	// 利用反射构建对象
//
//	private Object createObj(ResultSet rs, Object obj)
//			throws  Exception {
//		// TODO Auto-generated method stub
//		Object object = obj.getClass().newInstance();
//		// 获取字段
//		Field fields[] = obj.getClass().getFields();
//		// 获取方法
//		Method methods[] = obj.getClass().getMethods();
//
//		// 给字段设置值
//		for (int i = 0; i < fields.length; i++) {
//			// 用字段的名字构建set方法的名字
//			String fieldName = fields[i].getName();
//			String methodName = "set" + fieldName.substring(0, 1).toUpperCase()
//					+ fieldName.substring(1);
//
//			// 查找对应的方法
//			Method method = null;
//			for (int j = 0; j < methods.length; j++) {
//				if (methods[j].getName().equals(methodName)) {
//					method = methods[j];
//					break;
//				}
//			}
//
//			// 如果存在这个方法
//			if (method != null) {
//				// 开始执行方法，为当前的JavaBean对象字段设置值
//				Object value = rs.getObject(fieldName);
//				method.invoke(object, new Object[] { value });
//
//			}
//		}
//		return object;
//	}


}
