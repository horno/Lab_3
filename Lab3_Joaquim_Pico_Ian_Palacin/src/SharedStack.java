public class SharedStack<E> implements Stack<E> {


    private final Node<E> topOfStack;

    private static class Node<E> {
        private final E elem;
        private final Node<E> next;

        private Node(E elem, Node<E> next) {
            this.elem = elem;
            this.next = next;
        }
    }

    public SharedStack() {
        topOfStack = new Node<>(null, null);
    }

    private SharedStack(E elem, Node<E> next) {
        topOfStack = new Node<>(elem, next);
    }

    private SharedStack(Node<E> topOfStack) {
        this.topOfStack = topOfStack;
    }

    @Override
    public SharedStack<E> push(E elem) {
        return new SharedStack<>(elem, this.topOfStack);
    }

    @Override
    public SharedStack<E> pop() throws StackError {
        if (isEmpty()) {
            throw new StackError("StackError: empty stack");
        } else {
            return new SharedStack<>(topOfStack.next);
        }
    }

    @Override
    public E top() throws StackError {
        if (isEmpty()) {
            throw new StackError("StackError: empty stack");
        } else {
            return topOfStack.elem;
        }

    }

    @Override
    public boolean isEmpty() {
        return topOfStack.next == null;

    }

    /* We are assuming that if two stacks contain the same elements
      * but in different order, the stacks aren't equals */
    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object obj) {
            SharedStack<E> stack = (SharedStack<E>) obj;
            Node<E> otherNode = stack.topOfStack;
            Node<E> thisNode = this.topOfStack;
            while (otherNode.next != null && thisNode.next != null) {
                E otherElem = otherNode.elem;
                E thisElem = thisNode.elem;
                if((otherElem != null && thisElem == null || otherElem == null && thisElem != null
                        || otherElem != null && !(thisElem.equals((otherElem))))){
                    return false;
                }
                otherNode = otherNode.next;
                thisNode = thisNode.next;
            }
            return true;
    }

    /* We have chosen "//" as an element separator because
     * in each position it will be called the toString function
     * of the element, so using commas ( , ) it could cause confusion */
    @Override
    public String toString() {
        String string = "//";
        Node<E> node = topOfStack;
        while (node.next != null) {
            E elem = node.elem;
            string = string + elem.toString() + "//";
            node = node.next;
        }
        return string;
    }

}





