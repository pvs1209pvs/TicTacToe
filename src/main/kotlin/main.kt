import java.util.*

const val difficulty = 5

fun main() {


//    testRun()
//    return


    val board = Board()

    println("Choose your token: X or O")
//    val humanToken = readln()[0]
    val humanToken = 'x'

    while (true) {



        val moves = MiniMax.nextMoves(board,'0')
        moves.forEach{
            it.score = MiniMax.miniMax(it, humanToken, 1)
        }


        val ans = moves.minBy { it.score }


        println(ans)

        board.deepCopy(ans)

        println("Take your turn")
        val humanLocInput = readln().map { it.digitToInt() }
        board.mark(humanToken, Pair(humanLocInput[0], humanLocInput[1]))

        println(board)

    }
//    testRun()


}

fun testRun() {

    val board = Board()
    board.board[0] = arrayOf('x','o','x')
    board.board[1] = arrayOf('x','o',' ')
    board.board[2] = arrayOf('x',' ','o')
    println(MiniMax.heuristic(board))

}