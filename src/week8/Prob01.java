package week8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

class MarkovChainText {
    private Map<String, List<String>> prefixToSuffixes;
    private Random random;

    MarkovChainText(String text) {
        prefixToSuffixes = new HashMap<>();
        random = new Random();
        buildMap(text);
    }


    private void buildMap(String text) {
        String[] words = text.split("\\s+"); //공백을 기준으로 나눔
        for (int i = 0; i < words.length - 2; i++){ //배열의 단어를 순회하며 각 접두사-접미사 조합 찾는다
            //배열의 마지막에서 두 번째 단어까지만 순회, 접두사와 접미사가 모두 필요하기 때문
            String prefix = words[i] + " " + words[i + 1]; //현재 단어와 그 다음 단어로 접두사 만듬
            String suffix = words[i + 2]; //그 다음 단어의 다음 단어를 접미사로 설정
            //해당 접두사에 대한 접미사 목록을 가져옴, 해당 접두사 없으면 새로운 arraylist 생성
            List<String> suffixes = new ArrayList<>(prefixToSuffixes.getOrDefault(prefix, new ArrayList<>()));
            //해당 접시마를 접미사 목록에 추가
            suffixes.add(suffix);
            //접두사-접미사 목록을 다시 맵에 저장, 새로운 접미사를 추가한 목록을 업데이트 하기 위함
            prefixToSuffixes.put(prefix, suffixes);
        }
    }
    public String generateText(String startPrefix, int maxLength) {
        //생성된 텍스트를 저장할 StringBuilder 객체 생성, 시작 접두사를 초기 값으로 설정
        StringBuilder generatedText = new StringBuilder(startPrefix);
        //현재 접두사를 시작 접두사로 초기화
        String currentPrefix = startPrefix;
        //시작 접두사에 두 개의 단어가 포함되어 있으므로 단어 수를 2로 초기화
        int wordCount = 2;
        //단어 수가 최대 길이를 초과하지 않고 현재 접두사에 대한 접미사가 존재하는 동안 반복
        while (wordCount <= maxLength && prefixToSuffixes.containsKey(currentPrefix)) {
            //현재 접두사에 대한 접미사 목록을 가져온다
            List<String> suffixes = prefixToSuffixes.get(currentPrefix);
            //접미사 목록중 무작위 선택
            String nextSuffix = suffixes.get(random.nextInt(suffixes.size()));
            //선택된 접미사를 공백 + 단어로 생성된 텍스트에 추가, 공백 문자로 단어가 구분
            generatedText.append(" ").append(nextSuffix);
            //현재 접두사를 업데이트, 이전 접두사의 두번째 단어와 새로운 접미사를 결합하여 새로운 접두사 생성//공백을 기준으로 분할, 두번째 단어, + 새로운 단
            currentPrefix = currentPrefix.split("\\s+")[1] + " " + nextSuffix;
            //단어 수 증가
            wordCount++;
        }

        return generatedText.toString();
    }
}
public class Prob01 {
    public static void main(String[] args) {
        try {
            //파일 읽어오기
            String realText = Files.readString(Path.of("/Users/guhanseo/study/java-algorithm/src/week8/HarryPotter.txt"));
            //실제 텍스트를 이용하여 객체 초기화
            MarkovChainText textGenerator = new MarkovChainText(realText);
            //가짜 텍스트 시작 접두사와 최대 길이 설정
            String startPrefix = "There was";
            int maxLength = 100;
            //시작 접두사와 최대길이로 카타텍스트 생성
            String generatedText = textGenerator.generateText(startPrefix, maxLength);
            //가자 텍스트 콘솔에 출력
            System.out.println(generatedText);
        } catch (IOException e) {
            //읽기 중 오류
            System.err.println("Error reading real text file: " + e.getMessage());
        }
    }
}
