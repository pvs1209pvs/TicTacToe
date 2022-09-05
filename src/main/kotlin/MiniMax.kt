object MiniMax {

    fun bestMove(board: Board): Board {

        val moves = nextMoves(board, aiToken)

        moves.forEach {
            it.score = miniMax(it, humanToken)
        }


        return moves.minBy { it.score }

    }

    private fun miniMax(board: Board, token: Char): Int {

        if (board.isWin(if (token == 'x') 'o' else 'x') || board.countBlank() == 0) {
            return heuristic(board)
        }

        return if (token == 'x') {

            var localMax = Int.MIN_VALUE

            val nextMoves = nextMoves(board, 'o')

            nextMoves.forEach {
                localMax = maxOf(localMax, miniMax(it, 'o'))
            }

            localMax

        } else {

            var localMin = Int.MAX_VALUE

            val nextMoves = nextMoves(board, 'x')

            nextMoves.forEach {
                localMin = minOf(localMin, miniMax(it, 'x'))
            }

            localMin

        }

    }

    private fun heuristic(board: Board): Int {
        val x = if (board.isWin('x')) board.countBlank() + 1 else 0
        val o = if (board.isWin('o')) board.countBlank() + 1 else 0
        return x - o
    }

    /**
     * @param token Char to mark.
     */
    private fun nextMoves(board: Board, token: Char): List<Board> {

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

}