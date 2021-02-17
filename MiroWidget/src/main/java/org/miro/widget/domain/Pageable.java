package org.miro.widget.domain;

import org.miro.widget.dto.base.Base;
import org.miro.widget.dto.response.WidgetResponse;

import java.util.List;

public class Pageable extends Base {

    public static final int DEFAULT_PAGE_SIZE = 10;

    private final List<WidgetResponse> list;

    private int pageSize = DEFAULT_PAGE_SIZE;

    private int page;

    private int startingIndex;

    private int endingIndex;

    private int maxPages;

    public Pageable(List<WidgetResponse> list) {
        this.list = list;
        this.page = 1;
        this.maxPages = 1;

        calculatePages();
    }

    private void calculatePages() {
        if (pageSize > 0) {
            if (list.size() % pageSize == 0) {
                maxPages = list.size() / pageSize;
            } else {
                maxPages = (list.size() / pageSize) + 1;
            }
        }
    }

    public List<WidgetResponse> getContent() {
        return list.subList(startingIndex, endingIndex);
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
        calculatePages();
    }

    public void setPage(int p) {
        if (p >= maxPages) {
            this.page = maxPages;
        } else {
            this.page = Math.max(p, 1);
        }
        startingIndex = pageSize * (page - 1);
        if (startingIndex < 0) {
            startingIndex = 0;
        }
        endingIndex = startingIndex + pageSize;
        if (endingIndex > list.size()) {
            endingIndex = list.size();
        }
    }
}