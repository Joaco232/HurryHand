package com.hurryhand.backend.enums;

public enum SortingDirection {

    ASC("ASC"),
    DESC("DESC");


    private final String value;

    SortingDirection(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
