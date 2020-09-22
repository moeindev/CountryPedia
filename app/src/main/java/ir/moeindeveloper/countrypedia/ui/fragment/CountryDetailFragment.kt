package ir.moeindeveloper.countrypedia.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import coil.ImageLoader
import coil.load
import coil.request.CachePolicy
import dagger.hilt.android.AndroidEntryPoint
import ir.moeindeveloper.countrypedia.R
import ir.moeindeveloper.countrypedia.data.local.Constants
import ir.moeindeveloper.countrypedia.databinding.FragmentCountryDetailBinding
import ir.moeindeveloper.countrypedia.util.toStringNumber
import javax.inject.Inject

@AndroidEntryPoint
class CountryDetailFragment : Fragment() {

    private lateinit var binding: FragmentCountryDetailBinding

    private val args: CountryDetailFragmentArgs by navArgs()

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCountryDetailBinding.inflate(inflater,container,false)

        postponeEnterTransition()
        binding.detailImage.load(args.country.flag,imageLoader) {
            crossfade(false)
            allowHardware(false)
            placeholder(R.drawable.ic_app_icon)
            error(R.drawable.ic_broken_photo)
            //diskCachePolicy(CachePolicy.ENABLED)
            listener { _, _ ->
                startPostponedEnterTransition()
            }
        }
        binding.detailTitle.text = args.country.name
        binding.detailCapital.text = "${getString(R.string.detail_capital)} ${args.country.capital}"
        binding.detailCode.text = "${getString(R.string.detail_code)} ${args.country.numericCode}"
        binding.detailPopulation.text = "${getString(R.string.detail_population)} ${args.country.population?.toStringNumber()}"
        // Inflate the layout for this fragment
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            ViewCompat.setTransitionName(detailImage,
                Constants.SharedElements.getTransitionName(Constants.SharedElements.image,args.country.id))
            ViewCompat.setTransitionName(detailTitle,
                Constants.SharedElements.getTransitionName(Constants.SharedElements.title,args.country.id))
        }
    }

}