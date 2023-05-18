package rs.raf.RentACarNotificationService.userservice.dto;

public class RankDto {
    private Long id;
    private String name;
    private String description;
    private Integer minDaysRented;
    private Integer maxDaysRented;
    private Integer discount;

    public RankDto(Long id, String name, String description, Integer minDaysRented, Integer maxDaysRented, Integer discount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.minDaysRented = minDaysRented;
        this.maxDaysRented = maxDaysRented;
        this.discount = discount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getMinDaysRented() {
        return minDaysRented;
    }

    public void setMinDaysRented(Integer minDaysRented) {
        this.minDaysRented = minDaysRented;
    }

    public Integer getMaxDaysRented() {
        return maxDaysRented;
    }

    public void setMaxDaysRented(Integer maxDaysRented) {
        this.maxDaysRented = maxDaysRented;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }
}
