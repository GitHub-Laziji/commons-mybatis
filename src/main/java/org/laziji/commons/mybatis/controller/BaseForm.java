package org.laziji.commons.mybatis.controller;

import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public abstract class BaseForm {

    private static Validator validator = Validation.byProvider(HibernateValidator.class).configure().failFast(true).buildValidatorFactory().getValidator();

    public boolean validationData(){
        Set<ConstraintViolation<BaseForm>> constraintViolations = validator.validate(this);
        return constraintViolations.size() == 0;
    }

}
