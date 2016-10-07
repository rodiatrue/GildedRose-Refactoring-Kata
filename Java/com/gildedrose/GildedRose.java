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
                applyNegativeQuality(0, "Sulfuras, Hand of Ragnaros", i);
            } else {
                if (filterQuality(i, 50)) {
                    if (items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        filterSellIn(i, 11, 50);
                        filterSellIn(i, 6, 50);
                    }
                }
            }

            if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                decrementSellIn(i);
            }

            if (items[i].sellIn < 0) {
                if (!items[i].name.equals("Aged Brie")) {
                    if (!items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        applyNegativeQuality(0, "Sulfuras, Hand of Ragnaros", i);
                    } else {
                        resetQuality(i);
                    }
                } else {
                    filterQuality(i, 50);
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

    private void applyDecrementQuality(String name, int i)
    {
        if (!items[i].name.equals(name)) {
            decrementQuality(i);
        }
    }

    private boolean filterQuality(int i, int filter)
    {
        if (items[i].quality < filter) {
            incrementQuality(i);

            return true;
        }

        return false;
    }

    private void filterSellIn(int i, int filter, int filterForQuality)
    {
        if (items[i].sellIn < filter) {
            filterQuality(i, filterForQuality);
        }
    }

    private void applyNegativeQuality(int filter, String name, int i)
    {
        if (items[i].quality > filter) {
            applyDecrementQuality(name, i);
        }
    }
}
