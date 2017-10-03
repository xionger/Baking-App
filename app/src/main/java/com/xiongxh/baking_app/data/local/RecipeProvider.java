package com.xiongxh.baking_app.data.local;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.xiongxh.baking_app.data.local.RecipeContract.RecipeEntry;
import com.xiongxh.baking_app.data.local.RecipeContract.IngredientEntry;
import com.xiongxh.baking_app.data.local.RecipeContract.StepEntry;

public class RecipeProvider extends ContentProvider{
    public static final String LOG_TAG =RecipeProvider.class.getSimpleName();

    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private RecipeDbHelper mRecipeDbHelper;

    private static final int RECIPES = 100;
    private static final int RECIPE_IDX = 101;

    private static final int INGREDIENTS = 200;
    private static final int INGREDIENT_IDX = 201;

    private static final int STEPS = 300;
    private static final int STEP_IDX = 301;

    private static final String mRecipeIdSelection =
            RecipeEntry.TABLE_NAME + "." + RecipeEntry.COL_RECIPE_ID + "=?";

    private static final String mIngredientIdSelection =
            IngredientEntry.TABLE_NAME + "." + IngredientEntry.COL_INGR_ID + "=?";
    private static final String mStepIdSelection =
            StepEntry.TABLE_NAME + "." + StepEntry.COL_STEP_ID + "=?";


    public static UriMatcher buildUriMatcher(){

        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = RecipeContract.CONTENT_AUTHORITY;

        matcher.addURI(authority, RecipeContract.PATH_RECIPES, RECIPES);
        matcher.addURI(authority, RecipeContract.PATH_RECIPES + "/#", RECIPE_IDX);

        matcher.addURI(authority, RecipeContract.PATH_INGREDIENTS, INGREDIENTS);
        matcher.addURI(authority, RecipeContract.PATH_INGREDIENTS + "/#", INGREDIENT_IDX);

        matcher.addURI(authority, RecipeContract.PATH_STEPS, STEPS);
        matcher.addURI(authority, RecipeContract.PATH_STEPS + "/#", STEP_IDX);

        return matcher;
    }

    @Override
    public boolean onCreate() {
        mRecipeDbHelper = new RecipeDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        final SQLiteDatabase db = mRecipeDbHelper.getReadableDatabase();
        final int match = sUriMatcher.match(uri);

        Cursor returnCursor;

        Log.d(LOG_TAG, "Query matches: " + match);

        switch (match){
            case RECIPES: {

                returnCursor = db.query(
                        RecipeEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            }
            case RECIPE_IDX: {

                String movieIndexStr = uri.getPathSegments().get(1);
                String mSelection = "_id=?";
                String[] mSelectionArgs = new String[]{movieIndexStr};

                returnCursor = db.query(
                        RecipeEntry.TABLE_NAME,
                        projection,
                        mSelection,
                        mSelectionArgs,
                        null,
                        null,
                        sortOrder);

                break;
            }
            case INGREDIENT_IDX: {
                Log.d(LOG_TAG, "1: Error here?");
                String recipeIndexStr = uri.getPathSegments().get(1);
                //String mSelection = "_id=?";
                Log.d(LOG_TAG, "movieIndexStr: " + recipeIndexStr);
                String[] mSelectionArgs = new String[]{recipeIndexStr};
                Log.d(LOG_TAG, "2: Error here?");
                returnCursor = db.query(
                        IngredientEntry.TABLE_NAME,
                        projection,
                        mIngredientIdSelection,
                        //mSelection,
                        mSelectionArgs,
                        null,
                        null,
                        null);
                Log.d(LOG_TAG, "3: Error here?");
                break;

            }
            case STEP_IDX: {
                Log.d(LOG_TAG, "1: Error here?");
                String recipeIndexStr = uri.getPathSegments().get(1);
                //String mSelection = "_id=?";
                Log.d(LOG_TAG, "movieIndexStr: " + recipeIndexStr);
                String[] mSelectionArgs = new String[]{recipeIndexStr};
                Log.d(LOG_TAG, "2: Error here?");
                returnCursor = db.query(
                        StepEntry.TABLE_NAME,
                        projection,
                        mStepIdSelection,
                        //mSelection,
                        mSelectionArgs,
                        null,
                        null,
                        null);
                Log.d(LOG_TAG, "3: Error here?");
                break;

            }

            default: {
                throw new UnsupportedOperationException("Unknown uri: " + uri);
            }
        }

        returnCursor.setNotificationUri(getContext().getContentResolver(), uri);

        return returnCursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        final int match = sUriMatcher.match(uri);

        switch (match){
            case RECIPES:{
                return RecipeEntry.CONTENT_TYPE;
            }
            case RECIPE_IDX:{
                return RecipeEntry.CONTENT_ITEM_TYPE;
            }
            case INGREDIENTS:{
                return IngredientEntry.CONTENT_TYPE;
            }
            case INGREDIENT_IDX:{
                return IngredientEntry.CONTENT_TYPE;
            }
            case STEPS:{
                return StepEntry.CONTENT_TYPE;
            }
            case STEP_IDX:{
                return StepEntry.CONTENT_TYPE;
            }

            default:{
                throw new UnsupportedOperationException("Unkown Uri: " + uri);
            }
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

}
