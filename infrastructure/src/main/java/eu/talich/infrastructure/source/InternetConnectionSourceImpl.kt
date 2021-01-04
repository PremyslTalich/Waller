package eu.talich.infrastructure.source

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import eu.talich.data.source.InternetConnectionSource
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlin.coroutines.CoroutineContext

@FlowPreview
@ExperimentalCoroutinesApi
class InternetConnectionSourceImpl(
    context: Context
) : InternetConnectionSource, CoroutineScope {
    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    var connectivityManager: ConnectivityManager? =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private val internetAvailability = ConflatedBroadcastChannel<Boolean>(false)

    init {
        connectivityManager?.registerDefaultNetworkCallback(object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                launch {
                    internetAvailability.send(true)
                }
            }

            override fun onLost(network: Network) {
                launch {
                    internetAvailability.send(false)
                }
            }
        })
    }

    override suspend fun observeInternetConnection(): Flow<Boolean> {
        return internetAvailability.asFlow()
    }
}
