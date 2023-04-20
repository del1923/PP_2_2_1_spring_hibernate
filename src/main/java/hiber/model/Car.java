package hiber.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="car")
public class Car implements Serializable {

    @Id
    @OneToOne
    @JoinColumn(name="users_id", referencedColumnName = "id")
    private User user;

    @Column(name="modelCar")
    private String modelCar;

    @Column(name = "seriesCar")
    private int seriesCar;

    private Car(){
    }

    public Car(User user, String modelCar, int seriesCar) {
        this.user = user;
        this.modelCar = modelCar;
        this.seriesCar = seriesCar;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
    /*
    Добавить ещё toString!
     */
}
