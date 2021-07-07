import java.lang.Exception

fun main(args : Array<String>) {
    runTestcases()
}

fun transformString(input: String): String {
    if (input.isBlank()) {
        throw Exception("Error: The input was not valid! (Empty String)")
    }

    val digitsPair: Array<Pair<String, Int>> = arrayOf(Pair("zero", 0), Pair("one", 1), Pair("two", 2), Pair("three", 3), Pair("four", 4),
            Pair("five", 5), Pair("six", 6), Pair("seven", 7), Pair("eight", 8), Pair("nine", 9))
    val splitStrings: Array<String>
    try {
        splitStrings = input.toLowerCase().split("-", ignoreCase = true, limit = 0).toTypedArray()
    } catch (e: Exception) {
        throw Exception("Error: Input " + input + "could not be parsed!")
    }

    var resultString: String = ""
    var resultCalculation: Int = 0
    for (splitString in splitStrings) {
        var foundDigit: Boolean = false
        for (digitPair in digitsPair) {
            if (splitString.equals(digitPair.first)) {
                resultString += digitPair.second.toString()
                resultCalculation += digitPair.second
                foundDigit = true
                break
            }
        }
        if (!foundDigit) {
            throw Exception("Error: " + splitString + " is not a valid digit!")
        }
        resultString += "+"
    }
    resultString = resultString.substring(0, resultString.length - 1) + "="
    resultString += resultCalculation.toString()

    return resultString
}

fun runTestcases() {
    // "normal" input
    var writtenNumbers: String = "four-three-two-zero-eight" // Should evaluate to "4+3+2+0+8=17"
    var digitNumbers: String = transformString(writtenNumbers)
    var resultComparison: String = "4+3+2+0+8=17"
    require(digitNumbers.compareTo(resultComparison) == 0) {"ERROR: Real result should be $resultComparison"}
    println(digitNumbers + " test success")

    // only one digit as input
    writtenNumbers = "three" // Should evaluate to "3=3"
    digitNumbers = transformString(writtenNumbers)
    resultComparison = "3=3"
    require(digitNumbers.compareTo(resultComparison) == 0) {"ERROR: Real result should be $resultComparison"}
    println(digitNumbers + " test success")

    // all digits as input
    writtenNumbers = "nine-two-seven-four-zero-eight-one-three-six-five" // Should evaluate to "9+2+7+4+0+8+1+3+6+5=45"
    digitNumbers = transformString(writtenNumbers)
    resultComparison = "9+2+7+4+0+8+1+3+6+5=45"
    require(digitNumbers.compareTo(resultComparison) == 0) {"ERROR: Real result should be $resultComparison"}
    println(digitNumbers + " test success")

    // input with lower- and uppercase (which is valid in this implementation)
    writtenNumbers = "Three-sEven-NINE-one-twO" // Should evaluate to "3+7+9+1+2=22"
    digitNumbers = transformString(writtenNumbers)
    resultComparison = "3+7+9+1+2=22"
    require(digitNumbers.compareTo(resultComparison) == 0) {"ERROR: Real result should be $resultComparison"}
    println(digitNumbers + " test success")

    // invalid input 1
    var threwException: Boolean = false
    writtenNumbers = "-three"
    try {
        digitNumbers = transformString(writtenNumbers)
    } catch (e: Exception) {
        //println(e.message)
        threwException = true
    }
    require(threwException == true) {"ERROR: Should've received an exception for input $resultComparison but didn't!"}
    println(writtenNumbers + " test success")

    // invalid input 2
    threwException = false
    writtenNumbers = "thre"
    try {
        digitNumbers = transformString(writtenNumbers)
    } catch (e: Exception) {
        //println(e.message)
        threwException = true
    }
    require(threwException == true) {"ERROR: Should've received an exception for input $resultComparison but didn't!"}
    println(writtenNumbers + " test success")
    threwException = false // reset for other invalid tests

    // invalid input 3 (empty string)
    threwException = false
    writtenNumbers = ""
    try {
        digitNumbers = transformString(writtenNumbers)
    } catch (e: Exception) {
        //println(e.message)
        threwException = true
    }
    require(threwException == true) {"ERROR: Should've received an exception for input $resultComparison but didn't!"}
    println("Empty input test success")
    threwException = false // reset for other invalid tests

    // invalid input 4
    threwException = false
    writtenNumbers = "five-"
    try {
        digitNumbers = transformString(writtenNumbers)
    } catch (e: Exception) {
        //println(e.message)
        threwException = true
    }
    require(threwException == true) {"ERROR: Should've received an exception for input $resultComparison but didn't!"}
    println(writtenNumbers + " test success")
    threwException = false // reset for other invalid tests

    // invalid input 5
    threwException = false
    writtenNumbers = "five-si"
    try {
        digitNumbers = transformString(writtenNumbers)
    } catch (e: Exception) {
        //println(e.message)
        threwException = true
    }
    require(threwException == true) {"ERROR: Should've received an exception for input $resultComparison but didn't!"}
    println(writtenNumbers + " test success")
    threwException = false // reset for other invalid tests

    // invalid input 6
    threwException = false
    writtenNumbers = "si"
    try {
        digitNumbers = transformString(writtenNumbers)
    } catch (e: Exception) {
        //println(e.message)
        threwException = true
    }
    require(threwException == true) {"ERROR: Should've received an exception for input $resultComparison but didn't!"}
    println(writtenNumbers + " test success")
    threwException = false // reset for other invalid tests
}
