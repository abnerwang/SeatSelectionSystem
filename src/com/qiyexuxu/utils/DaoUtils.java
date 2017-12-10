package com.qiyexuxu.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DaoUtils {
    /**
     * 将一个 JavaBean 转换为 Map
     *
     * @param bean 要转换的 JavaBean
     * @param <T>  JavaBean 的类型
     * @return 返回转换后的 Map
     * @throws IntrospectionException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static <T> Map<String, Object> bean2Map(T bean) throws IntrospectionException,
            InvocationTargetException, IllegalAccessException {
        // 新建一个存放 JavaBean 中信息以供返回的 Map
        Map<String, Object> propertyMap = new HashMap<>();

        // 将泛型类的属性内省出来
        // 排除从 Object 继承而来的 class 属性
        BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass(), Object.class);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (int i = 0; i < propertyDescriptors.length; i++) {
            String propertyName = propertyDescriptors[i].getName();
            Method readMethod = propertyDescriptors[i].getReadMethod();
            Object propertyValue = readMethod.invoke(bean, null);

            // 假如用户提交过来的是字符串，就需要对这个字符串进行处理（删除空白字符）
            if (propertyValue instanceof String) {
                propertyValue = ((String) propertyValue).trim();

                // 如果删除空白字符后为空，则还需要进一步处理
                if ("".equals(propertyValue)) {
                    propertyValue = null;
                }

                // 将处理后的属性值放回到 JavaBean 的对象中供以后使用
                Method writeMethod = propertyDescriptors[i].getWriteMethod();
                writeMethod.invoke(bean, propertyValue);
            }

            // 将 JavaBean 中的信息放到 propertyMap 中
            propertyMap.put(propertyName, propertyValue);
        }

        return propertyMap;
    }

    /**
     * 将 Map 类型的值存入数据库对应的表中
     *
     * @param map       要存入的 Map 类型
     * @param tableName 数据库中将要写入数据的表名
     * @return 返回存入成功与否
     */
    public static boolean insert2DBByMap(Map<String, Object> map, String tableName) {
        // 要通过 StringBuilder 构造插入语句
        StringBuilder keyInsert = new StringBuilder();
        StringBuilder valueInsert = new StringBuilder();

        // 将 map 中的数据取出来放到对应的 keyInsert 和 valueInsert 中
        Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> entry = iterator.next();
            keyInsert.append(entry.getKey()).append(',');

            if (entry.getValue() != null) {
                valueInsert.append("'").append(entry.getValue()).append("'").append(',');
            } else {   //如果key对应的值为null，则不应加在null两端加单引号，否则sql语句是错误的
                valueInsert.append(entry.getValue()).append(',');
            }
        }
        // 去除最后一个 ',' 号
        keyInsert.deleteCharAt(keyInsert.length() - 1);
        valueInsert.deleteCharAt(valueInsert.length() - 1);

        // 构造插入语句
        StringBuilder sql = new StringBuilder("INSERT INTO ");
        sql.append(tableName).append('(');
        sql.append(keyInsert).append(')');
        sql.append(" VALUES ").append('(');
        sql.append(valueInsert).append(')');

        // 连接到数据库
        Connection conn = DBUtils.getConnection();

        // 使用 PreparedStatement 防止注入式 SQL 攻击
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(sql.toString());

            // SQL 执行后影响的行数为 0 则插入失败
            int effectedRows = preparedStatement.executeUpdate();
            if (effectedRows < 1) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {    // 执行完一次 SQL 后关闭连接
            DBUtils.close(conn, preparedStatement);
        }

        return true;
    }

    /**
     * 将数据库查询结果转换为 JavaBean 的集合
     *
     * @param tClass    JavaBean 的类型
     * @param resultSet 数据库查询得到的结果
     * @param <T>       返回的泛型（JavaBean）的类型
     * @return 返回一个从数据库查询结果中封装好的 JavaBean
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws SQLException
     */
    public static <T> ArrayList<T> autoBean(Class<T> tClass, ResultSet resultSet) throws IllegalAccessException,
            InstantiationException, SQLException, InvocationTargetException {
        T bean = null;
        ArrayList<T> list = new ArrayList<>();

        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        while (resultSet.next()) {
            bean = tClass.newInstance();
            for (int i = 1; i <= columnCount; i++) {
                String propertyName = metaData.getColumnName(i);
                Object propertyValue = resultSet.getObject(i);
                BeanUtils.setProperty(bean, propertyName, propertyValue);
            }
            list.add(bean);
        }

        return list;
    }
}
