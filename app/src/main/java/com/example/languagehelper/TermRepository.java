package com.example.languagehelper;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TermRepository {

//    Usually, repositories implement the logic for deciding whether to fetch data from a network or use results cached in a local database.

    private TermDao termDao;
    private LiveData<List<Term>> allTerms;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    TermRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        termDao = db.termDao();
        allTerms = termDao.getSorted();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<Term>> getAllTerms() {
        return allTerms;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(Term word) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            termDao.insert(word);
        });
    }
}
