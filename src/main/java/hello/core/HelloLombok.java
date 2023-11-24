package hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HelloLombok {
    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();

        //@Setter
        helloLombok.setName("Kyungshik");
        helloLombok.setAge(27);

        String name = helloLombok.name;
        System.out.println("name = " + name);

        int age = helloLombok.getAge();
        System.out.println("age = " + age);

        //@ToString
        System.out.println("helloLombok = " + helloLombok);
    }

}
