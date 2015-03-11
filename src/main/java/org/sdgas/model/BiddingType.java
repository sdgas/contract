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
     * 其他
     */
    OTHERS {
        public int getStatus() {
            return 4;
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
            case OTHERS:
                return "其他";
            default:
                return "ERR03";
        }
    }
}
