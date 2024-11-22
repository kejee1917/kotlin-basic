import kotlin.random.Random


//기본 접근 제어자가 public
//기본적으로 클래스, 메소드 final

//const : 상수 (최상위 레벨에 상수변수)
const val num = 20 // java는 클래스가 필수지만 코틀린은 그렇지 않다. 이렇게 class 바깥으로 빼는 것도 가능.

fun main(args: Array<String>) {

    // [타입]
    //val : 상수 (자바의 final)
    //var : 변수
    var int: Int = 10
    var name = ""
    var point = 3.3

    // [형변환 .to~]
    var i = 10
    var l = 20L
    l = i.toLong()
    i = l.toInt()

    // [print]
    val userName = "kej"
    print("제 이름은 $userName 입니다.")
    print("제 이름은 ${userName}입니다.") //${} 수식이나 바로 뒤에 문자열 결합 시 사용

    // [random]
    val nextInt = Random.nextInt(0, 100) // 0~ 99

    // [list]
    // listOf : 불변 / mutableListOf : 가변

    // [for]
    val items = listOf(1, 2, 3, 4, 5)
    for(item in items) {
        print(item)
    }

    // [배열]
    val arr = arrayOf(1, 2, 3)
    arr.size //length X

    // [null safety]
    // 기본적으로 코틀린은 null을 변수에 못 넣는다고 가정함. null을 넣고 싶으면 타입 뒤에 ? 붙여주기
    var name1: String? = null
    var name2: String = ""
    // 그럼 name1과 name2는 같은 타입일까? 아니다! (String과 String? 타입은 서로 다른 타입)
    // name2 = name1 이게 컴파일 오류 뱉음
    // (1) 위에 것을 정상적으로 하기 위해서는 name1이 null이 아님을 체크해줘야 함.
    if(name1 != null) { // null이 아님은 체크해주면서 name1이 String으로 smart casting됨
        name2 = name1
    }

    // (2) 아니면 다른 방법 (!!)
    name2 = name1!! // name1이 절대로 null이 아니라는 개발자의 보장. 그러니 조심.

    // (3) 이렇게 더 간단히 사용 가능 (?.)
    name1?.let{// name1이 null이 아니라면 let 블럭을 실행하자!
        name2 = name1
    }

    // [Nothing] 으로 선언한 변수는 null만 대입 가능
    val nonData: Nothing? = null

    // [lateinit]
    // 코틀린은 프로퍼티(속성)에 선언과 동시에 초기화가 필수이다. (지역변수 말고 클래스의 프로퍼티=멤버변수)
    // lateinit: 내부에서 null로 초기화를 한 후 이후에 들어 오는 값으로 변경하는 방식임
    // 따라서 Primitive Type에는 사용할 수 없다.
    // (왜냐하면 non-Nullable한 int는 그대로 java의 int로 디컴파일 되었지만 nullable한 int는 Integer가 된다.
    //  lateinit은 내부에서 null로 초기화하는 로직을 태우는데 primitive type은 null이 될 수 없기 때문이다)
    lateinit var data3: String
    data3 = "";

    // [to 키워드]
    // key to value는 Pair(key, value)
    // Pair란? 내부 객체의 타입이 다를수 있는...?

}

// [함수]
fun add(a: Int, b: Int): Int {
    return a + b
}

// 코틀린은 함수 바디 부분이 한줄이라면 아래처럼 간략화 가능
fun addSimple(a: Int, b: Int): Int = a + b

// return 타입도 생략 가능, 하지만 인풋(파라미터) 타입은 무조건 명시해야 함
fun addReturn(a: Int, b: Int) = a + b

// overloading (코틀린은 이런식으로도 가능)
fun addReturn(a: Int, b: Int, c: Int = 0) = a + b

// 반환 타입이 없는 경우에는 Unit이 생략된 것 (Unit은 자바의 void 개념)
fun some() {
    println("test")
}

fun plus(data1: Int, data2: Int = 10): Int {
    return data1 + data2
}

// [상속]
// abstract class를 상속받으려면 open 키워드 필요없음
// 일반 class를 상속받으려면 open 명시 필요. 꼭 () 소괄호 붙여 주기 (기본 생성자)
//
// interface는 class명 옆에 ,(콤마) 찍고 implements 하면 됨(소괄호 안붙임).

// [타입 체크] is 사용 : 반환되는 값이 true면 자동 타입 캐스팅
// if(dog is Dog) //dog타입이 Dog의 타입이 맞느냐는 의미

// [타입 캐스팅] as 사용
// cat as Dog //cat을 Dog로 강제형변환 하겠다는 의미

// [class]
class Box<T>(val value: T) { // class의 생성자에는 val, var와 같은 변수 종류 명시 필수, 참고로 메소드는 아님!

}

// [콜백함수(고차함수): 함수를 매개변수로 전달하는 함수]
//fun main() {
//    myFunc({ //이 블럭 안에 함수를 작성
//        println("함수 호출2")
//    })
//}


//코틀린에서는 전달하는 인자가 함수인 경우 그 함수를 바깥으로 뺄 수 있음
fun main() {
    myFunc() { //함수의 매개변수가 하나면 심지어 ()도 생략 가능
        println("함수 호출2")
    }
}

fun myFunc(callBackFun: () -> Unit) { //함수명: 파라미터 -> 리턴 타입
    println("함수 시작1")
    callBackFun()
    println("함수 끝3")
}


// object와 companion object (싱글톤)
//public class Person{
//    public static final int MAX_AGE = 500;
//
//    public static void sayHello() {
//        System.out.println("안녕하세요~");
//    }
//}

// 위의 자바코드를 코틀린으로 바꿔봄
// 이러면 Person.Sample.~ 으로 이름으로만 접근이 가능함! (자바의 static 생각하면 이해가 됨)
class Person {
    companion object Sample { //companion object -> static // 옆에 이름(Sample)은 생략도 가능
        const val MAX_AGE: Int = 500 // 이 부분이 static final

        fun sayHello() {
            println("안녕하세요~")
        }
    }
}
