package com.ordermanagement.orders.extservice;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "productCode",
    "productName",
    "availableQuantity",
    "unitPrice"
})
public class ItemResponse {

    @JsonProperty("productCode")
    private Integer productCode;
    @JsonProperty("productName")
    private String productName;
    @JsonProperty("availableQuantity")
    private Integer availableQuantity;
    @JsonProperty("unitPrice")
    private Integer unitPrice;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("productCode")
    public Integer getProductCode() {
        return productCode;
    }

    @JsonProperty("productCode")
    public void setProductCode(Integer productCode) {
        this.productCode = productCode;
    }

    @JsonProperty("productName")
    public String getProductName() {
        return productName;
    }

    @JsonProperty("productName")
    public void setProductName(String productName) {
        this.productName = productName;
    }

    @JsonProperty("availableQuantity")
    public Integer getAvailableQuantity() {
        return availableQuantity;
    }

    @JsonProperty("availableQuantity")
    public void setAvailableQuantity(Integer availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    @JsonProperty("unitPrice")
    public Integer getUnitPrice() {
        return unitPrice;
    }

    @JsonProperty("unitPrice")
    public void setUnitPrice(Integer unitPrice) {
        this.unitPrice = unitPrice;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
