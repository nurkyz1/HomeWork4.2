package com.example.ad2l2.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.preference.DialogPreference;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ad2l2.databinding.ItemLayoutBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {
    private List<HomeViewModel> list = new ArrayList<>();
    private ItemLayoutBinding binding;
    private Listen listen;

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new HomeViewHolder(ItemLayoutBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        holder.onBind(list.get(position), listen);
        if (position % 2 == 0) {
            binding.holderItem.setBackgroundColor(Color.CYAN);
        } else
            binding.holderItem.setBackgroundColor(Color.YELLOW);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public HomeAdapter(Listen listen) {
        this.listen = listen;
    }

    public void addList(HomeViewModel homeViewModel) {
        list.add(homeViewModel);
        notifyDataSetChanged();
    }

    public HomeViewModel getModelToId(int id) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == id) {
                return list.get(i);
            }
        }
        return null;
    }

    class HomeViewHolder extends RecyclerView.ViewHolder {

        private com.example.ad2l2.databinding.ItemLayoutBinding itemView;

        public HomeViewHolder(@NonNull ItemLayoutBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void onBind(HomeViewModel homeViewModel, Listen listen) {
            binding.nameItem.setText(homeViewModel.getTitle());
            binding.numberItem.setText(homeViewModel.getDescription());
            binding.date.setText(homeViewModel.getDate());

            binding.getRoot().setOnClickListener(v -> {
                listen.SetDataForForm(homeViewModel, getAdapterPosition());
            });
            binding.getRoot().setOnLongClickListener(v -> {
                AlertDialog.Builder dialog = new AlertDialog.Builder(binding.getRoot().getContext());
                String positive = "Да";
                String negative = "Нет";
                dialog.setMessage("Вы хотите удолить ?");
                dialog.setPositiveButton(positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        list.remove(getAdapterPosition());
                        notifyDataSetChanged();
                    }

                });
                dialog.setNegativeButton(negative, null);
                dialog.show();
                return true;
            });
        }
    }
}



