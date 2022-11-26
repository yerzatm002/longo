package kz.meirambekuly.backendlongo.services;

import kz.meirambekuly.backendlongo.web.dto.ProductDto;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface ParsingService {
    Set<ProductDto> getProductsByType(String type);

}
