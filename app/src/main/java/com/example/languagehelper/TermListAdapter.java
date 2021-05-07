package com.example.languagehelper;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

public class TermListAdapter extends ListAdapter<Term, TermViewHolder> {

    public TermListAdapter(@NonNull DiffUtil.ItemCallback<Term> diffCallback) {
        super(diffCallback);
    }

    @Override
    public TermViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return TermViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(TermViewHolder holder, int position) {
        Term current = getItem(position);
        holder.bind(current.getTerm());
    }

    static class TermDiff extends DiffUtil.ItemCallback<Term> {

        @Override
        public boolean areItemsTheSame(@NonNull Term oldItem, @NonNull Term newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Term oldItem, @NonNull Term newItem) {
            return oldItem.getTerm().equals(newItem.getTerm());
        }
    }
}
