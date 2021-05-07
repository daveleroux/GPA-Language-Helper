package com.example.languagehelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

class TermViewHolder extends RecyclerView.ViewHolder {
    private final TextView wordItemView;

    private TermViewHolder(View itemView) {
        super(itemView);
        wordItemView = itemView.findViewById(R.id.term_view);
    }

    public void bind(String text) {
        wordItemView.setText(text);
    }

    static TermViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new TermViewHolder(view);
    }
}
