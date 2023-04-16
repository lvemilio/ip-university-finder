package com.ipuniversityfinder.eurofins.validation;

import com.neovisionaries.i18n.CountryCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.InetAddressValidator;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@Slf4j
public class ParamValidator {
    InetAddressValidator ipValidator = new InetAddressValidator();

    public boolean validateIp(String ip){
        return ipValidator.isValid(ip);
    }

    public boolean validateCountry(String country){
        for (String countryCode : Locale.getISOCountries()) {
            Locale locale = new Locale("", countryCode);
            String name = locale.getDisplayCountry();
            if (name.equalsIgnoreCase(country)) {
                return true;
            }
        }
        return false;
    }

}
