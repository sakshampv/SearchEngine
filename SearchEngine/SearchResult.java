public class SearchResult implements Comparable<SearchResult> {

    PageEntry page_entry;
    float rel;
    public SearchResult() {
        page_entry = new PageEntry();
        rel = (float) 0.0;
    }

    public SearchResult(PageEntry p, float r) {
        page_entry= p;
        rel = r;
    }

    public PageEntry getPageEntry() {
        return page_entry;
    }

    public double getRelevance() {
        return rel;
    }

    public int compareTo(SearchResult otherObject) {
        if (otherObject.rel > this.rel) {
            return 1;
        } 
        else if (otherObject.rel == this.rel) {
            return 0;
        } 
        else { 
            return -1;
        }
    }
}