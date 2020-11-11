package calculator;

public class Stack {

    private Node head;

    public Stack() {
        head = null;
    }

    public boolean push(String data) {

        if (head != null) {
            Node newNode = new Node(data);
            newNode.setNext(head);
            head = newNode;
        } else {
            Node newNode = new Node(data);
            head = newNode;
        }

        //print();
        return true;
    }

    public String pop() {

        Node removed = head;
        //print();
        Node next = head.getNext();

        head = next;
        return removed.getData();
    }

    public String nextPop() {

        if (head != null) {
            return head.getData();
        } else {
            return "";
        }
    }

    public void print() {
        Node q = head;

        if (q != null) {
            do {
                System.out.print(q.getData() + " ");
                q = q.getNext();
            } while (q != null);
        }

        System.out.println();
    }
}
