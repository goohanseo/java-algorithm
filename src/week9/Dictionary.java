package week9;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Dictionary {
    private Map<String, List<String>> graph;

    public Dictionary(){
        graph = new HashMap<>();
    }

    public void readFile(String filename){
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))){
        String line;
        while ((line = reader.readLine()) != null){
            String[] parts = line.split("\t");
            if(parts.length != 2){
                continue;
            }

            String word = parts[0];
            String description = parts[1];
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
        // 빈 arraylist 초기화
        List<String> descriptionword = new ArrayList<>();
        //for-each 루프 사용하여 words 배열의 단어 반복
        for (String word : words){
            descriptionword.add(word);
        }
        return descriptionword;
    }

    public Map<String, List<String>> getWordDescriptions(){
        return graph;
    }

    public static void main(String[] args) {
        Dictionary dictionary = new Dictionary();
        dictionary.readFile("dict_simplified.txt");
        System.out.println(dictionary.getWordDescriptions());
    }

    public void buildGraph() {
        //graph의 각 항복에 대해 반복 entry는 사전 항목 하나 나타냄 단어와 설명을 가져옴
        for (Map.Entry<String, List<String>> entry : graph.entrySet()) {
            String word = entry.getKey();
            List<String> description = entry.getValue();
            //description의 각 단어가 graph의 단어와 일치하는지 확인후 일치하면 엣지를 추가
            for (String descriptionWord : description) {
                if (graph.containsKey(descriptionWord)) {
                    addEdge(word, descriptionWord);
                }
            }
        }
    }
    public void addEdge(String word1, String word2) {
        // Get the current edges for word1 and word2
        Set<String> edges1 = graph.getOrDefault(word1, new HashSet<>());
        Set<String> edges2 = graph.getOrDefault(word2, new HashSet<>());

        // Add the other word to the list of edges
        edges1.add(word2);
        edges2.add(word1);

        // Update the graph with the new edges
        graph.put(word1, edges1);
        graph.put(word2, edges2);
    }

    public int vertexCount() {
        // Return the number of vertices in the graph
        return graph.size();
    }

    public int edgeCount(){
        int totalEdges = 0;

        for (List<String> edges : graph.values()){
            totalEdges += edges.size();
        }
        return totalEdges/2;
    }

    public Pair findMaxDegreeVertex() {
        String maxDegreeWord = "";
        int maxDegree = 0;

        // Iterate over each key (word) in the graph
        for (Map.Entry<String, List<String>> entry : graph.entrySet()) {
            String word = entry.getKey();
            int degree = entry.getValue().size();

            // Check if the current word's degree is greater than the current max degree
            if (degree > maxDegree) {
                maxDegreeWord = word;
                maxDegree = degree;
            }
        }

        // Return the word with the maximum degree and its degree as a WordDegree object
        return new Pair(maxDegreeWord, maxDegree);
    }

    public int largestConnectedComponentSize() {
        Set<String> visited = new HashSet<>();
        int maxSize = 0;

        for (String word : graph.keySet()) {
            if (!visited.contains(word)) {
                int componentSize = bfs(word, visited);
                maxSize = Math.max(maxSize, componentSize);
            }
        }

        return maxSize;
    }

    private int bfs(String start, Set<String> visited) {
        Queue<String> queue = new LinkedList<>();
        int componentSize = 0;

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            componentSize++;

            for (String neighbor : graph.get(current)) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                }
            }
        }

        return componentSize;
    }


    public class Pair {
        private String word;
        private int Integer;

        public Pair(String word, int degree) {
            this.word = word;
            this.Integer = degree;
        }

        public String getWord() {
            return word;
        }

        public int getDegree() {
            return Integer;
        }
    }
}
