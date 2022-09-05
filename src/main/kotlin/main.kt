import java.util.*

const val difficulty = 5
var round = 0

fun main() {


//    testRun()
//    return


    var board = Board()

    println("Choose your token: X or O")
//    val humanToken = readln()[0]

    val humanToken = 'x'

    while (true) {

        val moves = MiniMax.nextMoves(board, 'o')
        moves.forEach {
            it.score = MiniMax.miniMax(it, humanToken, 1)
        }

        val ans = moves.minBy { it.score }

        println(ans)
        board = ans
        ++round

        if (board.isWin('o')) {
            println("AI WON")
            break
        }
        if (round == 9) {
            println("It's a DRAW")
            break
        }

        println("Take your turn")
        var humanLocInput = readln().map { it.digitToInt() }

        while (!board.isBlank(Pair(humanLocInput[0], humanLocInput[1]))){
            println("Enter a valid cell")
            humanLocInput = readln().map { it.digitToInt() }
        }

        board.mark(humanToken, Pair(humanLocInput[0], humanLocInput[1]))

        println(board)
        ++round
        if (board.isWin('x')) {
            println("Human WON")
            return
        }
        if (round == 9) {
            println("It's a DRAW")
            return
        }

    }


}

fun testRun() {

    val board = Board()
    board.board[0] = arrayOf('x', 'o', 'x')
    board.board[1] = arrayOf('x', 'o', ' ')
    board.board[2] = arrayOf('x', ' ', 'o')
    println(MiniMax.heuristic(board))

}