package ir.moeindeveloper.countrypedia.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import coil.ImageLoader
import com.jakewharton.rxbinding4.widget.textChangeEvents
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import ir.moeindeveloper.countrypedia.R
import ir.moeindeveloper.countrypedia.core.intent.UserIntent
import ir.moeindeveloper.countrypedia.core.state.CountryViewState
import ir.moeindeveloper.countrypedia.data.local.Constants
import ir.moeindeveloper.countrypedia.data.model.local.Country
import ir.moeindeveloper.countrypedia.data.model.view.SharedElement
import ir.moeindeveloper.countrypedia.databinding.FragmentHomeBinding
import ir.moeindeveloper.countrypedia.ui.adapter.CountryAdapter
import ir.moeindeveloper.countrypedia.core.state.State
import ir.moeindeveloper.countrypedia.core.view.CountryView
import ir.moeindeveloper.countrypedia.util.ui.changeState
import ir.moeindeveloper.countrypedia.viewModel.CountryViewModel
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(),CountryAdapter.CountryCallback, CountryView<CountryViewState<List<Country>>> {



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

            noConnectionLayout.tryAgainButton.setOnClickListener {
                loadData(true)
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
        viewModel.state.observe(viewLifecycleOwner, {res->
            render(res)
        })


    }

    override fun render(state: CountryViewState<List<Country>>) {
        binding.changeState(state.status)
        when(state.status){
            State.LOADING -> {
                //do nothing here
            }

            State.SUCCESS -> {
                state.data?.let { list ->
                    adapter.updateCountries(list)
                }
            }

            State.ERROR -> {
                state.MessageID?.let {msg->
                    Toast.makeText(requireContext(),msg,Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.homeLayout.homeCountryList.apply {
            postponeEnterTransition()
            viewTreeObserver.addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }
        }
        loadData(true)
    }
    override fun onSelect(country: Country, sharedElement: SharedElement) {
        val directions = HomeFragmentDirections.detailFragment(country)
        val extras = FragmentNavigatorExtras(
            sharedElement.imageView to Constants.SharedElements.getTransitionName(Constants.SharedElements.image,country.id),
            sharedElement.titleView to Constants.SharedElements.getTransitionName(Constants.SharedElements.title,country.id)
        )
        findNavController().navigate(directions,extras)
    }

    private fun loadData(emitLoading: Boolean) {
        lifecycleScope.launch {
            when(emitLoading){
                true -> viewModel.intents.send(UserIntent.fetchCountries)
                false -> viewModel.intents.send(UserIntent.realoadCountries)
            }
        }
    }
}