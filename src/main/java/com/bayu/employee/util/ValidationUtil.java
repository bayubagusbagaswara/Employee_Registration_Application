package com.bayu.employee.util;

import com.bayu.employee.payload.employee.UpdateEmployeeRequest;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class ValidationUtil {
//    validationChecksForDataUpdateRequests
    public static void validationChecksForEmployeeUpdateRequests(UpdateEmployeeRequest updateEmployeeRequest, BindingResult bindingResult) {
        if (updateEmployeeRequest.getPosition() == null || updateEmployeeRequest.getPosition().equals("")) {
            bindingResult.addError(new FieldError("updateEmployeeRequest", "position", "Posisi wajib diisi."));
        }

        if (updateEmployeeRequest.getNik() == null || updateEmployeeRequest.getNik().equals("")) {
            bindingResult.addError(new FieldError("updateEmployeeRequest", "nik", "NIK wajib diisi."));
        }

        if (updateEmployeeRequest.getFirstName() == null || updateEmployeeRequest.getFirstName().equals("")) {
            bindingResult.addError(new FieldError("updateEmployeeRequest", "firstName", "Nama Depan wajib diisi."));
        }

        if (updateEmployeeRequest.getLastName() == null || updateEmployeeRequest.getLastName().equals("")) {
            bindingResult.addError(new FieldError("updateEmployeeRequest", "lastName", "Nama Belakang wajib diisi."));
        }

        if (updateEmployeeRequest.getGender() == null || updateEmployeeRequest.getGender().equals("")) {
            bindingResult.addError(new FieldError("updateEmployeeRequest", "gender", "Jenis Kelamin wajib diisi."));
        }

        if (updateEmployeeRequest.getAge() == null || updateEmployeeRequest.getAge().equals("")) {
            bindingResult.addError(new FieldError("updateEmployeeRequest", "age", "Umur wajib diisi."));
        }

        if (updateEmployeeRequest.getPlaceOfBirth() == null || updateEmployeeRequest.getPlaceOfBirth().equals("")) {
            bindingResult.addError(new FieldError("updateEmployeeRequest", "placeOfBirth", "Tempat Lahir wajib diisi."));
        }

        if (updateEmployeeRequest.getDateOfBirth() == null) {
            bindingResult.addError(new FieldError("updateEmployeeRequest", "dateOfBirth", "Tanggal Lahir wajib diisi."));
        }
    }
}
