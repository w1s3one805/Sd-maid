package eu.darken.sdmse.common.upgrade.core.billing

import android.content.Context
import eu.darken.sdmse.R
import eu.darken.sdmse.common.error.HasLocalizedError
import eu.darken.sdmse.common.error.LocalizedError

class GplayServiceUnavailableException(cause: Throwable) :
    BillingException("Google Play services are unavailable.", cause), HasLocalizedError {

    override fun getLocalizedError(context: Context): LocalizedError = LocalizedError(
        throwable = this,
        label = "Google Play Services Unavailable",
        description = context.getString(R.string.upgrades_gplay_unavailable_error)
    )

}