public class MySet<X>
{   
    MyLinkedList<X> ll = new MyLinkedList<X>();

    public MySet()
    {
      ll = new MyLinkedList<X>();

    }
    public int isEmpty()
    {
        if(ll.start == ll.end)
        {
            return 1;
        }
        return 0;
    }

    public void addElement(X element)
    {  
         if(this.ll.isMember(element)==0)
         {
         ll.insertAtEnd(element);
         }
    }

    
    public void add(X element)
    {  
         if(this.ll.isMember(element)==0)
         {
         ll.insertAtEnd(element);
         }
    }

    MySet<X> union(MySet<X> otherSet)
    {
        MySet<X> temp = new MySet<X>();
        Node<X> curr = this.ll.start;
        while(curr != null)
        {
            temp.addElement(curr.data);
             curr= curr.next;
        }
        Node<X> curr2 = otherSet.ll.start;
         while(curr2 != null)
        {
            temp.addElement(curr2.data);
            curr2= curr2.next;
        }

        return temp;
    }

    MySet<X> intersection(MySet<X> otherSet)
    {
        MySet<X> temp = new MySet<X>();
        Node<X> curr= otherSet.ll.start;
        while(curr != null)
        {
            if(this.ll.isMember(curr.data)!=0)
            {
                 temp.addElement(curr.data);
            }

            curr = curr.next;
        }
        return temp;
    }


}