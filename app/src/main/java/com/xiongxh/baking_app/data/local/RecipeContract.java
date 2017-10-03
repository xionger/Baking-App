package com.xiongxh.baking_app.data.local;


import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public class RecipeContract {
    public static final String CONTENT_AUTHORITY = "com.xiongxh.baking_app";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_RECIPES = "recipes";
    public static final String PATH_INGREDIENTS = "ingredients";
    public static final String PATH_STEPS = "steps";

    private RecipeContract(){}

    public static final class RecipeEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_RECIPES).build();

        public  static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_RECIPES;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE+ "/" + CONTENT_AUTHORITY + "/" + PATH_RECIPES;

        public static final String TABLE_NAME = "recipes";

        public static final String COL_RECIPE_ID = "recipe_id";
        public static final String COL_RECIPE_NAME = "name";
        public static final String COL_IMAGE_URL = "recipe_image_url";
        public static final String COL_RECIPE_SERVINGS = "recipe_servings";

        public static Uri buildRecipeUri(String id){
            return CONTENT_URI.buildUpon().appendPath(id).build();
        }

        public static Uri buildRecipeWithStepsUri(String id){
            return CONTENT_URI.buildUpon().appendPath(id).appendPath(PATH_STEPS).build();
        }

        public static Uri buildRecipeWithIngredientsUri(String id){
            return CONTENT_URI.buildUpon().appendPath(id).appendPath(PATH_INGREDIENTS).build();
        }

        public static String getRecipeIdByUri(Uri uri){
            return uri.getPathSegments().get(1);
        }

    }

    public static final class IngredientEntry implements BaseColumns {
        public static final String TABLE_NAME = "ingredients";

        public static final String COL_INGR_ID = "ingredient_id";
        public static final String COL_INGR_MEASURE = "ingredient_measure";
        public static final String COL_INGR_NAME = "ingredient_name";
        public static final String COL_INGR_QUANTITY = "ingredient_quantity";

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_INGREDIENTS).build();

        public  static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_INGREDIENTS;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE+ "/" + CONTENT_AUTHORITY + "/" + PATH_INGREDIENTS;

        public static Uri buildIngredientUri(String id){
            return CONTENT_URI.buildUpon().appendPath(id).build();
        }

        public static String getIngredientIdByUri(Uri uri){
            return uri.getPathSegments().get(1);
        }
    }

    public static final class StepEntry implements BaseColumns {
        public static final String TABLE_NAME = "steps";

        public static final String COL_STEP_ID = "step_id";
        public static final String COL_STEP_SHORT_DESC = "step_short_desc";
        public static final String COL_STEP_DESCREPTION = "step_description";
        public static final String COL_STEP_VIDEO_URL = "step_video_url";
        public static final String COL_STEP_IMAGE_URL = "step_image_url";
        //public static final String COL_STEP_RECIPE_ID = "recipe_id_ref";

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_STEPS).build();

        public  static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_STEPS;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE+ "/" + CONTENT_AUTHORITY + "/" + PATH_STEPS;

        public static Uri buildStepUri(String id){
            return CONTENT_URI.buildUpon().appendPath(id).build();
        }
    }
}
