const val difficulty = 3

fun main() {


    val board = Board()

    println("Choose your token: X or O")

    val p0 = Player(readln()[0])
    val p1 = Player(if (p0.token == 'x') 'o' else 'x')

    while (true) {

        println("Take your turn")
        val humanLocInput = readln().map { it.digitToInt() }
        board.mark(p0.token, Pair(humanLocInput[0], humanLocInput[1]))

        println(board)

        MiniMax.miniMax(board, true, 1, p0,p1)

        println("Result")
        println(MiniMax.globalBest)
        board.deepCopy(MiniMax.globalBest)
    }
//    testRun()


}

fun testRun() {


}