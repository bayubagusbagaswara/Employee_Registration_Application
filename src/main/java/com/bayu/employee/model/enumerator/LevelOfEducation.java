package com.bayu.employee.model.enumerator;

public enum LevelOfEducation {

    SD("SD"),
    SMP("SMP"),
    SMA("SMA"),
    SMK("SMK"),
    D1("D1"),
    D2("D2"),
    D3("D3"),
    D4("D4"),
    S1("S1"),
    S2("S2"),
    S3("S3");

    private final String levelOfEducationName;

    LevelOfEducation(String levelOfEducationName) {
        this.levelOfEducationName = levelOfEducationName;
    }

    public String getLevelOfEducationName() {
        return levelOfEducationName;
    }
}
