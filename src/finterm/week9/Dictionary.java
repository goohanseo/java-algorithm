package finterm.week9;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dictionary {
    //자료구조
    public Map<String, List<String>> graph;

    //
    public Dictionary(){ graph = new HashMap<>(); }

    public void readFile(String filename){
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))){
            String line;
            //마지막이 되어 null 반환할 때 까지 파일을 읽음
            while((line = reader.readLine()) != null){
                String[] parts = line.split("\t");
                if (parts.length != 2){
                    continue;
                }
                //tab나올때까지 한 단어이므로 word
                String word = parts[0];
                //word뒤의 모든 설명 저장
                String description = parts[1];
                //토큰화처리하여 문자열 목록 반환
                List<String> descriptionword = tokenizeDescription(description);
                graph.put(word, descriptionword);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private List<String> tokenizeDescription(String description) {
        //입력 설명을 단어 배열로 분할 정규식 \\s+는 하나 이상의 공백 문자와 일치하도록 모든 공백 문자에서 설명이 분할
        String[] words = description.split("\\s+");
        //빈 arraylist 초기화
        List<String> descriptionword = new ArrayList<>();
        //for-each 루프 사용하여 words 배열의 단어 반복
        for (String word : words) {
            descriptionword.add(word);
        }
        return descriptionword;
    }

    public static void main(String[] args){
        Dictionary graph = new Dictionary();
        graph.readFile("/Users/guhanseo/study/java-algorithm/src/week9/dict_simplified.txt");
    }

    public void buildGraph(){
        //새 hashmap을 만들고 원래 graph 맵 복사
        //반복 중 수정된 내용이 원래 맵에 영향을 주지 않기 위해
        Map<String, List<String>> graphCopy = new HashMap<>(graph);
        //그래프 맵에 추가해야 하는 새 엣지를 임시로 저장하는데 사용
        Map<String, List<String>> tempEdges = new HashMap<>();

        //for-each 루프 사용하여 grapCopy 반복
        for (Map.Entry<String, List<String>> entry : graphCopy.entrySet()){
            String word = entry.getKey();
            List<String> description = entry.getValue();

            //현재 항목에 대한 설명의 각 단어 반복
            for (String descriptionWord : description){
                //descriptionWord가 원본 graph에 존재하는지 확인
                if (graph.containsKey(descriptionWord)) {
                    //일치하는 경우 새 엣지 추가
                    //존재하지 않으면 해당 키의 값으로 새 Arraylist만들고 해당 단어를 목록에 추가
                    //양방향 연결을 보장하기 위해 서로의 엣지에 추가
                    tempEdges.computeIfAbsent(word, k -> new ArrayList<>()).add(descriptionWord);
                    tempEdges.computeIfAbsent(descriptionWord, k -> new ArrayList<>()).add(word);
                }
            }
        }

        //tempEdges 맵의 각 항목을 반복, 새 엣지에 매핑된 단어를 나ㅏ냄
        for(Map.Entry<String, List<String>> entry : tempEdges.entrySet()){
            String word = entry.getKey();
        }
    }
}

//연결리스트 생성 key-value값으로 되어야 하므로 hashmap
//단어는 읽어오면서 hashmap의 key로 넣기
//설명은 value로 넣고
//list 만들어서 각 key값들을 생성
//for문 돌면서 다른 value에 key값이 있으면 a와 b의 리스트에 서로 추가