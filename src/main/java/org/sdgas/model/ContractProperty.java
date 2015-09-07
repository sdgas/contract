package org.sdgas.model;

/**
 * Created by 120378 on 2015-03-03.
 */
public enum ContractProperty {

    /**
     * 新签
     */
    NEW {
        public int getStatus() {
            return 0;
        }
    },
    /**
     * 续签
     */
    RENEW {
        public int getStatus() {
            return 1;
        }
    },
    /**
     * 改签
     */
    CHANGE {
        public int getStatus() {
            return 2;
        }
    },
    /**
     * 其他
     */
    OTHER {
        public int getStatus() {
            return 3;
        }
    },
    /**
     * 注销
     */
    CANCLE {
        public int getStatus() {
            return 4;
        }
    };

    public abstract int getStatus();

    @Override
    public String toString() {
        switch (this) {
            case NEW:
                return "新签";
            case RENEW:
                return "续签";
            case CHANGE:
                return "改签";
            case CANCLE:
                return "注销";
            default:
                return "ERR01";
        }
    }
}
