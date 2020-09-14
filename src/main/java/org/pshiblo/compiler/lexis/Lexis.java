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
                lexisResult.addNextLexeme(Lexeme.sign(matcher.group(2)), About.INT);
            } else {
                String viraz = matcher.group(4);
                System.out.println(viraz);
                Pattern patternViraz = Pattern.compile("(([\\d\\w]+)([\\+\\-\\*\\/]?))+");
                Matcher matcherViraz = patternViraz.matcher(viraz);
                while (matcherViraz.find()) {
                    for (int i = 1; i < matcherViraz.groupCount(); i++) {
                        System.out.println(matcherViraz.group(i));
                    }
                }
            }
        }


        return lexisResult;
    }
}
