public class PageIndex
{
  public MyLinkedList<WordEntry> pill = new MyLinkedList<WordEntry>();


public void addPositionForWord(String str, Position p)
{   
     Node<WordEntry> curr = new Node<WordEntry>();
     curr = this.pill.start;
     int flag = 0;
   
     while(curr!=null)
     {
         if(curr.data.str.equals(str))
         {
             curr.data.p.insertAtEnd(p);
             curr.data.AVL.insert(p);
             flag =1 ;
             break;
         }
         curr = curr.next;
     }
     
     if(flag == 0)
     {

         WordEntry we = new WordEntry(str);
         we.str = str;
         MyLinkedList<Position> pp = new MyLinkedList<Position>();
         pp.insertAtStart(p);
         we.p = pp;
         we.AVL.insert(p);
         pill.insertAtEnd(we);
     }
}


public MyLinkedList<WordEntry> getWordEntries()
{
    return pill;
}

MyLinkedList<Integer> getIndicesOfWord(String str)
{   MyLinkedList<Integer> lll = new MyLinkedList<Integer>();
    Node<WordEntry> curr = this.pill.start;
    while(curr!=null)
    {
        if(curr.data.str.equals(str))
        {
            Node<Position> curr2 = new Node<Position>();
            curr2= curr.data.p.start;
            while(curr2!=null)
            {          if(lll.isMember(curr2.data.wordIndex)==0 )
                {
                    lll.insertAtEnd(curr2.data.wordIndex);
                }
                    curr2 = curr2.next;

            }

            break;
        }

        curr = curr.next;
    }

    return lll;
} 

int getWordFrequency(String str)
{ 
    MyLinkedList<Integer> lll = new MyLinkedList<Integer>();
    lll = getIndicesOfWord(str);
    Node<Integer> curr = new Node<Integer>();
    curr = lll.start;
    int count  = 0;
    while(curr!=null)
    {
        count++;
        curr= curr.next;
    }

    return count;
}




}