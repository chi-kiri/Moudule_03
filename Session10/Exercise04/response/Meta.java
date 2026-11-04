package response;

public class Meta {
    private long totalRecords;
    private int currentPage;

    public Meta() {
    }

    public Meta(long totalRecords, int currentPage) {
        this.totalRecords = totalRecords;
        this.currentPage = currentPage;
    }

    public long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
