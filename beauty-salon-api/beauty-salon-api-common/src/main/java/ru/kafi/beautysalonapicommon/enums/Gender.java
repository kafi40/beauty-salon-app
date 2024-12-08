package ru.kafi.beautysalonapicommon.enums;

import lombok.Getter;

@Getter
public enum Gender {
    M("male"),
    F("female");

    private final String value;

    Gender(final String value) {
        this.value = value;
    }
}