package com.example.student.crystalball;



public class Predictions {

    private static Predictions predictions;
    private String[] answers;

    private Predictions() {
    answers = new String[] {
      "youre wishes will come true",
            "Youre wishes will never comr true"
    };
    }

    public static Predictions get() {
        if(predictions == null) {
            predictions = new Predictions();
        }
        return predictions;
    }

    public String getPrediction() {
        return answers[1];
    }
}
