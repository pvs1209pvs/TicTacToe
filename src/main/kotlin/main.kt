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

        var humanLocInput = readln().trim()

        while (humanLocInput.length != 2 || humanLocInput.toIntOrNull()==null) {
            println("Enter 2 digits with 0 and 2")
            humanLocInput = readln().trim()
        }
        var humanMove = humanLocInput.map { it.digitToInt() }

        while (!board.isBlank(Pair(humanMove[0], humanMove[1]))) {
            println("Enter a valid cell")
            humanLocInput = readln().trim()
            while (humanLocInput.length != 2) {
                println("Enter 2 digits with 0 and 2")
                humanLocInput = readln().trim()
            }
            humanMove = humanLocInput.map { it.digitToInt() }
        }

        board.mark(humanToken, Pair(humanMove[0], humanMove[1]))

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
