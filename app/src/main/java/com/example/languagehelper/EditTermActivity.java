package com.example.languagehelper;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import static com.example.languagehelper.ViewTermActivity.EXTRA_ID;

public class EditTermActivity extends AppCompatActivity {

    private Integer termId;
    private ViewTermViewModel viewTermViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_term);

        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);


        termId = null;

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            if (bundle.containsKey(EXTRA_ID))
                termId = bundle.getInt(EXTRA_ID);
        }

        if(termId != null)
        {
            getSupportActionBar().setTitle("Edit Term");
        }
        else
        {
            getSupportActionBar().setTitle("Create Term");
        }

        viewTermViewModel = new ViewModelProvider(this, new ViewTermViewModel.ViewTermViewModelFactory(getApplication(), termId)).get(ViewTermViewModel.class);

        TextView textView = findViewById(R.id.edit_term);
        TextView textViewDescription = findViewById(R.id.edit_term_description);

        if (viewTermViewModel.getTerm() != null) {
            viewTermViewModel.getTerm().observe(this, new Observer<Term>() {
                @Override
                public void onChanged(Term term) {
                    textView.setText(viewTermViewModel.getTerm().getValue().getTerm());
                    textViewDescription.setText(viewTermViewModel.getTerm().getValue().getDescription());
                }
            });
        }

        final Button button = findViewById(R.id.save_term);
        button.setOnClickListener(view -> {

            String textViewTermString = textView.getText().toString();
            String textViewDescriptionString = textViewDescription.getText().toString();

            if (viewTermViewModel.getTerm() != null) {
                viewTermViewModel.getTerm().getValue().setTerm(textViewTermString);
                viewTermViewModel.getTerm().getValue().setDescription(textViewDescriptionString);
                viewTermViewModel.update();
            } else {
                Term word = new Term(textViewTermString);
                word.setDescription(textViewDescriptionString);
                viewTermViewModel.insert(word);

            }

            Intent replyIntent = new Intent();
            setResult(RESULT_OK, replyIntent);
            finish();
        });


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