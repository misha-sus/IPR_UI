package api.Pojo;

import java.util.Objects;

public class Car {
    private String engineType;
    private Integer id;
    private String mark;
    private String model;
    private Double price;
    private Integer engine_type_id;
    private Integer person_id;


    public Car(Integer id,String mark, String model, Double price, Integer engine_type_id, Integer person_id) {
        this.id = id;
        this.mark = mark;
        this.model = model;
        this.price = price;
        this.engine_type_id = engine_type_id;
        this.person_id = person_id;
    }

    public Car(String engineType, String mark, String model, Double price) {
        this.engineType = engineType;
        this.mark = mark;
        this.model = model;
        this.price = price;
    }

    public Car() {
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getEngine_type_id() {
        return engine_type_id;
    }

    public void setEngine_type_id(Integer engine_type_id) {
        this.engine_type_id = engine_type_id;
    }

    public Integer getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Integer person_id) {
        this.person_id = person_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(mark, car.mark) && Objects.equals(model, car.model) && Objects.equals(price, car.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mark, model, price);
    }

}
