object MiniMax {

    private const val difficulty = 3

    var globalBest = Board()

    // human max x
    // computer min o
    fun miniMax(board: Board, isMaximizer: Boolean, level: Int, p0: Player, p1: Player) {

        if (level == difficulty) {
            board.score = heuristic(board, p0, p1)
            return
        }

        if (isMaximizer) {

            val nextMoves = nextMoves(board, p1.token)

            nextMoves.forEach {
                miniMax(it, false, level + 1, p0, p1)
                if (it.score > board.score) {
                    board.score = it.score

                }
            }

            if (level == 1) {
                globalBest.deepCopy(nextMoves.maxBy { it.score })
            }

        } else {

            val nextMoves = nextMoves(board, p0.token)

            nextMoves.forEach {
                miniMax(it, false, level + 1, p0, p1)
                if (it.score < board.score) {
                    board.score = it.score
                }
            }

            if (level == 1) {
                globalBest.deepCopy(nextMoves.minBy { it.score })
            }

        }

    }

    /**
     * @param token Char to mark.
     */
    private fun nextMoves(board: Board, token: Token): List<Board> {

        val moves = mutableListOf<Board>()

        for (i in 0 until 3) {
            for (j in 0 until 3) {
                if (board.isBlank(Pair(i, j))) {
                    val newBoard = board.make().apply { mark(token, Pair(i, j)) }
                    moves.add(newBoard)
                }
            }
        }

        return moves.distinct()

    }

    private fun hRow(board: Board, token: Token): Int {

        var score = 0

        for (row in 0 until 3) {
            score += when (board.board[row].count { it == token }) {
                3 -> 100
                2 -> 10
                1 -> 1
                else -> 0
            }
        }

        return score
    }

    private fun hCol(board: Board, token: Token): Int {

        var score = 0


        for (row in 0 until 3) {

            var xCount = 0
            var oCount = 0

            for (col in 0 until 3) {
                if (board.board[col][row] == token) {
                    xCount += 1
                }
            }

            score += when (xCount) {
                3 -> 100
                2 -> 10
                1 -> 1
                else -> 0
            }

        }

        return score

    }

    private fun hDiag(board: Board, token: Token): Int {

        var score = 0

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

        return score
    }

    private fun heuristic(board: Board, p0: Player, p1: Player): Int {
        val p0Score = hRow(board, p0.token) + hCol(board, p0.token) + hDiag(board, p0.token)
        val p1Score = hRow(board, p1.token) + hCol(board, p1.token) + hDiag(board, p1.token)
        return p1Score - p0Score
    }

}