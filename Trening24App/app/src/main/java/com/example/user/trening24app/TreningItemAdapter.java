package com.example.user.trening24app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by User on 2017. 09. 12..
 */

public class TreningItemAdapter extends ArrayAdapter<TreningItem> {

    public ArrayList<TreningItem> MainList;

    public ArrayList<TreningItem> TreningListTemp;

    public TreningItemAdapter.SubjectDataFilter subjectDataFilter ;

    public TreningItemAdapter(Context context, int id, ArrayList<TreningItem> treningArrayList) {

        super(context, id, treningArrayList);

        this.TreningListTemp = new ArrayList<>();

        this.TreningListTemp.addAll(treningArrayList);

        this.MainList = new ArrayList<>();

        this.MainList.addAll(treningArrayList);
    }

    @Override
    public Filter getFilter() {

        if (subjectDataFilter == null){

            subjectDataFilter  = new TreningItemAdapter.SubjectDataFilter();
        }
        return subjectDataFilter;
    }


    public class ViewHolder {

        TextView tvName;
        TextView tvTrainer;
        TextView tvTrainingDate;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TreningItemAdapter.ViewHolder holder = null;

        if (convertView == null) {

            LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = vi.inflate(R.layout.trening_item, null);

            holder = new TreningItemAdapter.ViewHolder();

            holder.tvName = (TextView) convertView.findViewById(R.id.tvName);

            holder.tvTrainer = (TextView) convertView.findViewById(R.id.tvTrainer);

            holder.tvTrainingDate = (TextView) convertView.findViewById(R.id.tvTrainingDate);

            convertView.setTag(holder);

        } else {
            holder = (TreningItemAdapter.ViewHolder) convertView.getTag();
        }

        TreningItem trening = TreningListTemp.get(position);

        holder.tvName.setText(trening.getName());

        holder.tvTrainer.setText(trening.getTrainer());

        holder.tvTrainingDate.setText(trening.getTraining_date());

        return convertView;

    }

    private class SubjectDataFilter extends Filter
    {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            charSequence = charSequence.toString().toLowerCase();

            FilterResults filterResults = new FilterResults();

            if(charSequence != null && charSequence.toString().length() > 0)
            {
                ArrayList<TreningItem> arrayList1 = new ArrayList<TreningItem>();

                for(int i = 0, l = MainList.size(); i < l; i++)
                {
                    TreningItem subject = MainList.get(i);

                    if(subject.toString().toLowerCase().contains(charSequence))

                        arrayList1.add(subject);
                }
                filterResults.count = arrayList1.size();

                filterResults.values = arrayList1;
            }
            else
            {
                synchronized(this)
                {
                    filterResults.values = MainList;

                    filterResults.count = MainList.size();
                }
            }
            return filterResults;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

            TreningListTemp = (ArrayList<TreningItem>)filterResults.values;

            notifyDataSetChanged();

            clear();

            for(int i = 0, l = TreningListTemp.size(); i < l; i++)
                add(TreningListTemp.get(i));

            notifyDataSetInvalidated();
        }
    }
}
