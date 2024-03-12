package com.findmyip




import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.findmyip.domain.IpRepository
import com.findmyip.domain.model.IpInfo
import com.findmyip.presentation.viewmodel.IpViewModel
import com.google.gson.Gson
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import java.io.IOException
import javax.inject.Inject

@HiltAndroidTest
class IpViewModelTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var ipRepository: IpRepository

    @get:Rule
    val rule = InstantTaskExecutorRule()
    val gson = Gson()
    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun fetchIpInfoSuccess() = runBlocking {

        val mockIpInfo = gson.fromJson<IpInfo>(jsonStringResponse, IpInfo::class.java)

        Mockito.`when`(ipRepository.getIpInfo()).thenReturn(mockIpInfo)

        val viewModel = IpViewModel(ipRepository)
        viewModel.fetchIpInfo()

       // assertEquals(mockIpInfo, viewModel.ipInfo.value)
        assertNull(viewModel.error.value)
        assertFalse(viewModel.loading.value!!)
    }

    @Test
    fun fetchIpInfoFailure() = runBlocking {
        val errorMessage = "Network error"
        Mockito.`when`(ipRepository.getIpInfo()).thenAnswer {
            throw IOException(errorMessage)
        }

        val viewModel = IpViewModel(ipRepository)
        viewModel.fetchIpInfo()

        assertNull(viewModel.ipInfo.value)
        assertEquals(errorMessage, viewModel.error.value)
        assertFalse(viewModel.loading.value!!)
    }

    companion object{
        val jsonStringResponse = "{\n" +
                "    \"ip\": \"2405:201:2001:2988:4ded:3c8a:1d75:573f\",\n" +
                "    \"network\": \"2405:201:2001::/49\",\n" +
                "    \"version\": \"IPv6\",\n" +
                "    \"city\": \"Ahmedabad\",\n" +
                "    \"region\": \"Gujarat\",\n" +
                "    \"region_code\": \"GJ\",\n" +
                "    \"country\": \"IN\",\n" +
                "    \"country_name\": \"India\",\n" +
                "    \"country_code\": \"IN\",\n" +
                "    \"country_code_iso3\": \"IND\",\n" +
                "    \"country_capital\": \"New Delhi\",\n" +
                "    \"country_tld\": \".in\",\n" +
                "    \"continent_code\": \"AS\",\n" +
                "    \"in_eu\": false,\n" +
                "    \"postal\": \"380001\",\n" +
                "    \"latitude\": 23.0276,\n" +
                "    \"longitude\": 72.5871,\n" +
                "    \"timezone\": \"Asia/Kolkata\",\n" +
                "    \"utc_offset\": \"+0530\",\n" +
                "    \"country_calling_code\": \"+91\",\n" +
                "    \"currency\": \"INR\",\n" +
                "    \"currency_name\": \"Rupee\",\n" +
                "    \"languages\": \"en-IN,hi,bn,te,mr,ta,ur,gu,kn,ml,or,pa,as,bh,sat,ks,ne,sd,kok,doi,mni,sit,sa,fr,lus,inc\",\n" +
                "    \"country_area\": 3287590.0,\n" +
                "    \"country_population\": 1352617328,\n" +
                "    \"asn\": \"AS55836\",\n" +
                "    \"org\": \"Reliance Jio Infocomm Limited\"\n" +
                "}"
    }
}
