package calculator;

import javax.swing.JOptionPane;

public class PostfixConverter {

    public Queue operatorsList;
    public Queue varsList;
    public Queue constantsList;

    public Queue toPostfix(String infix) {

        infix += ')';
        operatorsList = new Queue();
        varsList = new Queue();
        constantsList = new Queue();
        Queue postfix = new Queue();//cola que almacenara el resultado final
        Stack stackTemp = new Stack();//pila temporal
        String operands = "";//string q almacena temporalmente los operandos hasta completarlos
        String operators = "";//string q almacena temporalmente los operadores hasta completarlos
        stackTemp.push("(");

        boolean next = true;
        int i = 0;
        while (next == true && i < infix.length()) {
            char ch = infix.charAt(i);
            switch (ch) {//segun el caracter
                case ' ':
                    break;
                case '('://si es parentesis de apertura
                    if (!operands.equals("")) {//si hay operando almacenado en el string 
                        postfix.add(operands);//se envia a la cola del postfijo
                        this.clasifyOperand(operands);//se envia a las listas de clasificación
                        operands = "";//y se limpia el string
                    }
                    stackTemp.push(ch + "");//se añade el caracter en la pila temporal
                    operatorsList.add(String.valueOf(ch));
                    break;
                case '&':// && and
                case '|':// || or
                case '>':// -> then
                case '=':// <->

                case '~':
                    if (!operands.equals("")) {//si hay operando almacenado en el string 
                        postfix.add(operands);//se envian a la cola final
                        this.clasifyOperand(operands);
                        operands = "";//y se limpia el string
                    }

                    while (priority(ch + "") <= priority(stackTemp.nextPop())) {
                        //mientras el operando en el caracter tenga menor prioridad que los operandos en la pila temporal
                        postfix.add(stackTemp.pop());//lleve los operandos de la pila temporal a la cola final
                    }
                    stackTemp.push(ch + "");//e ingresa la division en la pila temporal
                    operatorsList.add(String.valueOf(ch));
                    break;
                case ')'://si es parentesis de cierre
                    if (!operands.equals("")) {//si hay operando almacenado en el string 
                        postfix.add(operands);//se envian a la cola final
                        this.clasifyOperand(operands);
                        operands = "";//y se limpia el string
                    }
                    while (!stackTemp.nextPop().equals("(")) {//mientras no encuentre otreo parentesis anidado
                        postfix.add(stackTemp.pop());//llevar los operadores de la pila temporal a la cola real
                    }
                    stackTemp.pop();

                    if (i < infix.length() - 1) {
                        operatorsList.add(String.valueOf(ch));
                    }

                    break;
                case 'a':
                case 'b':
                case 'c':
                case 'd':
                case 'e':
                case 'f':
                case 'g':
                case 'h':
                case 'i':
                case 'j':
                case 'k':
                case 'l':
                case 'm':
                case 'n':
                case 'ñ':
                case 'o':
                case 'p':
                case 'q':
                case 'r':
                case 's':
                case 't':
                case 'u':
                case 'v':
                case 'w':
                case 'x':
                case 'y':
                case 'z':
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                case '_':
                case '-':
                    operands += ch;//si es un operando concatenarlo en el string de número hasta tenerlo completo
                    //operandsList.add(ch + "");
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Se encontraron caractéres inválidos en la expresión.");
                    next = false;
            }
            i++;
        }

        //System.out.print("Postfijo: ");
        //postfix.print();
        return postfix;
    }

    public void clasifyOperand(String operand) {
        switch (operand) {
            case "true":
            case "false":
                this.constantsList.add(operand);
                break;
            default:
                this.varsList.add(operand);
                break;
        }
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
        return operatorsList.toString();
    }

    public String getVars() {
        return varsList.toString();
    }

    public String getConstants() {
        return constantsList.toString();
    }
}
