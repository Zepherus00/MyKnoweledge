package com.example.javaapp

fun main() {
    val a = "name"
    val b = "name"
    val c = """
            snksfn sgsf %s
        """.format(a).trimIndent()
    println(c)
}