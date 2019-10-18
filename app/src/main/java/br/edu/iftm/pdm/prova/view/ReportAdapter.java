package br.edu.iftm.pdm.prova.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Iterator;

import br.edu.iftm.pdm.prova.R;
import br.edu.iftm.pdm.prova.model.Report;

public class ReportAdapter extends RecyclerView.Adapter<ReportViewHolder> {

    private ArrayList<Report> reports;
    private OnContactClickListener listener;

    public interface OnContactClickListener {
        void onContactClick(Report report);
        void onLongContactClick(Report report);
    }

    public ReportAdapter(OnContactClickListener listener, ArrayList<Report> reports) {
        this.listener = listener;
        this.reports = reports;
    }

    @NonNull
    @Override
    public ReportViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.view_holder_report,
                                            viewGroup, false);
        ReportViewHolder reportViewHolder = new ReportViewHolder(this.listener, view);
        return reportViewHolder;
    }

    /*
        O onBindViewHolder é aonde acontece a mágica da reciclagem.
        O papel desse método é associar a ViewHolder ao item resgatado
        na posição i da lista.
     */
    @Override
    public void onBindViewHolder(@NonNull ReportViewHolder reportViewHolder, int i) {
        reportViewHolder.bind(this.reports.get(i));
    }

    @Override
    public int getItemCount() {
        return this.reports.size();
    }

    // --> implementacao de utilidades

    public void removeContact(Report report) {
        int position = this.reports.indexOf(report);
        this.reports.remove(position);
        this.notifyItemRemoved(position);
    }

    public void removeSelectedContacts() {
        Iterator<Report> iterator = this.reports.iterator();
        int position = 0;
        while(iterator.hasNext()) {
            Report report = iterator.next();
            if(report.isSelected()) {
                iterator.remove();
                notifyItemRemoved(position--);
            }
            position++;
        }
    }
}
