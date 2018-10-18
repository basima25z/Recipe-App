package com.quiroga.shoppinglist;

public class IngredientObject {

    private boolean isOnList;
    private String FoodName;

    public IngredientObject(boolean isOnList, String FoodName){
        this.isOnList = isOnList;
        this.FoodName = FoodName;
    }

    public boolean getIsOnList() {
        return isOnList;
    }

    public void setOnList(boolean onList) {
        isOnList = onList;
    }

    public void setFoodName(String foodName) {
        FoodName = foodName;
    }

    public String getFoodName() {
        return FoodName;
    }

    @Override
    public String toString(){
        return getFoodName();
    }
}
