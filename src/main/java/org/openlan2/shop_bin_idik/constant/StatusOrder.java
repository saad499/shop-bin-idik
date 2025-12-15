package org.openlan2.shop_bin_idik.constant;

public enum StatusOrder {
    EN_TRAITEMENT,
    PREPAREE,
    EXPEDIEE,
    LIVREE;

    public StatusOrder getNext() {
        switch (this) {
            case EN_TRAITEMENT:
                return PREPAREE;
            case PREPAREE:
                return EXPEDIEE;
            case EXPEDIEE:
                return LIVREE;
            case LIVREE:
                return null; // Final status
            default:
                return null;
        }
    }

    public boolean isFinal() {
        return this == LIVREE;
    }
}
