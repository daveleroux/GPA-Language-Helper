package com.example.languagehelper;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.view.View;
import android.widget.TextView;

public class ViewTermActivity extends AppCompatActivity {
    public static final String EXTRA_ID = "EXTRA_ID";

    private ViewTermViewModel viewTermViewModel;
//    private TextInputEditText et_title,et_content;
//    private NoteDatabase noteDatabase;
//    private Note note;
//    private boolean update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_term);

//        Toolbar myToolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(myToolbar);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Integer termId = bundle.getInt(EXTRA_ID);
            TextView textView = findViewById(R.id.view_term);
            textView.setText(termId.toString());
        }


        viewTermViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(ViewTermViewModel.class);


//        et_title = findViewById(R.id.et_title);
//        et_content = findViewById(R.id.et_content);
//        noteDatabase = NoteDatabase.getInstance(AddNoteActivity.this);
//        Button button = findViewById(R.id.but_save);
//        if ( (note = (Note) getIntent().getSerializableExtra("note"))!=null ){
//            getSupportActionBar().setTitle("Update Note");
//            update = true;
//            button.setText("Update");
//            et_title.setText(note.getTitle());
//            et_content.setText(note.getContent());
//        }
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                note.setContent(et_content.getText().toString());
//                note.setTitle(et_title.getText().toString());
//                noteDatabase.getNoteDao().updateNote(note);
//            }
//        });
    }
}