package com.example.myrecyclerviewexample;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myrecyclerviewexample.model.Model;
import com.example.myrecyclerviewexample.model.Usuario;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import com.example.myrecyclerviewexample.MyRecyclerViewAdapter.Sort;

import static com.example.myrecyclerviewexample.MyRecyclerViewAdapter.Sort.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private ImageButton ibList;
    private ImageButton ibGrid;
    private boolean isListRecycler;
    private MyRecyclerViewAdapter myRecyclerViewAdapter;
    private EditText etSearch;
    private TextInputLayout textInputLayout;
    private ImageButton ibSort;
    private Sort sort;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isListRecycler = true;
        sort = NORMAL;
        String texto;

        if (savedInstanceState != null)
            texto = savedInstanceState.getString("texto");

        recyclerView = findViewById(R.id.recycler);
        ibList = findViewById(R.id.ibList);
        ibGrid = findViewById(R.id.ibGrid);
        ibSort = findViewById(R.id.ibSort);
        etSearch = findViewById(R.id.etSearch);
        textInputLayout = findViewById(R.id.textInputLayout);

        ibGrid.setOnClickListener(view -> {
            isListRecycler = false;
            updateRecycler();
        });

        ibList.setOnClickListener(view -> {
            isListRecycler = true;
            updateRecycler();
        });

        ibSort.setOnClickListener(v -> {
            sort = sort.next();
            myRecyclerViewAdapter.sort(sort);

            switch (sort){
                case ASC:ibSort.setImageDrawable(getDrawable(android.R.drawable.arrow_up_float));
                break;
                case DESC:ibSort.setImageDrawable(getDrawable(android.R.drawable.arrow_down_float));
                    break;
                default: ibSort.setImageDrawable(getDrawable(android.R.drawable.checkbox_off_background));
            }
        });

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                search();
            }
        });

        myRecyclerViewAdapter = new MyRecyclerViewAdapter(this, Model.getInstance().getList());
        myRecyclerViewAdapter.setOnClickListener(this);
        recyclerView.setAdapter(myRecyclerViewAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, RecyclerView.HORIZONTAL));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, RecyclerView.VERTICAL));

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
                                .setAction("Undo", v -> {
                                    Model.getInstance().getList().add(position, usuario);
                                    myRecyclerViewAdapter.notifyItemInserted(position);
                                }).show();
                    }

                });
        mIth.attachToRecyclerView(recyclerView);

        updateRecycler();

    }

    private void search() {
        myRecyclerViewAdapter.search(etSearch.getText().toString());
    }

    private void updateRecycler() {

        if (isListRecycler) {
            myRecyclerViewAdapter.setItem_layout(R.layout.simple_list_element);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        } else {
            myRecyclerViewAdapter.setItem_layout(R.layout.simple_grid_element);
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        }

        // Eliminamos el pool de vistas del recycler, por lo que forzamos la creacion otra vez de las vistas
        recyclerView.getRecycledViewPool().clear();

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("texto", "Esto se guarda");
    }

    @Override
    public void onClick(View view) {
        Usuario u = Model.getInstance().getList().get(recyclerView.getChildAdapterPosition(view));
        Toast.makeText(this, "Clic en " + u.getOficio(), Toast.LENGTH_SHORT).show();
    }
}