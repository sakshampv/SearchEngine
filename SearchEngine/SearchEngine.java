import java.io.IOException;
import java.util.ArrayList;

public class SearchEngine {
    InvertedPageIndex ipe = new InvertedPageIndex();

    public SearchEngine() {
        ipe = new InvertedPageIndex();

    }

    void performAction(String actionMessage) throws Exception {

        String[] am = actionMessage.split(" ");
        if (am[0].equals("addPage")) {

            try {
                PageEntry pe = new PageEntry(am[1]);
                ipe.addPage(pe);
            } 
            catch (Exception e) 
            {
                System.out.println("Page not added");
            }

            return;
        }

        if (am[0].equals("queryFindPagesWhichContainWord")) {
            String wrd = new String();
            wrd = am[1];
            wrd = wrd.toLowerCase();
            MySet<PageEntry> peee = new MySet<PageEntry>();
            peee = this.ipe.getPagesWhichContainWord(wrd);

            int flag = 0;
            Node<PageEntry> curr = new Node<PageEntry>();
            curr = peee.ll.start;
            int first = 1;
            while (curr != null) {
                flag = 1;
                if(first == 0)
                {
                    System.out.print(", ");
                }
                if(first==1)
                {
                    first = 0;
                }
                
                System.out.print(curr.data.page_name);
                curr = curr.next;
            }
            System.out.print('\n');

            if (flag == 0) {
                System.out.println("No webpage contains word " + wrd);
            }
            return;

        }

        if (am[0].equals("queryFindPositionsOfWordInAPage")) {
            String wrd = new String();
            String doc = new String();
            String wwrd = new String();
            wwrd = am[1];
            wrd = am[1];
            wrd = wrd.toLowerCase();
            doc = am[2];
           
            Node<PageEntry> curr = new Node<PageEntry>();
            curr = this.ipe.ll.start;
            MyLinkedList<Integer> lll = new MyLinkedList<Integer>();
            MyLinkedList<WordEntry> wee = new MyLinkedList<WordEntry>();
            
            int flag =1;
            while (curr != null) {
                if (curr.data.page_name.equals(doc)) {
                    flag = 0;
                    wee = curr.data.pind.pill;
                    break;
                }
                curr = curr.next;
            }
            if(flag == 1)
            {
                System.out.println("No webpage "+doc+" found");
                return;
            }
        
            Node<WordEntry> nu = new Node<WordEntry>();
            nu = wee.start;
            while(nu!=null)
            {
                if(nu.data.str.equals(wrd))
                {
                   Node<Position> curr6 = new Node<Position>();
                   curr6 = nu.data.p.start;
                   while(curr6 != null)
                   {
                       if(curr6.data.entry.page_name.equals(doc))
                       {
                           lll.insertAtEnd(curr6.data.wordIndex);
                       }
                       curr6 = curr6.next;
                   }
                   break;
                  
                }
                nu = nu.next;
            }

            Node<Integer> nn = new Node<Integer>();
            nn = lll.start;
            if (lll.start == null) {
                System.out.println("Webpage "+doc+" does not contain word "+wwrd);
                return;
            }


            int first = 1;
            while (nn != null) {
                if(first == 0 )
                { 
                    System.out.print(", ");
                }
                if(first == 1)
                {
                    first = 0;
                }
               
                System.out.print(nn.data);
                nn= nn.next;
        
            }
            System.out.println('\n');

            return;

        }

        if (am[0].equals("queryFindPagesWhichContainAllWords")) {
        
            String[] str = new String[am.length - 1];
                for (int i = 0; i < str.length; i++) {
                    str[i] = am[i + 1];
                }

                 MySet<PageEntry> ss = new MySet<PageEntry>();
                 ss = this.ipe.findPagesWhichContainAllWords(str);
                MySet<SearchResult> ssq = new MySet<SearchResult>();
                int k = ss.ll.size;
                for(int i = 0; i< k; i++)
                { 
                   SearchResult sr = new SearchResult();
                   sr.page_entry = ss.ll.start.data;
                   sr.rel= this.ipe.getRelevanceOfPage(str, false, sr.page_entry);
                   ssq.add(sr);
                   ss.ll.deleteAtPos(1);
                }

                ArrayList<SearchResult> al = new ArrayList<SearchResult>();
                MySort ms = new MySort();
                al =  ms.sortThisList(ssq);

                int first = 1;
              for(int i=0;i<al.size();i++)
               {
                    if(first == 0 )
                    { 
                        System.out.print(", ");
                    }
                    if(first == 1)
                    {
                        first = 0;
                    }
                   
                    System.out.print(al.get(i).page_entry.page_name);
                   
            
                }
                System.out.println('\n');
    
                   return;
               

               
              


        }

        if (am[0].equals("queryFindPagesWhichContainAnyOfTheseWords")) {
        
            String[] str = new String[am.length - 1];
                for (int i = 0; i < str.length; i++) {
                    str[i] = am[i + 1];
                }

                MySet<PageEntry> ss = new MySet<PageEntry>();
                ss = this.ipe.findPagesWhichContainAnyOfTheseWords(str);
                MySet<SearchResult> ssq = new MySet<SearchResult>();
                int k = ss.ll.size;
                for(int i = 0; i< k; i++)
                { 
                   SearchResult sr = new SearchResult();
                   sr.page_entry = ss.ll.start.data;
                   sr.rel= this.ipe.getRelevanceOfPage(str, false, sr.page_entry);
                   ssq.add(sr);
                   ss.ll.deleteAtPos(1);
                }

                ArrayList<SearchResult> al = new ArrayList<SearchResult>();
                MySort ms = new MySort();
                al =  ms.sortThisList(ssq);
                int first = 1;
                for(int i=0;i<al.size();i++)
                 {
                      if(first == 0 )
                      { 
                          System.out.print(", ");
                      }
                      if(first == 1)
                      {
                          first = 0;
                      }
                     
                      System.out.print(al.get(i).page_entry.page_name);
                     
              
                  }
                  System.out.println('\n');
                
                   return;

        }

      
        if (am[0].equals("queryFindPagesWhichContainPhrase")) {
            MySet<PageEntry> ss = new MySet<PageEntry>();
            String[] str = new String[am.length - 1];
            for (int i = 0; i < str.length; i++) {
                str[i] = am[i + 1];
            }
            ss = this.ipe.getPagesWhichContainPhrase(str);
            MySet<SearchResult> ssq = new MySet<SearchResult>();
            int k = ss.ll.size;
            for(int i = 0; i< k; i++)
            { 
               SearchResult sr = new SearchResult();
               sr.page_entry = ss.ll.start.data;
               sr.rel= this.ipe.getRelevanceOfPage(str, true, sr.page_entry);
               ssq.add(sr);
               ss.ll.deleteAtPos(1);
            }

            ArrayList<SearchResult> al = new ArrayList<SearchResult>();
            MySort ms = new MySort();
            al =  ms.sortThisList(ssq);
            
            int first = 1;
            for(int i=0;i<al.size();i++)
             {
                  if(first == 0 )
                  { 
                      System.out.print(", ");
                  }
                  if(first == 1)
                  {
                      first = 0;
                  }
                 
                  System.out.print(al.get(i).page_entry.page_name);
                 
          
              }
              System.out.println('\n');
               return;

        
        
        }
       

    }
}