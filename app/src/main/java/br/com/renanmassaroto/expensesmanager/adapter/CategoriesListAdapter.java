package br.com.renanmassaroto.expensesmanager.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.renanmassaroto.expensesmanager.R;
import br.com.renanmassaroto.expensesmanager.models.TransactionCategory;

/**
 * Created by renan on 12/11/15.
 */
public class CategoriesListAdapter extends RecyclerView.Adapter<CategoriesListAdapter.ViewHolder> {

    public static final String LOG_TAG = "CategoriesListAdapter";

    private Context context;
    private ArrayList<TransactionCategory> transactionCategoriesArrayList;

    private View.OnClickListener iconClickListener;

    public CategoriesListAdapter(Context context, View.OnClickListener iconClickListener) {
        this.context = context;
        this.iconClickListener = iconClickListener;
        this.transactionCategoriesArrayList = new ArrayList<>();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView mIconTextView;
        TextView mTitleTextView;

        public ViewHolder(View view, View.OnClickListener iconClickListener) {
            super(view);

            mIconTextView = (TextView) view.findViewById(R.id.text_1);
            mTitleTextView = (TextView) view.findViewById(R.id.text_2);

            if (iconClickListener != null)
                mIconTextView.setOnClickListener(iconClickListener);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_2, parent, false);
        ViewHolder viewHolder = new ViewHolder(view, iconClickListener);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TransactionCategory transactionCategory = transactionCategoriesArrayList.get(position);

        Drawable iconBackgroundDrawable = holder.mIconTextView.getBackground();
        iconBackgroundDrawable.setColorFilter(Color.parseColor(transactionCategory.getColorHex()),
                PorterDuff.Mode.MULTIPLY);

        holder.mIconTextView.setText("" + transactionCategory.getName().charAt(0));
        holder.mTitleTextView.setText(transactionCategory.getName());

        holder.mIconTextView.setTag(position);
    }

    @Override
    public int getItemCount() {

        return transactionCategoriesArrayList.size();
    }

    public void addTransactionCategories(ArrayList<TransactionCategory> transactionCategoriesArrayList) {
        for (TransactionCategory transactionCategory : transactionCategoriesArrayList) {
            Log.d("CategoriesListAdapter", "Added transaction category " + transactionCategory.getName() +
                    " to adapter.");
            this.transactionCategoriesArrayList.add(transactionCategory);

            notifyItemInserted(this.transactionCategoriesArrayList.size() - 1);
        }
    }

    public void setTransactionCategoriesArrayList(ArrayList<TransactionCategory> transactionCategoriesArrayList) {
        this.transactionCategoriesArrayList = transactionCategoriesArrayList;
    }

    public ArrayList<TransactionCategory> getTransactionCategoriesArrayList() {
        return transactionCategoriesArrayList;
    }
}
