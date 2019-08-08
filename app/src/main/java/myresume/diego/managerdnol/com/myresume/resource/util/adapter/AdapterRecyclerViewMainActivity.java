package myresume.diego.managerdnol.com.myresume.resource.util.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import myresume.diego.managerdnol.com.myresume.R;
import myresume.diego.managerdnol.com.myresume.resource.activities.ActivityPerfil;
import myresume.diego.managerdnol.com.myresume.resource.activities.ActivitySections;
import myresume.diego.managerdnol.com.myresume.resource.activities.MainActivity;
import myresume.diego.managerdnol.com.myresume.resource.util.constants.Constants;
import myresume.diego.managerdnol.com.myresume.resource.util.dialog.CustomDialog;
import myresume.diego.managerdnol.com.myresume.resource.util.ws.WebServiceCommunication;

import java.util.List;

public class AdapterRecyclerViewMainActivity extends RecyclerView.Adapter<AdapterRecyclerViewMainActivity.ViewHolder> {

    private TypedArray imagesArrayList;
    private List<String> descriptionArrayList;
    private Context context;

    public AdapterRecyclerViewMainActivity(Context context, TypedArray imagesArrayList, List<String> descriptionArrayList) {
        this.imagesArrayList = imagesArrayList;
        this.descriptionArrayList = descriptionArrayList;
        this.context = context;
    }

    @Override
    public AdapterRecyclerViewMainActivity.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.button_home_design, parent, false);

        return new AdapterRecyclerViewMainActivity.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setItem(position);
        Glide.with(context)
                .load(imagesArrayList.getResourceId(position, -1))
                .centerCrop()
                .into(holder.imageHomeList);
        holder.description.setText(descriptionArrayList.get(position));
        holder.context = context;
    }

    @Override
    public int getItemCount() {
        return descriptionArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView description;
        ImageView imageHomeList;
        int seleccionMainActivity;
        Context context;

        public ViewHolder(View itemView) {
            super(itemView);
            description = itemView.findViewById(R.id.textViewGrid);
            imageHomeList = itemView.findViewById(R.id.imageHomeList);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            Intent intent;
            switch (seleccionMainActivity) {
                case 0:
                    if (WebServiceCommunication.isNetworkConnected(context)){
                        intent = new Intent(view.getContext(), ActivityPerfil.class);
                        view.getContext().startActivity(intent);
                    }else{
                        CustomDialog.crearDialogo(Constants.DIALOG_INTERNET_TITLE, Constants.DIALOG_INTERNET_BODY, view).show();
                    }

                    break;
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                    if (WebServiceCommunication.isNetworkConnected(context)){
                        intent = new Intent(view.getContext(), ActivitySections.class);
                        intent.putExtra(Constants.EXTRAS_SECTION, seleccionMainActivity+1);
                        view.getContext().startActivity(intent);
                    }else{
                        CustomDialog.crearDialogo(Constants.DIALOG_INTERNET_TITLE, Constants.DIALOG_INTERNET_BODY, view).show();
                    }
                    break;
            }
        }

        public void setItem(int seleccionMainActivity) {
            this.seleccionMainActivity = seleccionMainActivity;
        }
    }
}
