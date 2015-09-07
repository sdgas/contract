package org.sdgas.model;

/**
 * Created by 120378 on 2015-03-11.
 */
public enum BiddingType {

    /**
     * 公开招标
     */
    OPEN {
        public int getStatus() {
            return 0;
        }
    },
    /**
     * 依法邀请招标
     */
    LAW_INVITE {
        public int getStatus() {
            return 1;
        }
    },
    /**
     * 内部邀请招标
     */
    IN_INVITE {
        public int getStatus() {
            return 2;
        }
    },
    /**
     * 直接发包
     */
    DIRECT {
        public int getStatus() {
            return 3;
        }
    },
    /**
     * 询价比价
     */
    COMPARE {
        public int getStatus() {
            return 4;
        }
    },
    /**
     * 其他
     */
    OTHERS {
        public int getStatus() {
            return 5;
        }
    },
    /**
     * 无
     */
    NO {
        public int getStatus() {
            return -1;
        }
    };

    public abstract int getStatus();

    @Override
    public String toString() {
        switch (this) {
            case OPEN:
                return "公开招标";
            case LAW_INVITE:
                return "依法邀请招标";
            case IN_INVITE:
                return "内部邀请招标";
            case DIRECT:
                return "直接发包";
            case COMPARE:
                return "询价比价";
            case OTHERS:
                return "其他";
            case NO:
                return "无";
            default:
                return "ERR03";
        }
    }
}
