import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.ByteArrayInputStream
import java.io.InputStream
import java.util.Scanner

class Test {

    private val aiToken = 'o'
    private val humanToken = 'x'
    private var round = 0
    private var board = Board()

    lateinit var sysInBackup: InputStream

    @BeforeEach
    fun boardSet() {
        sysInBackup = System.`in` // backup System.in to restore it later
        round = 0
        board = Board()

    }

    @AfterEach
    fun cleanup() {
        System.setIn(sysInBackup)

    }

    @Test
    fun drawWhenHumanPlaysCenter() {

        System.setIn(ByteArrayInputStream("11 02 11 22".toByteArray()))
        val humanInput = Scanner(System.`in`)

        val result: Result

        while (true) {

            board = aiMove()
            ++round

            if (board.isWin(aiToken)) {
                result = Result.AI
                break
            }
            if (round == 9) {
                result = Result.DRAW
                break
            }

            while (humanInput.hasNext()) {
                val humanMove = humanInput.next()
                board.mark(humanToken, Pair(humanMove[0].digitToInt(), humanMove[1].digitToInt()))
                ++round
            }


            if (board.isWin(humanToken)) {
                result = Result.HUMAN
                break
            }
            if (round == 9) {
                result = Result.DRAW
                break
            }


        }

        assert(result == Result.DRAW)

    }

}