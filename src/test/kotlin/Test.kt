import org.junit.jupiter.api.Test

class Test {

    @Test
    fun heuristicEmptyBoard(){
//        assert(MiniMax.heuristic(Board())==0)
    }


    @Test
    fun heuristic() {

        val board = Board()

        board.mark('x', Pair(0, 0))
        board.mark('x', Pair(0, 1))

        board.mark('x', Pair(1, 0))
        board.mark('x', Pair(1, 1))

//        println(MiniMax.heuristic(board))

//        assert(MiniMax.heuristic(board,) == 332)
    }
}