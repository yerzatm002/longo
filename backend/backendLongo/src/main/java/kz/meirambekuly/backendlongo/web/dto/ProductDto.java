package kz.meirambekuly.backendlongo.web.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "ProductDto", description = "Dto for parsed products from markets")
public class ProductDto {
    private String name;
    private String image;
    private String price;
    private String instalment;
    private String href;
    private String type;
}
