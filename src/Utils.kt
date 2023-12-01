import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readLines

/**
 * Reads lines from the given input txt file.
 */
fun readInput(day: String, name: String): List<String> {
    val fileName  = "d$day-$name.txt"
    println("Reading input file: $fileName")
    return Path("src/inputs/$fileName").readLines()
}

/**
 * Reads lines from the given raw string.
 */
fun readString(content: String) = content.split('\n')

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)

fun checkSample(result: Any, expected: Any) {
    check(result == expected) {
        println("Check: Result is $result but should be $expected")
    }
    println("Check ok: $result")
}
