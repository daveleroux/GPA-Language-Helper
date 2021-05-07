package com.example.languagehelper;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TermDao {
//    @Query("SELECT * FROM term")
//    List<Term> getAll();

    @Query("SELECT * FROM term ORDER BY term ASC")
    LiveData<List<Term>> getSorted();

//    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
//    List<User> loadAllByIds(int[] userIds);
//
//    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
//            "last_name LIKE :last LIMIT 1")
//    User findByName(String first, String last);

    @Insert
    void insert(Term term);

    @Delete
    void delete(Term term);
}