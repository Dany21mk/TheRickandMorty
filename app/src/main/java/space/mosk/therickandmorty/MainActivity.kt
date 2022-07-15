package space.mosk.therickandmorty

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import retrofit2.Call
import retrofit2.Response
import space.mosk.therickandmorty.config.ApiClient
import space.mosk.therickandmorty.config.Character
import space.mosk.therickandmorty.config.ResponseApi


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(R.layout.activity_main)



        createRequest(ApiClient.apiService.fetchCharacters(" "))


        val searchText = findViewById<EditText>(R.id.search)
        searchText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }
            override fun afterTextChanged(s: Editable) {
                createRequest(ApiClient.apiService.fetchCharacters(searchText.text.toString()))
            }
        })
    }

    fun createRequest(client: Call<ResponseApi>){
        client.enqueue(object : retrofit2.Callback<ResponseApi>{

            override fun onResponse(call: Call<ResponseApi>, response: Response<ResponseApi>) {
                if (response.isSuccessful){
                    val result = response.body()?.result
                    result?.let{
                        val adapter = MainAdapter(result)
                        val recyclerView = findViewById<RecyclerView>(R.id.recycler_theme)
                        recyclerView?.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
                        recyclerView?.adapter = adapter
                    }
                } else{
                    Log.d("failed", ""+response.code())
                    val recyclerView = findViewById<RecyclerView>(R.id.recycler_theme)
                    recyclerView.removeAllViews()
                    Toast.makeText(getBaseContext(), "По вашему запросу ничего не найдено!", Toast.LENGTH_SHORT).show();
                }
            }



            override fun onFailure(call: Call<ResponseApi>, t: Throwable) {
                Log.d("failed", ""+t.message)
            }

        })
    }



}