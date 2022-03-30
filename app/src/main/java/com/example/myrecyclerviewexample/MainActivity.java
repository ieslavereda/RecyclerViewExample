package com.example.myrecyclerviewexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myrecyclerviewexample.model.Model;
import com.example.myrecyclerviewexample.model.Usuario;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler);

        MyRecyclerViewAdapter myRecyclerViewAdapter = new MyRecyclerViewAdapter(this, Model.getInstance().getList());
        myRecyclerViewAdapter.setOnClickListener(this);
        recyclerView.setAdapter(myRecyclerViewAdapter);

        LinearLayoutManager myLinearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(myLinearLayoutManager);

        ItemTouchHelper mIth = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                        // move item in `fromPos` to `toPos` in adapter.
                        recyclerView.getAdapter().notifyItemMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());
                        return true;// true if moved, false otherwise
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                        Usuario usuario = Model.getInstance().getList().get(viewHolder.getAdapterPosition());

                        int position = viewHolder.getAdapterPosition();

                        Model.getInstance().getList().remove(position);

                        myRecyclerViewAdapter.notifyItemRemoved(position);

                        Snackbar.make(recyclerView, "Deleted " + usuario.getNombre(), Snackbar.LENGTH_LONG)
                                .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Model.getInstance().getList().add(position, usuario);
                                myRecyclerViewAdapter.notifyItemInserted(position);
                            }
                        }).show();
                    }

                });
        mIth.attachToRecyclerView(recyclerView);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, RecyclerView.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

    }

    @Override
    public void onClick(View view) {
        Usuario u = Model.getInstance().getList().get(recyclerView.getChildAdapterPosition(view));
        Toast.makeText(this,"Clic en " + u.getOficio(),Toast.LENGTH_SHORT).show();
    }
}