import java.util.*;

public class MySort
{

    ArrayList<SearchResult> sortThisList(MySet<SearchResult> listOfSortableEntries)
    {
        ArrayList<SearchResult> al = new ArrayList<SearchResult>();
        Node<SearchResult> curr = new Node<SearchResult>();
        curr = listOfSortableEntries.ll.start;
        while(curr != null)
        {
            al.add(curr.data);
            listOfSortableEntries.ll.deleteAtPos(1);
            curr = curr.next;
        }

    for(int i=0;i<al.size();i++)
    {
        for(int j=0;j<al.size()-1;j++)
        {  SearchResult sr1 = new SearchResult();

            SearchResult sr2 = new SearchResult();
            sr1 = (SearchResult) al.get(j);
            sr2 = (SearchResult) al.get(j+1);            
            if(sr1.rel>sr2.rel)
            {
                Collections.swap(al,j,j+1);
            }
        }
    }
     
        return al;

    }
}