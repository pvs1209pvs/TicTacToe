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

        for (i in 0 until 3) {
            board.board[i] = Arrays.copyOf(MiniMax.globalBest.board[i], 3)
        }

        board.score = MiniMax.globalBest.score

        println(board)
    }
//    testRun()


}

fun testRun() {
    var board = Board()
    val humanToken = 'x'

    board.mark(humanToken, Pair(0, 0))

    println("start $board")

    while (true) {
        MiniMax.miniMax(board, 'x', 1) // comp
        board = MiniMax.globalBest
//        println("global ${MiniMax.globalBest}")
//        println("end $board")
    }


}