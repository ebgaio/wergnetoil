package com.wergnet.wergnetoil.api.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class PaginationUtil {

    public Pageable build(int pageNumber, int pageSize, String... ordenacao) {
        return PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, ordenacao);
    }

    public Pageable build(int pageNumber, int pageSize, Sort.Direction direction, String... ordenacao) {
        return PageRequest.of(pageNumber, pageSize, direction, ordenacao);
    }
}
