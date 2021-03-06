package ran.singletondev.weather_mvvm

import android.arch.lifecycle.MutableLiveData
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner
import ran.singletondev.weather_mvvm.common.domain.interactor.ForecastInteractor
import ran.singletondev.weather_mvvm.common.domain.model.LoadForecastDownloader
import ran.singletondev.weather_mvvm.common.domain.model.LoadForecastRepository
import ran.singletondev.weather_mvvm.common.viewmodel.Response
import ran.singletondev.weather_mvvm.dashboard.ForecastActivity
import ran.singletondev.weather_mvvm.dashboard.ForecastViewModel
import ran.singletondev.weather_mvvm.network.ApiService
import ran.singletondev.weather_mvvm.rx.SchedulersFacade


/**
 * Created by ran on 5/22/18.
 */

@Rule var mockitoRule = MockitoJUnit.rule()

@RunWith(MockitoJUnitRunner::class)
class ApiServiceTest {


    private lateinit var schedulersFacade : SchedulersFacade

    private lateinit var forecaseViewModel : ForecastViewModel

    private lateinit var forecastRepository: LoadForecastRepository

    private lateinit var apiService: ApiService

    private lateinit var forecastInteractor: ForecastInteractor

    private lateinit var forecastActivity : ForecastActivity

    companion object {
        @BeforeClass
        @Throws(Exception::class)
        fun onlyOnce(){

        }
    }

    @Before
    @Throws(Exception::class)
    fun setUp(){

        apiService = mock(ApiService::class.java)


        schedulersFacade = SchedulersFacade()

        forecastRepository = LoadForecastDownloader(apiService)

        forecastInteractor = ForecastInteractor(forecastRepository)

        forecaseViewModel = ForecastViewModel(forecastInteractor,schedulersFacade)

//        forecastActivity = mock(ForecastActivity::class.java)



    }

    @Test
    fun testServer(){
        val response = MutableLiveData<Response>()

        verify(forecaseViewModel).loadDataForecast("kasihan")

    }






}