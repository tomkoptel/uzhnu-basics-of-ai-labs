package uzhnu.edu.bai.dialog.android

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import uzhnu.edu.bai.dialog.android.adapter.MainAdapter
import uzhnu.edu.bai.dialog.models.BreedModel

class MainActivity : AppCompatActivity() {
    companion object {
        val TAG = MainActivity::class.java.simpleName
    }

    private lateinit var adapter: MainAdapter
    private lateinit var model: BreedModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        model = BreedModel(
            viewUpdate = { itemData ->
                Log.d(TAG, itemData.toString())
                adapter.submitList(itemData.allItems)
            },
            errorUpdate = { errorMessage ->
                Snackbar.make(breed_list, errorMessage, Snackbar.LENGTH_SHORT).show()
            }
        )

        adapter = MainAdapter(model::updateBreedFavorite)

        breed_list.adapter = adapter
        breed_list.layoutManager = LinearLayoutManager(this)

        model.getBreedsFromNetwork()
    }

    override fun onDestroy() {
        super.onDestroy()
        model.onDestroy()
    }
}
