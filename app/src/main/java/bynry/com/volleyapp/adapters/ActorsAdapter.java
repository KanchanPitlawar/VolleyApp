package bynry.com.volleyapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;

import java.util.ArrayList;

import bynry.com.volleyapp.R;
import bynry.com.volleyapp.models.Actors;

public class ActorsAdapter extends RecyclerView.Adapter<ActorsAdapter.ActorsHolder> {

    private Context mContext;
    private ArrayList<Actors> mActors;
    private RequestManager glide;

    public ActorsAdapter(Context context, ArrayList<Actors> actors, RequestManager glide){
        this.mContext = context;
        this.mActors = actors;
        this.glide = glide;
    }
    @NonNull
    @Override
    public ActorsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cell_card, null);
        ActorsAdapter.ActorsHolder viewHolder = new ActorsAdapter.ActorsHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ActorsHolder actorsHolder, int i) {

        Actors actors = mActors.get(i);
        actorsHolder.txtName.setText(actors.getName());
        glide.load(actors.imageURL).into(actorsHolder.imgView);


    }

    @Override
    public int getItemCount() {
        return mActors.size();
    }

    public class ActorsHolder extends RecyclerView.ViewHolder {

        private ImageView imgView;
        private TextView txtName;
        private CardView cardView;
        public ActorsHolder(@NonNull View itemView) {
            super(itemView);


            cardView = itemView.findViewById(R.id.card_view);
            imgView = itemView.findViewById(R.id.img_view);
            txtName = itemView.findViewById(R.id.txt_name);
        }
    }
}
