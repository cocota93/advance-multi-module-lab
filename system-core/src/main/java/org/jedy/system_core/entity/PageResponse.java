package org.jedy.system_core.entity;

import lombok.Data;
import org.springframework.data.domain.Pageable;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
public class PageResponse<T> {

    private final long total;
    private final int pageSize;
    private final int pageNumber;
    private final long offset;
    private final List<T> content = new ArrayList<>();

    private final boolean firstPage;
    private final boolean lastPage;
    private final boolean hasPrevious;
    private final boolean hasNext;

    public PageResponse(@NotNull List<T> content, @NotNull Pageable pageable, long total) {
        Assert.notNull(content, "Content must not be null!");
        Assert.notNull(pageable, "Pageable must not be null!");

        this.content.addAll(content);
        pageSize = pageable.getPageSize();
        pageNumber = pageable.getPageNumber();
        offset = pageable.getOffset();
        this.total = pageable.toOptional().filter(it -> !content.isEmpty())//
                             .filter(it -> it.getOffset() + it.getPageSize() > total)//
                             .map(it -> it.getOffset() + content.size())//
                             .orElse(total);

        hasPrevious = pageable.hasPrevious();
        firstPage = !hasPrevious;

        lastPage = offset + pageSize >= total;
        hasNext = !lastPage;
    }


}
