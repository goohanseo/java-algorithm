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

