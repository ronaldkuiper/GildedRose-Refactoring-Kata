package com.gildedrose.quality_updaters

import com.gildedrose.*

fun Item.updateQuality() {

    val updater = when(this.name) {
        "Backstage passes to a TAFKAL80ETC concert" -> ConcertQualityUpdater(this)
        "Sulfuras, Hand of Ragnaros" -> LegendaryProductQualityUpdater(this)
        "Aged Brie" -> AgedQualityUpdater(this)
        else -> NormalProductQualityUpdater(this)
    }

    updater.updateQuality()
}