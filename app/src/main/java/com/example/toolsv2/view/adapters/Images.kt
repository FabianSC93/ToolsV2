package com.example.toolsv2.view.adapters

import android.net.Uri

//Se genera una data class para las imagenes, indicando que tipo de valor deberá recibir, en este caso de tipo Uri, indicando que puede ser vacío
data class Images(val uri: Uri?)
