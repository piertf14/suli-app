package com.suli.suliapp.ui;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.suli.suliapp.R;
import com.suli.suliapp.data.models.ProjectResponse;
import com.suli.suliapp.ui.callbacks.GetProjectsCallback;
import com.suli.suliapp.ui.utils.Utils;

import java.util.List;

public class ProjectsActivity extends AppCompatActivity {

    private RecyclerView rvProjects;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Utils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);
        rvProjects = findViewById(R.id.rvProjects);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        setupToolbar((Toolbar) findViewById(R.id.toolbar));
        utils = new Utils(this);
        setupRecyclerView();
        getProjects();

        swipeRefreshLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getProjects();
            }
        });
    }

    private void setupToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void setupRecyclerView() {
        rvProjects.setLayoutManager(new LinearLayoutManager(this));
    }

    public void getProjects() {
        utils.getProjects(new GetProjectsCallback() {
            @Override
            public void onSuccess(List<ProjectResponse> projects) {
                rvProjects.setAdapter(new ProjectAdapter(projects));
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(String error) {
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(ProjectsActivity.this, error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_projects, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                utils.logout();
                startActivity(new Intent(this, LoginActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
