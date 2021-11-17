package com.br.itapemirim.response;

import java.util.List;
import lombok.Getter;

@Getter
public class PageResponse<T>{

    private int pageNumber;
    private int pageSize;
    private long totalCount;
    private List<T> listings = null;
    
    public PageResponse(int pageNumber, int pageSize, long totalCount, List<T> listings) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.listings = listings;
    }
}
