package fr.istic.pdl.ticpbackend.dto;

import fr.istic.pdl.ticpbackend.model.Tableau;

import java.util.List;

public class TableauDto {
    private int id;
    private int rang;
    private List<TourDto> tours;

    public TableauDto(int id, int rang, List<TourDto> tours) {
        this.id=id;
        this.rang=rang;
        this.tours = tours;
    }

    public TableauDto() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRang() {
        return rang;
    }

    public void setRang(int rang) {
        this.rang = rang;
    }

    public List<TourDto> getTours() {
        return tours;
    }

    public void setTours(List<TourDto> tours) {
        this.tours = tours;
    }
}
