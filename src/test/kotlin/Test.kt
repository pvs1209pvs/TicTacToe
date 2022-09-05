import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class Test {

    private val aiToken = 'o'
    private val humanToken = 'x'
    private var round = 0
    private var board = Board()

    private fun resetGame() {
        round = 0
        board = Board()
    }

    @BeforeEach
    fun boardSet() {
        resetGame()
    }

    @AfterEach
    fun cleanup() {
        resetGame()
    }


    private fun aiMove(): Board {

        val moves = MiniMax.nextMoves(board, aiToken)

        moves.forEach {
            it.score = MiniMax.miniMax(it, humanToken)
        }

        return moves.minBy { it.score }
    }

    fun startGame(moves: Iterator<Pair<Int, Int>>): Result {

        while (true) {

            board = aiMove()
            ++round

            if (board.isWin(aiToken)) {
                return Result.AI
            }
            if (round == 9) {
                return Result.DRAW
            }

            board.mark(humanToken, moves.next())
            ++round
            if (board.isWin(humanToken)) {
                return Result.HUMAN
            }
            if (round == 9) {
                return Result.DRAW
            }

        }

    }


    @Test
    fun `draw when human plays center`() {
        val moves = listOf(
            Pair(1, 1),
            Pair(0, 2),
            Pair(1, 0),
            Pair(2, 2),
        ).iterator()
        assert(startGame(moves) == Result.DRAW)

    }

    @Test
    fun `ai wins when human plays side 1`() {
        val moves = listOf(
            Pair(1, 0),
            Pair(0, 2),
            Pair(2, 1)
        ).iterator()
        assert(startGame(moves) == Result.AI)
    }

    @Test
    fun `ai wins when human plays side 2`() {
        val moves = listOf(
            Pair(1, 0),
            Pair(0, 2),
            Pair(2, 2)
        ).iterator()
        assert(startGame(moves) == Result.AI)
    }

}