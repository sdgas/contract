package org.sdgas.base;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class DaoSupport<T> implements DAO {

    @PersistenceContext
    public EntityManager em;

    @Override
    public void save(Object entity) {
        em.persist(entity);
    }

    public void update(Object entity) {
        em.merge(entity);
    }

    public void clear() {
        em.clear();
    }

    public <T> void delete(Class<T> entityClass, Object entityId) {
        em.remove(em.getReference(entityClass, entityId));
    }

    public void delete(Object entity) {
        em.remove(em.contains(entity) ? entity : em.merge(entity));
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public <T> T find(Class<T> entityClass, Object entityId) {
        return em.find(entityClass, entityId);
    }

    @Override
    public <T> List find(Class<T> entityClass) {
        String enetityName = getEntityName(entityClass);
        Query query = em.createQuery("from " + enetityName);
        return query.getResultList();
    }

    public <T> List findByFuzzy(Class<T> entityClass,
                                Map<String, Object> params) {

        List<Object> values = new ArrayList<Object>();

        String jpql = buildFuzzyJpql(params, values);
        String enetityName = getEntityName(entityClass);

        Query query = em.createQuery("select o from " + enetityName + " o " + jpql);
        for (int i = 1; i <= values.size(); i++) {
            query.setParameter(i, "%" + values.get(i - 1) + "%");
        }
        return query.getResultList();
    }


    public <T> List findByFields(Class<T> entityClass,
                                 Map<String, Object> params) {

        List<Object> values = new ArrayList<Object>();

        String jpql = buildWhereJpql(params, values);
        String enetityName = getEntityName(entityClass);

        Query query = em.createQuery("select o from " + enetityName + " o " + jpql);
        for (int i = 1; i <= values.size(); i++) {
            query.setParameter(i, values.get(i - 1));
        }
        return query.getResultList();
    }

    public <T> Object findSpecialObject(Class<T> entityClass,
                                        Map<String, Object> params) {
        List objects = findByFields(entityClass, params);
        return objects != null && objects.size() > 0 ? objects.get(0) : null;
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public <T> QueryResult<T> getScrollData(Class<T> entityClass, int fisrtIndex,
                                            int maxResult, String wherejpql, Object[] queryParams, Map<String, String> orderby) {

        String enetityName = getEntityName(entityClass);
        QueryResult<T> qr = new QueryResult<T>();

        Query query = em.createQuery("select count(o) from " + enetityName + " o "
                + (wherejpql == null ? "" : " where " + wherejpql));
        setQueryParams(query, queryParams);
        qr.setTotalResult((Long) query.getSingleResult());

        query = em.createQuery("select o from " + enetityName + " o " +
                (wherejpql == null ? "" : " where " + wherejpql) + buildOrderby(orderby));
        setQueryParams(query, queryParams);
        if (fisrtIndex != -1 && fisrtIndex < qr.getTotalResult()) {
            query.setFirstResult(fisrtIndex);
        }
        if (maxResult != -1) {
            query.setMaxResults(maxResult);
        }
        qr.setResultList(query.getResultList());
        return qr;
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public <T> QueryResult<T> getScrollData(Class<T> entityClass, int fisrtIndex,
                                            int maxResult, Map<String, String> orderby) {
        return getScrollData(entityClass, fisrtIndex, maxResult, null, null, orderby);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public QueryResult getScrollData(Class entityClass, int fisrtIndex,
                                     int maxResult, String wherejpql, Object[] queryParams) {
        return getScrollData(entityClass, fisrtIndex, maxResult, wherejpql, queryParams, null);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public QueryResult getScrollData(Class entityClass, int fisrtIndex,
                                     int maxResult) {
        return getScrollData(entityClass, fisrtIndex, maxResult, null, null, null);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public QueryResult getScrollData(Class entityClass) {
        return getScrollData(entityClass, -1, -1, null, null, null);
    }

    /**
     * 给query设置相关参数
     */
    protected void setQueryParams(Query query, Object[] queryParams) {
        if (queryParams != null && queryParams.length > 0) {
            for (int i = 0; i < queryParams.length; i++) {
                query.setParameter(i + 1, queryParams[i]);
            }
        }
    }

    /**
     * 获取实体名称
     */
    protected <T> String getEntityName(Class<T> entityClass) {
        String enetityName = entityClass.getName();
        Entity entity = entityClass.getAnnotation(Entity.class);
        if (entity.name() != null && !"".equals(entity.name())) {
            enetityName = entity.name();
        }
        return enetityName;
    }

    /**
     * 组装order by 语句
     */
    protected String buildOrderby(Map<String, String> orderby) {
        StringBuilder sb = new StringBuilder("");
        if (orderby != null && orderby.size() > 0) {
            sb.append(" order by ");
            for (String key : orderby.keySet()) {
                sb.append(" o.").append(key).append(" ").append(orderby.get(key)).append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    private String buildWhereJpql(Map<String, Object> params, List<Object> values) {

        if (params == null || params.size() == 0)
            return "";
        StringBuilder whereJpql = new StringBuilder("where ");
        int count = 1;
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            whereJpql.append("o.")
                    .append(entry.getKey()).append(" = ?").append(count++)
                    .append(" and ");

            values.add(entry.getValue());
        }
        return whereJpql.delete(whereJpql.lastIndexOf("and"), whereJpql.length()).toString();
    }

    private String buildFuzzyJpql(Map<String, Object> params, List<Object> values) {

        if (params == null || params.size() == 0)
            return "";
        StringBuilder whereJpql = new StringBuilder("where ");
        int count = 1;
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            whereJpql.append("o.")
                    .append(entry.getKey()).append(" like ?").append(count++)
                    .append(" or ");

            values.add(entry.getValue());
        }
        return whereJpql.delete(whereJpql.lastIndexOf("or"), whereJpql.length()).toString();
    }
}
