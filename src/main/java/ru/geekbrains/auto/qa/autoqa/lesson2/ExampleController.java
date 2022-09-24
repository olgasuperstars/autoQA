package ru.geekbrains.auto.qa.autoqa.lesson2;

import org.springframework.web.bind.annotation.*;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books")
public class ExampleController {
    Map<Integer, Books> data = new HashMap<>();

    @PostConstruct
    void init() {
        data.put(1, new Books(1, "Jack","Wolf", 18));
        data.put(2, new Books(2, "Alex","Adamson", 30));
        data.put(3, new Books(3, "Phil","Ball", 25));
    }

    @GetMapping("/{id}")
    public Books get(@PathVariable int id) {
        return data.get(id);
    }

    @GetMapping("/all")
    public List<Books> get() {
        return new ArrayList<>(data.values());
    }

    @PostMapping()
    public void save(@RequestBody Books books) {
        int id = data.size() + 1;
        books.setId(id);
        data.put(id, books);
    }

    @PutMapping("/{id}")
    public void change(@PathVariable int id, @RequestBody Books booksChanging) {
        Books books = data.get(id);
        books.setPrice(booksChanging.getPrice());
        books.setName(booksChanging.getName());
        books.setAuthor(booksChanging.getAuthor());
        data.put(id, books);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        data.remove(id);
    }

}
