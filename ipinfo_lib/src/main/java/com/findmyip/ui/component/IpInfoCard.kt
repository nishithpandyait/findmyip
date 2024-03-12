package com.findmyip.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Cake
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.LocationCity
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.MoneyOff
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.PinDrop
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Room
import androidx.compose.material.icons.filled.Terrain
import androidx.compose.material.icons.filled.Traffic
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material.icons.filled.WhereToVote
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.material.icons.filled.WorkOutline
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.findmyip.domain.model.IpInfo

@Composable
fun IpInfoCard(ipInfo: IpInfo, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),

    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState()).weight(weight = 1f, fill = false)
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            IpInfoItem(icon = Icons.Default.Place, label = "IP", value = ipInfo.ip.orEmpty())
            IpInfoItem(icon = Icons.Default.Terrain, label = "Network", value = ipInfo.network.orEmpty())
            IpInfoItem(icon = Icons.Default.Visibility, label = "Version", value = ipInfo.version.orEmpty())
            IpInfoItem(icon = Icons.Default.LocationCity, label = "City", value = ipInfo.city.orEmpty())
            IpInfoItem(icon = Icons.Default.Room, label = "Region", value = ipInfo.region.orEmpty())
            IpInfoItem(icon = Icons.Default.PinDrop, label = "Region Code", value = ipInfo.regionCode.orEmpty())
            IpInfoItem(icon = Icons.Default.Public, label = "Country", value = ipInfo.country.orEmpty())
            IpInfoItem(icon = Icons.Default.Traffic, label = "Country Name", value = ipInfo.countryName.orEmpty())
            IpInfoItem(icon = Icons.Default.WhereToVote, label = "Postal Code", value = ipInfo.postal.orEmpty())
            IpInfoItem(icon = Icons.Default.WbSunny, label = "Latitude", value = ipInfo.latitude?.toString().orEmpty())
            IpInfoItem(icon = Icons.Default.TrendingUp, label = "Longitude", value = ipInfo.longitude?.toString().orEmpty())
            IpInfoItem(icon = Icons.Default.Wifi, label = "Timezone", value = ipInfo.timezone.orEmpty())
            IpInfoItem(icon = Icons.Default.WorkOutline, label = "UTC Offset", value = ipInfo.utcOffset.orEmpty())
            IpInfoItem(icon = Icons.Default.Phone, label = "Country Calling Code", value = ipInfo.countryCallingCode.orEmpty())
            IpInfoItem(icon = Icons.Default.Money, label = "Currency", value = ipInfo.currency.orEmpty())
            IpInfoItem(icon = Icons.Default.MoneyOff, label = "Currency Name", value = ipInfo.currencyName.orEmpty())
            IpInfoItem(icon = Icons.Default.Language, label = "Languages", value = ipInfo.languages.orEmpty())
            IpInfoItem(icon = Icons.Default.Cake, label = "Country Area", value = ipInfo.countryArea?.toString().orEmpty())
            IpInfoItem(icon = Icons.Default.People, label = "Country Population", value = ipInfo.countryPopulation?.toString().orEmpty())
            IpInfoItem(icon = Icons.Default.Explore, label = "ASN", value = ipInfo.asn.orEmpty())
            IpInfoItem(icon = Icons.Default.Business, label = "Organization", value = ipInfo.org.orEmpty())
        }
    }
}

@Composable
fun IpInfoItem(icon: ImageVector, label: String, value: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = icon, contentDescription = null, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(text = label, style = MaterialTheme.typography.bodyLarge)
            Text(text = value, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
