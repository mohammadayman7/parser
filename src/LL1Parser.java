import java.io.IOException;
import java.util.List;
import java.util.Stack;
            public class LL1Parser {
               // private String[] tokens;
                private int index;
                private Stack<String> stack;

                List<Token> tokens;
                public LL1Parser(List<Token> tokens) {
                    this.tokens = tokens;
                    this.index = 0;
                    this.stack = new Stack<>();
                    stack.push("$");
                    stack.push("project-declaration");
                }

                public boolean parse() {
                    try{
                        while (!stack.isEmpty()) {
                            String top = stack.pop();
                            if (top.equals("$")) {
                                return true;
                            }
                                if ((!stack.isEmpty())&&(index>tokens.size())){
                                String errorMessage = "Error: Invalid expression";
                                System.out.println(errorMessage + " at line " + tokens.get(index).line + " Token " + tokens.get(index).value + " Of Type" + tokens.get(index+1).type);
                                return false;
                            }
                            if (top.equals(tokens.get(index).value) || (tokens.get(index).type == TokenType.USER_DEFINED_NAME && (top.equals("name") || top.equals("const-name") || top.equals("var-name"))) || ((tokens.get(index).type == TokenType.INTEGER) && top.equals("integer-value"))) {
                                index++;

                            } else if (top.equals("project-declaration")) {
                                stack.push(".");
                                stack.push("project-def");
                            } else if (top.equals("project-def")) {
                                stack.push("compound-stmt");
                                stack.push("declarations");
                                stack.push("project-heading");
                            } else if (top.equals("project-heading")) {
                                stack.push(";");
                                stack.push("name");
                                stack.push("project");
                            } else if (top.equals("declarations")) {
                                if (tokens.get(index).value.equals("const") || tokens.get(index).value.equals("var") || tokens.get(index).value.equals("subroutine") || tokens.get(index).value.equals("begin")) {
                                    stack.push("subroutine-decl");
                                    stack.push("var-decl");
                                    stack.push("const-decl");
                                }
                            } else if (top.equals("const-decl")) {
                                if (tokens.get(index).value.equals("const")) {
                                    stack.push("const-list");
                                    stack.push("const");
                                } else if (tokens.get(index).value.equals("var") || tokens.get(index).value.equals("subroutine") || tokens.get(index).value.equals("begin")) {
                                    continue;
                                }
                            } else if (top.equals("const-list")) {
                                if (tokens.get(index).type == TokenType.USER_DEFINED_NAME) {
                                    stack.push("const-list");
                                    stack.push(";");
                                    stack.push("integer-value");
                                    stack.push("=");
                                    stack.push("const-name");

                                } else if (tokens.get(index).value.equals("var") || tokens.get(index).value.equals("subroutine") || tokens.get(index).value.equals("begin")) {
                                    continue;
                                }
                            } else if (top.equals("var-decl")) {
                                if (tokens.get(index).value.equals("var")) {
                                    stack.push("var-list");
                                    stack.push("var");
                                } else if (tokens.get(index).value.equals("subroutine") || tokens.get(index).value.equals("begin")) {
                                    continue;
                                }
                            } else if (top.equals("var-list")) {
                                if (tokens.get(index).type == TokenType.USER_DEFINED_NAME) {
                                    stack.push("var-list");
                                    stack.push(";");
                                    stack.push("var-item");

                                } else if (tokens.get(index).value.equals("subroutine") || tokens.get(index).value.equals("begin")) {
                                    continue;
                                }
                            } else if (top.equals("var-item")) {
                                if (tokens.get(index).type == TokenType.USER_DEFINED_NAME) {
                                    stack.push("int");
                                    stack.push(":");
                                    stack.push("name-list");
                                }


                            } else if (top.equals("name-list")) {
                                if (tokens.get(index).type == TokenType.USER_DEFINED_NAME) {
                                    stack.push("more-names");
                                    stack.push("var-name");
                                }
                            } else if (top.equals("more-names")) {
                                if (tokens.get(index).value.equals(",")) {
                                    stack.push("name-list");
                                    stack.push(",");
                                } else if (tokens.get(index).value.equals(":")) {
                                    continue;
                                }
                            } else if (top.equals("subroutine-decl")) {
                                if (tokens.get(index).value.equals("subroutine")) {
                                    stack.push(";");
                                    stack.push("compound-stmt");
                                    stack.push("declarations");
                                    stack.push("subroutine-heading");
                                } else if (tokens.get(index).value.equals("begin")) {
                                    continue;
                                }
                            } else if (top.equals("subroutine-heading")) {
                                if (tokens.get(index).value.equals("subroutine")) {
                                    stack.push(";");
                                    stack.push("name");
                                    stack.push("subroutine");
                                }
                            } else if (top.equals("compound-stmt")) {
                                if (tokens.get(index).value.equals("begin")) {
                                    stack.push("end");
                                    stack.push("stmt-list");
                                    stack.push("begin");
                                }
                            } else if (top.equals("stmt-list")) {
                                if (tokens.get(index).value.equals("begin") || tokens.get(index).value.equals("name") || tokens.get(index).value.equals(";") || tokens.get(index).value.equals("scan")
                                        || tokens.get(index).value.equals("print") || tokens.get(index).value.equals("if") || tokens.get(index).value.equals("endif") ||
                                        tokens.get(index).value.equals("else") || tokens.get(index).value.equals("while")) {
                                    stack.push("stmt-list");
                                    stack.push(";");
                                    stack.push("statement");
                                } else if (tokens.get(index).value.equals("end")) {
                                    continue;
                                }
                            } else if (top.equals("statement")) {
                                if (tokens.get(index).type == TokenType.USER_DEFINED_NAME) {
                                    stack.push("ass-stmt");
                                } else if (tokens.get(index).value.equals("begin")) {
                                    stack.push("compound-stmt");
                                } else if (tokens.get(index).value.equals("scan") || tokens.get(index).value.equals("print")) {
                                    stack.push("inout-stmt");

                                } else if (tokens.get(index).value.equals("if")) {
                                    stack.push("if-stmt");
                                } else if (tokens.get(index).value.equals("while")) {
                                    stack.push("while-stmt");

                                } else if (tokens.get(index).value.equals("endif") || tokens.get(index).value.equals("else") || tokens.get(index).value.equals(";")) {
                                    continue;
                                }

                            } else if (top.equals("ass-stmt")) {
                                if (tokens.get(index).type == TokenType.USER_DEFINED_NAME) {
                                    stack.push("arith-exp");
                                    stack.push(":=");
                                    stack.push("name");
                                }
                            } else if (top.equals("arith-exp")) {
                                if (tokens.get(index).type == TokenType.USER_DEFINED_NAME || tokens.get(index).type == TokenType.INTEGER || tokens.get(index).value.equals("(")) {
                                    stack.push("arith-exp-prime");
                                    stack.push("term");

                                }

                            } else if (top.equals("arith-exp-prime")) {
                                if (tokens.get(index).value.equals("+") || tokens.get(index).value.equals("-")) {
                                    stack.push("arith-exp-prime");
                                    stack.push("term");
                                    stack.push("add-sign");
                                } else if (tokens.get(index).value.equals("else") || tokens.get(index).value.equals("endif") || tokens.get(index).value.equals(")") || tokens.get(index).value.equals(";")) {
                                    continue;

                                }

                            } else if (top.equals("term")) {
                                if (tokens.get(index).type == TokenType.USER_DEFINED_NAME || tokens.get(index).type == TokenType.INTEGER || tokens.get(index).value.equals("(")) {
                                    stack.push("term-prime");
                                    stack.push("factor");
                                }
                            } else if (top.equals("term-prime")) {
                                if (tokens.get(index).value.equals("*") || tokens.get(index).value.equals("/") || tokens.get(index).value.equals("%")) {
                                    stack.push("term-prime");
                                    stack.push("factor");
                                    stack.push("mul-sign");
                                } else if (tokens.get(index).value.equals(";") || tokens.get(index).value.equals(")") || tokens.get(index).value.equals("+") || tokens.get(index).value.equals("-")) {
                                    continue;
                                }
                            } else if (top.equals("factor")) {
                                if (tokens.get(index).type == TokenType.USER_DEFINED_NAME || tokens.get(index).type == TokenType.INTEGER) {
                                    stack.push("name-value");
                                } else if (tokens.get(index).value.equals("(")) {
                                    stack.push(")");
                                    stack.push("arith-exp");
                                    stack.push("(");

                                }
                            } else if (top.equals("name-value")) {
                                if (tokens.get(index).type == TokenType.USER_DEFINED_NAME) {
                                    stack.push("name");
                                } else if (tokens.get(index).type == TokenType.INTEGER) {
                                    stack.push("integer-value");
                                }

                            } else if (top.equals("add-sign")) {
                                if (tokens.get(index).value.equals("+")) {
                                    stack.push("+");
                                } else if (tokens.get(index).value.equals("-")) {
                                    stack.push("-");
                                }

                            } else if (top.equals("mul-sign")) {
                                if (tokens.get(index).value.equals("*")) {
                                    stack.push("*");
                                } else if (tokens.get(index).value.equals("/")) {
                                    stack.push("/");
                                } else if (tokens.get(index).value.equals("%")) {
                                    stack.push("%");
                                }

                            } else if (top.equals("inout-stmt")) {
                                if (tokens.get(index).value.equals("scan")) {
                                    stack.push(")");
                                    stack.push("name");
                                    stack.push("(");
                                    stack.push("scan");
                                } else if (tokens.get(index).value.equals("print")) {
                                    stack.push(")");
                                    stack.push("name-value");
                                    stack.push("(");
                                    stack.push("print");
                                }

                            } else if (top.equals("if-stmt")) {
                                if (tokens.get(index).value.equals("if")) {
                                    stack.push("endif");
                                    stack.push("else-part");
                                    stack.push("statement");
                                    stack.push("then");
                                    stack.push("bool-exp");
                                    stack.push("if");
                                }
                            } else if (top.equals("else-part")) {
                                if (tokens.get(index).value.equals("else")) {
                                    stack.push("statement");
                                    stack.push("else");
                                } else if (tokens.get(index).value.equals("endif")) {
                                    continue;

                                }
                            } else if (top.equals("while-stmt")) {
                                if (tokens.get(index).value.equals("while")) {
                                    stack.push("statement");
                                    stack.push("do");
                                    stack.push("bool-exp");
                                    stack.push("while");
                                }
                            } else if (top.equals("bool-exp")) {
                                if ((tokens.get(index).type == TokenType.USER_DEFINED_NAME) || (tokens.get(index).type == TokenType.INTEGER)) {
                                    stack.push("name-value");
                                    stack.push("relational-oper");
                                    stack.push("name-value");
                                }
                            } else if (top.equals("relational-oper")) {
                                if (tokens.get(index).value.equals("=")) {
                                    stack.push("=");
                                } else if (tokens.get(index).value.equals("|=")) {
                                    stack.push("|=");
                                } else if (tokens.get(index).value.equals("<")) {
                                    stack.push("<");
                                } else if (tokens.get(index).value.equals("=<")) {
                                    stack.push("=<");
                                } else if (tokens.get(index).value.equals(">")) {
                                    stack.push(">");
                                } else if (tokens.get(index).value.equals("=>")) {
                                    stack.push("=>");
                                }
                            } else {
                                String errorMessage = "Error: Invalid expression";
                                System.out.println(errorMessage + " at line " + tokens.get(index).line + " Token " + tokens.get(index).value + " Of Type" + tokens.get(index).type);
                                return false;

                            }


                        }
                    } catch (Exception e) {
                        String errorMessage = "Error: Invalid expression";
                        System.out.println(errorMessage + " at line " + tokens.get(index).line + " Token " + tokens.get(index).value + " Of Type" + tokens.get(index).type);
                        return false;


                    }

                    return true;
                         }
                    }