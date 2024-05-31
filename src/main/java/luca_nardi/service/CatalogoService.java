package luca_nardi.service;


import luca_nardi.models.ElementoCatalogo;
import luca_nardi.models.Libro;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CatalogoService {
    private List<ElementoCatalogo> catalogo;

    public CatalogoService() {
        catalogo = new ArrayList<>();
    }

    public void aggiungiElemento(ElementoCatalogo elemento) {
        catalogo.add(elemento);
    }

    public void rimuoviElemento(String isbn) {
        catalogo.removeIf(e -> e.getIsbn().equals(isbn));
    }

    public Optional<ElementoCatalogo> ricercaPerIsbn(String isbn) {
        return catalogo.stream()
                .filter(e -> e.getIsbn().equals(isbn))
                .findFirst();
    }

    public List<ElementoCatalogo> ricercaPerAnno(int annoPubblicazione) {
        return catalogo.stream()
                .filter(e -> e.getAnnoPubblicazione() == annoPubblicazione)
                .collect(Collectors.toList());
    }

    public List<Libro> ricercaPerAutore(String autore) {
        return catalogo.stream()
                .filter(e -> e instanceof Libro)
                .map(e -> (Libro) e)
                .filter(l -> l.getAutore().equalsIgnoreCase(autore))
                .collect(Collectors.toList());
    }

    public void salvaCatalogoSuDisco(String filePath) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(catalogo);
        }
    }

    public List<ElementoCatalogo> caricaCatalogoDaDisco(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (List<ElementoCatalogo>) ois.readObject();
        }
    }

    public List<ElementoCatalogo> getCatalogo() {
        return catalogo;
    }
}