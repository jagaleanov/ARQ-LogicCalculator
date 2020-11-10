package calculator;

public class Queue {

    private Node head;

    public Queue() {
        head = null;
    }

    public Node add(String data) {

        if (head != null) {
            Node tail = findTail();

            tail.setNext(new Node(data));
        } else {
            head = new Node(data);
        }

        //print();
        return head;
    }

    public String remove() {

        Node removed = head;
        Node next = head.getNext();

        head = next;
        //print();
        return removed.getData();
    }

    public String getHead() {

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

    public Node findTail() {
        Node tail = head;

        if (head != null) {
            while (tail.getNext() != null) {
                tail = tail.getNext();
            }
        }

        return tail;
    }

    @Override
    public String toString() {
        String string = "";
        Node q = head;
        while (q != null) {
            string += q.getData() + " ";
            q = q.getNext();
        }
        return string;
    }

}
