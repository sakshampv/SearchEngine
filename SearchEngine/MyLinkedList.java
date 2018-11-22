public class MyLinkedList<X>
{
    public Node<X> start;
    public Node<X> end;
    public int size;

    public MyLinkedList()
    {
        Node<X> start = new Node<X>();
        start = null;
         Node<X> end = new Node<X>();
        end = null;
        size = 0;
    }

    public int isMember(X dat)
    {
             if(start == null)
		{
			return 0;
		}
		if(start.data.equals(dat))
		{
			return 1;
		}
		Node<X> curr = start;
		while(curr!=null)
		{
			if(curr.data.equals(dat))
			{
				return 1;
			}
			curr = curr.next;
		}
		
		return 0;
		
    }

    public boolean isEmpty()
	{
		return start == null;
	}
     

    public void insertAtStart(X val)
	{
		Node<X> nptr = new Node<X>(val, null);     
        	size++ ;   
		if(start == null) 
		{
			start = nptr;
			end = start;
		}
		else 
		{
			nptr.next=start;
			start = nptr;
		}
	}

    public void insertAtEnd(X val)
	{
		Node<X> nptr = new Node<X>(val,null);    
	   	size++ ; 
		if(start == null) 
		{
			start = nptr;
			end = start;
		}
		else 
		{
			end.next=nptr;
			end = nptr;
		}
	}

    public int findpos(X o)   // To find position of certain object (One-Based indexing)
	{
		
		if(start.data.equals(o))
		{
			return 0;
		}
		Node<X> curr = start;
		int pos=0;     
		while(curr!=null)
		{
			if(curr.data.equals(o))
			{ 
				return pos;
			}
			else{
				curr=curr.next;
				pos++;
			}
			
		}
		return pos;
	}

    
    public void ll_delete(X o)
	{ 
		int pos = findpos(o);
		deleteAtPos(pos);
        
		
	}


   public void deleteAtPos(int pos)
	{        
		if (pos == 1) 
		{
			start = start.next;
			size--; 
			return ;
		}
		if (pos == size) 
		{
			Node<X> s = start;
			Node<X> t = start;
			while (s != end)
			{
				t = s;
				s = s.next;
			}
			end = t;
			end.next = null;
			size --;
			return;
		}
		Node<X> ptr = start;
		pos = pos - 1 ;
		for (int i = 1; i < size - 1; i++) 
		{
			if (i == pos) 
			{
				Node<X> tmp = ptr.next;
				tmp = tmp.next;
				ptr.next = tmp;
				break;
			}
			ptr = ptr.next;
		}
		size-- ;
	}   
	
}
