package com.suli.suliapp.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.suli.suliapp.data.models.MiningUnit;

import java.util.List;
import com.suli.suliapp.R;

public class MiningUnitAdapter extends RecyclerView.Adapter<MiningUnitAdapter.ViewHolder> {

    private List<MiningUnit> items;
    private MiningUnitSelectedListener listener;

    public MiningUnitAdapter(List<MiningUnit> items, MiningUnitSelectedListener listener) {
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_mining_unit, parent, false);
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

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName;
        private RecyclerView rvCustodyChain;
        private ImageButton btnAdd;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            rvCustodyChain = itemView.findViewById(R.id.rvCustodyChain);
            btnAdd = itemView.findViewById(R.id.btnAdd);
        }

        public void bind(final MiningUnit miningUnit) {
            tvName.setText(miningUnit.name);
            rvCustodyChain.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
            rvCustodyChain.setAdapter(new CustodyChainAdapter(miningUnit.custodyChain));
            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onSelected(v, miningUnit);
                }
            });
        }
    }

}