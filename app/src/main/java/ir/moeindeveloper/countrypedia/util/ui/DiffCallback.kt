package ir.moeindeveloper.countrypedia.util.ui

import androidx.recyclerview.widget.DiffUtil
import ir.moeindeveloper.countrypedia.data.model.local.Country


class CountryDiffCallback : DiffUtil.ItemCallback<Country>() {

    override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
        return oldItem.id== newItem.id
    }

    override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
        return oldItem == newItem
    }

}