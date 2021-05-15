package com.example.languagehelper;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private TermRepository termRepository;

    private final LiveData<List<Term>> allTerms;

    public MainViewModel(Application application) {
        super(application);
        termRepository = new TermRepository(application);
        allTerms = termRepository.getAllTerms();
    }

    LiveData<List<Term>> getAllTerms() {
        return allTerms;
    }

    public void insert(Term term) {
        termRepository.insert(term);
    }
}
