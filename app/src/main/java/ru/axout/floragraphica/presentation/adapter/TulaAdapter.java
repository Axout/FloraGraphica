package ru.axout.floragraphica.presentation.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
                .inflate(R.layout.list_row_add_tula, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        // Initialize main data
        final TulaData data = dataList.get(position);
        // Инициализация БД
        database = RoomDB.getInstance(context);

        // Вывод данных пользователю
        holder.tvIDSort.setText(Integer.toString(data.getSortID()));
        holder.tvPackNumber.setText(Integer.toString(data.getPackageNumber()));
        holder.tvDate.setText(data.getDateAdded());

        // Обработка нажатия кнопки удаления одного элемента БД
        holder.btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Initialize main data
                TulaData d = dataList.get(holder.getAdapterPosition());
                // Удаление текста из БД
                database.tulaDao().delete(d);
                // Уведомление после обновления данных
                int position = holder.getAdapterPosition();
                dataList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, dataList.size());
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvIDSort, tvPackNumber, tvDate;
        ImageView btDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIDSort = itemView.findViewById(R.id.tv_id_sort_tula);
            tvPackNumber = itemView.findViewById(R.id.tv_pack_number_tula);
            tvDate = itemView.findViewById(R.id.tv_date_tula);
            btDelete = itemView.findViewById(R.id.bt_delete);
        }
    }
}
