package org.pshiblo.compiler.lexis;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexis {

    public static LexisResult analysisInput(String input) {
        System.out.println("input = " + input);

        LexisResult lexisResult = new LexisResult();

        Pattern pattern = Pattern.compile("(\\w+)(;|(=)((.)+))");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            lexisResult.addNextLexeme(Lexeme.val(matcher.group(1)), About.INT);
            if (matcher.group(2).equals(";")) {
                lexisResult.addNextLexeme(Lexeme.sign(matcher.group(2)), About.OPERATOR);
            } else {
                lexisResult.addNextLexeme(Lexeme.sign(matcher.group(3)),  About.OPERATOR);
                Pattern p = Pattern.compile("([A-Za-z]+\\w*)|(\\d+\\.?\\d*)|([+\\-*/^])|[()]|;");
                Matcher m = p.matcher(matcher.group(4));
                while (m.find())
                {
                    String str = m.group();
                    if (str.matches("\\d+\\.?\\d*")) {
                        if (str.contains(".")) {
                            lexisResult.addNextLexeme(Lexeme.number(str), About.CONST_FLOAT);
                            lexisResult.getTableNames().get(0).setAbout(About.FLOAT.getVal());
                        } else {
                            lexisResult.addNextLexeme(Lexeme.number(str), About.CONST_INT);
                        }
                    }
                    else if (str.matches("[+\\-*/^]")) {
                        lexisResult.addNextLexeme(Lexeme.sign(str), About.OPERATOR);
                    }
                    else if (str.matches("[();]")) {
                        lexisResult.addNextLexeme(Lexeme.sign(str), About.BRACKET);
                    }
                    else if (str.matches("[A-Za-z]+\\w*")) {
                        lexisResult.addNextLexeme(Lexeme.val(str), About.INT);
                    }
                }
            }
        }


        return lexisResult;
    }
}
