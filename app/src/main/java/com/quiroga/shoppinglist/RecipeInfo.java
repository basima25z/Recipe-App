package com.quiroga.shoppinglist;

public class RecipeInfo {
    private String Title;
    private String Ingredients;
    private String Directions;

    public RecipeInfo(String Title, String Ingredients, String Directions){
        this.Title = Title;
        this.Ingredients = Ingredients;
        this.Directions= Directions;
    }

    public String getTitle() {
        return Title;
    }

    public String getIngredients() {
        return Ingredients;
    }

    public String getDirections() {
        return Directions;
    }


}
