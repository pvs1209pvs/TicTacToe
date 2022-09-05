import java.lang.StringBuilder
import java.util.*

class Board {

    val board = Array(3) { Array(3) { ' ' } }
    var score = 0

    fun make(): Board {
        val newBoard = Board()

        for (i in 0 until 3) {
            newBoard.board[i] = Arrays.copyOf(board[i], 3)
        }

        newBoard.score = score

        return newBoard

    }

    private fun isRowWin(token: Char): Boolean {

        for (row in 0 until 3) {
            if (board[row].count { it == token } == 3) {
                return true
            }
        }

        return false

    }

    private fun isColWin(token: Char): Boolean {

        for (row in 0 until 3) {

            var count = 0

            for (col in 0 until 3) {
                if (board[col][row] == token) {
                    count += 1
                }
            }

            if (count == 3) {
                return true
            }

        }

        return false

    }

    private fun isDiagonalWin(token: Char): Boolean {

        var diagCount = 0
        var antiDiagCount = 0

        for (i in 0 until 3) {

            if (board[i][i] == token) {
                diagCount += 1
            }
            if (board[i][2 - i] == token) {
                antiDiagCount += 1
            }

        }

        if (diagCount == 3) {
            return true
        }

        if (antiDiagCount == 3) {
            return true
        }

        return false

    }

    fun isWin(token: Char) = isRowWin(token) || isColWin(token) || isDiagonalWin(token)


    fun mark(token: Char, loc: Pair<Int, Int>) {
        if (withinBound(loc) && isBlank(loc)) {
            board[loc.first][loc.second] = token
        }
    }

    fun isBlank(loc: Pair<Int, Int>) = board[loc.first][loc.second] == ' '

    fun countBlank(): Int {

        var blanks = 0

        for (row in 0 until 3) {
            for (col in 0 until 3) {
                if (isBlank(Pair(row, col))) blanks++
            }
        }

        return blanks

    }

    private fun withinBound(loc: Pair<Int, Int>) = loc.first in 0..2 && loc.second in 0..2

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Board

        if (other.score != score) return false

        for (i in 0 until 3) {
            for (j in 0 until 3) {
                if (other.board[i][j] != board[i][j]) return false
            }
        }

        return true
    }

    override fun hashCode(): Int {
        return board.contentDeepHashCode()
    }

    override fun toString(): String {

        val result = StringBuilder()

        for (i in 0 until 3) {
            result.append(board[i].contentToString()).append("\n")
        }

        return result.toString()
    }
}
