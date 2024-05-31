package luca_nardi;

import luca_nardi.models.Libro;
import luca_nardi.models.Rivista;
import luca_nardi.service.CatalogoService;

public class Application {
    public static void main(String[] args) {
        CatalogoService catalogoService = new CatalogoService();

        Libro libro1 = new Libro("9788880336181", "Il diario di una schiappa: la dura verità", 2012, 220, "Jeff Kinney", "Comics");
        Rivista rivista1 = new Rivista("9788863555578", "National Geographic", 2023, 150, Rivista.Periodicita.MENSILE);

        catalogoService.aggiungiElemento(libro1);
        catalogoService.aggiungiElemento(rivista1);

        System.out.println("Ricerca per ISBN:");
        catalogoService.ricercaPerIsbn("9788880336181").ifPresent(System.out::println);

        System.out.println("\nRicerca per anno di pubblicazione:");
        catalogoService.ricercaPerAnno(2023).forEach(System.out::println);

        System.out.println("\nRicerca per autore:");
        catalogoService.ricercaPerAutore("Jeff Kinney").forEach(System.out::println);
    }
}
