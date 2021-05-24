package com.example.languagehelper;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;


public class ViewTermViewModel extends AndroidViewModel {

    private TermRepository termRepository;
    private LiveData<Term> term;

    public ViewTermViewModel(Application application, Integer termId) {
        super(application);
        termRepository = new TermRepository(application);
        if (termId != null)
            term = termRepository.getTerm(termId);
    }

    public LiveData<Term> getTerm() {
        return term;
    }

    public void update() {
        termRepository.update(term.getValue());
    }

    public void insert(Term word) {
        termRepository.insert(word);
    }

    public static class ViewTermViewModelFactory implements ViewModelProvider.Factory {
        private Application mApplication;
        private Integer termId;

        public ViewTermViewModelFactory(Application application, Integer termId) {
            mApplication = application;
            this.termId = termId;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            return (T) new ViewTermViewModel(mApplication, termId);
        }
    }
}
