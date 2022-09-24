package ru.geekbrains.auto.qa.autoqa.lesson2;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Books {
    private int id;
    private String Name;
    private String author;
    private int price;

}
