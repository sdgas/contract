package org.sdgas.model;

/**
 * Created by HEWI2 on 1/22/14.
 */
public enum NewsState {

    NOT_DEAL {
        public int getNewsState() {
            return 0;
        }
    },

    DEAL {
        public int getNewsState() {
            return 1;
        }
    };

    public abstract int getNewsState();

    @Override
    public String toString() {
        switch (this) {
            case DEAL:
                return "已回复";
            case NOT_DEAL:
                return "没回复";
            default:
                return "Error";
        }
    }
}
