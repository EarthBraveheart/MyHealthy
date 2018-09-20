package com.example.healthymop.myhealthy.Weight;

public class Weight {
    String date;
    float weight;
    String status;


public Weight(String date, float weight, String status){
    this.date = date;
    this.weight = weight;
    this.status = status;
}

public float getWeight(){
    return weight;
}

public String getDate(){
    return date;
}

public String getStatus(){
    return status;
}

public void setWeight(float weight){
    this.weight = weight;
}

public void setDate(String date){
    this.date = date;
}

public void setStatus(String status){
    this.status = status;
}
}