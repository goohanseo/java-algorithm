public class 이진트리중순위 {
    public class Node {
        int key;
        Node left, right;

        public Node(int key) {
            this.key = key;
            left = right = null;
        }
    }

    Node root;

    public 이진트리중순위() {
        root = null;
    }
    // Inorder Traversal
    public void inorderTraversal() {
        inorderTraversal(root);
    }
    private void inorderTraversal(Node node) {
        if (node != null) {
            // 1. Visit left subtree
            inorderTraversal(node.left);

            // 2. Process the node
            System.out.print(node.key + " ");

            // 3. Visit right subtree
            inorderTraversal(node.right);
        }
    }

    public static void main(String[] args) {
        이진트리중순위 tree = new 이진트리중순위();
        tree.root = tree.new Node(1);
        tree.root.left = tree.new Node(2);
        tree.root.right = tree.new Node(3);
        tree.root.left.left = tree.new Node(4);
        tree.root.left.right = tree.new Node(5);

        System.out.println("Inorder traversal:");
        tree.inorderTraversal();
    }
}
