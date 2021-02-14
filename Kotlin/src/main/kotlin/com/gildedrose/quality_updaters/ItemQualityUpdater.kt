package com.gildedrose.quality_updaters

import com.gildedrose.Item

abstract class ItemQualityUpdater(protected val item: Item) {
    companion object {
        const val max_quality = 50
        const val min_quality = 0
    }

    protected open fun calculateNewQuality() = item.quality

    private fun age() {
        item.sellIn--
    }

    open fun updateQuality() {
        age()
        setQuality(calculateNewQuality())
    }

    private fun setQuality(quality: Int) {
        item.quality = quality.coerceIn(min_quality, max_quality)
    }
}