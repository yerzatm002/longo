package kz.meirambekuly.backendlongo.web.dto;

import lombok.Data;

@Data
public class ProductDto {
    private String name;
    private String image;
    private String price;
    private String instalment;
    private String href;
    private String type;
}
