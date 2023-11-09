package spring_app.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import spring_app.models.Person;
import spring_app.repositories.PeopleRepository;

@Component
public class PersonValidator implements Validator {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PersonValidator(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        if (peopleRepository.findByName(person.getName()).isPresent()){
            errors.rejectValue("name", "", "Person with such name is already exists!");
        }

/*        if (personDAO.show(person.getEmail()).isPresent()) {
            //если человек вернулся с таким же email то помещаем его в лист ошибок
            // в начале указываем, над каким полем произошла ошибка, далее код ошибки, далее сообщение ошибки
            // .isPresent() у Optional проверяет, есть ли вернувшийся объект или нет
            errors.rejectValue("email", "", "This email is already taken");
        }*/
    }
}
