package be.technifutur.hotel_management.models.forms;

import lombok.Data;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
// Mettre les validations sur les propriétés ne suffit pas, la classe doit aussi être annotée Validated
// Enfin, dans mon ManagerForm, dans la méthode insert, je spécifie en paramètre que le paramètre form doit être @Valid
@Validated
public class ManagerForm {

    // Une fois la dépendance validation configurée dans le pom.xml,
    // je peux spécifiquer que je veux absolument que le gérant aie un nom avec @NonNull,
    // je ne veux pas qu'il soit vide non plus avec @NotBlank
    // Finalement, pas besoin de NonNull car NotBlank le spécifie
    // Je veux aussi une taille min de 2 caractères et max de 50
//    @NonNull
    @NotBlank
    @Size(min = 2, max = 50)
    private String name;
    private String surname;
    // Pour la carrière, je ne veux pas que le user puisse entrer une date du futur
    @PastOrPresent
    private LocalDate beginCareerOn;

}
