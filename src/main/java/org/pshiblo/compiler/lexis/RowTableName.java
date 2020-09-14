package org.pshiblo.compiler.lexis;

public class RowTableName {
    private final String id;

    private final int number;

    private final String about;

    public RowTableName(String id, int number, String about) {
        this.id = id;
        this.number = number;
        this.about = about;
    }

    public String getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }

    public String getAbout() {
        return about;
    }

}
