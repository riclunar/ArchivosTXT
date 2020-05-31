package mx.edu.tesoem.isc.riclr.archivostxt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText txtNombre;
    TextView lblContenido;
    ArrayList<String> Datos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtNombre = findViewById(R.id.txtNombre);
        lblContenido = findViewById(R.id.lblContenido);
        CargarInfo();
    }

    public void Grabar(View v){
        Archivos conexion = new Archivos();
        conexion.Agregar(txtNombre.getText().toString(),Datos);
        Datos= conexion.getDatos();
        if (conexion.Grabar(this, Datos )){
            Toast.makeText(this,"Se grabó Correctamente", Toast.LENGTH_LONG).show();
            CargarInfo();
        }else{
            Toast.makeText(this, "No se Grabó correctamente", Toast.LENGTH_LONG ).show();
        }
    }

    public void CargarInfo(){
        Archivos conexion = new Archivos();
        String dato ="";
        if (conexion.VerificaArch(this)){
            if (conexion.Leer(this)){
                Datos=conexion.getDatos();
                for (String elemento: Datos)
                    dato+=elemento+"\n";
                lblContenido.setText(dato);
            }else {
                Toast.makeText(this, "Error al obtener información", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(this, "No existe el archivo", Toast.LENGTH_LONG).show();
        }
    }
}
