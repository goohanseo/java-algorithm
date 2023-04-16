public class
BinarySearchTree {
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
