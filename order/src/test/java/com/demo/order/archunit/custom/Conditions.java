package com.demo.order.archunit.custom;

import com.tngtech.archunit.core.domain.JavaField;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;

public class Conditions {

	public static ArchCondition<JavaField> haveANameEndingWith(final String suffix) {

		return new ArchCondition<JavaField>( "have a name ending with \"" + suffix + "\"" ) {
			@Override
			public void check(JavaField field, ConditionEvents events) {
				boolean satisfied = field.getName().endsWith( suffix );
				String message = String.format( "Field %s.%s has a name%s ending with \"" + suffix + "\"",
						field.getOwner().getName(), field.getName(), satisfied ? "" : " not" );
				events.add( new SimpleConditionEvent( field, satisfied, message ) );
			}
		};
	}
}
