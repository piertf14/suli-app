package com.suli.suliapp.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.suli.suliapp.R;
import com.suli.suliapp.data.models.CustodyChain;

import java.util.List;

public class CustodyChainAdapter extends RecyclerView.Adapter<CustodyChainAdapter.ViewHolder>{

    private List<CustodyChain> items;

    public CustodyChainAdapter(List<CustodyChain> items){
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_custody_chain, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvName;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
        }

        public void bind(CustodyChain custodyChain) {
            tvName.setText(custodyChain.agent.name);
        }
    }

}