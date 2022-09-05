object MiniMax {

    var globalBest = Board()

    // human max x
    // computer min o
    fun miniMax(board: Board, token: Char, level: Int): Int { // x

//        if (nextMoves(board, if (token == 'x') 'o' else 'x').isEmpty()) {
//            return heuristic(board)
//        }

//        if (nextMoves(board, token).isEmpty()) {
//            return heuristic(board)
//        }

        if (board.isWin(if (token == 'x') 'o' else 'x') || board.countBlank() == 0) {
            return heuristic(board)
        }

//        if (level==difficulty) {
//            return heuristic(board)
//        }

        return if (token == 'x') {

            var localMax = Int.MIN_VALUE

            val nextMoves = nextMoves(board, 'o')

            nextMoves.forEach {
                localMax = maxOf(localMax, miniMax(it, 'o', level + 1))
            }

            localMax

        } else {

            var localMin = Int.MAX_VALUE

            val nextMoves = nextMoves(board, 'x')

            nextMoves.forEach {
                localMin = minOf(localMin, miniMax(it, 'x', level + 1))
            }

            localMin

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

        return moves.distinct()

    }


    fun heuristic(board: Board): Int {

        var x = 0

        if (board.isWin('x')) {
            x = 1 + board.countBlank()
//            println("x $x")
        }

        var o = 0

        if (board.isWin('o')) {
            o = board.countBlank() + 1
//            println("o $o")
        }

        return x - o

    }


}