package com.example.student.crystalball;



public class prediction {

    private static prediction prediction;
    private String[] answers;

    private prediction() {
    answers = new String[] {
      "youre wishes will come true"
    };
    }

    public static prediction get() {
        if(prediction == null) {
            prediction = new prediction();
        }
        return prediction;
    }
}
