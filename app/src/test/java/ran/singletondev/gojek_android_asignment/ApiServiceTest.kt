package ran.singletondev.gojek_android_asignment

import android.arch.lifecycle.MutableLiveData
import io.reactivex.Single
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnit
import ran.singletondev.gojek_android_asignment.common.domain.interactor.ForecastInteractor
import ran.singletondev.gojek_android_asignment.network.ApiService
import javax.inject.Inject
import org.mockito.junit.MockitoJUnitRunner
import ran.singletondev.gojek_android_asignment.common.domain.model.Forecast
import ran.singletondev.gojek_android_asignment.common.domain.model.LoadForecastDownloader
import ran.singletondev.gojek_android_asignment.common.domain.model.LoadForecastRepository
import ran.singletondev.gojek_android_asignment.common.viewmodel.Response
import ran.singletondev.gojek_android_asignment.dashboard.ForecastActivity
import ran.singletondev.gojek_android_asignment.dashboard.ForecastViewModel
import ran.singletondev.gojek_android_asignment.rx.SchedulersFacade


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