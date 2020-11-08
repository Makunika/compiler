package org.pshiblo.compiler.codegenerator;

/**
 * @author Максим Пшибло
 */
public class CodeOptimization {

    private CodeGeneratorOutput codeGeneratorOutput;

    public CodeOptimization(CodeGeneratorOutput codeGeneratorOutput) {
        this.codeGeneratorOutput = codeGeneratorOutput;
    }

    /*
    1. Если «+» – коммутативная операция, можно заменить последовательность
    команд вида LOAD α; ADD β последовательностью LOAD β; ADD α.
    Требуется, однако, чтобы в других местах программы не было перехода к
    оператору ADD β.

    2. Если «*» – коммутативная операция, можно заменить LOAD α; MPY β на
    LOAD β; MPY α.

    3. Последовательность операторов вида STORE α; LOAD α можно удалить из
    программы при условии, что-либо ячейка α не будет далее использоваться,
    либо перед использованием α будет заполнена заново. (Чаще можно удалить
    один лишь оператор LOAD α; для этого только требуется, чтобы к оператору
    LOAD α не было перехода в других местах программы.)

    4. Последовательность LOAD α; STORE β можно удалить, если за ней следует
    другой оператор LOAD и нет перехода к оператору STORE β, а последующие
    вхождения β будут заменены на α вплоть до того места, где появляется
    другой оператор STORE β (но исключая его).
     */

    private CodeGeneratorOutput optimization() {
        CodeGeneratorOutput codeOptimizationOutput = new CodeGeneratorOutput();


        return codeOptimizationOutput;
    }
}
