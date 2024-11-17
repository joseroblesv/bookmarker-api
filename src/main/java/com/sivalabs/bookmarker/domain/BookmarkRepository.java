package com.sivalabs.bookmarker.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    @Query("select new com.sivalabs.bookmarker.domain.BookmarkDto(b.id, b.title, b.url, b.createdAt) from Bookmark b")
    Page<BookmarkDto> findBookmarks (Pageable pageable);

    @Query("""
            select new com.sivalabs.bookmarker.domain.BookmarkDto(b.id, b.title, b.url, b.createdAt) from Bookmark b
            where lower(b.title) like lower(concat('%', :query, '%'))
            """)
    Page<BookmarkDto> searchBookmarks (String query, Pageable pageable);

    Page<BookmarkDto> findByTitleContainsIgnoreCase (String query, Pageable pageable);

}
