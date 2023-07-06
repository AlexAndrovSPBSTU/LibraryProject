package ru.alexandrov.springcourse.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.alexandrov.springcourse.models.Reader;
import ru.alexandrov.springcourse.services.ReadersService;

@Component
public class ReaderValidator implements Validator {
    private final ReadersService readersDAO;

    @Autowired
    public ReaderValidator(ReadersService readersDAO) {
        this.readersDAO = readersDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Reader.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Reader reader = (Reader) target;
        if (readersDAO.findReaderByName(reader.getName()).isPresent()) {
            errors.rejectValue("name", "", "Reader with this name is already exist");
        }
    }
}
