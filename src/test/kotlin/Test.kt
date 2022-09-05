import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.ByteArrayInputStream
import java.io.InputStream
import java.util.Scanner

class Test {

    private lateinit var systemInBackup : InputStream

    @BeforeEach
    fun saveSystemInInstance() {
        systemInBackup = System.`in`
    }

    @AfterEach
    fun resetSystemInInstance() {
        System.setIn(systemInBackup)
    }

    @Test
    fun `draw when human plays center`() {
        System.setIn(ByteArrayInputStream("11 02 10 22".toByteArray()))
        assert(startGame(Scanner(System.`in`))==Result.DRAW)
    }

    @Test
    fun `draw when human plays corner`() {
        System.setIn(ByteArrayInputStream("02 11 10 22".toByteArray()))
        assert(startGame(Scanner(System.`in`))== Result.DRAW)
    }

    @Test
    fun `ai wins when human plays side`() {
        System.setIn(ByteArrayInputStream("10 02 21".toByteArray()))
        assert(startGame(Scanner(System.`in`))== Result.AI)
    }


}