package org.pshiblo.compiler.codegenerator;

import org.pshiblo.compiler.syntax.CodeBlock;
import org.pshiblo.compiler.syntax.SyntaxOutput;
import org.pshiblo.compiler.tree.Tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CodeGenerator {
    private final SyntaxOutput syntaxOutput;
    private int l;

    public CodeGenerator(SyntaxOutput syntaxOutput) {
        this.syntaxOutput = syntaxOutput;
        l = 0;
    }

    public CodeGeneratorOutput generateCodeDoWhile() {
        CodeGeneratorOutput codeGeneratorOutput = new CodeGeneratorOutput();
        codeGeneratorOutput.getOperatorsAssembler().add("loop:");
        for (CodeBlock codeBlock : syntaxOutput.getCodeBlocks()) {
            if (codeBlock.isTree()) {
                codeGeneratorOutput.getOperatorsAssembler().addAll(generateCodeExpression(codeBlock.getTree()));
            }
        }



        return codeGeneratorOutput;
    }


    private List<String> generateCodeExpression(Tree tree) {
        String code = tree.getStringForCodeGenerator(l + 1);
        l = tree.getL();
        List<String> assCode = new LinkedList<>();
        for (String s : code.split(";")) {
            s = s + ";";
            assCode.add(s);
        }
        return assCode;
    }
}
