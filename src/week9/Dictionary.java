package week9;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

public class Dictionary {
    public Map<String, List<String>> graph;

    public Dictionary() {
        graph = new HashMap<>();
    }

    public void readFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t");
                if (parts.length != 2) {
                    continue;
                }

                String word = parts[0];
                String description = parts[1];
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

    public Map<String, List<String>> getWordDescriptions() {
        return graph;
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
        String startWord = "word1";
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
        // Create a copy of the graph for iteration
        Map<String, List<String>> graphCopy = new HashMap<>(graph);

        // Iterate over each entry in the graph copy
        for (Map.Entry<String, List<String>> entry : graphCopy.entrySet()) {
            String word = entry.getKey();
            List<String> description = entry.getValue();

            // Check if each description word matches a word in the graph, then add an edge
            for (String descriptionWord : description) {
                if (graph.containsKey(descriptionWord)) {
                    addEdge(word, descriptionWord);
                }
            }
        }
    }
    public void addEdge(String word1, String word2) {
        // Get the current edges for word1 and word2
        List<String> edges1 = graph.getOrDefault(word1, new ArrayList<>());
        List<String> edges2 = graph.getOrDefault(word2, new ArrayList<>());
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

        // Return the word with the maximum degree and its degree as a Pair object
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
                for (String neighbor : graph.get(currentWord)) {
                    if (!visited.contains(neighbor)) {
                        queue.add(new Pair(neighbor, currentDistance + 1));
                        visited.add(neighbor);
                    }
                }
            }
        }

        return result;
    }
}


