import java.util.*

const val difficulty = 3

fun main() {



//    testRun()
//    return

    val board = Board()

    println("Choose your token: X or O")
    val humanToken = readln()[0]


    while (true) {

        println("Take your turn")
        val humanLocInput = readln().map { it.digitToInt() }
        board.mark(humanToken, Pair(humanLocInput[0], humanLocInput[1]))

        println(board)

        val result = MiniMax.miniMax(board, humanToken, 1)

//        for (i in 0 until 3) {
//            board.board[i] = Arrays.copyOf(MiniMax.globalBest.board[i], 3)
//        }

//        board.score = MiniMax.globalBest.score

        println("Result")
        println(MiniMax.globalBest)
        board.deepCopy(MiniMax.globalBest)
    }
//    testRun()


}

fun testRun() {

    val board = Board()

    board.mark('x', Pair(0, 0))
    board.mark('x', Pair(1, 0))

    board.mark('o', Pair(2, 1))
    board.mark('o', Pair(2, 2))
//    println(board)

    println("s ${MiniMax.heuristic(board,'o')}")

    val board2 = Board()

    board2.mark('x', Pair(0, 0))
    board2.mark('x', Pair(1, 0))

    board2.mark('o', Pair(2, 0))
    board2.mark('o', Pair(2, 2))
//    println(board2)

    println("s ${MiniMax.heuristic(board2,'o')}")


}