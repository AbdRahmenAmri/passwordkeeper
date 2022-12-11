package com.fsb.pwdkeeper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fsb.pwdkeeper.model.Password;

import java.util.List;

public class PasswordRecycleViewAdapter extends RecyclerView.Adapter<PasswordRecycleViewAdapter.ViewHolder> {
    List<Password> passwordList;
    Context context;
    private final RecycleViewInterface recycleViewInterface;

    public PasswordRecycleViewAdapter(List<Password> passwordList, Context context, RecycleViewInterface recycleViewInterface) {
        this.passwordList = passwordList;
        this.context = context;
        this.recycleViewInterface = recycleViewInterface;
    }

    @NonNull
    @Override
    public PasswordRecycleViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view, parent, false);
        return new PasswordRecycleViewAdapter.ViewHolder(view,recycleViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull PasswordRecycleViewAdapter.ViewHolder holder, int position) {
        holder.password_name.setText(passwordList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return passwordList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView password_name;
        public ViewHolder(@NonNull View itemView,RecycleViewInterface recycleViewInterface) {
            super(itemView);
            password_name = itemView.findViewById(R.id.password_name);
            itemView.setOnClickListener(v -> {
                if(recycleViewInterface != null){
                    if(getAdapterPosition() != RecyclerView.NO_POSITION){
                        recycleViewInterface.onItemClick(getAdapterPosition());
                    }
                }
            });
        }
    }
}
