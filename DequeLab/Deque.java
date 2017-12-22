/**
 * MORRIS OMBIRO
 * Lab 11 (CSCI 1913 - Intro. to Alg.)
 * Professor James B. Moen
 * Created by misfitdev on 11/29/2017.
 */
class Deque<Base> {
    private class Node {
        private Base object;
        private Node left, right;
        private Node(Base object, Node left, Node right) {
            this.object = object;
            this.left = left;
            this.right = right;
        }
    }
    private Node head;
    public Deque() {
        head = new Node(null, null, null);
        head.right = head;
        head.left = head;
    }
    public void enqueueFront(Base object) {
        if (isEmpty()) {
            Node a = new Node(object, head, head);
            head.left = a;
            head.right = a;
        } else {
            Node a = new Node(object, head, head.right);
            head.right.left = a;
            head.right = a;
        }
    }
    public void enqueueRear(Base object) {
        if (isEmpty()) {
            Node e = new Node(object, head, head);
            head.left = e;
            head.right = e;
        } else {
            Node e = new Node(object, head.left, head);
            head.left.right = e;
            head.left = e;
        }
    }
    public Base dequeueFront() {
        if (isEmpty())
            throw new IllegalStateException("Empty");
        else {
            Base temp = head.right.object;
            head.right.right.left = head;
            head.right = head.right.right;
            return temp;
        }
    }
    public Base dequeueRear() {
        if(isEmpty())
            throw new IllegalStateException("Empty");
        else {
            Base temp = head.left.object;
            head.left.left.right = head;
            head.left = head.left.left;
            return temp;
        }
    }
    public boolean isEmpty() {
        return head.left == head && head.right == head;
    }
}


class ObservationDeque
{

//  MAIN. Test the DEQUE on various example arguments.

    public static void main(String [] args)
    {
        Deque<String> deque = new Deque<String>();

        System.out.println(deque.isEmpty());       // true                2 points.

        try
        {
            System.out.println(deque.dequeueFront());
        }
        catch (IllegalStateException ignore)
        {
            System.out.println("No dequeueFront.");  //  No dequeueFront.   2 points.
        }

        try
        {
            System.out.println(deque.dequeueRear());
        }
        catch (IllegalStateException ignore)
        {
            System.out.println("No dequeueRear.");   //  No dequeueRear.    2 points.
        }

//  Enqueueing to the rear and dequeueing from the rear makes the DEQUE act
//  like a stack.

        deque.enqueueRear("A");
        deque.enqueueRear("B");
        deque.enqueueRear("C");

        System.out.println(deque.isEmpty());       //  false              2 points.

        System.out.println(deque.dequeueRear());   //  C                  2 points.
        System.out.println(deque.dequeueRear());   //  B                  2 points.
        System.out.println(deque.dequeueRear());   //  A                  2 points.

        System.out.println(deque.isEmpty());       //  true               2 points.

//  Enqueueing to the rear and dequeueing from the front makes the DEQUE act
//  like a queue.

        deque.enqueueRear("A");
        deque.enqueueRear("B");
        deque.enqueueRear("C");

        System.out.println(deque.dequeueFront());  //  A                  2 points.
        System.out.println(deque.dequeueFront());  //  B                  2 points.
        System.out.println(deque.dequeueFront());  //  C                  2 points.

        System.out.println(deque.isEmpty());       //  true               2 points.

//  Enqueueing to the front and dequeueing from the front makes the DEQUE act
//  like a stack.

        deque.enqueueFront("A");
        deque.enqueueFront("B");
        deque.enqueueFront("C");

        System.out.println(deque.dequeueFront());  //  C                  2 points.
        System.out.println(deque.dequeueFront());  //  B                  2 points.
        System.out.println(deque.dequeueFront());  //  A                  2 points.

        System.out.println(deque.isEmpty());       //  true               2 points.

//  Enqueueing to the front and dequeueing from the rear makes the DEQUE act
//  like a queue.

        deque.enqueueFront("A");
        deque.enqueueFront("B");
        deque.enqueueFront("C");

        System.out.println(deque.dequeueRear());   //  A                  2 points.
        System.out.println(deque.dequeueRear());   //  B                  2 points.
        System.out.println(deque.dequeueRear());   //  C                  2 points.

        System.out.println(deque.isEmpty());       //  true               2 points.
    }
}

/*
true
 No dequeueFront.
 No dequeueRear.
 false
 C
 B
 A
 true
 A
 B
 C
 true
 C
 B
 A
true
 A
 B
 C
 true
 */