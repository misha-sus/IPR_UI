package api.Pojo;

import java.util.Objects;

public class Car implements Comparable<Car>{
    private String engineType;
    private Integer id;
    private String mark;
    private String model;
    private Double price;
    private Integer engine_type_id;
    private Integer person_id;

    public Car() {
    }

    public String getEngineType() {
        return engineType;
    }

    public Car setEngineType(String engineType) {
        this.engineType = engineType;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public Car setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getMark() {
        return mark;
    }

    public Car setMark(String mark) {
        this.mark = mark;
        return this;
    }

    public String getModel() {
        return model;
    }

    public Car setModel(String model) {
        this.model = model;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public  Car setPrice(Double price) {
        this.price = price;
        return this;
    }

    public Integer getEngine_type_id() {
        return engine_type_id;
    }

    public Car setEngineTypeId(Integer engine_type_id) {
        this.engine_type_id = engine_type_id;
        return this;
    }

    public Integer getPerson_id() {
        return person_id;
    }

    public Car setPersonId(Integer person_id) {
        this.person_id = person_id;
        return this;
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

    @Override
    public int compareTo(Car o) {
        return this.getId() - o.getId();
    }
}
