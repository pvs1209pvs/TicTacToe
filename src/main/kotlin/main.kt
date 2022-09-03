
fun main() {

    var round = 0
    val board = Board()

    println("Choose your token:\n1. X\n2. O")
    val userInput = readLine()!!.toInt()

    val p0 = Player(if (userInput == 1) Token.X else Token.O)
    val p1 = Player(if (p0.token == Token.X) Token.O else Token.X)

    while (true) {

        println("Take your turn")
        val humanLocInput = readln().map { it.digitToInt() }
        board.mark(p0.token, Pair(humanLocInput[0], humanLocInput[1]))
        println(board)
        round++

        if(board.isWin(p0.token)){
            println("p0 is the winner")
            break
        }
        if(round==9){
            println("It's a draw")
            break
        }



        MiniMax.miniMax(board, true, 1, p0, p1)
        ++round

        MiniMax.globalBest.also {
            println(it)
            board.deepCopy(it)

        }


        if(board.isWin(p1.token)){
            println("p1 is the winner")
            break
        }
        if(round==9){
            println("It's a draw")
            break
        }

    }


}

fun testRun() {


}