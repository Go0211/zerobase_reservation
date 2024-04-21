package com.zerobase.zerobase_reservation_project.type;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Partner {
    YES("YES"),
    NO("NO"),
    NOT_MANAGER("NOT_MANAGER");

    private final String partnerText;

    Partner(String partnerText) {
        this.partnerText = partnerText;
    }

    public static Partner fromString(String partnerText) {
        return Partner.valueOf(partnerText.toUpperCase());
    }

    @JsonValue
    public String getText() {
        return partnerText;
    }
}
