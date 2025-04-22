package com.example.taller2_interfaces

import androidx.appcompat.app.AppCompatActivity
import kotlin.math.pow


class CalculadoraMetasActivity : /*@@zdwuku@@*/AppCompatActivity() {

    private var etMontoObjetivo: /*@@nlbiyp@@*/android.widget.EditText? = null
    private var etPlazoDeseado: /*@@nlbiyp@@*/android.widget.EditText? = null
    private var etAhorroInicial: /*@@nlbiyp@@*/android.widget.EditText? = null
    private var etTasaInteres: /*@@nlbiyp@@*/android.widget.EditText? = null
    private var btnCalcular: /*@@gmaqgo@@*/android.widget.Button? = null
    private var btnGuardar: /*@@gmaqgo@@*/android.widget.Button? = null
    private var tvResultado: /*@@mayeoh@@*/android.widget.TextView? = null
    private var lvHistorialMetas: /*@@doavhq@@*/android.widget.ListView? = null
    private var metasAdapter: /*@@koggnc@@*/android.widget.ArrayAdapter</*@@derojm@@*/kotlin.String?>? = null
    private var historialMetas: /*@@oxvpzt@@*/java.util.ArrayList</*@@derojm@@*/kotlin.String?>? = null
    private var sharedPreferences: /*@@ddursh@@*/android.content.SharedPreferences? = null

    override fun /*@@slbnyy@@*/onCreate(savedInstanceState: /*@@ddxktk@@*/android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(android.R.layout.activity_calculadora_metas.xml)

        // Inicializar vistas
        etMontoObjetivo = findViewById</*@@hndyil@@*/android.widget.EditText?>(android.R.id.etMontoObjetivo)
        etPlazoDeseado = findViewById</*@@hndyil@@*/android.widget.EditText?>(android.R.id.etPlazoDeseado)
        etAhorroInicial = findViewById</*@@hndyil@@*/android.widget.EditText?>(android.R.id.etAhorroInicial)
        etTasaInteres = findViewById</*@@hndyil@@*/android.widget.EditText?>(android.R.id.etTasaInteres)
        btnCalcular = findViewById</*@@gfrjnz@@*/android.widget.Button?>(android.R.id.btnCalcular)
        btnGuardar = findViewById</*@@gfrjnz@@*/android.widget.Button?>(android.R.id.btnGuardar)
        tvResultado = findViewById</*@@klicgl@@*/android.widget.TextView?>(android.R.id.tvResultado)
        lvHistorialMetas = findViewById</*@@ulvaif@@*/android.widget.ListView?>(android.R.id.lvHistorialMetas)

        // Inicializar SharedPreferences
        sharedPreferences = getSharedPreferences("Metas", android.content.Context.MODE_PRIVATE)

        // Inicializar historial de metas
        historialMetas = java.util.ArrayList</*@@derojm@@*/kotlin.String?>(sharedPreferences.getStringSet("historial", java.util.HashSet</*@@derojm@@*/kotlin.String?>()))
        metasAdapter = android.widget.ArrayAdapter</*@@derojm@@*/kotlin.String?>(/*~~ttizff~~*/this, android.R.layout.simple_list_item_1, historialMetas)
        lvHistorialMetas.setAdapter(metasAdapter)

        // Configurar botón de cálculo
        btnCalcular.setOnClickListener(object : android.view.View.OnClickListener {
            override fun /*@@buvroe@@*/onClick(v: /*@@febtgi@@*/android.view.View?) {
                calcularAhorro()
            }
        })

        // Configurar botón de guardar
        btnGuardar.setOnClickListener(object : android.view.View.OnClickListener {
            override fun /*@@bejpnq@@*/onClick(v: /*@@febtgi@@*/android.view.View?) {
                guardarResultado()
            }
        })
    }

    private fun /*@@exkwpw@@*/calcularAhorro() {
        // Obtener datos de la interfaz de usuario
        val montoObjetivo: /*@@lpdkpo@@*/kotlin.Double = etMontoObjetivo.getText().toString().toDouble()
        val plazoDeseado: /*@@rvjrou@@*/kotlin.Int = etPlazoDeseado.getText().toString().toInt()
        val ahorroInicial: /*@@lpdkpo@@*/kotlin.Double = etAhorroInicial.getText().toString().toDouble()
        val tasaInteres: /*@@lpdkpo@@*/kotlin.Double = etTasaInteres.getText().toString().toDouble() / 100

        // Calcular ahorro mensual necesario
        val ahorroMensual: /*@@lpdkpo@@*/kotlin.Double = (montoObjetivo - ahorroInicial) / ((((1 + tasaInteres).pow(plazoDeseado.toDouble())) - 1) / tasaInteres)

        // Mostrar resultado
        tvResultado.setText("Ahorro mensual necesario: " + ahorroMensual)
    }

    private fun /*@@pitdhz@@*/guardarResultado() {
        // Obtener resultado del cálculo
        val resultado: /*@@derojm@@*/kotlin.String? = tvResultado.getText().toString()

        // Obtener fecha actual
        val sdf: /*@@lmbhqo@@*/java.text.SimpleDateFormat? = java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss", java.util.Locale.getDefault())
        val fecha: /*@@derojm@@*/kotlin.String? = sdf.format(java.util.Date())

        // Guardar resultado en SharedPreferences
        historialMetas.add(fecha + ": " + resultado)
        val editor: /*@@yuiyma@@*/android.content.SharedPreferences.Editor? = sharedPreferences.edit()
        editor.putStringSet("historial",
            historialMetas?.let { java.util.HashSet</*@@derojm@@*/kotlin.String?>(it) })
        editor.apply()

        // Notificar al usuario
        android.widget.Toast.makeText(/*~~ltwjvf~~*/this, "Resultado guardado", android.widget.Toast.LENGTH_SHORT).show()

        // Actualizar lista
        metasAdapter.notifyDataSetChanged()
    }
}