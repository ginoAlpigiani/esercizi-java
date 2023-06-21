package net.bcsoft.bcvinaino.entity;

public class Menu {
    private Long idMenu;
    private String focaccia;
    private double prezzo;

    public Menu(Long idMenu, String focaccia, double prezzo) {
        this.idMenu = idMenu;
        this.focaccia = focaccia;
        this.prezzo = prezzo;
    }

    public Long getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Long idMenu) {
        this.idMenu = idMenu;
    }

    public String getFocaccia() {
        return focaccia;
    }

    public void setFocaccia(String focaccia) {
        this.focaccia = focaccia;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }
}
