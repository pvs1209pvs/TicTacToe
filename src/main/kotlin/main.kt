import java.util.*

const val difficulty = 3

fun main() {



    val board = Board()

    println("Choose your token: X or O")
//    val humanToken = readln()[0]
    val humanToken = 'x'

    while (true) {

        println("Take your turn")
        val humanLocInput = readln().map { it.digitToInt() }
        board.mark(humanToken, Pair(humanLocInput[0], humanLocInput[1]))

        println(board)

        val moves = MiniMax.nextMoves(board,'0')
        moves.forEach{
            it.score = MiniMax.miniMax(it, humanToken, 1)
            println(it.score)
        }


        val ans = moves.minBy { it.score }


        println("cool")
        println(ans)

        board.deepCopy(ans)

//        println("Result")
//        println(MiniMax.globalBest)
//        board.deepCopy(MiniMax.globalBest)
    }
//    testRun()


}

fun testRun() {


}