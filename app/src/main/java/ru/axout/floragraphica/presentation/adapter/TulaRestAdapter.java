package ru.axout.floragraphica.presentation.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ru.axout.floragraphica.R;
import ru.axout.floragraphica.data.RoomDB;
import ru.axout.floragraphica.data.TulaData;

import java.util.List;

public class TulaRestAdapter extends RecyclerView.Adapter<TulaRestAdapter.ViewHolder> {
    // Инициализируем переменные
    private List<TulaData> dataList;
    private Activity context;

    // Конструктор
    public TulaRestAdapter(Activity context, List<TulaData> dataList) {
        this.context = context;
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Initialize view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_tula, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        // Initialize main data
        final TulaData data = dataList.get(position);

        // Вывод данных пользователю
//        holder.tvSort.setText(Integer.toString(data.getSort_ID()));
//        holder.tvQuantity.setText(Integer.toString(data.getPackageNumber()));
        holder.tvSort.setText("Sort 1");
        holder.tvQuantity.setText("100500");
    }

    @Override
    public int getItemCount() {
//        return dataList.size();
        return 3;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvSort, tvQuantity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSort = itemView.findViewById(R.id.tv_sort);
            tvQuantity = itemView.findViewById(R.id.tv_quantity);
        }
    }
}
