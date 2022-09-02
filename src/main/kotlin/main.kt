import java.util.*

const val difficulty = 3

fun main() {



    val board = Board()

    println("Choose your token: X or O")
    val humanToken = readln()[0]


    while (true) {

        println("Take your turn")
        val humanLocInput = readln().map { it.digitToInt() }
        board.mark(humanToken, Pair(humanLocInput[0], humanLocInput[1]))

        println(board)

        MiniMax.miniMax(board, humanToken, 1)

        println("Result")
        println(MiniMax.globalBest)
        board.deepCopy(MiniMax.globalBest)
    }
//    testRun()


}

fun testRun() {


}