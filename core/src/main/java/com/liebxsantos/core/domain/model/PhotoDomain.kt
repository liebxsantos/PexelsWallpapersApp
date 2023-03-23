package com.liebxsantos.core.domain.model

data class PhotoDomain(
    var description: String? = "",
    var avgColor: String? = "",
    var id: Int? = 0,
    var photographer: String? = "",
    var photographerId: Int? = 0,
    var photographerUrl: String? = "",
    var srcDomain: SrcDomain?,
    var url: String? = "",
    @JvmField var imageBytes: ByteArray?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as PhotoDomain
        return imageBytes.contentEquals(other.imageBytes)
    }

    override fun hashCode(): Int {
        return imageBytes.contentHashCode()
    }
}
