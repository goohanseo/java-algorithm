package week8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

class text {
    String preprefix, postprefix;

    text(String pre, String post) {
        preprefix = pre;
        postprefix = post;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof text)) {
            return false;
        }
        text t = (text) obj;
        return Objects.equals(preprefix, t.preprefix) && Objects.equals(postprefix, t.postprefix);
    }

    @Override
    public int hashCode() {
        return Objects.hash(preprefix, postprefix);
    }
}

public class Prob01 {
    public static void main(String[] args) {
        Map<text, Integer> hashMap = new HashMap<>();
        text t1 = new text("kartik", "kapoor");
        text t2 = new text("Ram", "Singh");
        text t3 = new text("Laxman", "Prasad");

        hashMap.put(t1, 100);
        hashMap.put(t2, 200);
        hashMap.put(t3, 100);

        for (Map.Entry<text, Integer> e : hashMap.entrySet()) {
            //hashMap이라는 HashMap 항목을 반복하는 for-each 루프의 시작입니다.
            //hashMap에는 Person 유형의 키와 Integer 유형의 값이 포함됩니다.
            System.out.println("[" + e.getKey().preprefix + ", " + e.getKey().postprefix + "] => " + e.getValue());
        }
    }
}

//ArrayList 선언하기
//ArrayList<String> s = new ArrayList<String>();