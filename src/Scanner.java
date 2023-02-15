import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
//
//public class Scanner {
//    private String sourceCode;
//    private int index;
//    private List<Token> tokens;
//    private StringBuilder buffer;
//    private int state;
//
//    public Scanner(String sourceCode) {
//        this.sourceCode = sourceCode;
//        this.index = 0;
//        this.tokens = new ArrayList<>();
//        this.buffer = new StringBuilder();
//        this.state = 0;
//    }
//
//    public List<Token> scan() {
//        while (index < sourceCode.length()) {
//            char ch = sourceCode.charAt(index);
//            switch (state) {
//                case 0:
//                    if (Character.isLetter(ch)) {
//                        buffer.append(ch);
//                        state = 1;
//                    } else if (Character.isDigit(ch)) {
//                        buffer.append(ch);
//                        state = 2;
//                    } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
//                        tokens.add(new Token(TokenType.Operator, Character.toString(ch)));
//                    } else if (ch == '(' || ch == ')' || ch == ';' || ch == '=' || ch == '<' || ch == '>') {
//                        tokens.add(new Token(TokenType.SpecialSymbol, Character.toString(ch)));
//                    }
//                    break;
//                case 1:
//                    if (Character.isLetterOrDigit(ch)) {
//                        buffer.append(ch);
//                    } else {
//                        String word = buffer.toString();
//                        if (word.equals("const") || word.equals("var") || word.equals("subroutine")) {
//                            tokens.add(new Token(TokenType.Keyword, word));
//                        } else {
//                            tokens.add(new Token(TokenType.Identifier, word));
//                        }
//                        buffer.setLength(0);
//                        state = 0;
//                        continue;
//                    }
//                    break;
//                case 2:
//                    if (Character.isDigit(ch)) {
//                        buffer.append(ch);
//                    } else {
//                        tokens.add(new Token(TokenType.Number, buffer.toString()));
//                        buffer.setLength(0);
//                        state = 0;
//                        continue;
//                    }
//                    break;
//            }
//            index++;
//        }
//
//        if (buffer.length() > 0) {
//            switch (state) {
//                case 1:
//                    String word = buffer.toString();
//                    if (word.equals("const") || word.equals("var") || word.equals("subroutine")) {
//                        tokens.add(new Token(TokenType.Keyword, word));
//                    } else {
//                        tokens.add(new Token(TokenType.Identifier, word));
//                    }
//                    break;
//                case 2:
//                    tokens.add(new Token(TokenType.Number, buffer.toString()));
//                    break;
//            }
//            buffer.setLength(0);
//        }
//        return tokens;
//    }
//}
//import java.util.ArrayList;
//import java.util.List;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.charset.Charset;
//import java.nio.file.Files;
//import java.util.ArrayList;
//import java.util.List;
//
//
//public class Scanner {
//
//    private static final String[] reservedWords = {
//            "project", "const","var", "int", "subroutine", "end", "begin", "scan", "print", "if", "then", "endif", "else", "while", "do"
//    };
//
//    private static boolean isReservedWord(String word) {
//        for (String reservedWord : reservedWords) {
//            if (reservedWord.equals(word)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public static List<Token> scan(List<String> code) {
//        List<Token> tokens = new ArrayList<>();
//        StringBuilder wordBuilder = new StringBuilder();
//        StringBuilder wordBuilderDig = new StringBuilder();
//
//        for (int i = 0; i < code.size(); i++) {
//            int x=0;
//
//            while (x<code.get(i).length()) {
//
//                char c = code.get(i).charAt(x);
//                if (Character.isLetter(c)) {
//                        wordBuilder.append(c);
//
//                } else if (Character.isDigit(c)&&wordBuilder.isEmpty()) {
//                        wordBuilderDig.append(c);
//                } else {
//                    String word;
//                    if (wordBuilder.isEmpty()){
//                         word=wordBuilderDig.toString();
//                    }else {
//                         word = wordBuilder.toString();
//                    }
//                    if (!word.isEmpty()) {
//                        if (isReservedWord(word)) {
//                            tokens.add(new Token(TokenType.RESERVED_WORD, word,i));
//                        } else if (isInteger(word)) {
//                            tokens.add(new Token(TokenType.INTEGER, word, i));
//                        } else {
//                            tokens.add(new Token(TokenType.USER_DEFINED_NAME, word, i));
//                        }
//                        wordBuilder.setLength(0);
//                    }
//                    if (c == '.' || c == ';' || c == '<' || c == '>' || c == '(' || c == ')' || c == '+' || c == '-' || c == '*' || c == '/' || c == '%' || c == ',') {
//                        tokens.add(new Token(TokenType.TERMINAL_TOKEN, Character.toString(c), i));
//                    } else if (c == ':') {
//                        if (code.get(i).charAt(x + 1) == '=') {
//                            tokens.add(new Token(TokenType.TERMINAL_TOKEN, ":=", i));
//                            i++;
//                        } else {
//                            tokens.add(new Token(TokenType.TERMINAL_TOKEN, Character.toString(c), i));
//                        }
//                    } else if (c == '|') {
//                        if (code.charAt(i + 1) == '=') {
//                            tokens.add(new Token(TokenType.TERMINAL_TOKEN, "|=", i));
//                            i++;
//                        } else {
//                            tokens.add(new Token(TokenType.TERMINAL_TOKEN, Character.toString(c), i));
//                        }
//                    } else if (c == '=') {
//                        if (code.charAt(i + 1) == '<') {
//                            tokens.add(new Token(TokenType.TERMINAL_TOKEN, "=<", i));
//                            i++;
//                        } else if (code.charAt(i + 1) == '>') {
//                            tokens.add(new Token(TokenType.TERMINAL_TOKEN, "=>", line));
//                            i++;
//                        } else {
//                            tokens.add(new Token(TokenType.TERMINAL_TOKEN, Character.toString(c), line));
//
//                        }
//
//                    }
//
//                }
//            }
//        }
//        return tokens;
//    }
//
//    private static boolean isInteger(String word) {
//        try {
//            Integer.parseInt(word);
//            return true;
//        } catch (NumberFormatException e) {
//            return false;
//        }
//    }
//
//}

