package com.heifeng.search.controller;

import com.heifeng.common.pojo.PageResult;
import com.heifeng.search.pojo.SearchRequest;
import com.heifeng.search.service.SearchService;
import com.heifeng.search.pojo.Goods;
import com.heifeng.search.pojo.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class SearchController {
    @Autowired
    private SearchService searchService;

    /**
     * 搜索商品
     *
     * @param request
     * @return
     */
    @PostMapping("page")
    public ResponseEntity<PageResult<Goods>> search(@RequestBody SearchRequest request) {
        SearchResult searchResult = this.searchService.search(request);
        if (searchResult == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(searchResult);
    }
}
