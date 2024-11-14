package com.sivalabs.bookmarker.domain;

import java.util.List;
import org.springframework.data.domain.Page;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookmarksDto {

    private List<BookmarkDto> data;
    private long totalElements;
    private int totalPages;
    private int currentPage;
    @JsonProperty("isFirstPage")
    private boolean isFirstPage;
    @JsonProperty("isLastPage")
    private boolean isLastPage;
    private boolean hasNextPage;
    private boolean hasPreviousPage;

    public BookmarksDto(Page<BookmarkDto> bookmarkPage) {
        this.data = bookmarkPage.getContent();
        this.setTotalElements(bookmarkPage.getTotalElements());
        this.setTotalPages(bookmarkPage.getTotalPages());
        this.setCurrentPage(bookmarkPage.getNumber() + 1);
        this.setFirstPage(bookmarkPage.isFirst());
        this.setLastPage(bookmarkPage.isLast());
        this.setHasNextPage(bookmarkPage.hasNext());
        this.setHasPreviousPage(bookmarkPage.hasPrevious());


    }
}
