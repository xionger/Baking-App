package com.xiongxh.baking_app.data.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.xiongxh.baking_app.data.local.RecipeContract.RecipeEntry;
import com.xiongxh.baking_app.data.local.RecipeContract.IngredientEntry;
import com.xiongxh.baking_app.data.local.RecipeContract.StepEntry;

import static android.os.Build.VERSION_CODES.M;


public class RecipeDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    static final String DATABASE_NAME = "bakingapp.db";

    RecipeDbHelper(@NonNull Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_MOVIE_TABLE = "CREATE TABLE " + RecipeEntry.TABLE_NAME + " (" +
                RecipeEntry.COL_RECIPE_ID + " INTEGER PRIMARY KEY NOT NULL, " +
                RecipeEntry.COL_RECIPE_NAME + " TEXT, " +
                RecipeEntry.COL_IMAGE_URL + " TEXT, " +
                RecipeEntry.COL_RECIPE_SERVINGS + " INTEGER, " +
                " UNIQUE (" + RecipeEntry.COL_RECIPE_ID + ") ON CONFLICT REPLACE);";

        final String SQL_CREATE_INGREDIENT_TABLE = "CREATE_TABLE " + IngredientEntry.TABLE_NAME + " (" +
                IngredientEntry.COL_INGR_ID + " INTEGER PRIMARY NOT NULL, " +
                IngredientEntry.COL_INGR_NAME + " TEXT, " +
                IngredientEntry.COL_INGR_MEASURE + " TEXT, " +
                IngredientEntry.COL_INGR_QUANTITY + " REAL, " +
                RecipeEntry.COL_RECIPE_ID + " INTEGER NOT NULL, " +
                " UNIQUE (" + IngredientEntry.COL_INGR_ID + ") ON CONFLICT REPLACE);";

        final String SQL_CREATE_STEP_TABLE = "CREATE_TABLE " + StepEntry.TABLE_NAME + " (" +
                StepEntry.COL_STEP_ID + " INTEGER PRIMARY KEY NOT NULL, " +
                StepEntry.COL_STEP_SHORT_DESC + " TEXT, " +
                StepEntry.COL_STEP_DESCREPTION + " TEXT, " +
                StepEntry.COL_STEP_IMAGE_URL + " TEXT, " +
                StepEntry.COL_STEP_VIDEO_URL + " TEXT, " +
                RecipeEntry.COL_RECIPE_ID + " INTEGER NOT NULL, " +
                " UNIQUE (" + StepEntry.COL_STEP_ID + ") ON CONFLICT REPLACE);";

        sqLiteDatabase.execSQL(SQL_CREATE_MOVIE_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_INGREDIENT_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_STEP_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + RecipeEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + IngredientEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + StepEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onDowngrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        onUpgrade(sqLiteDatabase, oldVersion, newVersion);
    }
}
