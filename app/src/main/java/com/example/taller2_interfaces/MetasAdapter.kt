package com.example.taller2_interfaces

import android.content.Context
import android.widget.ArrayAdapter


class MetasAdapter // Línea corregida
// El resto del código del adaptador...
    (private val context: Context, private val metas: List<String>) :
    ArrayAdapter<String?>(context, 0, metas)