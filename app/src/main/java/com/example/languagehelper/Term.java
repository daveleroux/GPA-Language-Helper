package com.example.languagehelper;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Term {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @NonNull
    @ColumnInfo(name = "term")
    public String term;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "grammar_item")
    public boolean grammarItem;

    public String getTerm() {
        return this.term;
    }

    public String getDescription() {
        return description;
    }

    public Term(@NonNull String term) {
        this.term = term;
    }

    public Integer getId() {
        return uid;
    }

    public void setTerm(@NonNull String term) {
        this.term = term;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
