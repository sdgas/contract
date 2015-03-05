package org.sdgas.service;

import org.sdgas.base.DAO;
import org.sdgas.model.Position;

import java.util.List;

/**
 * Created by 120378 on 2015-03-05.
 */
public interface PositionService extends DAO {

    /**
     * 查找全部职位
     *
     * @return 全部职位
     */
    public List<Position> findAllPosition();

    /**
     * 根据职位名称查找
     *
     * @param position 职位名称
     * @return 职位
     */
    public Position findPositionByName(String position);
}
