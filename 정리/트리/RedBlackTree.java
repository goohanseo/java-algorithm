public class RedBlackNode<T extends Comparable<T>> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private T value;
    private RedBlackNode<T> left;
    private RedBlackNode<T> right;
    private boolean color;
    private int size;

    public RedBlackNode(T value) {
        this.value = value;
        this.color = RED;
        this.size = 1;
    }

    public T getValue() {
        return value;
    }

    public RedBlackNode<T> getLeft() {
        return left;
    }

    public RedBlackNode<T> getRight() {
        return right;
    }

    public boolean isRed() {
        return color == RED;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setRed() {
        color = RED;
    }

    public void setBlack() {
        color = BLACK;
    }

    public void setLeft(RedBlackNode<T> left) {
        this.left = left;
    }

    public void setRight(RedBlackNode<T> right) {
        this.right = right;
    }

    public RedBlackNode<T> insert(T value) {
        if (value.compareTo(this.value) < 0) {
            if (left == null) {
                left = new RedBlackNode<>(value);
                return left;
            } else {
                return left.insert(value);
            }
        } else {
            if (right == null) {
                right = new RedBlackNode<>(value);
                return right;
            } else {
                return right.insert(value);
            }
        }
    }

    public RedBlackNode<T> delete(T value) {
        if (value.compareTo(this.value) < 0) {
            if (left != null) {
                left = left.delete(value);
            }
        } else if (value.compareTo(this.value) > 0) {
            if (right != null) {
                right = right.delete(value);
            }
        } else {
            if (left == null) {
                return right;
            } else if (right == null) {
                return left;
            } else {
                RedBlackNode<T> temp = right.minNode();
                value = temp.value;
                right = right.delete(value);
            }
        }

        return this;
    }

    public RedBlackNode<T> select(int rank) {
        int leftSize = (left == null) ? 0 : left.getSize();

        if (rank < leftSize) {
            return left.select(rank);
        } else if (rank > leftSize) {
            return right.select(rank - leftSize - 1);
        } else {
            return this;
        }
    }

    public RedBlackNode<T> minNode() {
        if (left == null) {
            return this;
        } else {
            return left.minNode();
        }
    }
}
