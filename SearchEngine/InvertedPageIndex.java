
import java.lang.*;

public class InvertedPageIndex
{
    MyLinkedList<PageEntry> ll = new MyLinkedList<PageEntry>();
    MyHashTable hsht = new MyHashTable();


    public void addPage(PageEntry p)
    {    MyLinkedList<WordEntry> well = new MyLinkedList<WordEntry>();
        this.ll.insertAtEnd(p);
         well = p.pind.getWordEntries();
         Node<WordEntry> curr = new Node<WordEntry>();
         curr = well.start;
         while(curr != null)
         {
             hsht.addPositionsForWord(curr.data);
             curr =curr.next;
         }
        
    }

    MySet<PageEntry> getPagesWhichContainWord(String str)
    {
       int j = hsht.getHashIndex(str);
       Node<WordEntry> curr = new Node<WordEntry>();
       if(hsht.ll[j]==null)
       {
           hsht.ll[j] = new MyLinkedList<WordEntry>();
           MySet<PageEntry> peee = new MySet<PageEntry>();
           return peee;
       }
       curr = hsht.ll[j].start;
       MySet<PageEntry> set = new MySet<PageEntry>();
       while(curr!=null)
       {
           if(curr.data.str.equals(str))
           {
               Node<Position> curr2 = new Node<Position>();
               curr2 = curr.data.p.start;
              
               while(curr2 != null)
               {
                  set.addElement(curr2.data.entry);
            
                   curr2 = curr2.next;
               }

               break;
           }

           curr = curr.next;
       }

       return set;
    }
    

    MySet<PageEntry> getPagesWhichContainPhrase(String str[])
    {
       Node<PageEntry> curr = new Node<PageEntry>();
       
        MySet<PageEntry> s = new MySet<PageEntry>();
        str[0] = str[0].toLowerCase();
            if(str[0].equals("stacks"))
            {
                str[0] = "stack";
            }
            if(str[0].equals("applications"))
            {
                str[0] = "application";
            }
            if(str[0].equals("structures"))
            {
                str[0] = "structure";
            }
            s = getPagesWhichContainWord(str[0]);

        for(int i=0;i<str.length;i++)
        {    str[i] = str[i].toLowerCase();
            if(str[i].equals("stacks"))
            {
                str[i] = "stack";
            }
            if(str[i].equals("applications"))
            {
                str[i] = "application";
            }
            if(str[i].equals("structures"))
            {
                str[i] = "structure";
            }
            s=s.intersection(getPagesWhichContainWord(str[i]));
        }
        curr = s.ll.start;
       // System.out.println(s.isEmpty()==1);
       MySet<PageEntry> ppp = new MySet<PageEntry>();
       
      
        while(curr != null)
        {
             if(checkForPhrase(str,curr.data)!=0)
             {
                ppp.addElement(curr.data);
             }

             curr = curr.next;
         
        }
      // System.out.println(ppp.isEmpty()==1);
       return ppp;
    }

    public int checkForPhrase(String str[], PageEntry p)
    {
        int num = 0;
        for(int i=0;i<p.getWordEntry(str[0]).ll_size();i++)
        {   
            int flag = 0;
       
            int num_words = p.getWordEntry(str[0]).AVL.get(i).newWordIndex;
            for(int j=1;j<str.length;j++)
            {
                num_words++;
                if(p.getWordEntry(str[j]).AVL.contains(num_words)==0)
                {
                    flag = 1;
                    break;
                }
            }

            if(flag==0)
            {
                num ++;
            }

        }    
           return num;
        
    }



    MySet<PageEntry> findPagesWhichContainAllWords(String str[])
    {
       Node<PageEntry> curr = new Node<PageEntry>();
        curr = this.ll.start;
        MySet<PageEntry> s = new MySet<PageEntry>();
        str[0] = str[0].toLowerCase();
            if(str[0].equals("stacks"))
            {
                str[0] = "stack";
            }
            if(str[0].equals("applications"))
            {
                str[0] = "application";
            }
            if(str[0].equals("structures"))
            {
                str[0] = "structure";
            }
            s = getPagesWhichContainWord(str[0]);

        for(int i=0;i<str.length;i++)
        {    str[i] = str[i].toLowerCase();
            if(str[i].equals("stacks"))
            {
                str[i] = "stack";
            }
            if(str[i].equals("applications"))
            {
                str[i] = "application";
            }
            if(str[i].equals("structures"))
            {
                str[i] = "structure";
            }
            s=s.intersection(getPagesWhichContainWord(str[i]));
        }
            
        return s;
      
        }

        MySet<PageEntry> findPagesWhichContainAnyOfTheseWords(String str[])
        {
            Node<PageEntry> curr = new Node<PageEntry>();
            curr = this.ll.start;
            MySet<PageEntry> s = new MySet<PageEntry>();
            str[0] = str[0].toLowerCase();
                if(str[0].equals("stacks"))
                {
                    str[0] = "stack";
                }
                if(str[0].equals("applications"))
                {
                    str[0] = "application";
                }
                if(str[0].equals("structures"))
                {
                    str[0] = "structure";
                }
                s = getPagesWhichContainWord(str[0]);
    
            for(int i=0;i<str.length;i++)
            {    str[i] = str[i].toLowerCase();
                if(str[i].equals("stacks"))
                {
                    str[i] = "stack";
                }
                if(str[i].equals("applications"))
                {
                    str[i] = "application";
                }
                if(str[i].equals("structures"))
                {
                    str[i] = "structure";
                }
                s=s.union(getPagesWhichContainWord(str[i]));
            }
                
            return s;
          
            }

            float getRelevanceOfPage(String str[ ], boolean doTheseWordsRepresentAPhrase, PageEntry p)
            {   float rel = (float) 0.0;
                if(!doTheseWordsRepresentAPhrase)
                {
                     for(int i=0;i<str.length; i++)
                     {
                          rel = rel + relevance(p, str[i]);
                     }

                     return rel;
                }
                else
                {
                   int m = checkForPhrase(str, p);
                   int tot_word = p.total_words;
                   int k = str.length;
                   int tot_webpg = this.ll.size;
                   int nw = this.getPagesWhichContainPhrase(str).ll.size;
                   float tf = (float) m/(tot_word-(k-1)*m);
                   float  idf = (float) tot_webpg/nw;
                   float logidf = (float) Math.log((double) idf);
                    rel = tf*logidf;
                   return rel;

                }
            }

            float relevance(PageEntry p, String str)
            {   if( p.pind.getIndicesOfWord(str) == null)
                {
                    return (float)0.0;
                }
                int freq = p.pind.getIndicesOfWord(str).size;
                int tot_word = p.total_words;
                float tf = (float) freq/tot_word;
                int tot_webpg = this.ll.size;
                int nw = getPagesWhichContainWord(str).ll.size;
                float idf = (float) nw/tot_webpg;
                float logidf = (float) Math.log10((double) idf);
                float rel = (float) tf*logidf;
                return rel;

                
            }

    }

