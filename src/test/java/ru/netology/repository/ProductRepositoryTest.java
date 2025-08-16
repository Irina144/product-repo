package ru.netology.repository;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Product;
import ru.netology.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.*;

public class ProductRepositoryTest {

    @Test
    void shouldRemoveExistingItem() {
        ProductRepository repo = new ProductRepository();
        Product p1 = new Product(1, "A", 100);
        Product p2 = new Product(2, "B", 200);
        Product p3 = new Product(3, "C", 300);

        repo.save(p1);
        repo.save(p2);
        repo.save(p3);

        repo.removeById(2);

        Product[] expected = {p1, p3};
        Product[] actual = repo.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldThrowNotFoundWhenRemovingNonExisting() {
        ProductRepository repo = new ProductRepository();
        Product p1 = new Product(1, "A", 100);
        Product p2 = new Product(2, "B", 200);

        repo.save(p1);
        repo.save(p2);

        NotFoundException ex = assertThrows(
                NotFoundException.class,
                () -> repo.removeById(3) // id=3 нет
        );
        assertEquals("Element with id: 3 not found", ex.getMessage());
    }
}
