package lk.robot.newgenic.dto.response;

import lk.robot.newgenic.dto.CombinationDTO;
import lk.robot.newgenic.dto.ProductVariationDTO;

import java.util.List;

public class ProductResponseDTO {

    private String uuid;
    private String productCode;
    private String name;
    private String description;
    private String brand;
    private boolean freeShipping;
    private List<CombinationDTO> variationList;

    public ProductResponseDTO() {
    }

    public ProductResponseDTO(String uuid,
                              String productCode,
                              String name,
                              String description,
                              String brand,
                              boolean freeShipping,
                              List<CombinationDTO> variationList) {
        this.uuid = uuid;
        this.productCode = productCode;
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.freeShipping = freeShipping;
        this.variationList = variationList;
    }


    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public boolean isFreeShipping() {
        return freeShipping;
    }

    public void setFreeShipping(boolean freeShipping) {
        this.freeShipping = freeShipping;
    }

    public List<CombinationDTO> getVariationList() {
        return variationList;
    }

    public void setVariationList(List<CombinationDTO> variationList) {
        this.variationList = variationList;
    }

    @Override
    public String toString() {
        return "ProductResponseDTO{" +
                "uuid='" + uuid + '\'' +
                ", productCode='" + productCode + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", brand='" + brand + '\'' +
                ", freeShipping=" + freeShipping +
                ", variationList=" + variationList +
                '}';
    }
}
