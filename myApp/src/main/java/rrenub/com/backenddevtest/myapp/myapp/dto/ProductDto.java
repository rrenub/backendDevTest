package rrenub.com.backenddevtest.myapp.myapp.dto;

public class ProductDto {

    private final String id;
    private final String name;
    private final float price;
    private final boolean isAvailable;

    public ProductDto(String id, String name, float price, boolean isAvailable) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return isAvailable;
    }
}
