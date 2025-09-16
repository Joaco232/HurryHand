package com.hurryhand.backend.enums;

public enum SortingServicePosts {

    RATING("rating"),
    PRICE("price"),
    CREATED_AT("createdAt");

    private final String value;

    SortingServicePosts(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

}
