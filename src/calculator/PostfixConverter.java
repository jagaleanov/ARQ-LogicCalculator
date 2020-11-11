package calculator;

import javax.swing.JOptionPane;

public class PostfixConverter {

    public Queue operators;
    public Queue operands;

    public Queue toPostfix(String infix) {

        infix += ')';
        operators = new Queue();
        operands = new Queue();
        Queue postfix = new Queue();//cola que almacenara el resultado final
        Stack temp = new Stack();//pila temporal
        String number = "";//string q almacena temporalmente los operandos y permite
        //operar numeros de varios digitos o reales
        temp.push("(");
        
        boolean next = true;
        int i = 0;
        while(next == true && i < infix.length()){
            char ch = infix.charAt(i);
            switch (ch) {//segun el caracter
                case '('://si es parentesis inicial
                    if (!number.equals("")) {//si hay operando almacenado en el string 
                        postfix.add(number);//se envian a la cola final
                        number = "";//y se limpia el string
                    }
                    temp.push(ch + "");//se añade el caracter en la pila temporal
                    operators.add(ch + "");
                    break;
                case '&':
                case '~':
                case '|':
                case '>':
                case '='://si es cualquier operador
                    if (!number.equals("")) {//si hay operando almacenado en el string 
                        postfix.add(number);//se envian a la cola final
                        number = "";//y se limpia el string
                    }

                    while (priority(ch + "") <= priority(temp.nextPop())) {
                        //mientras el operando en el caracter tenga menor prioridad que los operandos en la pila temporal
                        postfix.add(temp.pop());//lleve los operandos de la pila temporal a la cola final
                    }
                    temp.push(ch + "");//e ingresa la division en la pila temporal
                    operators.add(ch + "");
                    break;
                case ')'://si es parentesis final
                    if (!number.equals("")) {//si hay operando almacenado en el string 
                        postfix.add(number);//se envian a la cola final
                        number = "";//y se limpia el string
                    }
                    while (!temp.nextPop().equals("(")) {//mientras no encuentre otreo parentesis anidado
                        postfix.add(temp.pop());//llevar los operadores de la pila temporal a la cola real
                    }
                    temp.pop();

                    if (i < infix.length() - 1) {
                        operators.add(ch + "");
                    }

                    break;
                case 'p':
                case 'q':
                case 'r':
                case 's':
                case 't':
                    number += ch;//si es un operando concatenarlo en el string de número hasta tenerlo completo
                    operands.add(ch + "");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Se encontraron caractéres incorrectos en la expresión");
                    next = false;
            }
            i++;
        }

        //System.out.print("Postfijo: ");
        postfix.print();
        return postfix;
    }

    public int priority(String ch) {
        int res = 0;
        switch (ch) {
            case ")":
                res = 5;
                break;
            case "~":
                res = 4;
                break;
            case "&":
            case "|":
                res = 3;
                break;
            case "=":
            case ">":
                res = 2;
                break;
            case "(":
                res = 1;
                break;
        }
        return res;
    }

    public String getOperators() {
        return operators.toString();
    }

    public String getOperands() {
        return operands.toString();
    }
}
