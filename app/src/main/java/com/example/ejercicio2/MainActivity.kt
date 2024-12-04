package com.example.ejercicio2

import android.annotation.SuppressLint
import android.os.Bundle

import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import kotlin.math.sqrt


class MainActivity : AppCompatActivity() {


    //var counter = 0
    private lateinit var numeroeditTextNumber: EditText
    private lateinit var acceptButton: Button
    private lateinit var resultadotextView: TextView


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numeroeditTextNumber = findViewById(R.id.numeroeditTextNumber)
        acceptButton = findViewById(R.id.acceptButton)
        resultadotextView = findViewById(R.id.resultadotextView)

        //Funciones

        //Funcion para limpiar el texto donde se introducen los numeros
        numeroeditTextNumber.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                numeroeditTextNumber.text.clear()
            }
        }
        // Acción al presionar el botón
        acceptButton.setOnClickListener {
            val input = numeroeditTextNumber.text.toString()

            try {
                val number = input.toInt()

                if (number <= 0) {
                    resultadotextView.text = "Por favor ingresa un número positivo."
                } else if (esPrimo(number)) {
                    val siguientePrimo = encuentraSiguientePrimo(number)
                    resultadotextView.text = "El siguiente número primo es: $siguientePrimo"
                } else {
                    resultadotextView.text = "El número ingresado no es primo."
                }

            } catch (e: NumberFormatException) {
                Toast.makeText(this, "Por favor ingresa un número válido", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Función para encontrar el siguiente número primo
    private fun encuentraSiguientePrimo(n: Int): Int {
        var nextNumber = n + 1
        while (!esPrimo(nextNumber)) {
            nextNumber++
        }
        return nextNumber
    }

    // Función para verificar si un número es primo
    private fun esPrimo(number: Int): Boolean {
        if (number <= 1) return false
        for (i in 2..sqrt(number.toDouble()).toInt()) {
            if (number % i == 0) {
                return false
            }
        }
        return true
    }
}
