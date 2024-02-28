import java.util.Stack

class Postfix {

    private fun importanceOfOperator (op:Char) :Int
    {
        if(op == '+' || op == '-') return 1
        if(op == '*' || op == '/') return 2
        return 0
    }
    fun infixToPostfixForm(exprInfix:String) : String
    {
        val opStack = Stack<Char>() // stack ce contine operatori
        var exprPostfix = ""

        for (ch in exprInfix)
        {
            if(ch.isDigit())
            {
                exprPostfix += ch
            }
            else
            {
                exprPostfix += " "
                if(ch == '(')
                {
                    opStack.push(ch)
                }
                else if(ch == ')')
                {
                    while(!opStack.empty() && opStack.peek() != '(')
                    {
                        exprPostfix += opStack.peek()
                        opStack.pop()
                    }
                    opStack.pop()
                }
                else
                {
                    while(!opStack.empty() && importanceOfOperator(opStack.peek()) >= importanceOfOperator(ch))
                    {
                        exprPostfix += opStack.peek()
                        opStack.pop()
                    }
                    opStack.push(ch)
                }
            }

        }

        while (!opStack.empty())
        {
            exprPostfix += opStack.peek()
            opStack.pop()
        }

        return exprPostfix
    }

    fun evaluateExpr(postfix: String) : Int
    {
        var numStack = Stack<Int>()
        var finResult:Int = 0

        var numStr = String()

        for(ch in postfix)
        {
            if(ch.isDigit())
            {
                numStr += ch
            }
            else
            {
                if(numStr != "")
                {
                    numStack.push(numStr.toInt())
                    println(numStr.toInt())
                    numStr = ""
                }

                if (ch in "+-/*")
                {
                    val op2 = numStack.pop()
                    val op1 = numStack.pop()

                    when (ch) {
                        '+' -> finResult = op1 + op2
                        '-' -> finResult = op1 - op2
                        '/' -> finResult = op1 / op2
                        '*' -> finResult = op1 * op2
                    }
                    numStack.push(finResult)
                }
            }
        }
        finResult = numStack.pop()
        return finResult
    }

}