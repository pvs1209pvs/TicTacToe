object MiniMax {

    var globalBest = Board()

    // human max x
    // computer min o
    fun miniMax(board: Board, token: Char, level: Int) { // x

        if (level == difficulty) {
            board.score = heuristic(board)
            return
        }

        if (token == 'x') {

            val nextMoves = nextMoves(board, 'o')

            nextMoves.forEach {
                miniMax(it, 'o', level + 1)
                if (it.score > board.score) {
                    board.score = it.score

                }
            }

            if (level == 1) {
                globalBest.deepCopy(nextMoves.maxBy { it.score })
            }

        } else {

            val nextMoves = nextMoves(board, 'x')

            nextMoves.forEach {
                miniMax(it, 'x', level + 1)
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


    fun hRow(board: Board, token: Char): Int {

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

    fun hCol(board: Board, token: Char): Int {

        var score = 0

        // xxo
//        if(board.board[0][0] =='x' && board.board[1][0] == 'x' && board.board[2][0]=='o'){
//            score += 1
//        }
//        if(board.board[0][1] =='x' && board.board[1][1] == 'x' && board.board[2][1]=='o'){
//            score += 1
//        }
//        if(board.board[0][2] =='x' && board.board[1][2] == 'x' && board.board[2][2]=='o'){
//            score += 1
//        }
//
//        if(board.board[0][0] =='o' && board.board[1][0] == 'x' && board.board[2][0]=='o'){
//            score += 1
//        }
//        if(board.board[0][1] =='x' && board.board[1][1] == 'x' && board.board[2][1]=='o'){
//            score += 1
//        }
//        if(board.board[0][2] =='x' && board.board[1][2] == 'x' && board.board[2][2]=='o'){
//            score += 1
//        }
//

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

    fun hDiag(board: Board, token: Char): Int {

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

    fun heuristic(board: Board): Int {

        var xScore = hRow(board, 'x') + hCol(board, 'x') + hDiag(board, 'x')
        var oScore = hRow(board, 'o') + hCol(board, 'o') + hDiag(board, 'o')

        return oScore - xScore

    }

}