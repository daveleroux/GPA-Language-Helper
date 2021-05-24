package com.example.languagehelper;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class ViewTermActivity extends AppCompatActivity {
    public static final String EXTRA_ID = "EXTRA_ID";

    private ViewTermViewModel viewTermViewModel;
    private Integer termId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_term);

        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        termId = 0;

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            termId = bundle.getInt(EXTRA_ID);
        }

        viewTermViewModel = new ViewModelProvider(this, new ViewTermViewModel.ViewTermViewModelFactory(getApplication(), termId)).get(ViewTermViewModel.class);

        TextView textView = findViewById(R.id.view_term);
//        textView.setTypeface(textView.getTypeface(), Typeface.BOLD);

        TextView textViewDescription = findViewById(R.id.view_term_description);

        viewTermViewModel.getTerm().observe(this, new Observer<Term>() {
            @Override
            public void onChanged(Term term) {
                textView.setText(viewTermViewModel.getTerm().getValue().getTerm());
                textViewDescription.setText(viewTermViewModel.getTerm().getValue().getDescription());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.view_term_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit_term:
                Intent intent = new Intent(ViewTermActivity.this, EditTermActivity.class);
                intent.putExtra(ViewTermActivity.EXTRA_ID, termId);
                startActivityForResult(intent, 0);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}