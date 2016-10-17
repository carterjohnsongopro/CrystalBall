package com.example.student.crystalball;



public class prediction {

    public static prediction prediction;

    private prediction() {

    }

    public static prediction get() {
        if(prediction == null) {
            prediction = new prediction();
        }
        return prediction;
    }
}