import java.io.BufferedReader;
        import java.io.FileReader;
        import java.io.IOException;
        import java.util.ArrayList;
        import java.util.List;

public class Scanner {

    private static final String[] reservedWords = {
            "project", "const", "var", "int", "subroutine", "end", "begin", "scan", "print", "if", "then", "endif", "else", "while", "do"
    };

    private static boolean isReservedWord(String word) {
        for (String reservedWord : reservedWords) {
            if (reservedWord.equals(word)) {
                return true;
            }
        }
        return false;
    }

    public static List<Token> scan(String filePath) {
        List<Token> tokens = new ArrayList<>();
        StringBuilder wordBuilder = new StringBuilder();

        int lineNumber = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                for (int i = 0; i < line.length(); i++) {
                    char c = line.charAt(i);
                    if (i==0&&!wordBuilder.isEmpty()){
                        String word = wordBuilder.toString();
                        if (!word.isEmpty()) {
                            if (isReservedWord(word)) {
                                tokens.add(new Token(TokenType.RESERVED_WORD, word, lineNumber));
                            } else if (isInteger(word, tokens, lineNumber)) {
                                tokens.add(new Token(TokenType.INTEGER, word, lineNumber));
                            }
                            wordBuilder.setLength(0);
                        }
                    }
                    if (Character.isLetterOrDigit(c)) {
                        wordBuilder.append(c);
                    } else {
                        String word = wordBuilder.toString();
                        if (!word.isEmpty()) {
                            if (isReservedWord(word)) {
                                tokens.add(new Token(TokenType.RESERVED_WORD, word, lineNumber));
                            } else if (isInteger(word,tokens,lineNumber)) {
                                tokens.add(new Token(TokenType.INTEGER, word, lineNumber));
                            }
                            wordBuilder.setLength(0);
                        }
                        if (c == '.' || c == ';' || c == '<' || c == '>' || c == '(' || c == ')' || c == '+' || c == '-' || c == '*' || c == '/' || c == '%' || c == ',') {
                            tokens.add(new Token(TokenType.TERMINAL_TOKEN, Character.toString(c),lineNumber));
                        } else if (c == ':') {
                            if (line.charAt(i + 1) == '=') {
                                tokens.add(new Token(TokenType.TERMINAL_TOKEN, ":=", lineNumber));
                                i++;
                            } else {
                                tokens.add(new Token(TokenType.TERMINAL_TOKEN, Character.toString(c),lineNumber));
                            }
                        } else if (c == '|') {
                            if (line.charAt(i + 1) == '=') {
                                tokens.add(new Token(TokenType.TERMINAL_TOKEN, "|=", lineNumber));
                                i++;
                            } else {
                                tokens.add(new Token(TokenType.TERMINAL_TOKEN, Character.toString(c), lineNumber));
                            }
                        } else if (c == '=') {
                            if (line.charAt(i + 1) == '<') {
                                tokens.add(new Token(TokenType.TERMINAL_TOKEN, "=<", lineNumber));
                                i++;

                            } else if (line.charAt(i + 1) == '>') {
                                tokens.add(new Token(TokenType.TERMINAL_TOKEN, "=>", lineNumber));
                                i++;

                            } else {
                                tokens.add(new Token(TokenType.TERMINAL_TOKEN, Character.toString(c),lineNumber));

                            }
                        }

                    }
                }
            }


        }
        catch(IOException e){
            System.err.println("Error reading file: " + e.getMessage());
        }

        return tokens;

    }
    private static boolean isInteger(String word, List<Token> tokens, int line) {
        if (!Character.isDigit(word.charAt(0))) {
            tokens.add(new Token(TokenType.USER_DEFINED_NAME, word, line));
            return false;
        } else {

            int index = 0;
            while (index < word.length() && Character.isDigit(word.charAt(index))) {
                index++;
            }

            if (index == word.length()) {
                // The entire word is a number, so add it as an INTEGER token
                tokens.add(new Token(TokenType.INTEGER, word, line));
            } else {
                // The first part of the word is a number, so add it as an INTEGER token
                tokens.add(new Token(TokenType.INTEGER, word.substring(0, index), line));

            }
            return false;
        }
    }


}