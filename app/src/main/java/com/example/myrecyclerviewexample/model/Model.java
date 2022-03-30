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
        list.add(new Usuario(12,"Pedro","Lopez Lopez","Vendedor"));
        list.add(new Usuario(4,"Carlos","Sanchez Ruiz","Cocinero"));
        list.add(new Usuario(5,"Tomas","Garcia Sanchez","Estudiante"));
        list.add(new Usuario(6,"Juan","Gutierrez Ruiz","Instagramer"));
        list.add(new Usuario(7,"Jose","Gimenez Lopez","Pintor"));
        list.add(new Usuario(8,"Santiago","Sanchez Ruiz","Policia"));
        list.add(new Usuario(9,"Victor","Sanz Martinez","Youtuber"));
        list.add(new Usuario(10,"Javi","Sanchez Ruiz","Politico (Activo)"));
        list.add(new Usuario(2,"Gabriel","Martinez Sanz","Albañil"));
        list.add(new Usuario(3,"Miguel","Gimenez Ruiz","Banquero"));
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
