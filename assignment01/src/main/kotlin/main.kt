import java.util.*
import kotlin.collections.ArrayList

//중간 평가 과제

fun main() {
    val school = School()
    school.inputStudentInfo()
    school.printEachInfo()
    school.act()
    school.checkNumber()
}

enum class StudentType(val str:String) {
    BASIC("일반"), SOCCER("축구부"), VOLLEY("배구부"), SWIM("수영부")
}

//학교를 만든다.
class School{
    val scanner = Scanner(System.`in`)
    var studentList = ArrayList<Student>()
    var totalStudent = 0
    var basicType = 0
    var soccerType = 0
    var volleyType = 0
    var swimType = 0

    //소속, 이름, 학년, 국어, 영어, 수학은 키보드로 입력을 받는다.
    fun inputStudentInfo() {
        var inputNumber = 0

        while (true) {
            print("학생의 정보를 입력하시겠습니까? (1. 예, 0. 아니오) ")
            inputNumber = scanner.nextInt()

            //프로그램을 시작하면 학생 정보를 입력받으며 0 을 입력할 때 까지 계속 입력을 받는다.
            if (inputNumber == 0) {
                break
            }

            totalStudent++

            print("소속 : (일반, 축구부, 배구부, 수영부) ")
            var type = scanner.next()
            var enumType: StudentType = StudentType.BASIC
            when(type) {
                "일반" -> enumType = StudentType.BASIC
                "축구부" -> enumType = StudentType.SOCCER
                "배구부" -> enumType = StudentType.VOLLEY
                "수영부" -> enumType = StudentType.SWIM
                else -> {
                    println("일치하는 소속이 없습니다. 다시 입력해주세요.")
                    totalStudent--
                    continue
                }
            }
            print("이름 : ")
            var name = scanner.next()
            print("학년 : ")
            var grade = scanner.nextInt()
            print("국어 점수 : ")
            var korean = scanner.nextInt()
            print("영어 점수 : ")
            var english = scanner.nextInt()
            print("수학 점수 : ")
            var math = scanner.nextInt()

            if (enumType == StudentType.BASIC) {
                basicType++
            } else if (enumType == StudentType.SOCCER) {
                soccerType++
            }
            if (enumType == StudentType.VOLLEY) {
                volleyType++
            }
            if (enumType == StudentType.SWIM) {
                swimType++
            }


            // 학생 객체를 생성한다.
            var student =
                when(enumType) {
                    StudentType.BASIC -> Student(enumType,name,grade,korean,english,math)
                    //축구부 학생 : 소속, 이름, 학년, 국어, 영어, 수학, 학생 정보 출력, 공부를 한다, 축구를 한다, 수영을 한다.
                    StudentType.SOCCER -> object : Student(enumType,name,grade,korean,english,math),Swim,Soccer {
                        override fun swimming() {
                            println("$name 학생이 수영장에서 수영을 하고 있습니다")
                        }

                        override fun playSoccer() {
                            println("$name 학생이 운동장에서 축구를 하고 있습니다")
                        }
                    }
                    //배구부 학생 : 소속, 이름, 학년, 국어, 영어, 수학, 학생 정보 출력, 공부를 한다, 배구를 한다, 축구를 한다.
                    StudentType.VOLLEY -> object : Student(enumType,name,grade,korean,english,math),VolleyBall,Soccer {
                        override fun playSoccer() {
                            println("$name 학생이 운동장에서 축구를 하고 있습니다")

                        }

                        override fun playVolleyBall() {
                            println("$name 학생이 체육관에서 배구를 하고 있습니다")
                        }
                    }
                    //수영부 학생 : 소속, 이름, 학년, 국어, 영어, 수학, 학생 정보 출력, 공부를 한다, 수영을 한다, 배구를 한다.
                    StudentType.SWIM -> object : Student(enumType,name,grade,korean,english,math),Swim, VolleyBall {
                        override fun playVolleyBall() {
                            println("$name 학생이 체육관에서 배구를 하고 있습니다")
                        }

                        override fun swimming() {
                            println("$name 학생이 수영장에서 수영을 하고 있습니다")
                        }
                    }
                }
            studentList.add(student)
        }
    }

