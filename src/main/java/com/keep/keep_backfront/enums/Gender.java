package com.keep.keep_backfront.enums;

public enum Gender {
    MALE("女"), FEMALE("男");

    private String description;

    Gender(String description) {
        this.description = description;
    }
}
