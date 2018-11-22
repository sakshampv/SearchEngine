public class Node<X>
{
    public X data;
    public Node next;

    public Node()
    {
        next = null;
    }

    public Node(X dat, Node link)
    {
        data = dat;
        next = link;
    }
}