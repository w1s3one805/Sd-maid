package eu.darken.sdmse.main.ui.settings

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.darken.sdmse.common.WebpageTool
import eu.darken.sdmse.common.coroutine.DispatcherProvider
import eu.darken.sdmse.common.uix.ViewModel2
import eu.darken.sdmse.common.upgrade.UpgradeRepo
import eu.darken.sdmse.setup.SetupManager
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    @Suppress("unused") val handle: SavedStateHandle,
    dispatcherProvider: DispatcherProvider,
    private val upgradeRepo: UpgradeRepo,
    private val setupManager: SetupManager,
    private val webpageTool: WebpageTool,
) : ViewModel2(dispatcherProvider) {

    val state = combine(
        upgradeRepo.upgradeInfo.map { it.isPro },
        setupManager.state.map { it.isComplete },
    ) { isPro, isSetUp ->
        State(
            isPro = isPro,
            setupDone = isSetUp,
        )
    }.asLiveData2()

    fun openWebsite(url: String) {
        webpageTool.open(url)
    }

    fun openUpgradeWebsite() {
        webpageTool.open(upgradeRepo.mainWebsite)
    }

    data class State(
        val isPro: Boolean,
        val setupDone: Boolean,
    )

}