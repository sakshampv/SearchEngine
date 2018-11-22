
public class MyHashTable 
{
    MyLinkedList<WordEntry>[] ll = new MyLinkedList[5000];
     
    public MyHashTable()
    {
        MyLinkedList<WordEntry>[] ll = new MyLinkedList[5000];
        for(int i=0;i<5000;i++)
        {
             ll[i] = new MyLinkedList<WordEntry>();
        }
     
    }
    public int getHashIndex(String str)
    {
       int hc = (str.hashCode()) % 5000;
       if(hc<0)
       {
           hc+=5000;
       }
       return hc;
    }

    public void addPositionsForWord(WordEntry w)
    {// System.out.println("yaha pe aaya");
        int q = this.getHashIndex(w.str);
     //   System.out.println("yaha pe aaya22");
        Node<WordEntry> curr = new Node<WordEntry>();
        
     // System.out.println(ll[q]==null);
     if(ll[q]==null)
     {
        ll[q] = new MyLinkedList<WordEntry>();
     }
        curr = ll[q].start;

        
      //  System.out.println("yaha pe aaya33");
       // MyLinkedList<Position> positions = new MyLinkedList<Position>();
         int flag = 0;
        
        while(curr != null)
        {
               if(curr.data.str.equals(w.str))
               {
                    curr.data.addPositions(w.p);
                    Node<Position> curr3= new Node<Position>();
                    curr3 = w.p.start;
                    //System.out.print("For word "+w);
                    // while(curr3!=null)
                    // {
                    //     System.out.print("  "+curr3.data.p.page_name);
                    //     curr3 = curr3.next;
                    // }
                  //  System.out.println("Added entry "+w.p.page_name);
                    flag =1;
                    break;
               }
               curr = curr.next;
        }
        if(flag == 0)
        {
            ll[q].insertAtEnd(w);
        }
       
    }

}