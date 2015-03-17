package org.sdgas.model;

/**
 * Created by 120378 on 2015-03-17.
 */
public enum ReceiveOrPay {

    /**
     * 收款
     */
    RECEIVE {
        public int getState() {
            return 0;
        }
    },
    /**
     * 付款
     */
    PAY {
        public int getState() {
            return 1;
        }
    };

    public abstract int getState();

    @Override
    public String toString() {
        switch (this) {
            case RECEIVE:
                return "收款";
            case PAY:
                return "付款";
            default:
                return "ERR06";
        }
    }
}
