package com.xiongxh.baking_app.recipes;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.xiongxh.baking_app.BakingApp;
import com.xiongxh.baking_app.R;
import com.xiongxh.baking_app.data.bean.Recipe;
import com.xiongxh.baking_app.utils.ActivityUtils;
import com.xiongxh.baking_app.utils.FragmentUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipesActivity extends AppCompatActivity {
        //implements RecipesContract.View {
        //, NavigationView.OnNavigationItemSelectedListener{

    private static final String LOG_TAG = RecipesActivity.class.getSimpleName();
    private static final String TAG_FRAGMENT_RECIPES = "TAG_FRAGMENT_RECIPES";

    private RecipesContract.Presenter mRecipesPresenter;
    private RecipesContract.View mRecipesFragment;

    //@BindView(R.id.drawer_layout)
    //DrawerLayout mDrawerLayout;

    //@BindView(R.id.toolbar)
    //Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_recipe);

        ButterKnife.bind(this);

        /*
        mRecipesFragment = (RecipesContract.View) getSupportFragmentManager().findFragmentById(R.id.recipes_fragment_container);

        if (mRecipesFragment == null){
            mRecipesFragment = RecipesFragment.newInstance();
            FragmentUtils.addFragmentTo(getSupportFragmentManager(), (Fragment) mRecipesFragment, R.id.recipes_fragment_container);
        }

        mRecipesPresenter = BakingApp.get().presenterProvider.recipesProvider();
        */

        /*
        setSupportActionBar(mToolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        );

        mDrawerLayout.addDrawerListener(toggle);
*/

        //if (savedInstanceState == null){
            mRecipesFragment = RecipesFragment.newInstance();

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.recipes_fragment_container, (Fragment)mRecipesFragment, TAG_FRAGMENT_RECIPES)
                    .commit();
        //}

        //if (mRecipesFragment == null){
        //    mRecipesFragment = (RecipesContract.View) getSupportFragmentManager()
        //            .findFragmentByTag(TAG_FRAGMENT_RECIPES);
        //}

        //mRecipesPresenter = BakingApp.get().presenterProvider.recipesProvider();

        //mRecipesFragment.setPresenter(mRecipesPresenter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id =  item.getItemId();

        if (id == R.id.action_refresh){
            mRecipesPresenter.loadRecipes();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*
 @Override
 public void onBackPressed(){
     if (mDrawerLayout.isDrawerOpen(GravityCompat.START)){
         mDrawerLayout.closeDrawer(GravityCompat.START);
     } else {
         super.onBackPressed();
     }
 }


 @Override
 public boolean onNavigationItemSelected(@NonNull MenuItem item) {
     int id = item.getItemId();

     switch (id){
         case R.id.nav_about: {
             Toast.makeText(this, "Make dialog", Toast.LENGTH_SHORT).show();
             break;
         }
         case R.id.nav_sourcecode: {
             Intent gglIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"));
             startActivity(gglIntent);
             break;
         }
     }

     DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
     drawerLayout.closeDrawer(GravityCompat.START);
     return true;
 }
*/
    /*
    @Override
    public void setPresenter(RecipesContract.Presenter presenter) {

    }

    @Override
    public void showRecipeList(List<Recipe> recipeList) {

    }

    @Override
    public void showLoadingIndicator(boolean show) {

    }

    @Override
    public void showLoadingRecipesErrorMessage(String errorMessage) {

    }

    @Override
    public void showLoadingRecipesCompletedMessage() {

    }

    @Override
    public void showRecipeDetails(long recipeId) {

    }
    */
}
