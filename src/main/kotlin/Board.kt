import java.lang.StringBuilder
import java.util.*

class Board() {

    val board = Array(3) { Array(3) { ' ' } }
    var score = 0

    init {
//        b = Board()
    }

    fun make(): Board {
        val newBoard = Board()

        for (i in 0 until 3){
            newBoard.board[i] = Arrays.copyOf(board[i],3)
        }

        newBoard.score = score

        return newBoard


    }

    fun deepCopy(other:Board){

        for (i in 0 until 3){
            for (j in 0 until  3){
                board[i][j] = other.board[i][j]
            }
        }

        score = other.score

    }

    fun mark(token: Char, loc: Pair<Int, Int>) {
        if (withinBound(loc) && isBlank(loc)) {
            board[loc.first][loc.second] = token
        }
    }

    fun isBlank(loc: Pair<Int, Int>) = board[loc.first][loc.second] == ' '

    private fun withinBound(loc: Pair<Int, Int>) = loc.first in 0..2 && loc.second in 0..2

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Board

        if(other.score != score) return false

        for (i in 0 until 3){
            for (j in 0 until 3){
                if(other.board[i][j] != board[i][j]) return false
            }
        }

        return true
    }

    override fun hashCode(): Int {
        return board.contentDeepHashCode()
    }

    override fun toString(): String {

        val result = StringBuilder()

        result.append(score).append("\n")

        for (i in 0 until  3){
            result.append(board[i].contentToString()).append("\n")
        }

        return result.toString()
    }
}
