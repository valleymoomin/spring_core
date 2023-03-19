package hello.core;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
//@ToString
public class HelloLombok {

    private String name;
    private int age;

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }

    public static void main(String[] args) {

        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("asdg");

        String name = helloLombok.getName();
        System.out.println("name = " + name);

        System.out.println("helloLombok = " + helloLombok);

        //helloLombok = HelloLombok(name=asdg, age=0) <-toString있을때
        //helloLombok = hello.core.HelloLombok@614c5515 <-toString없을때

    }
}
