package com.nrup.mygitactionsdemo.practice

fun main() {

    println("Enter first number")
    val firstNumber:Int = readLine()!!.toInt()
    println("Enter second number")
    val secondNumber:Int = readLine()!!.toInt()

    println("Enter total numbers to print")
    val totalNumbers:Int = readLine()!!.toInt()

    println("First number is $firstNumber, Second number is $secondNumber, Total numbers are $totalNumbers")

    val myObject = FibonacciSeries()
    myObject.myFibonacciSeries(firstNumber,secondNumber,totalNumbers)
}

// 0, 1, 1, 2, 3, 5, 8, 13, 21, 34

class FibonacciSeries {

    fun myFibonacciSeries(firstNumber: Int, secondNumber: Int, totalNumbers: Int) {
        var a = firstNumber
        var b = secondNumber

        val myArrayList = arrayListOf<Int>()

        for (i in 1..totalNumbers) {
            myArrayList.add(a)
            val sum = a + b
            a = b
            b = sum

        }

        println("{${myArrayList.toList()}}")
    }
}

/* Output is :

    Enter first number
    0
    Enter second number
    1
    Enter total numbers to print
    10
    First number is 0, Second number is 1, Total numbers are 10
    {[0, 1, 1, 2, 3, 5, 8, 13, 21, 34]}

* */