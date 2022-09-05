import java.util.*

var round = 0

fun main() {

    var board = Board()

    val humanToken = 'x'
    val aiToken = 'o'

    while (true) {

        val moves = MiniMax.nextMoves(board, aiToken)
        moves.forEach {
            it.score = MiniMax.miniMax(it, humanToken)
        }

        val ans = moves.minBy { it.score }

        println(ans)
        board = ans
        ++round

        if (board.isWin(aiToken)) {
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
        if (board.isWin(humanToken)) {
            println("Human WON")
            return
        }
        if (round == 9) {
            println("It's a DRAW")
            return
        }

    }


}
