package com.nrup.mygitactionsdemo.practice

fun main() {

    println("Enter string to check")
    val myString = readLine()!!.toString()

    println("You have enter $myString to check")

    val myObject = StringValidation()
    println("Total validation failed are  ${myObject.checkValidation(myString)}")

}

/*
    ^                       : the starting of the string
    (?=.*[a-z])             : at least one lowercase character.
    (?=.*[A-Z])             : at least one uppercase character
    (?=.*\d)                : at least one digit
    (?=.*[-+_!@#$%^&*.,?])  : at least one special character
    .                       : any character except line break
    +                       : one or more times

* */

class StringValidation {

    var counter: Int = 0

    fun checkValidation(myString: String): Int {

        if (!myString.contains("[A-Z]".toRegex())) {
            counter++
        }
        if (!myString.contains("[a-z]".toRegex())) {
            counter++
        }

        if (!myString.contains("\\d".toRegex())) {
            counter++
        }

        if (!myString.contains("[-+_!@#\$%^&*.,?]".toRegex())) {
            counter++
        }
        return counter

    }
}