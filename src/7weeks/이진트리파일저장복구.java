import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class LogFileAnalyzer {
    public static void main(String[] args) {
        String filename = "log.csv"; // 로그 파일 이름
        ArrayList<String[]> logData = new ArrayList<String[]>(); // 로그 데이터를 저장할 ArrayList
        String line;

        // 파일 읽기
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            while ((line = br.readLine()) != null) {
                String[] log = line.split(","); // 콤마로 구분된 로그 항목들을 배열로 분리
                logData.add(log); // 로그 데이터를 ArrayList에 추가
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // URL을 알파벳 순서로 정렬
        Collections.sort(logData, new Comparator<String[]>() {
            @Override
            public int compare(final String[] entry1, final String[] entry2) {
                return entry1[2].compareTo(entry2[2]);
            }
        });

        // 결과 출력
        for (String[] log : logData) {
            System.out.println(log[0] + "," + log[1] + "," + log[2] + "," + log[3]);
        }
    }
}
import java.io.FileOutputStream;
        import java.io.IOException;
        import java.io.ObjectOutputStream;

public class BinaryTreeFileIO {
    public static void save(Node root, String filename) {
        try (FileOutputStream fileOut = new FileOutputStream(filename);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
//hjkjl