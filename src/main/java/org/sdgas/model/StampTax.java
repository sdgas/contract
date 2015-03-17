package org.sdgas.model;

/**
 * Created by 120378 on 2015-03-17.
 */
public enum StampTax {

    /**
     * 已购买
     */
    PURCHASED {
        public int getState() {
            return 1;
        }
    },
    /**
     * 未购买
     */
    NOT_PURCHASED {
        public int getState() {
            return 0;
        }
    },
    /**
     * 无须购买
     */
    NO_NEED {
        public int getState() {
            return 2;
        }
    };

    public abstract int getState();

    @Override
    public String toString() {
        switch (this) {
            case PURCHASED:
                return "已购买";
            case NOT_PURCHASED:
                return "未购买";
            case NO_NEED:
                return "无须购买";
            default:
                return "ERR05";
        }
    }
}
