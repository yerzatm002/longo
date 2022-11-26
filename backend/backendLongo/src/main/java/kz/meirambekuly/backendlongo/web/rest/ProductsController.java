package kz.meirambekuly.backendlongo.web.rest;

import com.codahale.metrics.annotation.Timed;
import kz.meirambekuly.backendlongo.services.ParsingService;
import kz.meirambekuly.backendlongo.utilities.Constants;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Parsing Controller
 * */
@RestController
@RequestMapping(Constants.PUBLIC_ENDPOINT + "/products")
@RequiredArgsConstructor
public class ProductsController {
    private final Logger log = LoggerFactory.getLogger(ProductsController.class);

    private final ParsingService parsingService;

    /**
     * @param type searched type of products
     * @return list of parsed products from yandex and kaspi markets
     * */
    @GetMapping("")
    @Timed
    public ResponseEntity<?> getProducts(@RequestParam("type")String type){
        log.debug("getting {} products from markets", type);
        return ResponseEntity.ok(parsingService.getProductsByType(type));
    }
}
