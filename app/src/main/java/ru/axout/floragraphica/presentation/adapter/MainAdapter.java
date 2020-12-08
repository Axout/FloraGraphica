package ru.axout.floragraphica.presentation.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ru.axout.floragraphica.data.MainData;
import ru.axout.floragraphica.R;
import ru.axout.floragraphica.data.RoomDB;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    // Инициализируем переменные
    private List<MainData> dataList;
    private Activity context;
    private RoomDB database;

    // Конструктор
    public MainAdapter(Activity context, List<MainData> dataList) {
        this.context = context;
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Initialize view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_main, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        // Initialize main data
        final MainData data = dataList.get(position);
        // Инициализация БД
        database = RoomDB.getInstance(context);

        // Вывод данных пользователю
        holder.textViewID.setText(Integer.toString(data.getID()));
        holder.textViewSort.setText(data.getSort());
        holder.textViewColor.setText(data.getColor());
        holder.textViewType.setText(data.getType());

        // Обработка нажатия кнопки удаления одного элемента БД
        holder.btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Initialize main data
                MainData d = dataList.get(holder.getAdapterPosition());
                // Удаление текста из БД
                database.mainDao().delete(d);
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
        TextView textViewID, textViewSort, textViewColor, textViewType;
        ImageView btDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewID = itemView.findViewById(R.id.text_view_id);
            textViewSort = itemView.findViewById(R.id.text_view_sort);
            textViewColor = itemView.findViewById(R.id.text_view_color);
            textViewType = itemView.findViewById(R.id.text_view_type);
            btDelete = itemView.findViewById(R.id.bt_delete);
        }
    }
}
