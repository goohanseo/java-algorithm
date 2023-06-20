package week9;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Dictionary {
    //인접리스트 정의
    public Map<String, List<String>> graph;

    //HashMap은 Map 인터페이스를 구형하고 키-값 쌍을 저장하는 방법
    public Dictionary() {
        graph = new HashMap<>();
    }

    public void readFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            //더이상 줄이 없어 null을 반환할 때까지 파일을 읽음
            while ((line = reader.readLine()) != null) {
                //탭 문자로 구분
                String[] parts = line.split("\t");
                if (parts.length != 2) {
                    continue;
                }

                String word = parts[0];
                String description = parts[1];
                //토큰화처리하여 문자열 목록을 반환
                List<String> descriptionword = tokenizeDescription(description);
                graph.put(word, descriptionword);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> tokenizeDescription(String description) {
        //입력 설명을 단어 배열로 분할 정규식 \\s+는 하나 이상의 공백 문자와 일치하도록 모든 공백 문자에서 설명이 분할
        String[] words = description.split("\\s+");
        // 빈 arraylist 초기화
        List<String> descriptionword = new ArrayList<>();
        //for-each 루프 사용하여 words 배열의 단어 반복
        for (String word : words) {
            descriptionword.add(word);
        }
        return descriptionword;
    }

    public static void main(String[] args) {
        Dictionary graph = new Dictionary();
        graph.readFile("/Users/guhanseo/study/java-algorithm/src/week9/dict_simplified.txt");
        graph.buildGraph();
        System.out.println("Vertices: " + graph.vertexCount());
        System.out.println("Edges: " + graph.edgeCount());

        // Find the vertex with the maximum degree and output the word and degree corresponding to the vertex
        Pair maxDegreeVertex = graph.findMaxDegreeVertex();
        System.out.println("Max degree vertex: " + maxDegreeVertex.getWord() + " with degree " + maxDegreeVertex.getDegree());

        // Find the largest connected component and print the size (number of vertices) of that connected component
        System.out.println("Size of the largest connected component: " + graph.largestConnectedComponentSize());

        // Input a word and an integer representing the search depth
        String startWord = "mountain";
        int distance = 2;

        // Find all words that are less than or equal to the distance from the word in the graph and output them one by one per line
        List<String> wordsWithinDistance = graph.wordsWithinDistance(startWord, distance);
        System.out.println("Words within distance " + distance + " from " + startWord + ":");
        for (String word : wordsWithinDistance) {
            System.out.println(word);
        }

        // Print the count of the last printed word
        System.out.println("Count of the last printed word: " + wordsWithinDistance.size());
    }

    public void buildGraph() {
        //새 HashMap을 만들고 원본 graph 맵의 복사한다.
        //반복중 수정된 내용이 원래 지도에 영향을 주지 않기 위해
        Map<String, List<String>> graphCopy = new HashMap<>(graph);
        //그래프 맵에 추가해야 하는 새 엣지를 임시로 저장하는데 사용
        Map<String, List<String>> tempEdges = new HashMap<>();

        //for-each 루프 사용하여 graphCopy 반복
        for (Map.Entry<String, List<String>> entry : graphCopy.entrySet()) {
            String word = entry.getKey();
            List<String> description = entry.getValue();

            //현재 항목에 대한 설명의 각 단어 반복
            for (String descriptionWord : description) {
                //desciptionWord가 원본 graph에 존재하는지 확인
                if (graph.containsKey(descriptionWord)) {
                    //일치하는 경우 새 엣지 추가
                    //존재하지 않으면  해당 키의 값으로 새 Arraylist만들고 해당 단어를 목록에 추가
                    //양방향 연결을 보장하기 위해 서로의 엣지에 추가
                    tempEdges.computeIfAbsent(word, k -> new ArrayList<>()).add(descriptionWord);
                    tempEdges.computeIfAbsent(descriptionWord, k -> new ArrayList<>()).add(word);
                }
            }
        }

        //tempEdges 맵의 각 항목을 반복, 새 엣지에 매핑된 단어를 나타냄
        for (Map.Entry<String, List<String>> entry : tempEdges.entrySet()) {
            String word = entry.getKey();
            List<String> newEdges = entry.getValue();

            //원본 graph에서 word에 대한 현재 엣지 목록 검색
            //word가 키로 존재하면 엣지 목록 검색, 그렇지 않으면 새 빈 ArrayList 기본값으로 반환
            List<String> edges = graph.getOrDefault(word, new ArrayList<>());

            // Add the new edges to the current edges
            edges.addAll(newEdges);

            // Update the graph with the new edges
            graph.put(word, edges);
        }
    }


    public int vertexCount() {
        //graph의 사이즈가 전체 정점의 갯수
        return graph.size();
    }

    public int edgeCount(){
        int totalEdges = 0;
        //각 정점들의 엣지의 총합/2
        for (List<String> edges : graph.values()){
            totalEdges += edges.size();
        }
        return totalEdges/2;
    }

    public Pair findMaxDegreeVertex() {
        //지금까지 만난 정점중 차수가 가장 높은 것을 찾음
        String maxDegreeWord = "";
        int maxDegree = 0;

        //for-each 루프 사용하여 graph 맵의 각 키-값 쌍을 반복
        for (Map.Entry<String, List<String>> entry : graph.entrySet()) {
            String word = entry.getKey();
            //연결된 엣지 목록의 크기를 가져온다
            int degree = entry.getValue().size();

            //현재 최대 차수보다 높은지 대소비교
            if (degree > maxDegree) {
                maxDegreeWord = word;
                maxDegree = degree;
            }
        }

        //단어와 차수를 Pair 객체로 반환
        return new Pair(maxDegreeWord, maxDegree);
    }

    public int largestConnectedComponentSize() {
        Set<String> visited = new HashSet<>();
        int maxSize = 0;

        //그래프의 각 키를 반복
        for (String word : graph.keySet()) {
            //contains 메소드를 사용해 visited 세트에 존재하는지 확인
            if (!visited.contains(word)) {
                //현재 단어와 visited를 인수로 설정하여 해당 단어를 포함하는 연결된 구성요소의 크기를 반환
                int componentSize = bfs(word, visited);
                //현재 maxsize와 componentsize를 비교
                maxSize = Math.max(maxSize, componentSize);
            }
        }

        return maxSize;
    }

    private int bfs(String start, Set<String> visited) {
        //linkedlist로 큐를 초기화
        Queue<String> queue = new LinkedList<>();
        int componentSize = 0;

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            componentSize++;

            List<String> neighbors = graph.get(current);
            if (neighbors != null) {
                for (String neighbor : neighbors) {
                    if (!visited.contains(neighbor)) {
                        queue.add(neighbor);
                        visited.add(neighbor);
                    }
                }
            }
        }

        return componentSize;
    }


    public List<String> wordsWithinDistance(String startWord, int distance) {
        Set<String> visited = new HashSet<>();
        Queue<Pair> queue = new LinkedList<>();
        List<String> result = new ArrayList<>();

        queue.add(new Pair(startWord, 0));
        visited.add(startWord);

        while (!queue.isEmpty()) {
            Pair currentPair = queue.poll();
            String currentWord = currentPair.getWord();
            int currentDistance = currentPair.getDegree();

            if (currentDistance <= distance) {
                result.add(currentWord);
            }

            if (currentDistance < distance) {
                List<String> neighbors = graph.get(currentWord);
                if (neighbors != null) {
                    for (String neighbor : neighbors) {
                        if (!visited.contains(neighbor)) {
                            queue.add(new Pair(neighbor, currentDistance + 1));
                            visited.add(neighbor);
                        }
                    }
                }
            }
        }

        return result;
    }

}



