package np.com.chalise.m_hike;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ObservationAdapter extends RecyclerView.Adapter<ObservationAdapter.ViewHolder>{

    private List<Observations> dataList;

    public ObservationAdapter(List<Observations> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ObservationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.obs_item, parent, false);
        ObservationAdapter.ViewHolder viewHolder = new ObservationAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ObservationAdapter.ViewHolder holder, int position) {
        final Observations myListData = dataList.get(position);
        holder.observation.setText(dataList.get(position).getObservation());
        holder.time.setText(dataList.get(position).getTimeOfObservation());
        holder.comment.setText(dataList.get(position).getAdditionalComment());
    }

    public void updateList(List<Observations> list){
        dataList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView observation, time, comment;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            observation = itemView.findViewById(R.id.obsItemName);
            time = itemView.findViewById(R.id.obsItemtime);
            comment = itemView.findViewById(R.id.hikeItemcomment);

        }
    }
}
