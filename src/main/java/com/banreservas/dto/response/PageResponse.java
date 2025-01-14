package com.banreservas.dto.response;

import java.util.List;

public class PageResponse<T> {
    public List<T> items;
    public long totalItems;
    public int page;
    public int size;
    public int totalPages;
}