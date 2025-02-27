package com.s22010388.gardenguardianapp;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;

public class TaskAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Task> tasks;
    private LayoutInflater inflater;

    public TaskAdapter(Context context, ArrayList<Task> tasks) {
        this.context = context;
        this.tasks = tasks;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public Object getItem(int position) {
        return tasks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.task_item, parent, false);
            holder = new ViewHolder();
            holder.checkBoxTask = convertView.findViewById(R.id.checkBoxTask);
            holder.textViewTask = convertView.findViewById(R.id.textViewTask);
            holder.buttonDelete = convertView.findViewById(R.id.buttonDelete);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final Task task = tasks.get(position);
        holder.textViewTask.setText(task.getTask());
        holder.checkBoxTask.setOnCheckedChangeListener(null); // Remove listener before setting the state
        holder.checkBoxTask.setChecked(task.isCompleted());

        holder.checkBoxTask.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isPressed()) { // Check if this event is triggered by user interaction
                    task.setCompleted(isChecked);
                    if (isChecked) {
                        // Show alert dialog
                        new AlertDialog.Builder(context)
                                .setTitle("Congratulations")
                                .setMessage("You have completed the task!")
                                .setPositiveButton("OK", null)
                                .show();
                    }
                }
            }
        });

        holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tasks.remove(position);
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    static class ViewHolder {
        CheckBox checkBoxTask;
        TextView textViewTask;
        Button buttonDelete;
    }
}