    fun act(){
        //각 학생들의 정보 출력이 끝나면 교실, 운동장, 체육관, 수영장에 학생들을 넣어준다.
        val room = StudyRoom()
        val playGround = PlayGround()
        val gym = Gym()
        val pool = SwimmingPool();


        println("-----------------------------------------")
        room.study(studentList)
        println()
        playGround.soccer(studentList)
        println()
        gym.volley(studentList)
        println()
        pool.swimming(studentList)
        println()
    }


    fun printEachInfo() {
        //입력이 끝나면 각 학생들의 정보를 출력한다.
        //
        //소속 : 00부
        //이름 : 000
        //학년 : 0학년
        //국어 : 000점
        //영어 : 000점
        //수학 : 000점
        //개인 총점 : 0000점
        //개인 평균 : 0000점
        for (st in studentList) {
            st.printInfo()
        }
    }

    fun checkNumber(){
        //전체 학생 수 : 000명
        //일반 학생 수  : 000명
        //축구부 학생 수 : 000명
        //배구부 학생 수 : 000명
        //수영부 학생 수 :000명
        //축구를 하는 학생 수 : 000명
        //배구를 하는 학생 수 : 000명
        //수영을 하는 학생 수 : 000명
        println("-----------------------------------------")
        println("전체 학생 수 : $totalStudent")
        println("일반 학생 수  : $basicType")
        println("축구부 학생 수 : $soccerType")
        println("배구부 학생 수 : $volleyType")
        println("수영부 학생 수 : $swimType")
        println("축구를 하는 학생 수 : ${soccerType+volleyType}")
        println("배구를 하는 학생 수 : ${volleyType+swimType}")
        println("수영을 하는 학생 수 : ${swimType+soccerType}")
    }
}

//소속, 이름, 학년, 국어, 영어, 수학
open class Student(var enumType:StudentType,var name:String, var grade:Int, var kor:Int, var eng:Int, var math:Int) {

    //일반 학생 : 소속, 이름, 학년, 국어, 영어, 수학, 학생 정보 출력, 공부를 한다.

    fun study() {
        println("$name 학생이 교실에서 공부하고 있습니다")
    }
    fun printInfo()
    {
        //소속 : 00부
        //이름 : 000
        //학년 : 0학년
        //국어 : 000점
        //영어 : 000점
        //수학 : 000점
        //개인 총점 : 0000점
        //개인 평균 : 0000점
        println("-----------------------------------------")
        println("소속 : ${enumType.str}")
        println("이름 : $name")
        println("학년 : $grade")
        println("국어 : $kor")
        println("영어 : $eng")
        println("수학 : $math")
        println("개인 총점 : ${kor+eng+math}")
        println("개인 평균 : ${(kor+eng+math)/3}")
        println()
    }

}

interface Swim {
    fun swimming()
}
interface Soccer {
    fun playSoccer()
}
interface VolleyBall {
    fun playVolleyBall()
}

//학교에는 교실, 운동장, 체육관, 수영장이 있다.
//교실에서는 학생들이 공부를 한다
class StudyRoom {
    fun study(studentList: ArrayList<Student>){
        for(student in studentList){
                student.study()
        }
    }
}
//운동장에서는 축구부와 배구부 학생들이 축구를 한다.
class PlayGround {
    fun soccer(studentList: ArrayList<Student>){
        for(student in studentList){
            if(student is Soccer)
                student.playSoccer()
        }
    }
}
//체육관에서는 배구부와 수영부 학생들이 배구를 한다.
class Gym {
    fun volley(studentList: ArrayList<Student>) {
        for (student in studentList) {
            if (student is VolleyBall)
                student.playVolleyBall()
        }
    }
}
//수영장에서는 수영부와 축구부 학생들이 수영을 한다.
class SwimmingPool {
    fun swimming(studentList: ArrayList<Student>) {
        for (student in studentList) {
            if (student is Swim)
                student.swimming()
        }
    }
}



