package ir.moeindeveloper.countrypedia.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.load
import coil.request.CachePolicy
import ir.moeindeveloper.countrypedia.R
import ir.moeindeveloper.countrypedia.data.model.local.Country
import ir.moeindeveloper.countrypedia.databinding.ItemCountryBinding
import ir.moeindeveloper.countrypedia.util.ui.CountryDiffCallback
import java.util.*

class CountryAdapter(private val callback: CountryCallback, private val imgLoader: ImageLoader): RecyclerView.Adapter<CountryAdapter.CountryViewHolder>(),Filterable {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val binding = ItemCountryBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return CountryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bindCountry(filteredItems.currentList[position])
    }

    override fun getItemCount(): Int = filteredItems.currentList.size

    private var items: List<Country> = listOf()

    private var filteredItems: AsyncListDiffer<Country> = AsyncListDiffer(this,CountryDiffCallback())

    fun updateCountries(cities: List<Country>) {
        items = cities
        filteredItems.submitList(items)
    }

    inner class CountryViewHolder(private val binding: ItemCountryBinding): RecyclerView.ViewHolder(binding.root) {

        init {
            binding.apply {
                root.setOnClickListener {
                    //call the callback
                    callback.onSelect(filteredItems.currentList[adapterPosition])
                }
            }
        }

        fun bindCountry(country: Country) {
            binding.apply {
                itemHomeTitle.text = country.name
                itemHomeRegion.text = country.region
                itemHomeImage.load(country.flag,imgLoader){
                    crossfade(true)
                    placeholder(R.drawable.ic_app_icon)
                    error(R.drawable.ic_broken_photo)
                    diskCachePolicy(CachePolicy.ENABLED)
                }
            }
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                val charString = p0.toString()

                val filtered = if (charString.isEmpty()) {
                    items
                } else {
                    items.filter { country -> country.name?.toLowerCase(Locale.ROOT)?.contains(charString.toLowerCase(Locale.ROOT)) ?: false }
                }

                val results = FilterResults()
                results.values = filtered

                return results
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                if (p1?.values is List<*>) filteredItems.submitList(p1.values as List<Country>)
            }
        }
    }

    interface CountryCallback {
        fun onSelect(country: Country)
    }
}