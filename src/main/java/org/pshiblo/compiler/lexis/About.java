package org.pshiblo.compiler.lexis;

enum About {
    INT("Переменная типа int"),
    FLOAT("Переменная типа float"),
    DOUBLE("Переменная типа double"),
    CONST_INT("Константа типа int"),
    CONST_FLOAT("Константа типа FLOAT"),
    CONST_DOUBLE("Константа типа DOUBLE");

    private final String val;

    About(String s) {
        val = s;
    }

    public String getVal() {
        return val;
    }
}
