import java.util.*

fun main() {
    var school = Shcool()
    school.inputStudentInfo()
    school.printInfo()
    school.printSubjectInfo()
}

//학교 클래스
class Shcool {
    val scanner = Scanner(System.`in`)

    var totalStudent = 0
    var studentList = ArrayList<Student>()

    // 학생 정보 입력
    fun inputStudentInfo() {
        while (true) {
            //프로그램이 시작하면 다음과 같이 입력을 받는다.
            //학생 정보를 입력해주세요
            //이름 : 홍길동
            //국어 : 100
            //영어 : 100
            //수학 : 100
            //계속 입력하시겠습니까?(1. 예, 2. 아니오) :
            print("이름 : ")
            var name = scanner.next()
            print("국어 : ")
            var korean = scanner.nextInt()
            print("영어 : ")
            var english = scanner.nextInt()
            print("수학 : ")
            var math = scanner.nextInt()

            totalStudent++
            var student = Student().apply {
                this.name = name
                this.kor = korean
                this.eng = english
                this.math = math
            }
            studentList.add(student)


            println()
            print("계속 입력하시겠습니까? (1. 예, 2. 아니오) ")
            var inputNumber = scanner.nextInt()

            //프로그램을 시작하면 학생 정보를 입력받으며 2를 입력할 때까지 계속 입력을 받는다.
            if (inputNumber == 2) {
                break
            }
        }
    }

    fun printInfo() {
        //각 학생들의 정보를 모두 출력한다.
        //이름 : 홍길동
        //국어 : 100
        //영어 : 100
        //수학 : 100
        //총점 : 300
        //평균 : 100
        for (student in studentList) {
            println("-----------------------")
            println("이름 : ${student.name}")
            println("국어 : ${student.kor}")
            println("영어 : ${student.eng}")
            println("수학 : ${student.math}")
            println("총점 : ${student.kor + student.eng + student.math}")
            println("평균 : ${(student.kor + student.eng + student.math)/3}")
        }
    }
    fun printSubjectInfo() {
        //국어 전체 총점 : 000점
        //영어 전체 총점 : 000점
        //수학 전체 총점 : 000점
        //국어 전체 평균 : 000점
        //영어 전체 평균 : 000점
        //수학 전체 평균 : 000점
        var kor_total = 0
        var eng_total = 0
        var math_total = 0
        for (student in studentList) {
            kor_total += student.kor
            eng_total += student.eng
            math_total += student.math
        }
        println("-----------------------")
        println("국어 전체 총점 : $kor_total")
        println("영어 전체 총점 : $eng_total")
        println("수학 전체 총점 : $math_total")
        println("국어 전체 평균 : ${kor_total/totalStudent}")
        println("영어 전체 평균 : ${eng_total/totalStudent}")
        println("수학 전체 평균 : ${math_total/totalStudent}")
    }
}

//학생은 이름, 국어, 영어, 수학, 총점, 평균
class Student {
    lateinit var name:String
    var kor:Int = 0
    var eng:Int =0
    var math:Int =0
}



