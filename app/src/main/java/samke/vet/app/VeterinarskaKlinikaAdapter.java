package samke.vet.app;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class VeterinarskaKlinikaAdapter extends RecyclerView.Adapter<VeterinarskaKlinikaAdapter.MyViewHolder>{

    private List<VeterinarskaKlinika> veterinarskaKlinikaList;
    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView name, type, address,distance;
        public ImageView image;
        public MyViewHolder(View view){
            super(view);
            name = view.findViewById(R.id.name);
            type = view.findViewById(R.id.type);
            address = view.findViewById(R.id.address);
            distance = view.findViewById(R.id.distance);
            image = view.findViewById(R.id.image);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    VeterinarskaKlinika veterinarskaKlinika = veterinarskaKlinikaList.get(getAdapterPosition());
                    Intent startDetailsActivityIntent = new Intent(view.getContext(), DetailsActivity2.class);
                    startDetailsActivityIntent.putExtra("vk", veterinarskaKlinika);
                    view.getContext().startActivity(startDetailsActivityIntent);
                }
            });
        }
    }

    public VeterinarskaKlinikaAdapter(List<VeterinarskaKlinika> veterinarskaKlinikaList) {
        this.veterinarskaKlinikaList = veterinarskaKlinikaList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.veterinarska_klinika_row, parent, false);

        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        VeterinarskaKlinika veterinarskaKlinika = veterinarskaKlinikaList.get(position);
        holder.name.setText(veterinarskaKlinika.getName());
        holder.type.setText(veterinarskaKlinika.getTypesAsString(false));
        holder.address.setText(veterinarskaKlinika.getAddress());
        holder.distance.setText(String.format("%.2f", veterinarskaKlinika.getDistance()/1000) + "km");
        holder.image.setImageResource(Integer.parseInt(veterinarskaKlinika.getPicture()));

    }
    @Override
    public int getItemCount() {
        return veterinarskaKlinikaList.size();
    }

}
