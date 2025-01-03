package com.ducpham.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageResponse<T> {
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private long totalPages;
    private List<T> content;
}
