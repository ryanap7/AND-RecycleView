package id.ryan.myrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class GridHeroAdapter(val listHero: ArrayList<Hero>) :
	RecyclerView.Adapter<GridHeroAdapter.GridHeroHolder>() {
	
	private lateinit var onItemClickCallback: OnItemClickCallback
	
	fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
		this.onItemClickCallback = onItemClickCallback
	}
	
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridHeroHolder {
		val view: View =
			LayoutInflater.from(parent.context).inflate(R.layout.item_grid_hero, parent, false)
		return GridHeroHolder(view)
	}
	
	override fun onBindViewHolder(holder: GridHeroHolder, position: Int) {
		Glide.with(holder.itemView.context)
			.load(listHero[position].photo)
			.apply(RequestOptions().override(350, 550))
			.into(holder.imgPhoto)
		
		holder.itemView.setOnClickListener { onItemClickCallback.onItemClick(listHero[holder.adapterPosition]) }
	}
	
	override fun getItemCount(): Int {
		return listHero.size
	}
	
	inner class GridHeroHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
	}
	
	interface OnItemClickCallback {
		fun onItemClick(data: Hero)
	}
}