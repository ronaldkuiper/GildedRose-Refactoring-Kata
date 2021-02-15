package com.gildedrose.quality_updaters

import com.gildedrose.*

fun Item.updateQuality() {

    val updater = when(this.name) {
        "Backstage passes to a TAFKAL80ETC concert" -> ConcertQualityUpdater(this)
        "Sulfuras, Hand of Ragnaros" -> LegendaryProductQualityUpdater(this)
        "Aged Brie" -> AgedQualityUpdater(this)
        "Conjured Mana Cake" -> NormalProductQualityUpdater(this, 2)
        else -> NormalProductQualityUpdater(this)
    }

    updater.updateQuality()
}