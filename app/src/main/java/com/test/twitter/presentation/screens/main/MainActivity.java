package com.test.twitter.presentation.screens.main;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

import com.test.twitter.R;
import com.test.twitter.presentation.core.base.BaseActivity;
import com.test.twitter.presentation.screens.main.favorites.FavoritesFragment;
import com.test.twitter.presentation.screens.main.nearby.NearbyFragment;
import com.test.twitter.presentation.screens.main.search.SearchFragment;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainContract.Presenter> implements MainContract.View, NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.subscribe();
    }

    @Override
    protected void initUI() {
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_search:
                navigator.switchFragment(SearchFragment.newInstance());
                drawerLayout.closeDrawer(navigationView);
                return true;
            case R.id.nav_nearby:
                navigator.switchFragment(NearbyFragment.newInstance());
                drawerLayout.closeDrawer(navigationView);
                return true;
            case R.id.nav_favorites:
                navigator.switchFragment(FavoritesFragment.newInstance());
                drawerLayout.closeDrawer(navigationView);
                return true;
        }
        return false;
    }

    @Override
    public void loadSearchFragment() {
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_search);
        navigator.switchFragment(SearchFragment.newInstance());
    }
}
