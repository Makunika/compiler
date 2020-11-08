package org.pshiblo.compiler.codegenerator;

import java.util.LinkedList;
import java.util.List;

public class CodeGeneratorOutput {
    private List<String> operatorsAssembler;

    public CodeGeneratorOutput() {
        operatorsAssembler = new LinkedList<>();
    }

    public List<String> getOperatorsAssembler() {
        return operatorsAssembler;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        operatorsAssembler.forEach(op -> sb.append(op).append("\n"));
        return sb.toString();
    }
}
