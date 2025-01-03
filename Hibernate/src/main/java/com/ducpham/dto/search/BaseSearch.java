package com.ducpham.dto.search;

import com.ducpham.enums.EnumStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseSearch {
    protected int pageNo = 1;
    protected int pageSize = 5;
    protected String keyword;
    protected String sort;
    protected EnumStatus status;
}
