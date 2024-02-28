import javax.swing.JFrame

// trying01 java project

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val calculator: Calculator = Calculator()
        calculator.setSize(230, 250)
        calculator.setTitle(" Java-Calc, PP Lab1 ")
        calculator.setResizable(false)
        calculator.setVisible(true)
        calculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    }
}