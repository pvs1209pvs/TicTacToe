import org.junit.jupiter.api.Test

class Test {

    @Test
    fun heuristicEmptyBoard(){
        assert(MiniMax.heuristic(Board(),'x')==0)
    }

    @Test
    fun heuristic() {

        val board = Board()

        board.mark('x', Pair(0, 0))
        board.mark('x', Pair(0, 1))
        board.mark('x', Pair(0, 2))

        board.mark('x', Pair(1, 0))
        board.mark('x', Pair(1, 1))

        board.mark('x', Pair(2, 0))

        assert(MiniMax.heuristic(board, 'x') == 332)
    }
}