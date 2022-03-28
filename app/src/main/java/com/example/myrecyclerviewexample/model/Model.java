package com.example.myrecyclerviewexample.model;

import java.util.ArrayList;
import java.util.List;

public class Model {

    private static Model model;
    private List<Usuario> list;

    private Model(){
        list = new ArrayList<>();
        list.add(new Usuario(1,"Manolo","Garcia Sanchez","Actor"));
        list.add(new Usuario(11,"Luis","Sanchez Ruiz","Politico (Retirado)"));
        list.add(new Usuario(12,"Manolo","Garcia Sanchez","Vendedor"));
        list.add(new Usuario(4,"Luis","Sanchez Ruiz","Cocinero"));
        list.add(new Usuario(5,"Manolo","Garcia Sanchez","Estudiante"));
        list.add(new Usuario(6,"Luis","Sanchez Ruiz","Instagramer"));
        list.add(new Usuario(7,"Manolo","Garcia Sanchez","Pintor"));
        list.add(new Usuario(8,"Luis","Sanchez Ruiz","Policia"));
        list.add(new Usuario(9,"Manolo","Garcia Sanchez","Youtuber"));
        list.add(new Usuario(10,"Luis","Sanchez Ruiz","Politico (Activo)"));
        list.add(new Usuario(2,"Manolo","Garcia Sanchez","Albañil"));
        list.add(new Usuario(3,"Luis","Sanchez Ruiz","Banquero"));
    }

    public static Model getInstance(){
        if(model==null)
            model = new Model();

        return model;
    }

    public List<Usuario> getList() {
        return list;
    }
}
