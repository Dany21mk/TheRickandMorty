package space.mosk.therickandmorty

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import coil.load
import space.mosk.therickandmorty.config.Character


class MainAdapter(val charactersList: List<Character>) :  RecyclerView.Adapter<MainAdapter.MainViewHolder>(){
    inner class MainViewHolder(private val itemView: View):RecyclerView.ViewHolder(itemView){
        fun bindData(character: Character){
            val name = itemView.findViewById<TextView>(R.id.item_name)
            val image = itemView.findViewById<ImageView>(R.id.item_img)
            val status = itemView.findViewById<TextView>(R.id.item_status)
            val gender = itemView.findViewById<TextView>(R.id.item_gender)
            status.text = character.status
            gender.text = character.gender
            name.text = character.name
            image.load(character.image)
            val infoBtn = itemView.findViewById<Button>(R.id.item_btn)
            infoBtn.setOnClickListener { view ->
                val intent = Intent(itemView.context, CharacterActivity::class.java)
                intent.putExtra("name", character.name);
                intent.putExtra("image", character.image);
                intent.putExtra("status", character.status);
                intent.putExtra("gender", character.gender);
                intent.putExtra("species", character.species);
                intent.putExtra("type", character.type);
                intent.putExtra("origin", character.origin.name);
                intent.putExtra("location", character.location.name);
                view.context.startActivity(intent)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_theme, parent, false))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bindData(charactersList[position])
    }

    override fun getItemCount(): Int {
        return charactersList.size
    }
}