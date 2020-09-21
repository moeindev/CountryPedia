package ir.moeindeveloper.countrypedia.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import coil.ImageLoader
import com.jakewharton.rxbinding4.widget.textChangeEvents
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import ir.moeindeveloper.countrypedia.R
import ir.moeindeveloper.countrypedia.data.model.local.Country
import ir.moeindeveloper.countrypedia.databinding.FragmentHomeBinding
import ir.moeindeveloper.countrypedia.ui.adapter.CountryAdapter
import ir.moeindeveloper.countrypedia.util.network.State
import ir.moeindeveloper.countrypedia.util.ui.changeState
import ir.moeindeveloper.countrypedia.viewModel.CountryViewModel
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(),CountryAdapter.CountryCallback {

    private val viewModel by  viewModels<CountryViewModel>()

    private lateinit var binding: FragmentHomeBinding

    private lateinit var adapter: CountryAdapter

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        observeViewModel()

        binding.apply {
            homeLayout.root.setColorSchemeColors(ResourcesCompat.getColor(resources,R.color.colorPrimary,null),
                ResourcesCompat.getColor(resources,R.color.colorPrimaryDark,null),
                ResourcesCompat.getColor(resources,R.color.colorAccent,null)
            )
            adapter = CountryAdapter(this@HomeFragment,imageLoader)
            homeLayout.homeCountryList.layoutManager = LinearLayoutManager(requireContext())
            homeLayout.homeCountryList.adapter = adapter
            homeLayout.root.setOnRefreshListener {
                loadData(false)
            }

            homeLayout.homeSearchTEdit.textChangeEvents()
                .skipInitialValue()
                .debounce(300, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    adapter.filter.filter(it.text)
                }
        }
        return binding.root
    }

    private fun observeViewModel(){
        viewModel.countries.observe(viewLifecycleOwner, {res->
            binding.changeState(res.status)
            when(res.status){
                State.LOADING -> {
                    //do nothing here
                }

                State.SUCCESS -> {
                    res.data?.let { list ->
                        adapter.updateCountries(list)
                    }
                }

                State.ERROR -> {
                    res.MessageID?.let {msg->
                        Toast.makeText(requireContext(),msg,Toast.LENGTH_LONG).show()
                    }
                }
            }
        })

    }


    override fun onSelect(country: Country) {
        //TODO country selected
    }

    private fun loadData(emitLoading: Boolean) = viewModel.loadCountries(emitLoading)

    override fun onResume() {
        super.onResume()
        loadData(true)
    }
}