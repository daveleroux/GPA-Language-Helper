package com.example.languagehelper;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TermRepository {

//    Usually, repositories implement the logic for deciding whether to fetch data from a network or use results cached in a local database.

    private TermDao termDao;
    private LiveData<List<Term>> allTerms;

    TermRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        termDao = db.termDao();
        allTerms = termDao.getSorted();
    }

    LiveData<List<Term>> getAllTerms() {
        return allTerms;
    }

    void insert(Term word) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            termDao.insert(word);
        });
    }

    void update(Term word) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            termDao.updateTerms(word);
        });
    }

    public LiveData<Term> getTerm(Integer termId) {
        return termDao.getTerm(termId);
    }
}
