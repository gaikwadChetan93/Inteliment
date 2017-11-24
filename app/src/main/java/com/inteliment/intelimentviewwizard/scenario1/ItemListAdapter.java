package com.inteliment.intelimentviewwizard.scenario1;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.inteliment.intelimentviewwizard.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ViewHolder> {


    private List<String> itemList;

    private DashboardActivity activity;
    private ViewHolder viewHolder;
    ItemListAdapter(DashboardActivity activity, List<String> trips) {
        this.itemList = trips;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        //inflate your layout and pass it to view holder
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.row_item_list, viewGroup, false);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder,final int position) {

        //setting data to view holder elements
        // populate row widgets from record data
        final String item = itemList.get(position);

        viewHolder.tvItemName.setText(item);
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.updateItem(item);
            }
        });
        viewHolder.cardView.setTag(position);
    }




    @Override
    public int getItemCount() {
        return (null != itemList ? itemList.size() : 0);
    }

    /**
     * View holder to display each RecylerView item
     */
    class ViewHolder extends RecyclerView.ViewHolder {
        // get widgets from the view
        @BindView(R.id.tv_item)
        TextView tvItemName;

        @BindView(R.id.card_view)
        CardView cardView;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}