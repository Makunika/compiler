package org.pshiblo.compiler.codegenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Максим Пшибло
 */
public class CodeOptimization {

    private CodeGeneratorOutput codeGeneratorOutput;

    public CodeOptimization(CodeGeneratorOutput codeGeneratorOutput) {
        this.codeGeneratorOutput = codeGeneratorOutput;
    }

    /**
    1. Если «+» – коммутативная операция, можно заменить последовательность
    команд вида LOAD α; ADD β последовательностью LOAD β; ADD α.
    Требуется, однако, чтобы в других местах программы не было перехода к
    оператору ADD β.

    2. Последовательность операторов вида STORE α; LOAD α можно удалить из
    программы при условии, что-либо ячейка α не будет далее использоваться,
    либо перед использованием α будет заполнена заново. (Чаще можно удалить
    один лишь оператор LOAD α; для этого только требуется, чтобы к оператору
    LOAD α не было перехода в других местах программы.)

    3. Последовательность LOAD α; STORE β можно удалить, если за ней следует
    другой оператор LOAD и нет перехода к оператору STORE β, а последующие
    вхождения β будут заменены на α вплоть до того места, где появляется
    другой оператор STORE β (но исключая его).
     */
    public CodeGeneratorOutput optimization() {
        CodeGeneratorOutput codeOptimizationOutput = new CodeGeneratorOutput();


        return codeOptimizationOutput;
    }


    /**
     *     LOAD a1; add a2 -> LOAD a2; add a1
     */
    private void firstRule(List<CodeCommand> codeCommands) {
        for (int i = 0; i < codeCommands.size(); i++) {
            if (i < codeCommands.size() - 1 && codeCommands.get(i).getCmd().equals("LOAD")) {
                if (List.of("ADD", "MPY", "SUB", "DIV").contains(codeCommands.get(i + 1).getCmd())) {
                    codeCommands.get(i).swapArg(codeCommands.get(i + 1));
                }
            }
        }
    }

    /**
     * Последовательность операторов вида STORE α; LOAD α можно удалить из
     *     программы при условии, что-либо ячейка α не будет далее использоваться,
     *     либо перед использованием α будет заполнена заново.
     *
     */
    private void secondRule(List<CodeCommand> codeCommands) {

        List<Integer> deletedIndexes = new ArrayList<>();

        for (int i = 0; i < codeCommands.size(); i++) {
            CodeCommand currentCommand = codeCommands.get(i);
            if (i < codeCommands.size() - 1
                    && currentCommand.getCmd().equals("STORE")) {
                CodeCommand nextCommand = codeCommands.get(i);
                if (nextCommand.getCmd().equals("LOAD")
                        && currentCommand.equalsArg(nextCommand)) {
                    String arg = nextCommand.getArg();
                    boolean deleted = false;
                    for (int j = i + 2; j < codeCommands.size(); j++) {
                        if (codeCommands.get(j).containArg(arg)) {
                            if (codeCommands.get(j).getCmd().equals("STORE")) {
                                deleted = true;
                            }
                            else {
                                deleted = false;

                            }
                            break;
                        }
                    }
                    if (deleted) {
                        deletedIndexes.add(i);
                        deletedIndexes.add(i+1);
                    }
                }
            }
        }

        for (Integer deletedIndex : deletedIndexes) {
            codeCommands.remove(deletedIndex.intValue());
        }
    }



}
