public class WordEntry {

    public MyLinkedList<Position> p;
    public String str;
    AVLtree AVL = new AVLtree();

    public int ll_size()
    {
        Node<Position> curr  = new Node<Position>();
        curr = this.p.start;
        int count = 0;
        while(curr!=null)
        {
            count ++;
            curr = curr.next;
        }
        return count;
    }

    public WordEntry(String word) {
        p = new MyLinkedList<Position>();
       str = new String();
        str = word;
    }

    public void addPosition(Position position) {
        p.insertAtEnd(position);
        AVL.insert(position);
    }

    public void addPositions(MyLinkedList<Position> positions) {
        Node<Position> curr = new Node<Position>();
        curr = positions.start;
        while (curr != null) {
            p.insertAtEnd(curr.data);
            AVL.insert(curr.data);
            curr = curr.next;

        }
    }

    MyLinkedList<Position> getAllPositionsForThisWord() {
        return p;
    }



}