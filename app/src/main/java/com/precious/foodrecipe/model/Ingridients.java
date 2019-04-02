package com.precious.foodrecipe.model;

public class Ingridients {

    private String text;
    private float weight;


    public Ingridients(String text, float weight) {
        this.text = text;
        this.weight = weight;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
}
