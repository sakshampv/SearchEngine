public class Position {
    PageEntry entry;
    int wordIndex;
    int newWordIndex;

    public Position() {

       entry = new PageEntry();
        wordIndex = 0;
         newWordIndex = 0;
    }

    public Position(PageEntry p, int w, int ww) {
      entry = new PageEntry();
        this.wordIndex = w;
        this.newWordIndex = ww;
        this.entry = p;

    }

    public PageEntry getPageEntry() {
        return entry;
    }

    public int getWordIndex() {
        return wordIndex;
    }
}