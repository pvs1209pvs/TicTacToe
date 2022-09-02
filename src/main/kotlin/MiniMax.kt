object MiniMax {

    var globalBest = Board()
    // human max x
    // computer min o
    fun miniMax(board: Board, token: Char, level: Int) { // x

        if (level == difficulty) {
            board.score = heuristic(board, if (level % 2 == 0) 'o' else 'x')
            return
        }

        if (token == 'x') {

            val nextMoves = nextMoves(board, 'o')

            if (nextMoves.isEmpty()) return

            // B C
            nextMoves.forEach {
                miniMax(it, 'o', level + 1)
            }

            board.score = nextMoves.minBy { it.score }.score
            if(level != 1)
            globalBest = board


        } else{ // token = o

            val nextMoves = nextMoves(board, 'x')

            if (nextMoves.isEmpty()) return

            // D E F G
            nextMoves.forEach {
                miniMax(it, 'x', level + 1)
            }

            board.score = nextMoves.maxBy { it.score }.score
            if (level != 1)
            globalBest = board

        }

    }

    /**
     * @param token Char to mark.
     */
    fun nextMoves(board: Board, token: Char): List<Board> {

        val moves = mutableListOf<Board>()

        for (i in 0 until 3) {
            for (j in 0 until 3) {
                if (board.isBlank(Pair(i, j))) {
                    val newBoard = board.make().apply { mark(token, Pair(i, j)) }
                    moves.add(newBoard)
                }
            }
        }



        return moves

    }

    fun heuristic(board: Board, token: Char): Int {

        var score = 0

        for (row in 0 until 3) {
            score += when (board.board[row].count { it == token }) {
                3 -> 100
                2 -> 10
                1 -> 1
                else -> 0
            }
        }

        for (row in 0 until 3) {

            var colCount = 0

            for (col in 0 until 3) {
                if (board.board[col][row] == token) {
                    colCount += 1
                }
            }

            score += when (colCount) {
                3 -> 100
                2 -> 10
                1 -> 1
                else -> 0
            }

        }

        var diagCount = 0
        var antiDiagCount = 0

        for (i in 0 until 3) {
            if (board.board[i][i] == token) {
                diagCount += 1
            }
            if (board.board[i][2 - i] == token) {
                antiDiagCount += 1
            }
        }

        score += when (diagCount) {
            3 -> 100
            2 -> 10
            1 -> 1
            else -> 0
        }

        score += when (antiDiagCount) {
            3 -> 100
            2 -> 10
            1 -> 1
            else -> 0
        }

        return if (token == 'x') score else -1 * score

    }

}