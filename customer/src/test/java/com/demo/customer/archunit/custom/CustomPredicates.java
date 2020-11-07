package com.demo.customer.archunit.custom;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaField;

import javax.inject.Inject;

public class CustomPredicates {

	public static DescribedPredicate<JavaField> areFeatureFlags() {

		return new DescribedPredicate<JavaField>( "are feature flag fields" ) {
			@Override
			public boolean apply(JavaField input) {
				boolean featureFlag = input.getRawType().isAssignableTo(Boolean.class)
						&& input.getFullName().contains("Enabled")
						&& input.isAnnotatedWith(Inject.class);

				return featureFlag;
			}
		};
	}
}
