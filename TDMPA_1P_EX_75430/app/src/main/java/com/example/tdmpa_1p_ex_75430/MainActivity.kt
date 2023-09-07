package com.example.tdmpa_1p_ex_75430

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //mostrar lista
        var vistaGasto = findViewById<TextView>(R.id.txtMostrarGasto)
        vistaGasto.text=imprimirNums()
        var btnAgregar = findViewById<Button>(R.id.btnAgregar)
        var gastoIngresado=findViewById<EditText>(R.id.txtGasto)
        var conceptoIngresado = findViewById<EditText>(R.id.txtConcepto)
        //btns
        var btnCalculoTotal = findViewById<Button>(R.id.btnMostrarTodo)
        var btnGastoPromedio = findViewById<Button>(R.id.btnGastoPromedio)
        //radiobuttons
        var rbComida = findViewById<RadioButton>(R.id.rbComida)
        var rbOcio = findViewById<RadioButton>(R.id.rbOcio)
        var rbSalud = findViewById<RadioButton>(R.id.rbSalud)
        var rbHogar = findViewById<RadioButton>(R.id.rbHogar)


        btnAgregar.setOnClickListener {
           GastoIngresadoList.add(gastoIngresado.text.toString())
            ConceptoIngresadoList.add(conceptoIngresado.text.toString())
            when {
                rbComida.isChecked -> {
                   Categorias.add("COMIDA")

                }

                rbOcio.isChecked -> {
                    Categorias.add("OCIO")

                }

                rbSalud.isChecked -> {
                    Categorias.add("SALUD")

                }

                rbHogar.isChecked -> {
                    Categorias.add("HOGAR")

                }

                else -> {

                }
            }
            vistaGasto.text=imprimirNums()

        }

        btnCalculoTotal.setOnClickListener {
            val sumaGastos = GastoIngresadoList.sumBy { it.toIntOrNull() ?: 0 }
            vistaGasto.text = "Suma de gastos Totales : $sumaGastos"
        }

        btnGastoPromedio.setOnClickListener {
            var total = 0
            var TotalXcategoria = 0
            when {
                rbComida.isChecked -> {
                    total = calcularPromedioPorCategoria("COMIDA")
                    TotalXcategoria = calcularTotalPorCategoria("COMIDA")
                }

                rbOcio.isChecked -> {
                     total = calcularPromedioPorCategoria("OCIO")
                    TotalXcategoria = calcularTotalPorCategoria("OCIO")
                }

                rbSalud.isChecked -> {
                    total = calcularPromedioPorCategoria("SALUD")
                    TotalXcategoria = calcularTotalPorCategoria("SALUD")


                }

                rbHogar.isChecked -> {
                    total= calcularPromedioPorCategoria("HOGAR")
                    TotalXcategoria = calcularTotalPorCategoria("HOGAR")

                }

                else -> {

                }

            }
            vistaGasto.text="El total por Categoria es : "+TotalXcategoria+"El promedio es: " + total.toString()

        }
    }


    var GastoIngresadoList:MutableList<String> =mutableListOf()
    var ConceptoIngresadoList:MutableList<String> =mutableListOf()
    var Categorias: MutableList<String> =mutableListOf()


    fun imprimirNums():String{
        var cadena=""

        for (i in 0 .. GastoIngresadoList.size-1){
            cadena=cadena+("Monto: ${GastoIngresadoList[i]},Concepto :${ConceptoIngresadoList[i]}\n ")
        }

        return cadena
    }


    fun calcularPromedioPorCategoria(categoriaSeleccionada: String): Int {
        var PromedioPorCategoria = 0

        for (i in (0 until GastoIngresadoList.count()-1)) {
            if (Categorias[i] == categoriaSeleccionada) {
                val gasto = GastoIngresadoList[i].toIntOrNull() ?: 0
                PromedioPorCategoria += gasto
            }
        }

        return PromedioPorCategoria
    }


        fun calcularTotalPorCategoria(categoriaSeleccionada: String): Int {
            var totalPorCategoria = 0

            for (i in 0 until GastoIngresadoList.size) {
                if (Categorias[i] == categoriaSeleccionada) {
                    val gasto = GastoIngresadoList[i].toIntOrNull() ?: 0
                    totalPorCategoria += gasto
                }
            }

            return totalPorCategoria
        }

}