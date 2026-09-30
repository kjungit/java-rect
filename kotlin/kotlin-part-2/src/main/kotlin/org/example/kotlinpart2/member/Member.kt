package org.example.kotlinpart2.member

class Member (var name: String, var email: String, var phone: String) {
    // 아직 회원이 들어오지 않은 빈 칸을 만들 때는 부생성자

    constructor() : this("", "", "")

    override fun toString(): String {
        return "name: $name, email: $email, phone: $phone"
    }
}