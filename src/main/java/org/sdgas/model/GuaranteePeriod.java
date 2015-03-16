package org.sdgas.model;

/**
 * Created by 120378 on 2015-03-16.
 */
public enum GuaranteePeriod {

    /**
     * 半年
     */
    HALF {
        public int getStatus() {
            return 0;
        }
    },
    /**
     * 1年
     */
    ONE {
        public int getStatus() {
            return 1;
        }
    },
    /**
     * 2年
     */
    TWO {
        public int getStatus() {
            return 2;
        }
    },
    /**
     * 3年
     */
    THREE {
        public int getStatus() {
            return 3;
        }
    },
    /**
     * 4年
     */
    FOUR {
        public int getStatus() {
            return 4;
        }
    },
    /**
     * 5年
     */
    FIVE {
        public int getStatus() {
            return 5;
        }
    },
    /**
     * 6年
     */
    SIX {
        public int getStatus() {
            return 6;
        }
    },
    /**
     * 7年
     */
    SEVEN {
        public int getStatus() {
            return 7;
        }
    },
    /**
     * 8年
     */
    EIGHT {
        public int getStatus() {
            return 8;
        }
    },
    /**
     * 9年
     */
    NINE {
        public int getStatus() {
            return 9;
        }
    },
    /**
     * 10年
     */
    TEN {
        public int getStatus() {
            return 10;
        }
    },
    /**
     * 终身
     */
    LIFELONG {
        public int getStatus() {
            return 11;
        }
    };

    public abstract int getStatus();

    @Override
    public String toString() {
        switch (this) {
            case HALF:
                return "半年";
            case ONE:
                return "1年";
            case TWO:
                return "2年";
            case THREE:
                return "3年";
            case FOUR:
                return "4年";
            case FIVE:
                return "5年";
            case SIX:
                return "6年";
            case SEVEN:
                return "7年";
            case EIGHT:
                return "8年";
            case NINE:
                return "9年";
            case TEN:
                return "10年";
            case LIFELONG:
                return "终身";
            default:
                return "ERR04";

        }
    }
}
