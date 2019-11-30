package com.example.firebaseapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    // delcarar objetos para recibir elementos de la vista
    private EditText editTema;
    private Spinner spiCarrera;
    private  Spinner spiMateria;

    private Button btnReg;
    // referencia a base de datos
    private DatabaseReference refDiario;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        refDiario = FirebaseDatabase.getInstance().getReference("Clase");

        /// asociar los objetos de la vista
        editTema = (EditText)findViewById(R.id.edxTema);
        spiCarrera = (Spinner)findViewById(R.id.spiCarrera);
        spiMateria = (Spinner)findViewById(R.id.spiMateria);
        btnReg = (Button)findViewById(R.id.btnRegistrar);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarClase();
            }
        });

    }

    // metodo registrar clase
    public void registrarClase(){

        String carrera = spiCarrera.getSelectedItem().toString();
        String materia = spiMateria.getSelectedItem().toString();
        String tema = editTema.getText().toString();

        if(!TextUtils.isEmpty(tema)){
            // se genera clave para la BD
            String id = refDiario.push().getKey();
            Clase leccion = new Clase(id,carrera,materia,tema);
            // se agrega un hijo dentro de la rama de lecciones
            refDiario.child("Lecciones").child(id).setValue(leccion);
            Toast.makeText(this,"Clase registrada", Toast.LENGTH_LONG).show();

        }else{Toast.makeText(this,"Ingresa tema", Toast.LENGTH_LONG).show();}

    }
}
