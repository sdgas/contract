package org.sdgas.model;

/**
 * Created by 120378 on 2015-03-24.
 */
public enum SettleAccount {

    /**
     * 超结算，已审核
     */
    Y {
        public int getStatus() {
            return 2;
        }
    },
    /**
     * 超结算，未审核
     */
    Y_N {
        public int getStatus() {
            return 1;
        }
    },
    /**
     * 没超结算
     */
    N {
        public int getStatus() {
            return 0;
        }
    };

    public abstract int getStatus();

    @Override
    public String toString() {
        switch (this) {
            case Y:
                return "超结算，已审核";
            case Y_N:
                return "超结算，未审核";
            case N:
                return "没超结算";
            default:
                return "ERR07";
        }
    }
}
