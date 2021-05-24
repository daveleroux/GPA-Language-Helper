package com.example.languagehelper;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    public static final int NEW_TERM_ACTIVITY_REQUEST_CODE = 1;
    public static final int VIEW_TERM_ACTIVITY_REQUEST_CODE = 2;

    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        ActivityCompat.requestPermissions();

        setContentView(R.layout.activity_main);
        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final TermListAdapter adapter = new TermListAdapter(new TermListAdapter.TermDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mainViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(MainViewModel.class);

        mainViewModel.getAllTerms().observe(this, terms -> {
            // Update the cached copy of the words in the adapter.
            adapter.submitList(terms);
        });

        FloatingActionButton addTermButton = findViewById(R.id.add_term);
        addTermButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, EditTermActivity.class);
            startActivityForResult(intent, NEW_TERM_ACTIVITY_REQUEST_CODE);
        });


        adapter.setOnItemClickListener(new TermListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Term term) {
                // after clicking on item of recycler view
                // we are opening a new activity and passing
                // a data to our activity.
                Intent intent = new Intent(MainActivity.this, ViewTermActivity.class);
                intent.putExtra(ViewTermActivity.EXTRA_ID, term.getId());
//                intent.putExtra(NewCourseActivity.EXTRA_COURSE_NAME, term.getCourseName());
//                intent.putExtra(NewCourseActivity.EXTRA_DESCRIPTION, term.getCourseDescription());
//                intent.putExtra(NewCourseActivity.EXTRA_DURATION, term.getCourseDuration());

                // below line is to start a new activity and
                // adding a edit course constant.
                startActivityForResult(intent, VIEW_TERM_ACTIVITY_REQUEST_CODE);
            }
        });

//        TextView termView = findViewById(R.id.term_view);
//        addTermButton.setOnClickListener(view -> {
//            Intent intent = new Intent(MainActivity.this, NewTermActivity.class);
//            startActivityForResult(intent, NEW_TERM_ACTIVITY_REQUEST_CODE);
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_import:
                importData();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void importData() {
        new AlertDialog.Builder(this)
                .setTitle("Result")
                .setMessage("Imported")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        finish();
                    }
                })
                .show();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        if (requestCode == NEW_TERM_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
//            Term word = new Term(data.getStringExtra(NewTermActivity.EXTRA_REPLY));
//            mainViewModel.insert(word);
//        }
//        else {
//            Toast.makeText(
//                    getApplicationContext(),
//                    R.string.empty_not_saved,
//                    Toast.LENGTH_LONG).show();
//        }
    }
}