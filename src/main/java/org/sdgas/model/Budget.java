package org.sdgas.model;

/**
 * Created by 120378 on 2015-03-04.
 */
public enum Budget {

    /**
     * 预算内
     */
    IN {
        public int getStatus() {
            return 0;
        }
    },
    /**
     * 预算外
     */
    OUT {
        public int getStatus() {
            return 1;
        }
    };

    public abstract int getStatus();

    @Override
    public String toString() {
        switch (this) {
            case IN:
                return "预算内";
            case OUT:
                return "预算外";
            default:
                return "ERR02";
        }
    }
}
