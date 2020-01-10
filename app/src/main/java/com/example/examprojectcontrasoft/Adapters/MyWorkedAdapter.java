package com.example.examprojectcontrasoft.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examprojectcontrasoft.Interfaces.OnNoteListener;
import com.example.examprojectcontrasoft.Models.WorkedDay;
import com.example.examprojectcontrasoft.R;

import java.util.List;


public class MyWorkedAdapter extends RecyclerView.Adapter<MyWorkedAdapter.CustomViewHolder> {

    private List<WorkedDay> dataList;
    private Context context;
    private OnNoteListener mOnNoteListener;

    public MyWorkedAdapter(List<WorkedDay> dataList, Context context, OnNoteListener onNoteListener) {
        this.dataList = dataList;
        this.context = context;
        this.mOnNoteListener = onNoteListener;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.my_worked_display_item, parent, false);
        return new CustomViewHolder(view, mOnNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.myWorkedDate.setText(context.getString(R.string.my_worked_date) + dataList.get(position).getDate());
        holder.myWorkedWorkTime.setText(context.getString(R.string.my_worked_work_time) + dataList.get(position).getWorkTime());
        holder.myWorkedTotalPause.setText(context.getString(R.string.my_worked_total_pause) + dataList.get(position).getPauseTotalTime());
        holder.myWorkedTotalWorked.setText(context.getString(R.string.my_worked_total_worked) + dataList.get(position).getTotalWorked());
        holder.myWorkedTotalTimeAtWork.setText(context.getString(R.string.my_work_total_time_at_work) + dataList.get(position).getTotalTimeAtWork());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final View mView;
        private OnNoteListener onNoteListener;

        private TextView myWorkedDate, myWorkedWorkTime, myWorkedTotalPause, myWorkedTotalWorked,
                myWorkedTotalTimeAtWork;

        CustomViewHolder(View itemView, OnNoteListener onNoteListener) {
            super(itemView);

            mView = itemView;
            myWorkedDate = mView.findViewById(R.id.myWorkedDate);
            myWorkedWorkTime = mView.findViewById(R.id.myWorkedWorkTime);
            myWorkedTotalPause = mView.findViewById(R.id.myWorkedTotalPause);
            myWorkedTotalWorked = mView.findViewById(R.id.myWorkedTotalWorked);
            myWorkedTotalTimeAtWork = mView.findViewById(R.id.myWorkedTotalTimeAtWork);

            this.onNoteListener = onNoteListener;

            mView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }
}
