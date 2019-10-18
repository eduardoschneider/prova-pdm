package br.edu.iftm.pdm.prova.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import br.edu.iftm.pdm.prova.R;
import br.edu.iftm.pdm.prova.model.Report;

public class ReportViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener, View.OnLongClickListener {

    private TextView txtDescricao;
    private TextView txtNatureza;
    private TextView txtData;
    private Report report;
    private ReportAdapter.OnReportClickListener listener;

    public ReportViewHolder(ReportAdapter.OnReportClickListener listener,
                            @NonNull View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
        this.listener = listener;
        initialize();
    }

    private void initialize(){
        this.txtDescricao = itemView.findViewById(R.id.txtDescricao);
        this.txtNatureza = itemView.findViewById(R.id.txtNatureza);
        this.txtData = itemView.findViewById(R.id.txtData);
    }

    public void bind(Report report) {
        this.txtDescricao.setText(report.getDescricao());
        this.txtNatureza.setText(report.getNatureza());
        this.txtData.setText(report.getData());
        this.report = report;
        this.itemView.setSelected(this.report.isSelected());
    }

    @Override
    public void onClick(View v) {
        this.listener.onReportClick(this.report);
    }

    private void selectItem(){
        this.report.setSelected(!this.report.isSelected());
        this.itemView.setSelected(!this.itemView.isSelected());
        this.listener.onLongReportClick(this.report);
    }

    @Override
    public boolean onLongClick(View v) {
        selectItem();
        return true;
    }
}
