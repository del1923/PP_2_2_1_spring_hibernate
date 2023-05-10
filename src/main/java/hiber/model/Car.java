package hiber.model;

import javax.persistence.*;

@Entity
@Table(name="car")
public class Car {

    @Id
    @Column( name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "model_car")
    private String modelCar;
    @Column(name = "series_car")
    private int seriesCar;

    public Car() {
    }

    public Car(String modelCar, int seriesCar) {
        this.modelCar = modelCar;
        this.seriesCar = seriesCar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModelCar() {
        return modelCar;
    }

    public void setModelCar(String modelCar) {
        this.modelCar = modelCar;
    }

    public int getSeriesCar() {
        return seriesCar;
    }

    public void setSeriesCar(int seriesCar) {
        this.seriesCar = seriesCar;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", modelCar='" + modelCar + '\'' +
                ", seriesCar=" + seriesCar +
                '}';
    }
}
