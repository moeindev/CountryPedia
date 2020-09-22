package ir.moeindeveloper.countrypedia

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.objectbox.BoxStore
import ir.moeindeveloper.countrypedia.data.local.preference.AppConfig
import ir.moeindeveloper.countrypedia.data.remote.CountryApiHelper
import ir.moeindeveloper.countrypedia.data.repository.CountryRepository
import ir.moeindeveloper.countrypedia.util.network.Resource
import ir.moeindeveloper.countrypedia.util.network.State
import ir.moeindeveloper.countrypedia.utils.MainCoroutineRule
import ir.moeindeveloper.countrypedia.viewModel.CountryViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class CountryViewModelTest {
    @Rule
    @JvmField
    val instanceTaskExecutorRule  = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @RelaxedMockK
    lateinit var repository: CountryRepository

    @RelaxedMockK
    lateinit var viewModel: CountryViewModel

    @Before
    fun before(){
        MockKAnnotations.init(this)

        viewModel = CountryViewModel(repository)
    }


    @Test
    fun given_loading_state() {
        //Given:
        coEvery { repository.getCountries() } returns flow { emit(Resource.loading(null)) }
        viewModel.countries.observeForever {  }

        //when:
        viewModel.loadCountries()


        //expecting:
        assertThat(viewModel.countries.value?.status).isEqualTo(State.LOADING)
    }

    @Test
    fun given_error_state() {
        //Given:
        coEvery { repository.getCountries() } returns flow { emit(Resource.error(data = null)) }
        viewModel.countries.observeForever {  }

        //when:
        viewModel.loadCountries()


        //expecting:
        assertThat(viewModel.countries.value?.status).isEqualTo(State.ERROR)
    }

    @Test
    fun given_success_response() {
        //Given:
        coEvery { repository.getCountries() } returns flow { emit(Resource.success(data = null)) }
        viewModel.countries.observeForever {  }

        //when:
        viewModel.loadCountries()


        //expecting:
        assertThat(viewModel.countries.value?.status).isEqualTo(State.SUCCESS)
    }

}