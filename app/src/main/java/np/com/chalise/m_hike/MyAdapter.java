package np.com.chalise.m_hike;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<HikeDataModel> dataList;
    Activity context;

    public MyAdapter(List<HikeDataModel> dataList, Activity context) {
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.hike_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        final HikeDataModel myListData = dataList.get(position);
        holder.name.setText(dataList.get(position).getName());
        holder.location.setText(dataList.get(position).getLocation());
        holder.date.setText(dataList.get(position).getDate());
        holder.viewMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ViewMoreActivity.class);
                intent.putExtra("id", dataList.get(holder.getAdapterPosition()).getId());
                intent.putExtra("name", dataList.get(holder.getAdapterPosition()).getName());
                intent.putExtra("location", dataList.get(holder.getAdapterPosition()).getLocation());
                intent.putExtra("date", dataList.get(holder.getAdapterPosition()).getDate());
                intent.putExtra("parking", dataList.get(holder.getAdapterPosition()).getAvailable());
                intent.putExtra("length", dataList.get(holder.getAdapterPosition()).getLength());
                intent.putExtra("difficulty", dataList.get(holder.getAdapterPosition()).getDifficulty());
                intent.putExtra("accommodation", dataList.get(holder.getAdapterPosition()).getAccommodation());
                intent.putExtra("limitation", dataList.get(holder.getAdapterPosition()).getLimitation());
                intent.putExtra("description", dataList.get(holder.getAdapterPosition()).getDescription());
                context.startActivity(intent);
            }
        });
    }

    public void updateList(List<HikeDataModel> list){
        dataList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView date, name, location, viewMore, parking, length, difficulty, description, accommodation, limitation;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.hikeItemDate);
            name = itemView.findViewById(R.id.hikeItemName);
            location = itemView.findViewById(R.id.hikeItemLocation);
            viewMore = itemView.findViewById(R.id.viewMoreText);
        }
    }
}
