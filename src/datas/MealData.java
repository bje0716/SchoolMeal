package datas;

import java.util.ArrayList;

public class MealData {

    private ArrayList<String> date;
    private ArrayList<String> food;

    public ArrayList<String> getDate() {
        return date;
    }

    public MealData setDate(ArrayList<String> date) {
        this.date = date;
        return this;
    }

    public ArrayList<String> getFood() {
        return food;
    }

    public MealData setFood(ArrayList<String> food) {
        this.food = food;
        return this;
    }
}
