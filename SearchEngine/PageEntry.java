import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.*;

public class PageEntry {
    PageIndex pind = new PageIndex();
    String page_name;
    int total_words = 0;

    public PageEntry() {
       page_name = new String();
        pind = new PageIndex();
    }

    public PageEntry(String pageName) throws FileNotFoundException {
        pind = new PageIndex();
        page_name = pageName;
        try {
            FileInputStream webpg = new FileInputStream("webpages/" + pageName);
           
            Scanner s = new Scanner(webpg);
            String instring = new String();
            Vector<String> v = new Vector<String>();
            
            while (s.hasNext()) {
                instring = s.next();

                String words[] = instring.split("[\\{\\}\\[\\]\\<\\>\\=\\(\\)\\:\\^\\,\\.\\;\\'\\?\\!\\#\\ \"\\-]");
                 this.total_words = words.length;
                for (int i = 0; i < words.length; i++) {
                    if (!(words[i].isEmpty())) {
                        v.add(words[i]);
                    }

                }

            } 
            s.close();

            for (int i = 0; i < v.size(); i++) {
                String stt = new String();
                stt = v.get(i);
                stt = stt.toLowerCase();
                if (stt.equals("stacks"))
                    stt = "stack";
                if (stt.equals("structures"))
                    stt = "structure";
                if (stt.equals("applications"))
                    stt = "application";
                v.set(i, stt);
               
            }
              int kk =0;
             total_words = v.size();
            for (int i = 0; i < v.size(); i++) {
            
                String str = new String();
                str = v.get(i);
                if (!str.equals("a") && !str.equals("an") && !str.equals("the") && !str.equals("they")
                        && !str.equals("was") && !str.equals("for") && !str.equals("is") && !str.equals("are")
                        && !str.equals("of") && !str.equals("or") && !str.equals("and") 
                        && !str.equals("this")  && !str.equals("does")   && !str.equals("these") 
                        && !str.equals("will") && !str.equals("whose") 
                        ) {

                    Position po = new Position();
                    po.entry = this;
                    po.wordIndex = i;
                    po.newWordIndex = kk;
                    kk++;
                    
                    this.pind.addPositionForWord(str, po);

                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not there");
            return;
        }

    }

    public int pageContainsWord(String str) {
        MyLinkedList<WordEntry> wwe = new MyLinkedList<WordEntry>();
        wwe = this.pind.getWordEntries();
        Node<WordEntry> curr = new Node<WordEntry>();
        curr = wwe.start;
        while (curr != null) {
            if (curr.data.str.equals(str)) {
                return 1;
            }
            curr = curr.next;
        }
        return 0;
    }

    public PageIndex getPageIndex() {
        return pind;
    }
      
 
 

    public WordEntry getWordEntry(String str)
    {
        Node<WordEntry> curr = new Node<WordEntry>();
        curr = this.pind.pill.start;
        while(curr != null)
        {
            if(curr.data.str.equals(str))
            {
                return curr.data;
            }
            curr = curr.next;
        }
        return null;
    }

}