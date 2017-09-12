package com.example.user.trening24app;

/**
 * Created by User on 2017. 09. 12..
 */

public class TreningItem {

    private String trainer = null;
    private String name = null;
    private String training_date = null;

    public TreningItem(String trainer, String name, String datum) {
        super();
        this.trainer = trainer;
        this.name = name;
        this.training_date = datum;
    }

    public String getTrainer() {
        return trainer;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTraining_date() {
        return training_date;
    }

    public void setTraining_date(String training_date) {
        this.training_date = training_date;
    }

    @Override
    public String toString() {
        return name + " " + trainer + " " + training_date ;
    }

}
