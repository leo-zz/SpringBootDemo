package com.designpattern;

import java.util.StringTokenizer;

import static com.designpattern.TerminalExpression.buildInterpreterTree;

public class Interpreter {



    public static void main(String[] args) {

        Expression define = buildInterpreterTree();
        String context1 = "D B e";
        String context2 = "A B C";
        String context3 = "D A";
        String context4 = "D C";
        String context5 = "D";
        System.out.println(define.interpret(context1));
        System.out.println(define.interpret(context2));
        System.out.println(define.interpret(context3));
        System.out.println(define.interpret(context4));
        System.out.println(define.interpret(context5));

//        StringTokenizer tokenizer = new StringTokenizer("l e o");
//        while (tokenizer.hasMoreTokens()){
//            String token = tokenizer.nextToken();
//            System.out.println(token);
//        }

    }
}

 abstract class Expression {
    public abstract boolean interpret(String str);
}

class TerminalExpression extends Expression {

    private String literal = null;

    public TerminalExpression(String str) {
        literal = str;
    }

    public boolean interpret(String str) {
        //将传入的字符串str进行分词处理，默认的分词器为：空格,tab键，换行等
        StringTokenizer st = new StringTokenizer(str);
        while (st.hasMoreTokens()) {
            String test = st.nextToken();
            if (test.equals(literal)) {
                return true;
            }
        }
        return false;
    }

    static class AndExpression extends Expression {

        private Expression expression1 = null;
        private Expression expression2 = null;

        public AndExpression(Expression expression1, Expression expression2) {
            this.expression1 = expression1;
            this.expression2 = expression2;
        }

        public boolean interpret(String str) {
            return expression1.interpret(str) && expression2.interpret(str);
        }
    }

    static class OrExpression extends Expression {
        private Expression expression1 = null;
        private Expression expression2 = null;

        public OrExpression(Expression expression1, Expression expression2) {
            this.expression1 = expression1;
            this.expression2 = expression2;
        }

        public boolean interpret(String str) {
            return expression1.interpret(str) || expression2.interpret(str);
        }
    }

    public static Expression buildInterpreterTree() {
        // Literal
        Expression terminal1 = new TerminalExpression("A");
        Expression terminal2 = new TerminalExpression("B");
        Expression terminal3 = new TerminalExpression("C");
        Expression terminal4 = new TerminalExpression("D");
        // B C
        Expression alternation1 = new TerminalExpression.OrExpression(terminal2, terminal3);
        // A Or (B C)
        Expression alternation2 = new TerminalExpression.OrExpression(terminal1, alternation1);
        // D And (A Or (B C))
        return new TerminalExpression.AndExpression(terminal4, alternation2);
    }

}
