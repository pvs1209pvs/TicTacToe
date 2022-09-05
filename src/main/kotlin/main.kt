import java.io.InputStream
import java.util.Scanner

const val humanToken = 'x'
const val aiToken = 'o'

fun startGame(scanner: Scanner): Result {

    var board = Board()
    var round = 0

    while (true) {

        val aiBestMove = MiniMax.bestMove(board)
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

        var humanLocInput = scanner.next().trim()
        println("test $humanLocInput")

        while (humanLocInput.length != 2 || humanLocInput.toIntOrNull() == null) {
            println("Enter 2 digits with 0 and 2")
            humanLocInput = scanner.next().trim()
        }
        var humanMove = humanLocInput.map { it.digitToInt() }

        while (!board.isBlank(Pair(humanMove[0], humanMove[1]))) {
            println("Enter a valid cell")
            humanLocInput = scanner.next().trim()
            while (humanLocInput.length != 2) {
                println("Enter 2 digits with 0 and 2")
                humanLocInput = scanner.next().trim()
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

    when (startGame(Scanner(System.`in`))) {
        Result.AI -> println("AI WON")
        Result.HUMAN -> println("Human WON")
        Result.DRAW -> println("It's a DRAW")
    }

}
