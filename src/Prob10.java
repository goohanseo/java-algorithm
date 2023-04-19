import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Prob10 {
    private static BinarySearchTree bst = new BinarySearchTree();
    public static void main(String[] args) throws IOException {


        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.print("$ ");
            String command = scanner.nextLine();
            String[] tokens = command.split(" "); // 입력된 문자열을 공백으로 분리하여 처리
            if (tokens[0] == null){
                continue;
            }
            if (tokens[0].equals("exit")){
                break;
            }
            else if (tokens[0].equals("read")){
                String fileName = tokens[1];
                read(fileName);
            }
            else if (tokens[0].equals("list")){
                list();
            }
            else if (tokens[0].equals("find")){
                String name = tokens[1];
                find(name);
            }
            else if (tokens[0].equals("add")){
                String name = tokens[1];
                add(name);
            }
            else if (tokens[0].equals("trace")){
                String name = tokens[1];
                trace(name);
            }
            else if (tokens[0].equals("delete")){
                String name = tokens[1];
                delete(name);
            }
            else if (tokens[0].equals("save")){
                String fileName = tokens[1];
                save(fileName);
            }

        }
    }


    private static void read(String fileName) throws IOException{
        String fileDir = "/Users/guhanseo/study/algorithm/src/" + fileName;
        List<Address> addressList = readAddressesFromCsv(fileDir);

        for (Address address : addressList) { //java의 for-each 루프 문법, addresslist 리스트 모든
            //원소 순회하면서 각 원소를 하나씩 address 타입의 address 변수에 담아 반복문 내부의 코드 실행
            bst.insert(address);
        }
    }
    public static List<Address> readAddressesFromCsv(String filePath) throws IOException {
        List<Address> addressList = new ArrayList<>();

        FileReader fileReader = new FileReader(filePath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line;
        int lineNumber = 0;
        while ((line = bufferedReader.readLine()) != null) {
            lineNumber++;
            if (lineNumber == 1) {
                continue; // 첫 번째 행은 무시
            }
            String[] data = line.split("\t"); // CSV 파일은 쉼표로 구분되므로, 쉼표를 기준으로 문자열을 분리합니다.
            String name = data[0];
            String company = data[1];
            String address = data[2];
            int zip = Integer.parseInt(data[3]);
            String phone = data[4];
            String email = data[5];

            Address newAddress = new Address(name, company, address, zip, phone, email);
            addressList.add(newAddress);
        }

        bufferedReader.close();

        return addressList;
    }
    private static void list(){
        bst.printInOrder();
    }

    private static void find(String name) {

        bst.find_name(name);
    }

    private static void add(String name) throws IOException{
        Node current = bst.getRoot(); // bst 객체의 루트 노드를 가져옴
        while (current != null){
            if(name.compareTo(current.getAddress().getName()) < 0) {
                current = current.getLeftChild();
            }
            else if (current.getAddress().getName().equals(name)){
                System.out.println("중복된 회원이 있습니다");
                return;
            }
            else if (name.compareTo(current.getAddress().getName()) > 0) {
                current = current.getRightChild();
            }
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Address user = new Address(name,"","",0,"","");
        user.setName(name);
        System.out.print("\tCompany? ");
        user.setCompany(br.readLine());
        System.out.print("\tAddress? ");
        user.setAddress(br.readLine());
        System.out.print("\tZipCode? ");
        user.setZip(Integer.parseInt(br.readLine()));
        System.out.print("\tPhones? ");
        user.setPhone(br.readLine());
        System.out.print("\tEmail? ");
        user.setEmail(br.readLine());


        bst.insert(user);
    }

    private static void trace(String name) {
        bst.trace(name);
    }

    private static void delete(String name){
        bst.delete(name);
    }

    private static void save(String fileName) throws IOException{
        String fileDir = "/Users/guhanseo/study/algorithm/src/" + fileName;
        BufferedWriter file = new BufferedWriter(new FileWriter(fileDir));
        Node root = bst.getRoot();
        saveInorderTraversal(root, file);
        file.close();
        if (file != null) {
            System.out.printf("'%s'파일이 성공적으로 저장되었습니다. (exit 호출시 파일이 보여집니다.)\n", fileName);
        }else{
            System.out.println("ERROR: 파일 저장 실패");
        }
    }
    private static void saveInorderTraversal(Node node, BufferedWriter file) throws IOException {
        if(node == null) return;
        saveInorderTraversal(node.getLeftChild(), file);
        file.write(
                node.getAddress().getName() + "\t"
                        + node.getAddress().getCompany() + "\t"
                        + node.getAddress().getAddress() + "\t"
                        + node.getAddress().getZip() + "\t"
                        + node.getAddress().getPhone() + "\t"
                        + node.getAddress().getEmail() + "\n"
        );
        saveInorderTraversal(node.getRightChild(), file);
    }
}

class BinarySearchTree {
    private Node root;

    public BinarySearchTree() {
        root = null;
    }

    public Node getRoot() {
        return root;
    }
    public void insert(Address address) { //새로운 노드 삽입
        root = insertRec(root, address);
    }

    public Node find(String name){
        Node current = root;
        while (current != null && !current.getAddress().getName().equals(name)){
            if(name.compareTo(current.getAddress().getName()) < 0) {
                current = current.getLeftChild();
            }
            else {
                current = current.getRightChild();
            }
        }
        return current;
    }
    private Node insertRec(Node root, Address address) { //새로운 노드 삽입을 위한 적절한 위치 탐색
        if (root == null) { //root는 트리의 루트 노드 의미x 현재 재귀 호출이 이루어지는 트리의 루트 노드
            root = new Node(address) {

            };
            return root;
        }

        if (address.getName().compareToIgnoreCase(root.getAddress().getName()) < 0) {
            root.setLeftChild(insertRec(root.getLeftChild(), address));
        } else {
            root.setRightChild(insertRec(root.getRightChild(), address));
        }

        return root;
    }

    public void printInOrder() {
        if (root == null) {
            System.out.println("Tree is empty");
        } else {
            printInOrderRec(root);
        }printInOrderRec(root);
    }

    private void printInOrderRec(Node root) {
        if (root != null) {
            printInOrderRec(root.getLeftChild());
            Address address = root.getAddress();
            String info = address.getName() + "\t" + address.getCompany()
                    + "\t" + address.getAddress() + "\t" + address.getZip()
                    + "\t" + address.getPhone() + "\t" + address.getEmail();
            System.out.println(info);
            printInOrderRec(root.getRightChild());
        }
    }

    public void find_name(String name) {
        Node node = find(name);
        if (node == null) {
            System.out.println("Node not found");
        } else {
            Address address = node.getAddress();
            String info = address.getName() + "\t" + address.getCompany()
                    + "\t" + address.getAddress() + "\t" + address.getZip()
                    + "\t" + address.getPhone() + "\t" + address.getEmail();
            System.out.println(info);
        }
    }

    public void trace(String name) {
        Node node = prevtrace(name);
        if (node == null) {
            System.out.println("Node not found");
        } else {
            Address address = node.getAddress();
            String info = address.getName() + "\t" + address.getCompany()
                    + "\t" + address.getAddress() + "\t" + address.getZip()
                    + "\t" + address.getPhone() + "\t" + address.getEmail();
            System.out.println(info);
        }
    }

    public Node prevtrace(String name){
        Node current = root;
        while (current != null && !current.getAddress().getName().equals(name)){
            Address address = current.getAddress();
            String info = address.getName() + "\t" + address.getCompany()
                    + "\t" + address.getAddress() + "\t" + address.getZip()
                    + "\t" + address.getPhone() + "\t" + address.getEmail();
            System.out.println(info);
            if(name.compareTo(current.getAddress().getName()) < 0) {
                current = current.getLeftChild();
            }
            else {
                current = current.getRightChild();
            }
        }
        return current;
    }

    public void delete(String name) {
        Node node = find(name);
        if (node.getLeftChild() == null || node.getRightChild() == null) {
            Node temp = null;
            if (node.getLeftChild() != null) {
                temp = node.getLeftChild();
            } else {
                temp = node.getRightChild();
            }
            if (temp == null) {
                temp = node;
                node = null;
            } else {
                node = temp;
            }
        } else {
            Node successor = getSuccessor(node);
            node.setAddress(successor.getAddress());
            node.setRightChild(deleteRec(node.getRightChild(), successor.getAddress().getName()));
        }
    }

    private Node deleteRec(Node root, String name) {
        if (root == null) {
            return root;
        }
        if (name.compareTo(root.getAddress().getName()) < 0) {
            root.setLeftChild(deleteRec(root.getLeftChild(), name));
        } else if (name.compareTo(root.getAddress().getName()) > 0) {
            root.setRightChild(deleteRec(root.getRightChild(), name));
        } else {
            if (root.getLeftChild() == null) {
                return root.getRightChild();
            } else if (root.getRightChild() == null) {
                return root.getLeftChild();
            }
            Node temp = getSuccessor(root);
            root.setAddress(temp.getAddress());
            root.setRightChild(deleteRec(root.getRightChild(), temp.getAddress().getName()));
        }
        return root;
    }

    private Node getSuccessor(Node deleteNode) {
        Node successsor = null;
        Node successsorParent = null;
        Node current = deleteNode.getRightChild();
        while (current != null) {
            successsorParent = successsor;
            successsor = current;
            current = current.getLeftChild();
        }
        if (successsor != deleteNode.getRightChild()) {
            successsorParent.setLeftChild(successsor.getRightChild());
            successsor.setRightChild(deleteNode.getRightChild());
        }
        return successsor;
    }

}
class Node {
    private Address address;
    private Node leftChild;
    private Node rightChild;

    public Node(Address address) {
        this.address = address;
        this.leftChild = null;
        this.rightChild = null;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }


}
class Address {
    private String name;
    private String company;
    private String address;
    private int zip;
    private String phone;
    private String email;

    private Node leftChild;

    private Node rightChild;

    public Address(String name, String company, String address, int zip, String phone, String email) {
        this.name = name;
        this.company = company;
        this.address = address;
        this.zip = zip;
        this.phone = phone;
        this.email = email;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }
}
