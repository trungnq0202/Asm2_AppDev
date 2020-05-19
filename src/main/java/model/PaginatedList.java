package model;

import java.util.ArrayList;
import java.util.List;

public class PaginatedList <T> {
    private int pageIndex;
    private int totalPage;
    private int offset;
    private boolean hasPrevPage;
    private boolean hasNextPage;
    private List<T> items;

    public PaginatedList (){
        items = new ArrayList<>();
    }

    public int getOffset() {
        return offset;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public boolean isHasPrevPage() {
        return hasPrevPage;
    }

    public void setHasPrevPage(boolean hasPrevPage) {
        this.hasPrevPage = hasPrevPage;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public List<T> getItems() {
        return items;
    }

    public void create(int totalRow, int pageIndex, int pageSize){
        this.pageIndex = pageIndex;
        //Calculate total number of pages
        this.totalPage = (int)(Math.ceil(totalRow/(float)pageSize));
        //Calculate the row where we start taking data
        this.offset = (pageIndex - 1) * pageSize;
        this.hasNextPage = pageIndex < totalPage;
        this.hasPrevPage = pageIndex > 1;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
