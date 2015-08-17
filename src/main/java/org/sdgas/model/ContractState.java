package org.sdgas.model;

/**
 * Created by HEWI2 on 1/22/14.
 */
public enum ContractState {

    /**
     * 完成
     */
    CONPLETED {
        public int getContractState() {
            return 0;
        }
    },
    /**
     * 作废
     */
    CANCELED {
        public int getContractState() {
            return 1;
        }
    },
    /**
     * 注销
     */
    DELETED {
        public int getContractState() {
            return 2;
        }
    },
    /**
     * 会签
     */
    COUNTERSIGN {
        public int getContractState() {
            return 3;
        }
    };

    public abstract int getContractState();

    @Override
    public String toString() {
        switch (this) {
            case CONPLETED:
                return "完成";
            case CANCELED:
                return "作废";
            case DELETED:
                return "注销";
            case COUNTERSIGN:
                return "会签";
            default:
                return "Error";
        }
    }
}
