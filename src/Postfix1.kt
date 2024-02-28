import java.util.*
//
//class Postfix1 {
//    fun evaluateExpression(expression: String): Int {
//        val tokens = expression.toCharArray()
//
//        // Stack for numbers: 'values'
//        val values = Stack<Int>()
//
//        // Stack for Operators: 'ops'
//        val ops = Stack<Char>()
//
//        var i = 0
//        while (i < tokens.size) {
//            // Current token is a
//            // whitespace, skip it
//            if (tokens[i] == ' ') {
//                i++
//                continue
//            }
//
//            // Current token is a number,
//            // push it to stack for numbers
//            if (tokens[i] >= '0' &&
//                tokens[i] <= '9'
//            ) {
//                val sbuf = StringBuffer()
//
//
//                // There may be more than one
//                // digits in number
//                while (i < tokens.size && tokens[i] >= '0' && tokens[i] <= '9') sbuf.append(tokens[i++])
//                values.push(
//                    sbuf.toString
//                        ().toInt()
//                )
//
//
//                // right now the i points to
//                // the character next to the digit,
//                // since the for loop also increases
//                // the i, we would skip one
//                // token position; we need to
//                // decrease the value of i by 1 to
//                // correct the offset.
//                i--
//            } else if (tokens[i] == '(') ops.push(tokens[i])
//            else if (tokens[i] == ')') {
//                while (ops.peek() != '(') values.push(
//                    applyOp(
//                        ops.pop(),
//                        values.pop(),
//                        values.pop()
//                    )
//                )
//                ops.pop()
//            } else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/') {
//                // While top of 'ops' has same
//                // or greater precedence to current
//                // token, which is an operator.
//                // Apply operator on top of 'ops'
//                // to top two elements in values stack
//                while (!ops.empty() &&
//                    hasPrecedence(
//                        tokens[i],
//                        ops.peek()
//                    )
//                ) values.push(
//                    applyOp(
//                        ops.pop(),
//                        values.pop(),
//                        values.pop()
//                    )
//                )
//
//                // Push current token to 'ops'.
//                ops.push(tokens[i])
//            }
//            i++
//        }
//
//        // Entire expression has been
//        // parsed at this point, apply remaining
//        // ops to remaining values
//        while (!ops.empty()) values.push(
//            applyOp(
//                ops.pop(),
//                values.pop(),
//                values.pop()
//            )
//        )
//
//        // Top of 'values' contains
//        // result, return it
//        return values.pop()
//    }
//
//    // Returns true if 'op2' has higher
//    // or same precedence as 'op1',
//    // otherwise returns false.
//    fun hasPrecedence(
//        op1: Char, op2: Char
//    ): Boolean {
//        if (op2 == '(' || op2 == ')') return false
//        return if ((op1 == '*' || op1 == '/') &&
//            (op2 == '+' || op2 == '-')
//        ) false
//        else true
//    }
//
//    // A utility method to apply an
//    // operator 'op' on operands 'a'
//    // and 'b'. Return the result.
//    fun applyOp(
//        op: Char,
//        b: Int, a: Int
//    ): Int {
//        when (op) {
//            '+' -> return a + b
//            '-' -> return a - b
//            '*' -> return a * b
//            '/' -> {
//                if (b == 0) throw UnsupportedOperationException(
//                    "Cannot divide by zero"
//                )
//                return a / b
//            }
//        }
//        return 0
//    }
//
//
//}


class Postfix1 {

    private fun hasPrecedence(op1: Char, op2: Char): Boolean {
        if (op2 == '(' || op2 == ')') return false
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) return false
        else return true
    }
    private fun importanceOfOperator (op:Char) :Int
    {
        if(op == '+' || op == '-') return 1
        if(op == '*' || op == '/') return 2
        return 0
    }
    private fun calculOp(ch:Char, t1:Double, t2:Double):Double {

        when (ch) {
            '+' -> return t2+t1
            '-' -> return t2-t1
            '/' -> {
                if(t1 == 0.0) System.err.println("Nu se poate realiza operatia de divide cu un despartitor nul!")
                else return t2/t1.toDouble()
            }
            '*' -> return t2*t1
        }
        return 0.0
    }

    fun expressionResult(exprString: String):Double
    {
        val numStack = Stack<Double>()
        val opStack = Stack<Char>()
        var txtNum = String()
        val charExpr = exprString.toCharArray()

        var i = 0

        while (i < charExpr.size)
        {
            if(charExpr[i].isDigit())
            {
                while(charExpr[i] in '0'..'9')
                {
                    txtNum += charExpr[i]
                    i++
                }
                    numStack.push(txtNum.toDouble())
                    txtNum=""
                    i--
            }

            else if(charExpr[i] == '(')
            {
                opStack.push(charExpr[i])
            }
            else if(charExpr[i] == ')')
            {
                while(opStack.peek() != '(') {
                    val aux = calculOp(opStack.pop(),numStack.pop(),numStack.pop())
                    numStack.push(aux)
                }
                opStack.pop()
            }
            else if(charExpr[i] in "+-/*" ) {
                while (!opStack.empty() && hasPrecedence(charExpr[i], opStack.peek())) {
                    val aux = calculOp(opStack.pop(),numStack.pop(),numStack.pop())
                    numStack.push(aux)
                }
                opStack.push(charExpr[i])
            }
            else break
            i++
        }
        while (!opStack.empty())
        {
            val aux = calculOp(opStack.pop(),numStack.pop(),numStack.pop())
            numStack.push(aux)
        }
        return numStack.pop()
    }

}