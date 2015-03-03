package org.sdgas.base;

import java.util.List;
import java.util.Map;


public interface DAO {

    /**
     * 保存实体
     *
     * @param entity the Object to be same
     */
    public void save(Object entity);

    /**
     * 更新实体
     *
     * @param entity to be update
     */
    public void update(Object entity);

    /**
     * 清队缓存
     */
    public void clear();

    /**
     * 删除实体
     *
     * @param <T>      实体类
     * @param entityId 实体ID(对应DB的主键)
     */
    public <T> void delete(Class<T> entityClass, Object entityId);

    /**
     * 删除实体
     *
     * @param entity 实体类
     */
    public void delete(Object entity);

    /**
     * 获取实体
     *
     * @param <T>
     * @param entityClass 实体类
     * @param entityId    实体ID(对应DB的主键)
     * @return
     */
    public <T> T find(Class<T> entityClass, Object entityId);

    /**
     * 获取实体
     *
     * @param <T>
     * @param entityClass 实体类
     * @return
     */
    public <T> List find(Class<T> entityClass);

    /**
     * 根据用户的要求查找相关数据
     *
     * @param entityClass 类名
     * @param params      属性名值对
     * @return 返回一个list集合
     */
    public <T> List findByFields(Class<T> entityClass, Map<String, Object> params);

    /**
     * 根据用户的要求查找相关数据
     *
     * @param entityClass 类名
     * @param params      属性名值对
     * @return 唯一一个实体
     */
    public <T> Object findSpecialObject(Class<T> entityClass, Map<String, Object> params);

    /**
     * 根据用户的要求模糊查找相关数据
     *
     * @param entityClass 类名
     * @param params      属性名值对
     * @return 唯一一个实体
     */
    public <T> List findByFuzzy(Class<T> entityClass, Map<String, Object> params);

    /**
     * 通用分页功能
     *
     * @param <T>
     * @param entityClass
     * @param fisrtIndex  索引位置
     * @param maxResult   每页的对象个数
     * @param wherejpql   where的查询语句
     * @param queryParams 语句的对象设置
     * @param orderby     排序
     * @return
     */
    public <T> QueryResult<T> getScrollData(Class<T> entityClass, int fisrtIndex, int maxResult, String wherejpql,
                                            Object[] queryParams, Map<String, String> orderby);

    public <T> QueryResult<T> getScrollData(Class<T> entityClass);

    public <T> QueryResult<T> getScrollData(Class<T> entityClass, int fisrtIndex, int maxResult);

    public <T> QueryResult<T> getScrollData(Class<T> entityClass, int fisrtIndex, int maxResult, Map<String, String> orderby);
    public <T> QueryResult<T> getScrollData(Class<T> entityClass, int fisrtIndex, int maxResult, String wherejpql, Object[] queryParams);

}
