package org.miro.widget.unit.domain;

import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.rule.impl.GetterMustExistRule;
import com.openpojo.validation.rule.impl.SetterMustExistRule;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;
import org.junit.jupiter.api.Test;
import org.miro.widget.domain.Coordinate;
import org.miro.widget.domain.MiroWidget;

public class MiroWidgetTest {

    @Test
    public void miroWidget() {
        PojoClass pojoClass = PojoClassFactory.getPojoClass(MiroWidget.class);
        Validator validator = ValidatorBuilder.create().with(new GetterMustExistRule())
                .with(new SetterMustExistRule())
                .with(new GetterTester())
                .with(new SetterTester())
                .build();
        validator.validate(pojoClass);
    }
}
