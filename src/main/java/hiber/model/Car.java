package hiber.model;

import javax.persistence.*;

@Entity
@Table(name="car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="modelCar")
    private String modelCar;

    @Column(name = "seriesCar")
    private int seriesCar;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    public Car(){
    }

    public Car(String modelCar, int seriesCar) {
        this.modelCar = modelCar;
        this.seriesCar = seriesCar;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", modelCar='" + modelCar + '\'' +
                ", seriesCar=" + seriesCar +
                ", user=" + user +
                '}';
    }
}
