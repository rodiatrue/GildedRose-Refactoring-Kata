package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            if (!items[i].name.equals("Aged Brie")
                    && !items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                if (items[i].quality > 0) {
                    if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                        decrementQuality(i);
                    }
                }
            } else {
                if (items[i].quality < 50) {
                    incrementQuality(i);

                    if (items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (items[i].sellIn < 11) {
                            if (items[i].quality < 50) {
                                incrementQuality(i);
                            }
                        }

                        if (items[i].sellIn < 6) {
                            if (items[i].quality < 50) {
                                incrementQuality(i);
                            }
                        }
                    }
                }
            }

            if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                decrementSellIn(i);
            }

            if (items[i].sellIn < 0) {
                if (!items[i].name.equals("Aged Brie")) {
                    if (!items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (items[i].quality > 0) {
                            if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                                decrementQuality(i);
                            }
                        }
                    } else {
                        resetQuality(i);
                    }
                } else {
                    if (items[i].quality < 50) {
                        incrementQuality(i);
                    }
                }
            }
        }
    }

    private void incrementQuality(int i)
    {
        items[i].quality = items[i].quality + 1;
    }

    private void decrementQuality(int i)
    {
        items[i].quality = items[i].quality - 1;
    }

    private void decrementSellIn(int i)
    {
        items[i].sellIn = items[i].sellIn - 1;
    }

    private void resetQuality(int i)    {
        items[i].quality = items[i].quality - items[i].quality;
    }
}
