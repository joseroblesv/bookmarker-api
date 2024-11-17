package com.sivalabs.bookmarker.domain;

import java.time.Instant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final BookmarkMapper bookmarkMapper;

    @Transactional
    public BookmarksDto getBookmarks(Integer page) {
        int pageNumber = page < 1 ? 0 : page - 1;
        Pageable pageable = PageRequest.of(pageNumber, 10, Sort.Direction.DESC, "createdAt");
        Page<BookmarkDto> bookmarkDtoPage = bookmarkRepository.findBookmarks(pageable);
        return new BookmarksDto(bookmarkDtoPage);
    }

    @Transactional
    public BookmarksDto searchBookmarks(String query, Integer page) {
        int pageNumber = page < 1 ? 0 : page - 1;
        Pageable pageable = PageRequest.of(pageNumber, 10, Sort.Direction.DESC, "createdAt");
        Page<BookmarkDto> bookmarkDtoPage = bookmarkRepository.searchBookmarks(query, pageable);
        return new BookmarksDto(bookmarkDtoPage);
    }

    public BookmarkDto createBookmark(@Valid CreateBookmarkRequest request) {
        Bookmark bookmark = new Bookmark(null, request.getTitle(), request.getUrl(), Instant.now());
        bookmarkRepository.save(bookmark);
        return bookmarkMapper.toBookmarkDto(bookmark);
    }
}
