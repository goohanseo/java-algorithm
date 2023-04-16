public class Node {
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
