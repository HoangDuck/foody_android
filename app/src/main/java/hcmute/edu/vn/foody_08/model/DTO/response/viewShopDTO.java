package hcmute.edu.vn.foody_08.model.DTO.response;

public class viewShopDTO {
    private String image;
    private String name;

    public viewShopDTO(String image, String name) {
        this.image = image;
        this.name = name;
    }

    public viewShopDTO() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
