package com.okbit.ubook.crud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.isEmpty
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.okbit.ubook.api.BookDbClient
import com.okbit.ubook.databinding.ActivityBookListBinding
import kotlin.concurrent.thread

class BookList : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityBookListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Listado de Libros"

        binding.recycler.adapter = BookAdapter(
                listOf(
                        Book(1,"Kotlin Apprentice", "https://i.postimg.cc/L6qbhrFN/kotlin1.jpg","Irina Galata, Joe Howard","Tecnologia","Beginning programming with kotlin","Intercambiar","099 11 11 11",2300.00, 12345689, "English", "Zona 1 Montevideo Shopping" ),
                        Book(1,"Maestro", "https://ubookweb.pythonanywhere.com/media/libro/L1.webp","Jorge Señorans","Historia","El papel del Maestro Tabárez, las historias íntimas y desconocidas del proceso de la selección uruguaya contado por sus protagonistas.","Intercambiar","Lesli 096245878",1.99, 1, "ES", "Zona 1 Montevideo Shopping" ),
                        Book(2, "No es digno, pero es legal", "https://ubookweb.pythonanywhere.com/media/libro/L2.webp","Darwin Desbocatti","Historia","ADVERTENCIA: El contenido de este libro puede herir la sensibilidad del lector. Para su consumo conviene olvidarse de que si rascamos algo que nos causa gracia, debajo de la cáscara un poco divertida hay un interior un poco desgraciado.","Donar","Pedro 096251233",1.99, 2, "ES", "Zona 2 Tres Cruces Shopping" ),
                        Book(3, "Caos", "https://ubookweb.pythonanywhere.com/media/libro/L3.webp","Magalí Tajes","Drama","Caos es un libro difícil de clasificar, que puede leerse de adelante hacia atrás y de atrás hacia adelante, un libro lúdico que exige la participación del lector.","Vender","Lorenzo 098250250",1.99, 3, "ES", "Zona 5 Portones Shopping" ),
                        Book(4, "Filosofía en 11 frases4", "https://ubookweb.pythonanywhere.com/media/libro/L4.webp","Darío Sztajnszrajber","Filosofía","Sólo sé que no sé nada. Pienso, luego existo. Todo lo sólido se desvanece en el aire. Dios ha muerto. Por medio de frases como esas, disparadores que estimulan el pensamiento, Darío Sztajnszrajber se propone sacar a la filosofía de los formatos que la hacen excluyente.","Donar","Fernanda 098250250",1.99, 4, "es", "Zona 3 Nuevo Centro Shopping" ),
                        Book(5, "El proyecto de mi vida", "https://ubookweb.pythonanywhere.com/media/libro/L5.webp","Megan Maxwell","Novelas","Branon Sivon, dueño del prestigioso bufete de abogados Sivon-Cardigan de Nueva York, adora a su hija, Sharon, a quien ha ido preparando desde pequeña para que se haga cargo del negocio familiar cuando él falte..","Vender","Camila 096250125m",1.99, 5, "ES", "Zona 1 Montevideo Shopping" ),
                        Book(6, "Más allá del invierno", "https://ubookweb.pythonanywhere.com/media/libro/L6.webp","Isabel Allende","Novelas","Isabel Allende parte de la célebre cita de Albert Camus -en medio del invierno aprendí por fin que había en mí un verano invencible- para urdir una trama que presenta la geografía humana de unos personajes propios de la América de hoy que se hallan en el más profundo invierno de sus vidas: una chilena, una joven guatemalteca ilegal y un maduro norteamericano.","Vender","Maria 098566230",1.99, 6, "ES", "Zona 1 Montevideo Shopping" ),
                        Book(7, "Una noche mágica", "https://ubookweb.pythonanywhere.com/media/libro/L7.webp","Danielle Steel","Novelas","Es verano y París se prepara para la Cena Blanca, un banquete anual en un lugar emblemático de la ciudad, al que sus exclusivos invitados acuden vestidos de blanco. Con la nueva novela de Danielle Steel creerás en la magia.","Intercambiar","Pablo 097265023",1.99, 6, "ES", "Zona 2 Tres Cruces Shopping" ),
                        Book(8, "Los perros duros no bailan", "https://ubookweb.pythonanywhere.com/media/libro/L8.webp","Arturo Pérez-Reverte","Thrillers","Una novela policial, una historia de supervivencia en un mundo donde la lealtad es puro instinto. Por Arturo Pérez-Reverte. Nací mestizo, cruce de mastín español y fila brasileña.","Intercambiar","Fabrizio 0992651470",1.99, 6, "ES", "Zona 4 Punta Carreta Shopping" ),
                        Book(9, "Origen", "https://ubookweb.pythonanywhere.com/media/libro/L9.webp","Dan Brown","Thrillers","¿DE DÓNDE VENIMOS? ¿ADÓNDE VAMOS?Robert Langdon, profesor de simbología e iconografía religiosa de Harvard, acude al Museo Guggenheim Bilbao para asistir a un trascendental anuncio que cambiará la faz de la ciencia para siempre.","Vender","Martín 097569845",1.99, 6, "ES", "Zona 5 Portones Shopping" ),
                        Book(10, "Pecho frío", "https://ubookweb.pythonanywhere.com/media/libro/L10.webp","Jaime Bayly","Ficción"," Pecho Frío es un hombre común. Está casado, vive en Lima, Perú, y trabaja en un banco. No lleva una vida muy emocionante, pero tampoco tiene nada de qué quejarse..., hasta el día en que decide concursar en un programa de televisión donde se enfrenta con la oportunidad de ganar un viaje lujoso.","Vender","Carmen 098250250",1.99, 6, "ES", "Zona 3 Nuevo Centro Shoppingg" ),

                        )
        ) { book ->
            navigateTo(book)
        }

        thread {
            val allBooks = BookDbClient.service.getAllBooks()
            val body = allBooks.execute().body()

            runOnUiThread {
                if (body != null)
                    Log.d("MainActivity", "Book count: ${body.documents.size}")
            }


        }

    }

    private fun navigateTo(book: Book) {
        val intent = Intent(this, DetailBookActivity::class.java)
        intent.putExtra(DetailBookActivity.EXTRA_BOOK, book)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("BookList", "onDestroy")
    }
}


