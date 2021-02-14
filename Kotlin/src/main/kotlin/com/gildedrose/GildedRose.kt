package com.gildedrose

import com.gildedrose.quality_updaters.updateQuality

class GildedRose(var items: Array<Item>) {

    fun updateQuality() {
        items.forEach(Item::updateQuality)
    }
}

