package com.kekulta.events.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@JvmInline
@Parcelize
@Serializable
value class Avatar(val url: String?): Parcelable