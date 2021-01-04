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

public class TulaAdapter extends RecyclerView.Adapter<TulaAdapter.ViewHolder> {
    // Инициализируем переменные
    private List<TulaData> dataList;
    private Activity context;
    private RoomDB database;

    // Конструктор
    public TulaAdapter(Activity context, List<TulaData> dataList) {
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
        // Инициализация БД
        database = RoomDB.getInstance(context);

        // Вывод данных пользователю
        holder.textViewID.setText(Integer.toString(data.getTulip_ID()));
        holder.textViewSort.setText(data.getSort());
        holder.textViewColor.setText(data.getColor());
        holder.textViewType.setText(data.getType());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewID, textViewSort, textViewColor, textViewType;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewID = itemView.findViewById(R.id.text_view_id);
            textViewSort = itemView.findViewById(R.id.text_view_sort);
            textViewColor = itemView.findViewById(R.id.text_view_color);
            textViewType = itemView.findViewById(R.id.text_view_type);
        }
    }
}
