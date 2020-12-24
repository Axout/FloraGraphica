package ru.axout.floragraphica.presentation.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ru.axout.floragraphica.R;
//import ru.axout.floragraphica.data.CountPack;
import ru.axout.floragraphica.data.CountPack;
import ru.axout.floragraphica.data.TulaData;

import java.util.List;

public class TulaRestAdapter extends RecyclerView.Adapter<TulaRestAdapter.ViewHolder> {

    public static final int PACKAGE_SIZE = 20; // количество цветов в упаковке
    private List<CountPack> countPackList;

    // Конструктор
    public TulaRestAdapter(List<CountPack> countPackList) {
        this.countPackList = countPackList;
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
        final CountPack countPack = countPackList.get(position);

        // Вывод данных пользователю
        holder.tvSort.setText(countPack.sort);
        holder.tvQuantity.setText(Integer.toString(countPack.countPack * PACKAGE_SIZE));
    }

    @Override
    public int getItemCount() {
        return countPackList.size();
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
