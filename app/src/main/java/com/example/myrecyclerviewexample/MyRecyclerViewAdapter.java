package com.example.myrecyclerviewexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myrecyclerviewexample.model.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private List<Usuario> list_original;
    private List<Usuario> list_showed;
    private final LayoutInflater inflater;
    private View.OnClickListener onClickListener;
    private int item_layout;

    public MyRecyclerViewAdapter(Context context, List<Usuario> list){
        this.list_original = list;
        this.list_showed = new ArrayList<>(list);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(item_layout,parent,false);
        view.setOnClickListener(onClickListener);
        return new ViewHolder(view);
    }

    public void setItem_layout(int item_layout) {
        this.item_layout = item_layout;
    }
    public void search(String text){
        list_showed = list_original.stream()
                .filter(usuario -> (usuario.getNombre()+" "+usuario.getApellidos()).toLowerCase().contains(text.toLowerCase()))
                .collect(Collectors.toList());
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Usuario u = list_showed.get(position);
        holder.title.setText(u.getApellidos().concat(", ").concat(u.getNombre()));
        holder.subtitle.setText(u.getOficio());
        switch (u.getImagen()){
            case 1 : holder.image.setImageResource(R.mipmap.ic_1_foreground);
            break;
            case 2 : holder.image.setImageResource(R.mipmap.ic_2_foreground);
                break;
            case 3 : holder.image.setImageResource(R.mipmap.ic_3_foreground);
                break;
            case 4 : holder.image.setImageResource(R.mipmap.ic_4_foreground);
                break;
            case 5 : holder.image.setImageResource(R.mipmap.ic_5_foreground);
                break;
            case 6 : holder.image.setImageResource(R.mipmap.ic_6_foreground);
                break;
            case 7 : holder.image.setImageResource(R.mipmap.ic_7_foreground);
                break;
            case 8 : holder.image.setImageResource(R.mipmap.ic_8_foreground);
                break;
            case 9 : holder.image.setImageResource(R.mipmap.ic_9_foreground);
                break;
            case 10 : holder.image.setImageResource(R.mipmap.ic_10_foreground);
                break;
            case 11 : holder.image.setImageResource(R.mipmap.ic_11_foreground);
                break;
            case 12 : holder.image.setImageResource(R.mipmap.ic_12_foreground);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return list_showed.size();
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView title;
        TextView subtitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            subtitle = itemView.findViewById(R.id.subtitle);
        }

    }
}
