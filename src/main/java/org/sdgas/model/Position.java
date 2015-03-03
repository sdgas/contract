package org.sdgas.model;

import javax.persistence.*;

/**
 * 职位
 * Created by 120378 on 2014/7/11.
 */

@Entity
public class Position {

    /**
     * 职位编号
     */
    private int positionId;

    /**
     * 职位
     */
    private String positionName;

    @Id
    @GeneratedValue
    @Column(length = 4)
    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    @Column(length = 15, nullable = false)
    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }
}
