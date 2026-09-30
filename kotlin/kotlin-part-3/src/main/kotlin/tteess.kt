package org.example

fun main() {
    fun calculate(a: Int, b: Int, operator: (Int, Int) -> Int): Int {
        return operator(a, b) // 넘겨받은 함수를 여기서 실행한다.
    }

    println(calculate(10, 20, {a, b ->  a+ b }))
}
