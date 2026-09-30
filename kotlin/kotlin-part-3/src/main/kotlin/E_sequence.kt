package org.example


// * 시퀀스 - 필요할 때만 계산하기

// 시퀀스는 중간 리스트를 만들지 않고, 원소 하나가 모든 단계를 통과하게 만든다.
// 원소가 몇 개 안되면 상관없지만, 수십만 개라면 리스트를 몇 번씩 새로 만드는 것이 낭비다.

// 1. 즉시 계산과 지연 계산
// [List - 즉시 계산]
// list.filter { } -> 전부 걸러서 리스트를 만든다.
// .map { }        -> 그 리스트를 전부 변환해서 또 리스트를 만든다.
// .first()        -> 그중 첫 번째를 꺼낸다.
// 단계별로 끝까지 다 처리한다. 첫 번째 하나만 필요해도 전부 계산한다.

// [Sequence - 지연 계산]
// 원소 하나가 filter -> map 을 통과하고, 그다음 원소가 다시 filter -> map을 통과한다.
// first()가 답을 얻는 순간 나머지 원소는 아예 건드리지도 않는다.

// 2. 중간 연산과 최종 연산
// [중간 연산] 시퀀스를 돌려준다. 이 시점에는 아무것도 계산하지 않는다.
// map { it * 10 }    원소를 변환한다. [3, 1, 4] -> [30, 10, 40]
// filter { it > 3 }  조건에 맞는 것만 남긴다. [3, 1, 4] -> [4]
// take (2)           앞에서 n개만 가져오고 거기서 멈춘다. [3, 1, 4] -> [3, 1]
// drop (2)           앞에서 n개를 버리고 나머지를 흘려보낸다 [3, 1, 4] -> [4]
// distinct ()        중복을 제거한다. 처음 나온 순서를 지킨다. [3, 1, 3] -> [3, 1]
// sorted ()          정렬한다. [3, 1, 4] -> [1, 3, 4]
// flatMap { }        변환한 뒤 한 겹 펼친다.(평탄화)
// ["a b", "c"] -> map [[a, b], [c]]
//              -> flatMap [a, b, c]
// onEach { }         들여다보기만 하고 원소를 흘려보낸다.

// [최종 연산] 시퀀스가 아닌 것을 돌려준다. 이때 전체가 실행된다.
// toList() / toSet() 결과를 모아 리스트나 집합으로 만든다. 가장 많이 사용된다.
// first() / find { } 조건에 맞는 첫 원소를 꺼낸다 -> 찾는 즉시 멈춘다.
// count()            개수를 센다
// sum() / sumOf { }  합계를 낸다.
// forEach { }        하나씩 꺼내 쓰고 끝낸다.
// any { } / all { }  하나라도 맞는가 / 전부 맞는가 -> 판정이 나면 즉시 멈춘다
// maxOrNull()        최댓값 전부 봐야 하므로 중간에 멈추지 못한다.
// * 최종 연산을 부르지 않으면 중간 연산은 한 줄도 실행되지 않는다. 시퀀스의 메커니즘.

// 3. 만들기
// list.asSequence() 이미 있는 컬렉션에서 사용
// sequence(1, 2, 3) 값을 나열하기
// generateSequence(1) { it + 1 } 규칙으로 무한히 만들기
// (1) : 시작값
// 1 -> 1+1=2, 2+1=3, 3+1=4,... 끝없이 이어진다.
// 끝이 없으므로 take(n) 같은 것으로 반드시 끊어 줘야한다.

// 4. 언제 쓰나 / 언제 쓰지 않나
// 쓰면 좋을 때
// - 원소가 아주 많다.(수만 개 이상)
// - 중간 연산 단계가 여러 개다
// - first, find, take 처럼 일부만 필요해서 중간에 멈출 수 있다.
// - 끝이 없는 데이터를 다룬다.

// 쓰지 않는 것이 나을 때
// - 원소가 적다. 시퀀스는 원소마다 함수 호출이 늘어나 오히려 느리다.
// - 단계가 하나뿐이다. list.map { } 한 번이면 그냥 리스트가 낫다.
// - sorted() 처럼 어차피 전부 모아야 하는 연산이 중간에 들어간다.

// * 기본은 List다. 느려서 문제가 되는 곳에서만 시퀀스를 쓴다.


fun e_isEven(n: Int): Boolean {
    println(" filter($n)")
    return n % 2 == 0
}

fun e_double(n: Int): Int {
    println(" map($n)")
    return n * 2
}

// 1. 실행 순서가 다르다.
fun e_exam1() {

    val nums = listOf(1, 2, 3, 4, 5, 6)
    println("[List]")
    val r1 = nums.filter { e_isEven(it) }.map { e_double(it) }
    println(r1)
    // filter(1)
    // filter(2)
    // filter(3)
    // filter(4)
    // filter(5)
    // filter(6)
    // map(2)
    // map(4)
    // map(6)
    // filter를 6번 다하고 그 다음 map을 3번 실행한다.

    println("[Sequence]")
    val r2 = nums.asSequence().filter { e_isEven(it) }.map { e_double(it) }.toList()
    println(r2)
    //  filter(1)
    // filter(2) -> map(2)
    // filter(3)
    // filter(4) -> map(4)
    // filter(5)
    // filter(6) -> map(6)
    // 원소 하나가 파이프를 끝까지 통과하고, 그 다음 원소가 들어간다.

    // 결과는 똑같다. 다른 것은 '언제 무엇을 계산하는가'뿐이다.

}

// 2. 최종 연산이 없으면 실행되지 않는다.
fun e_exam2() {

    val nums = listOf(1, 2, 3, 4, 5, 6)

    println("[중간 연산만]")
    val seq = nums.asSequence().filter { e_isEven(it) }.map { e_double(it) }
    println("여기까지 아무것도 출력되지 않는다.")
    println(seq)

    println("[최종 연산을 부르면]")
    println(seq.toList())

    println("[다시 사용]")
    println(seq.count())


}

fun main() {
    e_exam2()
}


















