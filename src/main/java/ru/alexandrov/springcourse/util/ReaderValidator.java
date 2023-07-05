package ru.alexandrov.springcourse.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.alexandrov.springcourse.dao.ReadersDAO;
import ru.alexandrov.springcourse.models.Reader;

@Component
public class ReaderValidator implements Validator {
    private final ReadersDAO readersDAO;

    @Autowired
    public ReaderValidator(ReadersDAO readersDAO) {
        this.readersDAO = readersDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Reader.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Reader reader = (Reader) target;
        if (readersDAO.show(reader.getName()).isPresent()){
            errors.rejectValue("name","","Reader with this name is already exist");
        }
    }
}
