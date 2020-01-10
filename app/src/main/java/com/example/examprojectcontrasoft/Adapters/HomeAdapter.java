package com.example.examprojectcontrasoft.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examprojectcontrasoft.Interfaces.OnNoteListener;
import com.example.examprojectcontrasoft.Models.Function;
import com.example.examprojectcontrasoft.R;

import java.util.List;


public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.CustomViewHolder> {

    private List<Function> dataList;
    private Context context;
    private OnNoteListener mOnNoteListener;

    public HomeAdapter(List<Function> dataList, Context context, OnNoteListener onNoteListener) {
        this.dataList = dataList;
        this.context = context;
        this.mOnNoteListener = onNoteListener;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.home_display_item, parent, false);
        return new CustomViewHolder(view, mOnNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
            holder.functionName.setText(dataList.get(position).getFunctionName());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final View mView;
        private OnNoteListener onNoteListener;

        private TextView functionName;

        CustomViewHolder(View itemView, OnNoteListener onNoteListener) {
            super(itemView);

            mView = itemView;
            functionName = mView.findViewById(R.id.homeFunctionTitle);
            this.onNoteListener = onNoteListener;

            mView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }
}
