package com.suli.suliapp.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.suli.suliapp.data.models.MiningUnit;
import com.suli.suliapp.data.models.ProjectResponse;

import java.util.List;
import com.suli.suliapp.R;

class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ViewHolder> {

    private List<ProjectResponse> items;

    public ProjectAdapter(List<ProjectResponse> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_project, parent, false);
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
        private RecyclerView rvMiningUnit;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            rvMiningUnit = itemView.findViewById(R.id.rvMiningUnit);
        }

        public void bind(final ProjectResponse project) {
            tvName.setText(project.observations);
            rvMiningUnit.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
            rvMiningUnit.setAdapter(new MiningUnitAdapter(project.miningUnits, new MiningUnitSelectedListener() {
                @Override
                public void onSelected(View view, MiningUnit miningUnit) {
                    Intent intent = new Intent(itemView.getContext(), AddCustodyChainActivity.class);
                    intent.putExtra(AddCustodyChainActivity.PROYECTO, project);
                    intent.putExtra(AddCustodyChainActivity.UNITY, miningUnit);
                    itemView.getContext().startActivity(intent);
                }
            }));
        }
    }

}