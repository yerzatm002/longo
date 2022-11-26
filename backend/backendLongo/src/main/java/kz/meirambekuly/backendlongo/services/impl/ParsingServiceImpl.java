package kz.meirambekuly.backendlongo.services.impl;

import kz.meirambekuly.backendlongo.services.ParsingService;
import kz.meirambekuly.backendlongo.web.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;


/**
 * Implementation of parsing methods
 * **/
@Service
@RequiredArgsConstructor
public class ParsingServiceImpl implements ParsingService {
    private final Logger log = LoggerFactory.getLogger(ParsingServiceImpl.class);

    @Value("#{'${website.urls}'.split(',')}")
    List<String> urls;

    @Override
    public Set<ProductDto> getProductsByType(String type) {
        Set<ProductDto> products = new HashSet<>();
        for (String url : urls){
            if(url.contains(type) && url.contains("kaspi")){
                extractDataFromKaspi(products, url, type);
            }else if (url.contains(type) && url.contains("yandex")){
                extractDataFromYandex(products, url, type);
            }
        }
        return products;
    }

    /**
     * Parsing products from Kaspi Shop
     * */
    private void extractDataFromKaspi(Set<ProductDto> products, String url, String type){
        try{
            Document document = Jsoup.connect(url).get();
            Element element = document.getElementsByClass("item-cards-grid__cards").first();
            Elements elements = element.getElementsByTag("div");
            for (Element product : elements){
                ProductDto productDto = new ProductDto();
                if(Objects.nonNull(product.getElementsByClass("item-card__image-wrapper").first())){
                    Element href = product.getElementsByClass("item-card__image-wrapper").first();
                    productDto.setHref("https://kaspi.kz/" + href.attr("href"));
                    Element imageElement = href.select("img").first();
                    String absoluteUrl = imageElement.absUrl("src");
                    String srcValue = imageElement.attr("src");
                    productDto.setImage(srcValue);
                    Element info = product.getElementsByClass("item-card__info").first();
                    Element name = info.getElementsByClass("item-card__name").first();
                    productDto.setName(name.getElementsByTag("a").first().text());
                    Element price = info.getElementsByClass("item-card__prices").first();
                    productDto.setPrice(price.getElementsByClass("item-card__debet").first()
                            .getElementsByClass("item-card__prices-price").first().text());
                    productDto.setInstalment(price.getElementsByClass("item-card__instalment").first()
                            .getElementsByClass("item-card__prices-price").first().text() + " : " +
                            price.getElementsByClass("item-card__instalment").first()
                                    .getElementsByClass("item-card__add-info").first().text() );
                    productDto.setType(type);
                }
                if (productDto.getHref() != null)
                    products.add(productDto);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Parsing products from yandex market by type
     * **/
    private void extractDataFromYandex(Set<ProductDto> products, String url, String type){
        try{
            Document document = Jsoup.connect(url).get();
            Element element = document.getElementsByAttributeValue("data-test-id", "virtuoso-item-list").first();
            Elements elements = element.getElementsByAttribute("data-index");
            for (Element product : elements){
                ProductDto productDto = new ProductDto();
                if (!StringUtils.isEmpty(product.attr("data-index"))){
                    Element article = product.getElementsByTag("article").first();
                    Element href = article.getElementsByClass("_2f75n _3xGwF cia-cs").first();
                    productDto.setHref("https://market.yandex.kz" + href.attr("href"));
                    productDto.setImage(href.getElementsByTag("img").first().attr("src"));
                    Element info = article.getElementsByClass("_3Fff3").first();
                    productDto.setName(info.getElementsByTag("a").first().attr("title"));
                    Element price = article.getElementsByAttributeValue("data-auto", "mainPrice").first();
                    productDto.setPrice(price.getElementsByTag("span").first().text());
                    productDto.setType(type);
                    if (productDto.getHref() != null)
                        products.add(productDto);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
