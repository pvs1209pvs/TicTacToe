import java.util.logging.Logger

var round = 0
var board = Board()
const val humanToken = 'x'
const val aiToken = 'o'


fun aiMove(): Board {

    val moves = MiniMax.nextMoves(board, aiToken)

    moves.forEach {
        it.score = MiniMax.miniMax(it, humanToken)
    }

    return moves.minBy { it.score }
}

fun startGame(): Result {

    while (true) {

        val aiBestMove = aiMove()
        println(aiBestMove)
        board = aiBestMove
        ++round

        if (board.isWin(aiToken)) {
            return Result.AI
        }
        if (round == 9) {
            return Result.DRAW
        }

        println("Take your turn")

        var humanLocInput = readln().trim()

        while (humanLocInput.length != 2 || humanLocInput.toIntOrNull() == null) {
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
            return Result.HUMAN
        }
        if (round == 9) {
            return Result.DRAW
        }

    }

}

fun main() {



    when (startGame()) {
        Result.AI -> println("AI WON")
        Result.HUMAN -> println("Human WON")
        Result.DRAW -> println("It's a DRAW")
    }

}
