package com.hfad.vacationdestination;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    private ArrayList<VacationDestination> destinationList;

    private FragmentManager fragmentManager;

    public MyAdapter(FragmentManager man, ArrayList<VacationDestination> vd)
    {
        fragmentManager = man;
        destinationList = Database.getData();
    }

    //Creates an empty view of a single row - inflate a vacation row item
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.vacation_row, parent, false);

        System.out.println("DONE CREATING A SINGLE ROWS VIEW");
        return new MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return destinationList.size();
    }

    //BINDS data to an empty row view
    //Position is the index in the list you want to show
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        VacationDestination vd = destinationList.get(position);
        holder.setData(vd, position);

        System.out.println("DONE CREATING POPULATING A ROW: " + position + " " + vd.getName());
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private ImageView imvPicture;
        private TextView tvName;
        private ImageView imvDelete;
        private ImageView imvMakeCopy;
        private ImageView imvFav;
        private CheckBox cbFav;

        private int currentPositionInList = -1;
        private VacationDestination currentDest = null;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imvPicture = itemView.findViewById(R.id.imvPlace);
            tvName = itemView.findViewById(R.id.tvPlaceName);
            imvDelete = itemView.findViewById(R.id.imvDelete);
            imvMakeCopy = itemView.findViewById(R.id.imvMakeCopy);
            imvFav = itemView.findViewById(R.id.imvFavorite);

            cbFav = itemView.findViewById(R.id.cbFav);

            imvDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    destinationList.remove(currentPositionInList);
                    notifyItemRemoved(currentPositionInList);
                    notifyItemRangeChanged(currentPositionInList, destinationList.size());
                }
            });

            imvMakeCopy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    destinationList.add(currentPositionInList, currentDest);
                    notifyItemInserted(currentPositionInList);
                    notifyItemRangeChanged(currentPositionInList, destinationList.size());
                }
            });

            imvFav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(currentDest.isFavorite())
                    {
                        imvFav.setImageResource(R.drawable.black_star);
                        currentDest.setFavorite(false);
                        //cbFav.setChecked(false);
                    }
                    else
                    {
                        imvFav.setImageResource(R.drawable.fill_star);
                        currentDest.setFavorite(true);
                        //cbFav.setChecked(true);
                    }

                }
            });

            itemView.setClickable(true);
            itemView.setOnClickListener(this);

        }

        public void setData(VacationDestination vd, int pos)
        {
            imvPicture.setImageResource(vd.getImageId());
            tvName.setText(vd.getName());
            currentPositionInList = pos;
            currentDest = vd;

        }

        @Override
        public void onClick(View view) {
            DialogShowDestination dialog = new DialogShowDestination(currentDest);
            dialog.show(fragmentManager, "");
        }
    }
}
