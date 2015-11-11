package br.com.renanmassaroto.expensesmanager.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.renanmassaroto.expensesmanager.R;
import br.com.renanmassaroto.expensesmanager.models.Account;

/**
 * Created by renancardosomassaroto on 11/9/15.
 */
public class NavigationDrawerAdapter extends RecyclerView.Adapter<NavigationDrawerAdapter.BaseViewHolder> {

    public static final String LOG_TAG = "NavigationDrawerAdapter";

    private static final int VIEW_TYPE_HEADER = 0;
    private static final int VIEW_TYPE_LIST_ITEM = 1;
    private static final int VIEW_TYPE_DIVIDER = 2;

    private Context context;

    private ArrayList<Account> accountsArrayList;

    public static class BaseViewHolder extends RecyclerView.ViewHolder {
        public BaseViewHolder(View view) {
            super(view);
        }
    }

    public static class HeaderViewHolder extends BaseViewHolder {

        public HeaderViewHolder(View view) {
            super(view);
        }
    }

    public static class ListItemViewHolder extends BaseViewHolder {

        public ImageView mImageView;
        public TextView mTextView;

        public ListItemViewHolder(View view) {
            super(view);

            this.mImageView = (ImageView) view.findViewById(R.id.image_1);
            this.mTextView = (TextView) view.findViewById(R.id.text_1);
        }
    }

    public static class DividerHolder extends BaseViewHolder {

        public DividerHolder(View view) {
            super(view);
        }
    }

    public NavigationDrawerAdapter(Context context) {
        this.context = context;
        this.accountsArrayList = new ArrayList<>();
    }

    public NavigationDrawerAdapter(Context context, ArrayList<Account> accountsArrayList) {
        this.context = context;
        this.accountsArrayList = accountsArrayList;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        BaseViewHolder viewHolder;

        switch (viewType) {
            case VIEW_TYPE_HEADER:
                view = LayoutInflater.from(context).inflate(R.layout.nav_header_main, parent, false);

                viewHolder = new HeaderViewHolder(view);

                return viewHolder;
            case VIEW_TYPE_LIST_ITEM:
                view = LayoutInflater.from(context).inflate(R.layout.list_item_1, parent, false);

                viewHolder = new ListItemViewHolder(view);

                return viewHolder;
            case VIEW_TYPE_DIVIDER:
                view = LayoutInflater.from(context).inflate(R.layout.divider_1, parent, false);

                viewHolder = new DividerHolder(view);

                return viewHolder;
            default:
                view = LayoutInflater.from(context).inflate(R.layout.list_item_1, parent, false);

                viewHolder = new BaseViewHolder(view);
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
//        Log.d(LOG_TAG, "Position " + Integer.toString(position));

        if (accountsArrayList.size() > 0) {
            if (position == 0) {

            } else if (position == 1) {
                ListItemViewHolder listItemViewHolder =  (ListItemViewHolder) holder;

                listItemViewHolder.mTextView.setText(context.getString(R.string.overview_activity_name));
            } else if (position == 2) {
                ListItemViewHolder listItemViewHolder =  (ListItemViewHolder) holder;

                listItemViewHolder.mTextView.setText(context.getString(R.string.history_activity_name));
            } else if (position == 3) {
                ListItemViewHolder listItemViewHolder =  (ListItemViewHolder) holder;

                listItemViewHolder.mTextView.setText(context.getString(R.string.distribution_activity_name));
            } else if (position == 4) {
                ListItemViewHolder listItemViewHolder =  (ListItemViewHolder) holder;

                listItemViewHolder.mTextView.setText(context.getString(R.string.wishlist_activity_name));
            } else if (position == 5) {
                // Divider
            } else if (position == 7 + accountsArrayList.size() - 1) {
                // Divider

            } else if (position == 8 + accountsArrayList.size() - 1) {
                ListItemViewHolder listItemViewHolder =  (ListItemViewHolder) holder;

                listItemViewHolder.mTextView.setText(context.getString(R.string.account_management_activity_name));
            } else if (position == 9 + accountsArrayList.size() - 1) {
                ListItemViewHolder listItemViewHolder =  (ListItemViewHolder) holder;

                listItemViewHolder.mTextView.setText(context.getString(R.string.categories_management_activity_name));
            } else if (position == 10 + accountsArrayList.size() - 1) {
                ListItemViewHolder listItemViewHolder =  (ListItemViewHolder) holder;

                listItemViewHolder.mTextView.setText(context.getString(R.string.settings_activity_name));
            } else {
                //Accounts

                ListItemViewHolder listItemViewHolder =  (ListItemViewHolder) holder;

                listItemViewHolder.mTextView.setText(accountsArrayList.get(position - 6).getName());
            }
        } else {
            if (position == 0) {

            } else if (position == 1) {
                ListItemViewHolder listItemViewHolder =  (ListItemViewHolder) holder;

                listItemViewHolder.mTextView.setText(context.getString(R.string.overview_activity_name));
            } else if (position == 2) {
                ListItemViewHolder listItemViewHolder =  (ListItemViewHolder) holder;

                listItemViewHolder.mTextView.setText(context.getString(R.string.history_activity_name));
            } else if (position == 3) {
                ListItemViewHolder listItemViewHolder =  (ListItemViewHolder) holder;

                listItemViewHolder.mTextView.setText(context.getString(R.string.distribution_activity_name));
            } else if (position == 4) {
                ListItemViewHolder listItemViewHolder =  (ListItemViewHolder) holder;

                listItemViewHolder.mTextView.setText(context.getString(R.string.wishlist_activity_name));
            } else if (position == 5) {
                // Divider
            } else if (position == 6) {
                ListItemViewHolder listItemViewHolder =  (ListItemViewHolder) holder;

                listItemViewHolder.mTextView.setText(context.getString(R.string.account_management_activity_name));
            } else if (position == 7) {
                ListItemViewHolder listItemViewHolder =  (ListItemViewHolder) holder;

                listItemViewHolder.mTextView.setText(context.getString(R.string.categories_management_activity_name));
            } else if (position == 8) {
                ListItemViewHolder listItemViewHolder =  (ListItemViewHolder) holder;

                listItemViewHolder.mTextView.setText(context.getString(R.string.settings_activity_name));
            }

        }
    }

    @Override
    public int getItemCount() {
        if (accountsArrayList.size() > 0){
            return 10 + accountsArrayList.size();
        } else {
            return 9;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return VIEW_TYPE_HEADER;
        } else if (position > 0 && position < 5) {
            return VIEW_TYPE_LIST_ITEM;
        } else if (position == 5) {
            return VIEW_TYPE_DIVIDER;
        } else if (position > 5 && position < (5 + accountsArrayList.size())) {
            return VIEW_TYPE_LIST_ITEM;
        } else if (position == (5 + accountsArrayList.size() + 1) && accountsArrayList.size() > 0) {
            return VIEW_TYPE_DIVIDER;
        } else {
            return VIEW_TYPE_LIST_ITEM;
        }
    }
}