package kz.meirambekuly.backendlongo.web.dto;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class PageDto <T> {
    private List<T> data;
    private int pageNumber;
    private int pageSize;
    private long total;
    private int totalPages;

    public PageDto(Page<T> dataPage, List<T> objects) {
        this.data = objects;
        this.pageNumber = dataPage.getNumber();
        this.pageSize = dataPage.getSize();
        this.total = dataPage.getTotalElements();
        this.totalPages = dataPage.getTotalPages();
    }
}
