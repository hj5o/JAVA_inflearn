package hello.core.singleton;

public class StatefulService {

    //오류를 막기 위해 스프링 빈을 무상태로 설계
    public int order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        return price;
    }

//    아래는 무상태가 아님
//    private int price; //상태를 유지하는 필드
//
//    public void order(String name, int price) {
//        System.out.println("name = " + name + " price = " + price);
//        this.price = price; //여기가 문제
//    }
//
//    public int getPrice() {
//        return price;
//    }
}
