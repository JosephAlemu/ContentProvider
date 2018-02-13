package githubprofile.joseph.com.clientapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import githubprofile.joseph.com.clientapp.R;
import githubprofile.joseph.com.clientapp.model.Info;


public class Info_Adapter extends  RecyclerView.Adapter<Info_Adapter.MyViewHolder> {

    private List<Info> InfoList;
    private Context context;
    public Info_Adapter(Context context, List<Info> InfoList) {
        this.InfoList = InfoList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Info info= InfoList.get(position);
        holder.txtName.setText(info.getName());
        holder.tvEmail.setText(info.getEmail());
        holder.tvPhone.setText(info.getPhone());
        holder.tvAddress.setText(info.getAddress());

    }

    @Override
    public int getItemCount() {
        return InfoList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView   txtName, tvEmail,tvPhone,tvAddress;


        public MyViewHolder(View view) {
            super(view);
            txtName = (TextView) view.findViewById(R.id.tvName);
            tvEmail = (TextView) view.findViewById(R.id.tvEmail);
            tvPhone = (TextView) view.findViewById(R.id.tvPhone);
            tvAddress = (TextView) view.findViewById(R.id.tvAddress);


        }
    }
}