package org.sdgas.service.impl;

import org.sdgas.base.DaoSupport;
import org.sdgas.model.Position;
import org.sdgas.service.PositionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 120378 on 2015-03-05.
 */
@Service
@Transactional
public class PositionServiceImpl extends DaoSupport<Position> implements PositionService {


    @Override
    public List<Position> findAllPosition() {
        return this.find(Position.class);
    }

    @Override
    public Position findPositionById(int positionId) {
        return this.find(Position.class, positionId);
    }
}
