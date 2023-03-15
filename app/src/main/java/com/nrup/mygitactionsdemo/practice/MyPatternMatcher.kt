package com.nrup.mygitactionsdemo.practice

import java.util.regex.Pattern

fun main() {

    println("Enter string to check")
    val myString = readLine()!!.toString()

    println("You have enter $myString to check")

    val myObject = MyPatternMatcher()
    println("Answer is ${myObject.myData(myString)}")

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

class MyPatternMatcher {

    private val myRegX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[-+_!@#$%^&*.,?]).+$"
    private var p: Pattern = Pattern.compile(myRegX)

    fun myData(myString: String): Boolean {
        return p.matcher(myString).matches()
    }
}