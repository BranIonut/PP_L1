import java.awt.BorderLayout
import java.awt.Color
import java.awt.FlowLayout
import javax.swing.*

class Calculator : JFrame() {
    private var digits: Array<JButton> = arrayOf(
        JButton(" 0 "),
        JButton(" 1 "),
        JButton(" 2 "),
        JButton(" 3 "),
        JButton(" 4 "),
        JButton(" 5 "),
        JButton(" 6 "),
        JButton(" 7 "),
        JButton(" 8 "),
        JButton(" 9 ")
    )

    private var operators: Array<JButton> = arrayOf(
        JButton(" + "),
        JButton(" - "),
        JButton(" * "),
        JButton(" / "),
        JButton(" = "),
        JButton(" C "),
        JButton(" ( "),
        JButton(" ) "),
        JButton("<-")
    )

    private var operValues: Array<String> = arrayOf("+", "-", "*", "/", "=", "", "(", ")")

    private var operator: Char = '0'

    private var area: JTextArea = JTextArea(3, 5)

    init {
        add(JScrollPane(area), BorderLayout.NORTH)
        val buttonPanel = JPanel()
        buttonPanel.layout = FlowLayout()

        for (i in digits.indices) buttonPanel.add(digits[i])

        for (i in operators.indices) buttonPanel.add(operators[i])

        add(buttonPanel, BorderLayout.CENTER)
        area.foreground = Color.BLACK
        area.background = Color.WHITE
        area.lineWrap = true
        area.wrapStyleWord = true
        area.isEditable = false

        for (i in digits.indices) {
            digits[i].addActionListener { area.append(i.toString()) }
        }

        for (i in operators.indices) {
            operators[i].addActionListener {
                if (i == 0 || i == 1 || i == 2 || i == 3)
                {
//                    if (area.text=="" && finalI==0 || finalI==1)
//                    {
//                        area.text = oper_values[finalI]
//                    }
                    if (!area.text[area.text.length-1].isDigit() && area.text[area.text.length-1] != '(' && area.text[area.text.length-1] != ')') {
                        area.text = area.text.dropLast(1)
                    }
                }
                if (i == 5) {
                    area.text = ""
                } else if (i == 4) {

                    val text = area.text
                    val obj = Postfix();
                    val finalResult = obj.evaluateExpr(obj.infixToPostfixForm(text))
                    area.text = finalResult.toString()

                } else if (i == 8) {
                    if (area.text != "") {
                        var aux = area.text.dropLast(1)
                        area.text = aux
                        aux = ""
                    }

                } else {
                    area.append(operValues[i])
                    operator = operValues[i][0]
                }
            }
        }
    }
}
