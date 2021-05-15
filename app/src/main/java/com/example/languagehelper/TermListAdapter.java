package com.example.languagehelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class TermListAdapter extends ListAdapter<Term, TermListAdapter.TermViewHolder> {

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public TermListAdapter(@NonNull DiffUtil.ItemCallback<Term> diffCallback) {
        super(diffCallback);
    }

    @Override
    public TermViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new TermViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TermViewHolder holder, int position) {
        Term current = getItem(position);
        holder.bind(current.getTerm());
    }

    static class TermDiff extends DiffUtil.ItemCallback<Term> {

        @Override
        public boolean areItemsTheSame(@NonNull Term oldItem, @NonNull Term newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Term oldItem, @NonNull Term newItem) {
            return oldItem.getTerm().equals(newItem.getTerm());
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Term term);
    }
    public class TermViewHolder extends RecyclerView.ViewHolder {
        private final TextView wordItemView;

        private TermViewHolder(View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.term_view);

            // adding on click listener for each item of recycler view.
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // inside on click listener we are passing
                    // position to our item of recycler view.
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }

        public void bind(String text) {
            wordItemView.setText(text);
        }
    }
}
