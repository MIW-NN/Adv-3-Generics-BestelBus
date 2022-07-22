package model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vincent Velthuizen <v.r.velthuizen@pl.hanze.nl>
 * <p>
 * Dit is wat het programma doet.
 */
public class BestelBus<T extends Vervoerbaar> {

    private String naam;
    private int maximumGewicht;
    private List<T> lading;

    public BestelBus(String naam, int maximumGewicht) {
        this.naam = naam;
        this.maximumGewicht = maximumGewicht;
        this.lading = new ArrayList<>();
    }

    public void laadPakket(T teVervoerenItem) {
        if (teVervoerenItem.getGewicht() >= 0 &&
                getTotaalGewicht() + teVervoerenItem.getGewicht() <= maximumGewicht) {
            this.lading.add(teVervoerenItem);
        }
    }

    public List<T> getLading() {
        return lading;
    }

    public int getTotaalGewicht() {
        int totaalGewicht = 0;

        for (T geladenPakket : lading) {
            totaalGewicht += geladenPakket.getGewicht();
        }

        return totaalGewicht;
    }

    public T zoekZwaarste() {
        if (lading.size() <= 0) {
            return null;
        }
        T zwaarste = lading.get(0);

        for (T t : lading) {
            if (zwaarste.getGewicht() < t.getGewicht()) {
                zwaarste = t;
            }
        }

        return zwaarste;
    }

    public T zoekZwaarstePakket() {
        if (lading.size() <= 0) {
            return null;
        }
        return zoekZwaarstePakketVanaf(0);
    }

    // 10, 15, 8, 11
    // 10, (15, 8, 11)
    // 10, (15, (8, 11))
    // 10, (15, (8, (11)))
    // 10, (15, 11))
    // 10, 15
    // 15

    public T zoekZwaarstePakketVanaf(int index) {
        if (index >= lading.size()) {
            throw new IndexOutOfBoundsException("Zoveel pakketten heb ik niet, sorry.");
        } else if (index == lading.size() - 1) {
            return lading.get(index);
        } else {
            T zwaarstePakketAchterMe = zoekZwaarstePakketVanaf(index + 1);
            if (zwaarstePakketAchterMe.getGewicht() >= lading.get(index).getGewicht()) {
                return zwaarstePakketAchterMe;
            } else {
                return lading.get(index);
            }
        }

    }
}
