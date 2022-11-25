package kz.meirambekuly.backendlongo.web.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "PageableDto", description = "Dto with pagination data")
public class PageableDto {
    @ApiModelProperty(value = "Page number", example = "0")
    private int pageNumber = 0;

    @ApiModelProperty(value = "Page size", example = "15")
    private int pageSize = 15;

    @ApiModelProperty(value = "Page number", example = "id")
    private String sortBy = "id";

    @ApiModelProperty(value = "Page number", example = "ASC")
    private Sort.Direction direction = Sort.Direction.ASC;
}