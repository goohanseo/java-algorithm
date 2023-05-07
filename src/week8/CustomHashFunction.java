package week8;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

class Person {
    String first, last;

    Person(String f, String l) {
        first = f;
        last = l;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Person)) {
            return false;
        }
        Person p = (Person) obj;
        return Objects.equals(first, p.first) && Objects.equals(last, p.last);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, last);
    }
}

public class CustomHashFunction {
    public static void main(String[] args) {
        Map<Person, Integer> hashMap = new HashMap<>();
        Person p1 = new Person("kartik", "kapoor");
        Person p2 = new Person("Ram", "Singh");
        Person p3 = new Person("Laxman", "Prasad");

        hashMap.put(p1, 100);
        hashMap.put(p2, 200);
        hashMap.put(p3, 100);

        for (Map.Entry<Person, Integer> e : hashMap.entrySet()) {
            //hashMap이라는 HashMap 항목을 반복하는 for-each 루프의 시작입니다.
            //hashMap에는 Person 유형의 키와 Integer 유형의 값이 포함됩니다.
            System.out.println("[" + e.getKey().first + ", " + e.getKey().last + "] => " + e.getValue());
        }
    }
}
