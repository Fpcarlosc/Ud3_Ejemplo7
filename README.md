# Ud3_Ejemplo7
_Ejemplo 7 de la Unidad 3._ 

Vamos a ver cómo salvar el estado de una Actividad. Guardaremos el valor de un contador que irá aumentado en 1 cada vez que se 
pulse el botón. Para salvar su estado usaremos un par de clave/valor.

Haremos la prueba rotando la pantalla y veremos como el valor del contador no se habrá perdido. También podremos observar cómo, 
si escribimos en el _EditText_ y rotamos, su valor no se pierde, ya que por defecto Android salva el estado de todos los elementos 
que tienen un _id_ único (otra razón de la importancia de utilizar _ids_).

Para ello primero nos creamos el _layout_, con un _EditText_, un botón y un _TextView_.

_activity_main.xml_:
```
<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <EditText
        android:id="@+id/editText"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_alignParentTop="true"
        android:layout_alignParentLeft="true"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintBottom_toTopOf="@+id/boton"/>

    <Button
        android:id="@+id/boton"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerInParent="true"
        android:text="@string/contar"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintBottom_toBottomOf="parent"/>

    <TextView
        android:id="@+id/textViewContador"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_below="@id/boton"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toBottomOf="@id/boton"
        app:layout_constraintBottom_toBottomOf="parent"/>

</android.support.constraint.ConstraintLayout>
```
Y en la clase _MainActivity_ vamos a:
+ Crear una constante para la clave y un atributo para el valor.
+ Sobrescribir los métodos _onSaveInstanceState_ para salvar el estado del contador y _onRestoreInstanceState_ para restaurarlo.
```
public class MainActivity extends AppCompatActivity {

    static final String CLAVE = "CONTADOR"; // Nombre de la clave.
    private int cont = 0; // Valor de la clave.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button boton = findViewById(R.id.boton);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cont++;

                TextView textview = findViewById(R.id.textViewContador);

                textview.setText("Contador: " + Integer.toString(cont));
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Guardamos el estado usando el par clave/valor
        // Usamos putInt ya que cont es entero.
        outState.putInt(CLAVE, cont);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // Recuperamos el valor en función de su clave.
        // Usamos getInt ya que cont es entero.
        cont = savedInstanceState.getInt(CLAVE);
    }
}
```
